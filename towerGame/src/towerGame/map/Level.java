package towerGame.map;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;

import javax.imageio.ImageIO;

import towerGame.CollisionChecker;

public class Level {
	public int size;
	public int mapTilesForeground[][];
	public int mapTilesBackground[][];
	public BufferedImage tilemap;
	public CollisionChecker cc=new CollisionChecker();
	public RescaleOp bg_tint;
	
	public Level(int size) {
		mapTilesForeground=new int[size][size];
		mapTilesBackground=new int[size][size];
	    bg_tint = new RescaleOp(0.82f, 0f, null);
		this.size=size;
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				mapTilesForeground[x][y]=y>8?5:x==13&y>3&y<8?2:x==3&y==6?17:0;
				mapTilesBackground[x][y]=y>8?5:y>6&y<9&x==7?6:y>2&x>4&x<10?x==6|x==8?y==5?13:y==4?12:3:3:y==2&x>4&x<10&(1&x)==1?3:x==12&y>3?2:0; // temporary
			}
		}
    	try {
    		tilemap=ImageIO.read(getClass().getResourceAsStream("/tilemap.png"));
    	}catch(IOException e) {
    		e.printStackTrace();
    		System.out.println("Tilemap failed to load!");
    	}
	}
	public void update() {
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				Tile.tiles[mapTilesBackground[x][y]].update(this,x,y,false);
				Tile.tiles[mapTilesForeground[x][y]].update(this,x,y,true);
			}
		}
	}
	public void render(Graphics2D g2) {
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				Tile.tiles[mapTilesBackground[x][y]].render(this,g2,x,y,false);
				Tile.tiles[mapTilesForeground[x][y]].render(this,g2,x,y,true);
			}
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
}
