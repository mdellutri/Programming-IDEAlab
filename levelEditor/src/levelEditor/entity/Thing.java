package levelEditor.entity;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import levelEditor.Level;
import levelEditor.LevelEditor;

public class Thing extends Enemy {
	public boolean isAttacking;
	public Thing(Level level) {
		super(level);
		this.health=5;
		this.maxHealth=5;
	}
	public String getSprite() {
		return "thing.png";
	}
	public void render(Graphics2D g2) {
		int[] positions = this.getPositionOnScreen();
		g2.drawImage(this.sprite, positions[0], positions[1], positions[0]+LevelEditor.tileSize, positions[1]+LevelEditor.tileSize, this.isAttacking?16:0, 0, this.isAttacking?32:16, 16, (ImageObserver)null);
	}
}
