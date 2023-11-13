package towerGame.map;

import java.awt.Rectangle;

public class BoulderTile extends Tile {

	public BoulderTile(int id, int textureId) {
		super(id, textureId, true);
	}
	public BoulderTile(int id, int textureId, Rectangle rectangle) {
		super(id, textureId, true, rectangle);
	}
	@Override
	public void update(Level level, int posX, int posY, boolean foreground) {
		if(foreground&&level.mapTilesForeground[posX][posY+1]==0) {
			
		}
	}
}
