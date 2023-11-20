package towerGame.npc;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import towerGame.map.Level;

public class FireEnemy extends Enemy {
	boolean isBlue;
	public FireEnemy(Level level, boolean isBlue) {
		super(level);
		this.isBlue=isBlue;
	}
	public FireEnemy(Level level) {
		super(level);
		this.isBlue=false;
	}
	@Override
	public void update() {
		this.posY=5+(float) Math.sin(System.nanoTime()/500000000.0D);
	}
	@Override
	public void loadImages() {
		try {
			if(this.isBlue) {
				this.sprite=ImageIO.read(getClass().getResourceAsStream("/bluefiresprite.png"));
			}else {
				this.sprite=ImageIO.read(getClass().getResourceAsStream("/firesprite.png"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load fire sprite", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
