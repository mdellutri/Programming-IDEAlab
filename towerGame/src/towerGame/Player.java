package towerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import towerGame.map.Level;

public class Player extends Entity {
	public float maxHealth=10.0F;
	public float health=maxHealth;
	public float xVelocity;
	public float yVelocity;
	public boolean onGround=false;
	public Player(Level level) {
		super(level);
		this.posX=6;
		this.posY=6;
		this.hitbox=new Rectangle(1,1,15,15);
	}
	public void loadImages() {
    	try {
    		this.sprite=ImageIO.read(getClass().getResourceAsStream("/player.png"));
    	}catch(IOException e) {
    		e.printStackTrace();
    		System.out.println("Player sprite failed to load!");
    	}
	}
	@Override
	public void update() {
		this.update((KeyHandler)null);
	}
	public void update(KeyHandler keyHandler) {
		if(keyHandler.upPressed&&this.onGround) {this.yVelocity=-0.18F;};
		//if(keyHandler.downPressed) {this.posY+=0.04;};
		if(keyHandler.leftPressed) {
			if(!this.level.cc.checkTile(this.level, this, Direction.LEFT, 0.052F)) {
				this.posX-=0.052;
			}else {
				if(!this.level.cc.checkTile(this.level, this, Direction.LEFT, 0.016F)) {
					this.posX-=0.016;
				}
			}
		}
		if(keyHandler.rightPressed) {
			if(!this.level.cc.checkTile(this.level, this, Direction.RIGHT, 0.052F)) {
				this.posX+=0.052;
			}else {
				if(!this.level.cc.checkTile(this.level, this, Direction.RIGHT, 0.016F)) {
					this.posX+=0.016;
				}
			}
		}
		if(!this.level.cc.checkTile(this.level, this, Direction.DOWN, 0.03F)) {
			this.posY+=0.03F;
		}
		this.posX+=xVelocity;
		this.yVelocity+=0.007F;//gravity
		if(!this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, yVelocity)) {
			this.posY+=yVelocity;
			this.onGround=false;
		}else {
			this.yVelocity=-(this.yVelocity/7); //bounce
			this.onGround=true;
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