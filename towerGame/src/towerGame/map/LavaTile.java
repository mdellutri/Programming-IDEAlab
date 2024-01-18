package towerGame.map;

public class LavaTile extends Tile {
	boolean top;
	public LavaTile(int id, int textureId, boolean top) {
		super(id, textureId, false);
		this.top=top;
	}
	public int getTextureId() {
		if(this.top) {
			return 22+(int)(System.nanoTime()/200000000.0D)%8;
		}else {
			return 31+(int)(System.nanoTime()/200000000.0D)%8;
		}
	}
	public void update(Level level, int posX, int posY, boolean foreground) {
		
	}
}
