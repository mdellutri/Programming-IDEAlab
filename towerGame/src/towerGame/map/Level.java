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
				tiles[x][y]=new Tile(this,y>8?5:y>6&y<9&x==7?6:y>2&x>4&x<10?(3):y==2&x>4&x<10&(1&x)==1?3:0,x,y); // temporary
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
