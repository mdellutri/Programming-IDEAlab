package towerGame.map;

public class BoulderTile extends Tile {

	public BoulderTile(int id, int textureId) {
		super(id, textureId, true);
	}
	@Override
	public void update(Level level, int posX, int posY, boolean foreground) {
		if(foreground&&level.mapTilesForeground[posX][posY+1]==0) {
			
		}
	};
}
