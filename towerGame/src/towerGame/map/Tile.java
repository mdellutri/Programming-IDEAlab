package towerGame.map;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Tile {
	public final int id;
	//public static final Tile[] tiles=new Tile[256];
	public int posX;
	public int posY;
	protected Level level;
	public Tile(Level level, int id, int x, int y) {
		//tiles[id]=this;
		this.id=id;	
		this.posX=x;
		this.posY=y;
		this.level=level;
	}
	public int getTextureId() {
		return this.id;
	}
	public void update() {};
	public void render(Graphics2D g2) {
		
		int frameX = (this.id % 16) * 16;
		int frameY = (this.id / 16) * 16;
		g2.drawImage(this.level.tilemap, posX*32, posY*32, posX*32+32, posY*32+32,
	    frameX, frameY, frameX+16, frameY+16, (ImageObserver)null);

	};
}