package towerGame.map;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import towerGame.TowerGame;

public class Tile {
	public final int id;
	public final boolean isSolid;
	public static final Tile[] tiles=new Tile[256];
	public Tile(int id, boolean isSolid) {
		tiles[id]=this;
		this.id=id;	
		this.isSolid=isSolid;
		//this.level=level;
	}
	public int getTextureId() {
		return this.id;
	}
	public void update(Level level, int posX, int posY) {};
	public void render(Level level, Graphics2D g2, int posX, int posY) {
		if(this.id==0) {return;}
		
		int frameX = (this.id % 16) * 16;
		int frameY = (this.id / 16) * 16;
		g2.drawImage(level.tilemap, posX*TowerGame.tileSize, posY*TowerGame.tileSize, posX*TowerGame.tileSize+TowerGame.tileSize, posY*TowerGame.tileSize+TowerGame.tileSize,
	    frameX, frameY, frameX+16, frameY+16, (ImageObserver)null);

	};
	public static Tile air=new Tile(0,false);
	public static Tile stone=new Tile(1,true);
	public static Tile crackedStone=new Tile(2,true);
	public static Tile bricks=new Tile(3,true);
	public static Tile crackedBricks=new Tile(4,true);
	public static Tile grass=new Tile(5,true);
	public static Tile wood=new Tile(6,true);
	public static Tile tallGrass=new Tile(7,false);
	
}