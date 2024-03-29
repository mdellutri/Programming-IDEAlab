package levelEditor;

import java.awt.AlphaComposite;
import java.awt.Color;
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

import levelEditor.entity.Entity;

public class Level {
	public int sizeX;
	public int sizeY;
	public int playerSpawnX;
	public int playerSpawnY;
	public float cameraX=0;
	public float cameraY=0;
	public int mapTilesForeground[][];
	public int mapTilesBackground[][];
	public BufferedImage tilemap;
	public BufferedImage tilemap_dark;
	public RescaleOp bg_tint;
	public List<Entity> entities=new ArrayList<Entity>();
	public HashMap<String,BufferedImage> sprites = new HashMap<String,BufferedImage>();
	public final ReentrantLock entity_lock = new ReentrantLock();
    public Color skyColor=new Color(98,204,249);
	
	public Level(int sizeX, int sizeY) {
		mapTilesForeground=new int[sizeX][sizeY];
		mapTilesBackground=new int[sizeX][sizeY];
	    bg_tint = new RescaleOp(0.82f, 0f, null);
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		for(int x=0;x<sizeX;x++) {
			for(int y=0;y<sizeY;y++) {
				mapTilesForeground[x][y]=y>8?5:x==13&y>3&y<8?2:y==6&x==3|y==8&x==11?17:y==8&x==5|y==7&x==4?7:0;
				mapTilesBackground[x][y]=y>8?8:y>6&y<9&x==7?6:y>2&x>4&x<10?x==6|x==8?y==5?13:y==4?12:3:3:y==2&x>4&x<10&(1&x)==1?3:x==12&y>3?2:0; // temporary
			}
		}
    	try {
    		tilemap=ImageIO.read(getClass().getResourceAsStream("/tilemap.png"));
    		tilemap_dark=bg_tint.filter(tilemap,null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load tilemap", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void render(Graphics2D g2) {
		for(int x=Math.max(0, (int)cameraX);x<Math.min((int)cameraX+21,this.sizeX);x++) {
			for(int y=Math.max(0, (int)cameraY);y<Math.min((int)cameraY+16,this.sizeY);y++) {
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
	}
	public void renderBackground(Graphics2D g2) {
		for(int x=0;x<this.sizeX;x++) {
			for(int y=0;y<this.sizeY;y++) {
				Tile.tiles[mapTilesBackground[x][y]].render(this,g2,x,y,false);
			}
		}
		for (Entity entity : this.entities) {
			entity.render(g2);
		}
	}
	public int getTileForeground(int x,int y) {
		if(x<0|x>=this.sizeX|y<0|y>=this.sizeY){	
			return 0;
		}
		return mapTilesForeground[x][y];
	}
	public int getTileBackground(int x,int y) {
		if(x<0|x>=this.sizeX|y<0|y>=this.sizeY){	
			return 0;
		}
		return mapTilesBackground[x][y];
	}
	public void setTileForeground(int x,int y,int tile) {
		if(x<0|x>=this.sizeX|y<0|y>=this.sizeY){	
			return;
		}
		mapTilesForeground[x][y]=tile;
	}
	public void setTileBackground(int x,int y,int tile) {
		if(x<0|x>=this.sizeX|y<0|y>=this.sizeY){	
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
		this.entities.add(entity);
	}
}
