package levelEditor;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import towerGame.map.Tile;

public class Tile {
	public final int id;
	public final int textureId;
	public final boolean isSolid;
	public static final Tile[] tiles=new Tile[256];
	public Tile(int id,int textureId, boolean isSolid) {
		tiles[id]=this;
		this.id=id;	
		this.textureId=textureId;	
		this.isSolid=isSolid;
		//this.level=level;
	}
	public Tile(int id,int textureId, boolean isSolid, Rectangle hitbox) {
		tiles[id]=this;
		this.id=id;	
		this.textureId=textureId;	
		this.isSolid=isSolid;
		//this.level=level;
	}
	public int getTextureId() {
		return this.textureId;
	}
	
	public void render(Level level, Graphics2D g2, int posX, int posY, boolean foreground) {
		if(this.id==0) {return;}
		
		int frameX = (this.getTextureId() % 16) * 16;
		int frameY = (this.getTextureId() / 16) * 16;
		if(!foreground) {
			g2.drawImage(level.tilemap_dark, posX*LevelEditor.tileSize-(int)(level.cameraX*LevelEditor.tileSize), posY*LevelEditor.tileSize-(int)(level.cameraY*LevelEditor.tileSize), posX*LevelEditor.tileSize+LevelEditor.tileSize-(int)(level.cameraX*LevelEditor.tileSize), posY*LevelEditor.tileSize+LevelEditor.tileSize-(int)(level.cameraY*LevelEditor.tileSize),frameX, frameY, frameX+16, frameY+16, (ImageObserver)null);
		}else {
			g2.drawImage(level.tilemap, posX*LevelEditor.tileSize-(int)(level.cameraX*LevelEditor.tileSize), posY*LevelEditor.tileSize-(int)(level.cameraY*LevelEditor.tileSize), posX*LevelEditor.tileSize+LevelEditor.tileSize-(int)(level.cameraX*LevelEditor.tileSize), posY*LevelEditor.tileSize+LevelEditor.tileSize-(int)(level.cameraY*LevelEditor.tileSize),frameX, frameY, frameX+16, frameY+16, (ImageObserver)null);
		}

	}
	public static Tile air=new Tile(0,0,false);
	public static Tile stone=new Tile(1,1,true);
	public static Tile crackedStone=new Tile(2,2,true);
	public static Tile bricks=new Tile(3,3,true);
	public static Tile crackedBricks=new Tile(4,4,true);
	public static Tile grass=new Tile(5,5,true);
	public static Tile wood=new Tile(6,6,true);
	public static Tile redBricks=new Tile(7,10,true);
	public static Tile dirt=new Tile(8,12,true);
	public static Tile log=new Tile(9,13,true);
	public static Tile boulder=new BoulderTile(10,11);
	public static Tile tallGrass=new AnimatedTile(11,19,false,3);
	public static Tile stoneWindowTop=new Tile(12,8,true);
	public static Tile stoneWindowBottom=new Tile(13,9,true);
	public static Tile flower1=new FlowerTile((byte)14,(byte)0);
	public static Tile flower2=new FlowerTile((byte)15,(byte)1);
	public static Tile flower3=new FlowerTile((byte)16,(byte)2);
	public static Tile blackOrb=new Tile(17,14,true);
	public static Tile stoneVines=new Tile(18,15,true);
	public static Tile lavaTop=new LavaTile(19,22,true);
	public static Tile lavaBottom=new LavaTile(20,31,false);
	public static Tile cloud=new AnimatedTile(21,39,false,3);
	public static Tile spike=new Tile(22,53,true);
	public static Tile darkBricks=new Tile(23,55,true);
	public static Tile darkBricksVine=new Tile(24,56,true);
	public static Tile conveyorRight=new Tile(25,64,true);
	
}