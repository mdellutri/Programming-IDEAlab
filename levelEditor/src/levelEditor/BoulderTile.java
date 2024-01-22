package levelEditor;

import java.awt.Rectangle;

public class BoulderTile extends Tile {

	public BoulderTile(int id, int textureId) {
		super(id, textureId, true);
	}
	public BoulderTile(int id, int textureId, Rectangle rectangle) {
		super(id, textureId, true, rectangle);
	}
}
