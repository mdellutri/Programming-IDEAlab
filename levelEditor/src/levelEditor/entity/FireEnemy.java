package levelEditor.entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.awt.Graphics2D;
import java.awt.Rectangle;


import levelEditor.LevelEditor;
import levelEditor.Level;

public class FireEnemy extends Enemy {
	public boolean isBlue;
	public float baseY;
	public int attackCooldown=200;
	public FireEnemy(Level level, boolean isBlue) {
		super(level);
		this.isBlue=isBlue;
		this.hitbox=new Rectangle(0,0,16,16);
		this.maxHealth=10.0f;
		this.health=this.maxHealth;
		if(this.isBlue) {
			this.attackDamage+=0.5F;
			this.maxHealth+=2.5F;
		}
	}
	public FireEnemy(Level level) {
		this(level,false);
	}
	public void update() {
		super.update();
	}

	public String getSprite() {
		if(this.isBlue) {
			return "bluefiresprite.png";
		} else {
			return "redfiresprite.png";
		}
	}
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.baseY=y;
	}
}