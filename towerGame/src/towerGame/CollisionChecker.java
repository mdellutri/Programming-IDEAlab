package towerGame;
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
		if(entityBottomY>level.size|entityTopY<0|entityRightX>level.size|entityLeftX<0) {
			return false;
		}
		switch(direction) {
			case UP:
				entityTopY=entity.posX+(entity.hitbox.x/16)-movement;
				tileNum1=level.mapTilesForeground[(int)entityLeftX][(int)entityTopY];
				tileNum2=level.mapTilesForeground[(int)entityRightX][(int)entityTopY];
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					return true;
				}
				break;
			case DOWN:
				if((int)entityBottomY>level.size|(int)entityTopY<0|(int)entityRightX>level.size|(int)entityLeftX<0) {
					return false;
				}
				entityTopY=entity.posX+(entity.hitbox.x/16)+movement;
				tileNum1=level.mapTilesForeground[(int)entityLeftX][(int)entityBottomY];
				tileNum2=level.mapTilesForeground[(int)entityRightX][(int)entityBottomY];
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid) {
					return true;
				}
				break;
			case LEFT:
				
				break;
			case RIGHT:
				
				break;
			}
		return false;
	}
}
