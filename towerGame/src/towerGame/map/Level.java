package towerGame.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level {
	public int size;
	public Tile[][] tiles;
	public BufferedImage tilemap;
	
	public Level(int size) {
		this.size=size;
		tiles=new Tile[size][size];
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				tiles[x][y]=new Tile(this,y==5?1:0,x,y);
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
				tiles[x][y].update();
			}
		}
	}
	public void render(Graphics2D g2) {
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				tiles[x][y].render(g2);
			}
		}
	}
}
