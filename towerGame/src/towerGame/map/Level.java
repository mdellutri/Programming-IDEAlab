package towerGame.map;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import towerGame.Entity;
import towerGame.EventHandler;
import towerGame.Player;
import towerGame.CollisionChecker;

public class Level {
	public int sizeX;
	public int sizeY;
	public int mapTilesForeground[][];
	public int mapTilesBackground[][];
	public BufferedImage tilemap;
	public BufferedImage tilemap_dark;
	public CollisionChecker cc=new CollisionChecker();
	public RescaleOp bg_tint;
	public List<Entity> entities=new ArrayList<Entity>();
	private List<Entity> entityQueue=new ArrayList<Entity>();
	public HashMap<String,BufferedImage> sprites = new HashMap<String,BufferedImage>();
	public Player player;
	public final ReentrantLock entity_lock = new ReentrantLock();
	public float cameraX;
	public float cameraY;
	
	public Level(int sizeX, int sizeY) {
		mapTilesForeground=new int[sizeX][sizeY];
		mapTilesBackground=new int[sizeX][sizeY];
	    bg_tint = new RescaleOp(0.87f, 0f, null);
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		for(int x=0;x<sizeX;x++) {
			for(int y=0;y<sizeY;y++) {
				mapTilesForeground[x][y]=y>8?5:x==13&y>7&y<9?2:x==13&y<8?10:y==6&x==3|y==8&x==11?17:y==8&x==5|y==7&x==4?7:0;
				mapTilesBackground[x][y]=y>8?8:y>6&y<9&x==7?6:y>2&x>4&x<10?x==6|x==8?y==5?13:y==4?12:3:3:y==2&x>4&x<10&(1&x)==1?3:x==12&y>3?2:0; // temporary
			}
		}
    	try {
    		tilemap=ImageIO.read(getClass().getResourceAsStream("/tilemap.png"));
    		tilemap_dark=bg_tint.filter(tilemap,null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load tilemap", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public void update(EventHandler eventHandler) {
		for(int x=0;x<this.sizeX;x++) {
			for(int y=0;y<this.sizeY;y++) {
				Tile.tiles[mapTilesBackground[x][y]].update(this,x,y,false);
				Tile.tiles[mapTilesForeground[x][y]].update(this,x,y,true);
			}
		}
		entity_lock.lock();
		try {
			for (Entity entity : this.entities) {
				if (entity!=null) {
					entity.update(eventHandler);
				}
			}
			if(this.player!=null) {
				this.player.update(eventHandler);
			}
			entities.addAll(entityQueue);
			entityQueue.clear();
			entities.removeIf((Entity e) -> e.markedForRemoval);
		} finally {
			entity_lock.unlock();
		}
		if(player.posX<cameraX+2) {
			cameraX=player.posX-2;
		}
		if(player.posX>cameraX+18) {
			cameraX=player.posX-18;
		}
		if(player.posY<cameraY+2) {
			cameraY=player.posY-2;
		}
		if(player.posY>cameraY+13) {
			cameraY=player.posY-13;
		}
	}
	public void update() {
		this.update(null);
	}
	public void render(Graphics2D g2) {
		for(int x=0;x<this.sizeX;x++) {
			for(int y=0;y<this.sizeY;y++) {
				Tile.tiles[mapTilesBackground[x][y]].render(this,g2,x,y,false);
				Tile.tiles[mapTilesForeground[x][y]].render(this,g2,x,y,true);
				
			}
		}
		entity_lock.lock();
		try {
			for (Entity entity : this.entities) {
				entity.render(g2);
			}
		} finally {
			entity_lock.unlock();
		}
		if(this.player!=null) {
			this.player.render(g2);
		}
	}
	public int getTileForeground(int x,int y) {
		if(x<0|x>15|y<0|y>15){	
			return 0;
		}
		return mapTilesForeground[x][y];
	}
	public int getTileBackground(int x,int y) {
		if(x<0|x>15|y<0|y>15){	
			return 0;
		}
		return mapTilesBackground[x][y];
	}
	public void setTileForeground(int x,int y,int tile) {
		if(x<0|x>15|y<0|y>15){	
			return;
		}
		mapTilesForeground[x][y]=tile;
	}
	public void setTileBackground(int x,int y,int tile) {
		if(x<0|x>15|y<0|y>15){	
			return;
		}
		mapTilesBackground[x][y]=tile;
	}
	public void addEntity(Entity entity) {
		String spriteName = entity.getSprite();
			if(spriteName != "") {
			if(!this.sprites.containsKey(spriteName)) {
				try {
					this.sprites.put(spriteName, ImageIO.read(getClass().getResourceAsStream("/"+spriteName)));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Failed to load "+spriteName+" sprite", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			entity.setSprite(this.sprites.get(spriteName));
		}
		this.entityQueue.add(entity);
	}
	public void setPlayer(Player player) {
		String spriteName = player.getSprite();
		if(spriteName != "") {
			if(!this.sprites.containsKey(spriteName)) {
				try {
					this.sprites.put(spriteName, ImageIO.read(getClass().getResourceAsStream("/"+spriteName)));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Failed to load player: "+spriteName+" sprite", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			player.setSprite(this.sprites.get(spriteName));
		}
		this.player=player;
	}
	public Player getPlayer() {
		return this.player;
	}
	public List<Entity> getAllEntities(){
		entity_lock.lock();
		try {
			List<Entity> e2 = new ArrayList<Entity>();
			e2.addAll(this.entities);
			e2.add(player);
			return e2;
		} finally {
			entity_lock.unlock();
		}
	}
}
