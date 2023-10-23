package towerGame;
import towerGame.TowerGame;
import towerGame.map.Level;
import towerGame.map.Tile;
public class CollisionChecker {
	
	public CollisionChecker() {
		
	}
	public void checkTile(Level level, Entity entity, Direction direction, float movement) {
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
				entityTopY=entity.posX+(entity.hitbox.x/16)+movement;
				tileNum1=level.mapTilesForeground[(int)entityLeftX][(int)entityTopY];
				tileNum2=level.mapTilesForeground[(int)entityRightX][(int)entityBottomY];
				if(Tile.tiles[tileNum1].isSolid||Tile.tiles[tileNum2].isSolid)
			break;
		case DOWN:
			
			break;
		case LEFT:
			
			break;
		case RIGHT:
			
			break;
		}
	}
}
