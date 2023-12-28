package towerGame.npc;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import towerGame.map.Level;

public class Thing extends Enemy {

	public Thing(Level level) {
		super(level);
	}
	@Override
	public void loadImages() {
		try {
			this.sprite=ImageIO.read(getClass().getResourceAsStream("/thing.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load sprite", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	@Override
	public String getSprite() {
		return "/thing.png";
	}
}
