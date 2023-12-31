package levelEditor.entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import levelEditor.Level;

import java.awt.Rectangle;

public class FireEnemy extends Enemy {
	public boolean isBlue;
	public int baseY;
	public FireEnemy(Level level, boolean isBlue) {
		super(level);
		this.isBlue=isBlue;
		this.hitbox=new Rectangle(0,0,16,16);
	}
	public FireEnemy(Level level) {
		this(level,false);
	}
	public String getSprite() {
		if(this.isBlue) {
			return "bluefiresprite.png";
		} else {
			return "redfiresprite.png";
		}
	}

}
