package towerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import towerGame.map.Level;
import towerGame.npc.FireProjectile;
import towerGame.npc.LivingEntity;

public class Player extends LivingEntity {
	public float xVelocity;
	public float yVelocity;
	public boolean onGround=false;
	public float mana=10.0f;
	public Player(Level level) {
		super(level);
		this.posX=4;
		this.posY=6;
		this.maxHealth=10.0f;
		this.health=this.maxHealth;
		this.hitbox=CollisionChecker.getHitbox(1,1,15,15);
	}
	public void loadImages() {
    	try {
    		this.sprite=ImageIO.read(getClass().getResourceAsStream("/player.png"));
    	}catch(Exception e) {
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(null, "Failed to load player sprite", "Error", JOptionPane.ERROR_MESSAGE);
    	}
	}
	@Override
	public String getSprite() {
		return "player.png";
	}
	@Override
	public void update(EventHandler eventHandler) {
		if(this.damageTimer!=0) {
			this.damageTimer--;
		}
		if(eventHandler.upPressed&&this.onGround) {this.yVelocity=-0.158F;};
		if(eventHandler.leftPressed) {
			if(!this.level.cc.checkTile(this.level, this, Direction.LEFT, 0.052F)) {
				this.posX-=0.052;
			}else {
				if(!this.level.cc.checkTile(this.level, this, Direction.LEFT, 0.052F/3)) {
					this.posX-=0.052F/3;
				}
				if(!this.level.cc.checkTile(this.level, this, Direction.LEFT, 0.052F/7)) {
					this.posX-=0.052F/7;
				}
			}
		}
		if(eventHandler.rightPressed) {
			if(!this.level.cc.checkTile(this.level, this, Direction.RIGHT, 0.052F)) {
				this.posX+=0.052;
			}else {
				if(!this.level.cc.checkTile(this.level, this, Direction.RIGHT, 0.052F/3)) {
					this.posX+=0.052F/3;
				}
				if(!this.level.cc.checkTile(this.level, this, Direction.RIGHT, 0.052F/7)) {
					this.posX+=0.052F/7;
				}
			}
		}
		if(eventHandler.mouse1Clicked) {
			Point mousePos= MouseInfo.getPointerInfo().getLocation();
			float angle=(float)Math.atan2((mousePos.x-TowerGame.frame.getLocation().x)-Math.round(this.posX*TowerGame.tileSize+0.5*TowerGame.tileSize), (mousePos.y-TowerGame.frame.getLocation().y)-Math.round(this.posY*TowerGame.tileSize+0.5*TowerGame.tileSize));
			PlayerProjectile p = new PlayerProjectile(this.level, this);
			p.xVelocity=(float) Math.sin(angle)/5;
			p.yVelocity=(float) (Math.cos(angle)/5)-0.1F;
			this.level.addEntity(p);
		}
		if(eventHandler.mouse2Clicked) {
			if(this.mana>0) {
				Point mousePos= MouseInfo.getPointerInfo().getLocation();
				float angle=(float)Math.atan2((mousePos.x-TowerGame.frame.getLocation().x)-Math.round(this.posX*TowerGame.tileSize+0.5*TowerGame.tileSize), (mousePos.y-TowerGame.frame.getLocation().y)-Math.round(this.posY*TowerGame.tileSize+0.5*TowerGame.tileSize));
				PlayerProjectile p = new PlayerProjectile(this.level, this);
				p.xVelocity=(float) Math.sin(angle)/5;
				p.yVelocity=(float) (Math.cos(angle)/5)-0.1F;
				p.size=3;
				this.level.addEntity(p);
				this.mana--;
			}
		}
		this.posX+=xVelocity;
		this.yVelocity+=0.007F;//gravity
		if(!this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity)) {
			this.posY+=yVelocity;
			this.onGround=false;
		}else {

			if(!this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, ((yVelocity<0)?-yVelocity:yVelocity)/3)) {
				this.posY+=yVelocity/3;
			}
			if(!this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, ((yVelocity<0)?-yVelocity:yVelocity)/7)) {
				this.posY+=yVelocity/7;
			}
			if(this.yVelocity>0) {
				this.onGround=true;
			}else {
				this.onGround=false;
			}
			this.yVelocity=yVelocity>0?-(this.yVelocity/8):-(this.yVelocity); //bounce
		}
	}
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(this.sprite,(int)Math.round(this.posX*TowerGame.tileSize),(int)Math.round(this.posY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize,null);
	}
	@Override
	public void renderDebug(Graphics2D g2) {
	}
}