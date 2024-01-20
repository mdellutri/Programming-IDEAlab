package levelEditor.entity;

import java.awt.Graphics2D;

import levelEditor.Level;
import levelEditor.LevelEditor;

public class ManaOrb extends Entity {
	
	public ManaOrb(Level level) {
		super(level);
	}
	public String getSprite() {
		return "manaorb.png";
	}
	public void render(Graphics2D g2) {
		int[] positions = this.getPositionOnScreen();
		g2.drawImage(this.sprite,positions[0],positions[1],LevelEditor.tileSize,LevelEditor.tileSize,null);
	}

}
