package towerGame.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level {
	public int size;
	public int mapTilesForeground[][];
	public int mapTilesBackground[][];
	public BufferedImage tilemap;
	
	public Level(int size) {
		mapTilesForeground=new int[size][size];
		mapTilesBackground=new int[size][size];
		this.size=size;
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				mapTilesForeground[x][y]=y>8?5:0;
				mapTilesBackground[x][y]=y>8?0:y>6&y<9&x==7?6:y>2&x>4&x<10?(3):y==2&x>4&x<10&(1&x)==1?3:0; // temporary
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
				Tile.tiles[mapTilesForeground[x][y]].update(this,x,y);
				Tile.tiles[mapTilesBackground[x][y]].update(this,x,y);
			}
		}
	}
	public void render(Graphics2D g2) {
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				Tile.tiles[mapTilesForeground[x][y]].render(this,g2,x,y);
				Tile.tiles[mapTilesBackground[x][y]].render(this,g2,x,y);
			}
		}
	}
}
