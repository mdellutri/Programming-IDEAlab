package towerGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import towerGame.TowerGame;
import towerGame.map.Level;
import towerGame.map.Tile;
public class CollisionChecker {
	
	public CollisionChecker() {}
	public static boolean checkTile(Level level, Entity entity, Direction direction, float movement) {
		float entityLeftX=entity.posX
				+((float)entity.hitbox.x/16);
		float entityRightX=entity.posX+((float)entity.hitbox.x/16)+((float)entity.hitbox.width/16);
		float entityTopY=entity.posY+((float)entity.hitbox.y/16);
		float entityBottomY=entity.posY+((float)entity.hitbox.y/16)+((float)entity.hitbox.height/16);
		int tileNum1,tileNum2;
		/*float entityLeftCol=entityLeftX/(TowerGame.tileSize);
		float entityRightCol=entityLeftX/(TowerGame.tileSize);
		float entityTopRow=entityLeftX/(TowerGame.tileSize);
		float entityBottomRow=entityLeftX/(TowerGame.tileSize);*/
		switch(direction) {
			case UP:
				entityTopY=entity.posY+((float)entity.hitbox.y/16)-movement;
				if(((int)entityBottomY>level.sizeX)|((int)entityTopY<0)|((int)entityRightX>level.sizeY)|((int)entityLeftX<0)) {
					return false;
				}
				tileNum1=level.getTileForeground((int)entityLeftX,(int)entityTopY);
				tileNum2=level.getTileForeground((int)entityRightX,(int)entityTopY);
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					if(Tile.tiles[tileNum1].hasCustomHitbox||Tile.tiles[tileNum2].hasCustomHitbox) {
						if(Tile.tiles[tileNum1].isSolid&&(int)entityTopY+((float)Tile.tiles[tileNum1].hitbox.y/16)+((float)Tile.tiles[tileNum1].hitbox.height/16)>entityTopY&&((int)entityLeftX+((float)Tile.tiles[tileNum1].hitbox.x)/16<entityRightX||(int)entityLeftX+((float)Tile.tiles[tileNum1].hitbox.x)/16+((float)Tile.tiles[tileNum1].hitbox.width)/16<entityLeftX)) {
							return true;
						}
						if(Tile.tiles[tileNum2].isSolid&&(int)entityTopY+((float)Tile.tiles[tileNum2].hitbox.y/16)+((float)Tile.tiles[tileNum2].hitbox.height/16)>entityTopY&&((int)entityRightX+((float)Tile.tiles[tileNum2].hitbox.x)/16<entityRightX||(int)entityRightX+((float)Tile.tiles[tileNum2].hitbox.x)/16+((float)Tile.tiles[tileNum2].hitbox.width)/16<entityLeftX)) {
							return true;
						}
						return false;
					}else {
						return true;
					}
				}
				break;
			case DOWN:
				entityBottomY=entity.posY+((float)entity.hitbox.y/16)+((float)entity.hitbox.height/16)+movement;
				if(((int)entityBottomY>level.sizeX)|((int)entityTopY<0)|((int)entityRightX>level.sizeY)|((int)entityLeftX<0)) {
					return false;
				}
				tileNum1=level.getTileForeground((int)entityLeftX,(int)entityBottomY);
				tileNum2=level.getTileForeground((int)entityRightX,(int)entityBottomY);
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					if(Tile.tiles[tileNum1].hasCustomHitbox||Tile.tiles[tileNum2].hasCustomHitbox) {
						if(Tile.tiles[tileNum1].isSolid&&(int)entityBottomY+((float)Tile.tiles[tileNum1].hitbox.y/16)<entityBottomY&&((int)entityRightX+((float)Tile.tiles[tileNum1].hitbox.x)/16<entityRightX)) {
							return true;
						}
						if(Tile.tiles[tileNum2].isSolid&&(int)entityBottomY+((float)Tile.tiles[tileNum2].hitbox.y/16)<entityBottomY&&((int)entityLeftX+((float)Tile.tiles[tileNum2].hitbox.x)/16+((float)Tile.tiles[tileNum2].hitbox.width)/16<entityLeftX)) {
							return true;
						}
						return false;
					}else {
						return true;
					}
				}
				break;
			case LEFT:
				entityLeftX=entity.posX+((float)entity.hitbox.x/16)-movement;
				if(((int)entityBottomY>level.sizeX)|((int)entityTopY<0)|((int)entityRightX>level.sizeY)|((int)entityLeftX<0)) {
					return false;
				}
				tileNum1=level.getTileForeground((int)entityLeftX,(int)entityBottomY);
				tileNum2=level.getTileForeground((int)entityLeftX,(int)entityTopY);
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					if(Tile.tiles[tileNum1].hasCustomHitbox||Tile.tiles[tileNum2].hasCustomHitbox) {
						if(Tile.tiles[tileNum1].isSolid&&(int)entityLeftX+((float)Tile.tiles[tileNum1].hitbox.x)/16+((float)Tile.tiles[tileNum1].hitbox.width)/16>entityLeftX&&((int)entityBottomY+((float)Tile.tiles[tileNum1].hitbox.y)/16<entityBottomY&&(int)entityTopY+((float)Tile.tiles[tileNum1].hitbox.y)/16+((float)Tile.tiles[tileNum1].hitbox.height)/16>entityTopY)) {
							return true;
						}
						if(Tile.tiles[tileNum2].isSolid&&(int)entityLeftX+((float)Tile.tiles[tileNum2].hitbox.x)/16+((float)Tile.tiles[tileNum2].hitbox.width)/16>entityLeftX&&((int)entityBottomY+((float)Tile.tiles[tileNum2].hitbox.y)/16<entityBottomY&&(int)entityTopY+((float)Tile.tiles[tileNum2].hitbox.y)/16+((float)Tile.tiles[tileNum2].hitbox.height)/16>entityTopY)) {
							return true;
						}
						return false;
					}else {
						return true;
					}
				}
				break;
			case RIGHT:
				entityRightX=entity.posX+((float)entity.hitbox.x/16)+((float)entity.hitbox.width/16)+movement;
				if(((int)entityBottomY>level.sizeX)|((int)entityTopY<0)|((int)entityRightX>level.sizeY)|((int)entityLeftX<0)) {
					return false;
				}
				
				tileNum1=level.getTileForeground((int)entityRightX,(int)entityBottomY);
				tileNum2=level.getTileForeground((int)entityRightX,(int)entityTopY);
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					if(Tile.tiles[tileNum1].hasCustomHitbox||Tile.tiles[tileNum2].hasCustomHitbox) {
						if(Tile.tiles[tileNum1].isSolid&&(int)entityRightX+((float)Tile.tiles[tileNum1].hitbox.x)/16<entityRightX&&((int)entityBottomY+((float)Tile.tiles[tileNum1].hitbox.y)/16<entityBottomY&&(int)entityTopY+((float)Tile.tiles[tileNum1].hitbox.y)/16+((float)Tile.tiles[tileNum1].hitbox.height)/16>entityTopY)) {
							return true;
						}
						if(Tile.tiles[tileNum2].isSolid&&(int)entityRightX+((float)Tile.tiles[tileNum2].hitbox.x)/16<entityRightX&&((int)entityBottomY+((float)Tile.tiles[tileNum2].hitbox.y)/16<entityBottomY&&(int)entityTopY+((float)Tile.tiles[tileNum2].hitbox.y)/16+((float)Tile.tiles[tileNum2].hitbox.height)/16>entityTopY)) {
							return true;
						}
						return false;
					}else {
						return true;
					}
				}
				break;
			}
		return false;
	}
	public static void renderDebug(Level level,Entity entity,Graphics2D g2) {
		g2.setColor(new Color(192,0,0,127));
		float entityLeftX=entity.posX+((float)entity.hitbox.x/16);
		float entityRightX=entity.posX+((float)entity.hitbox.x/16)+((float)entity.hitbox.width/16);
		float entityTopY=entity.posY+((float)entity.hitbox.y/16);
		float entityBottomY=entity.posY+((float)entity.hitbox.y/16)+((float)entity.hitbox.height/16);
		g2.fillRect((int)((int)entityLeftX*TowerGame.tileSize),(int)((int)entityBottomY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize);
		g2.fillRect((int)((int)entityRightX*TowerGame.tileSize),(int)((int)entityBottomY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize);
		g2.fillRect((int)((int)entityLeftX*TowerGame.tileSize),(int)((int)entityTopY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize);
		g2.fillRect((int)((int)entityRightX*TowerGame.tileSize),(int)((int)entityTopY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize);
	}
	public static Rectangle getHitbox(int x0,int y0,int x1,int y1) {
		return new Rectangle(x0,y0,x1-x0,y1-y0);
	}
	public static boolean checkAABB(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
		return x0<=x3&&x1>=x2&&y0<=y3&&y1>=y2;
	}
}
