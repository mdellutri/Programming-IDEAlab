package towerGame.npc;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import towerGame.Entity;
import towerGame.Player;
import towerGame.map.Level;

public class FireEnemy extends Enemy {
	public boolean isBlue;
	public int baseY;
	public FireEnemy(Level level, boolean isBlue) {
		super(level);
		this.isBlue=isBlue;
		this.hitbox=new Rectangle(0,0,16,16);
		this.maxHealth=15.0f;
		this.health=this.maxHealth;
	}
	public FireEnemy(Level level) {
		this(level,false);
	}
	@Override
	public void update() {
		super.update();
		this.posY=baseY+(float) Math.sin(System.nanoTime()/500000000.0D);
		if(this.level.player!=null) {
			if(this.level.cc.checkEntities(this,level.player)) {
				this.level.player.damage(1.0F);
			}
		}
	}
	@Override
	public void loadImages() {
		try {
			if(this.isBlue) {
				this.sprite=ImageIO.read(getClass().getResourceAsStream("/bluefiresprite.png"));
			}else {
				this.sprite=ImageIO.read(getClass().getResourceAsStream("/redfiresprite.png"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load fire sprite", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
