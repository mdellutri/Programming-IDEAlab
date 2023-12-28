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
		if(this.isBlue) {
			this.attackDamage+=0.5F;
		}
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
				this.level.player.damage(this.attackDamage);
			}
		}
	}

	@Override
	public String getSprite() {
		if(this.isBlue) {
			return "bluefiresprite.png";
		} else {
			return "redfiresprite.png";
		}
	}

}
