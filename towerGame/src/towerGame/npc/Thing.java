package towerGame.npc;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import towerGame.map.Level;

public class Thing extends Enemy {

	public Thing(Level level) {
		super(level);
	}
	@Override
	public String getSprite() {
		return "thing.png";
	}
}
