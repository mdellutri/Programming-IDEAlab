package towerGame.npc;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import towerGame.Entity;
import towerGame.Player;
import towerGame.PlayerProjectile;
import towerGame.TowerGame;
import towerGame.map.Level;

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
		this.posY=baseY+(float) Math.sin(System.nanoTime()/500000000.0D);
		if(this.level.player!=null) {
			if(this.level.cc.checkEntities(this,level.player)) {
				this.level.player.damage(this.attackDamage);
			}
		}
		if(this.attackCooldown==0) {
			float angle=(float)Math.atan2((this.level.player.posX)-this.posX, this.level.player.posY-this.posY);
			FireProjectile p = new FireProjectile(this.level, this.isBlue);
			p.xVelocity=(float) Math.sin(angle)/4.5F;
			p.yVelocity=(float) (Math.cos(angle)/4.5F)-0.1F - (0.002F * Math.abs(this.level.player.posX-this.posX));
			p.setPosition(this.posX, this.posY);
			this.level.addEntity(p);
			this.attackCooldown = (int)(Math.random() * (this.isBlue ? 150 : 200))+50;
		}else {
			this.attackCooldown--;
		}
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