package levelEditor;

public class GrassTile extends Tile {

	public GrassTile(int id, int textureId) {
		super(id, textureId, false);
	}
	public int getTextureId() {
		return this.textureId+(int)(System.nanoTime()/500000000.0D)%3;
	}

}
