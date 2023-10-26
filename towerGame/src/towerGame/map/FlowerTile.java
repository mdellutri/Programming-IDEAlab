package towerGame.map;

public class FlowerTile extends Tile {
	public FlowerTile(byte id) {
		super(id, false);
	}
	public int getTextureId() {
		return this.id;
	}
}
