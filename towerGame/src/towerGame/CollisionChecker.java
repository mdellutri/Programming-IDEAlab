package towerGame;
import java.awt.Color;
import java.awt.Graphics2D;

import towerGame.TowerGame;
import towerGame.map.Level;
import towerGame.map.Tile;
public class CollisionChecker {
	
	public CollisionChecker() {
		
	}
	public boolean checkTile(Level level, Entity entity, Direction direction, float movement) {
		float entityLeftX=entity.posX+(entity.hitbox.x/16);
		float entityRightX=entity.posX+(entity.hitbox.x/16)+(entity.hitbox.width/16);
		float entityTopY=entity.posY+(entity.hitbox.y/16);
		float entityBottomY=entity.posY+(entity.hitbox.y/16)+(entity.hitbox.height/16);
		int tileNum1,tileNum2;
		/*float entityLeftCol=entityLeftX/(TowerGame.tileSize);
		float entityRightCol=entityLeftX/(TowerGame.tileSize);
		float entityTopRow=entityLeftX/(TowerGame.tileSize);
		float entityBottomRow=entityLeftX/(TowerGame.tileSize);*/
		switch(direction) {
			case UP:
				entityTopY=entity.posY+(entity.hitbox.y/16)-movement;
				if(((int)entityBottomY>level.size)|((int)entityTopY<0)|((int)entityRightX>level.size)|((int)entityLeftX<0)) {
					return false;
				}
				tileNum1=level.getTileForeground((int)entityLeftX,(int)entityTopY);
				tileNum2=level.getTileForeground((int)entityRightX,(int)entityTopY);
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					return true;
				}
				break;
			case DOWN:
				entityBottomY=entity.posY+(entity.hitbox.y/16)+(entity.hitbox.height/16)+movement;
				if(((int)entityBottomY>level.size)|((int)entityTopY<0)|((int)entityRightX>level.size)|((int)entityLeftX<0)) {
					return false;
				}
				tileNum1=level.getTileForeground((int)entityLeftX,(int)entityBottomY);
				tileNum2=level.getTileForeground((int)entityRightX,(int)entityBottomY);
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					return true;
				}
				break;
			case LEFT:
				entityLeftX=entity.posX+(entity.hitbox.x/16)-movement;
				if(((int)entityBottomY>level.size)|((int)entityTopY<0)|((int)entityRightX>level.size)|((int)entityLeftX<0)) {
					return false;
				}
				tileNum1=level.getTileForeground((int)entityLeftX,(int)entityBottomY);
				tileNum2=level.getTileForeground((int)entityLeftX,(int)entityTopY);
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					return true;
				}
				break;
			case RIGHT:
				entityRightX=entity.posX+(entity.hitbox.x/16)+(entity.hitbox.width/16)+movement;
				if(((int)entityBottomY>level.size)|((int)entityTopY<0)|((int)entityRightX>level.size)|((int)entityLeftX<0)) {
					return false;
				}
				tileNum1=level.getTileForeground((int)entityRightX,(int)entityBottomY);
				tileNum2=level.getTileForeground((int)entityRightX,(int)entityTopY);
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					return true;
				}
				break;
			}
		return false;
	}
	public void renderDebug(Level level,Entity entity,Graphics2D g2) {
		g2.setColor(new Color(192,0,0,127));
		float entityLeftX=entity.posX+(entity.hitbox.x/16);
		float entityRightX=entity.posX+(entity.hitbox.x/16)+(entity.hitbox.width/16);
		float entityTopY=entity.posY+(entity.hitbox.y/16);
		float entityBottomY=entity.posY+(entity.hitbox.y/16)+(entity.hitbox.height/16);
		g2.fillRect((int)((int)entityLeftX*TowerGame.tileSize),(int)((int)entityBottomY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize);
		g2.fillRect((int)((int)entityRightX*TowerGame.tileSize),(int)((int)entityBottomY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize);
		g2.fillRect((int)((int)entityLeftX*TowerGame.tileSize),(int)((int)entityTopY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize);
		g2.fillRect((int)((int)entityRightX*TowerGame.tileSize),(int)((int)entityTopY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize);
	}
}
