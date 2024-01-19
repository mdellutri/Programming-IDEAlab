package towerGame.npc;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import towerGame.TowerGame;
import towerGame.map.Level;

public class Thing extends Enemy {
	public boolean isAttacking;
	public Thing(Level level) {
		super(level);
	}
	public String getSprite() {
		return "thing.png";
	}
	public void render(Graphics2D g2) {
		int[] positions = this.getPositionOnScreen();
		g2.drawImage(this.sprite, positions[0], positions[1], positions[0]+TowerGame.tileSize, positions[1]+TowerGame.tileSize, this.isAttacking?16:0, 0, this.isAttacking?32:16, 16, (ImageObserver)null);
	}
}
