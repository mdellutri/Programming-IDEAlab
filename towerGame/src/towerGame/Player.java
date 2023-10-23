package towerGame;

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
	public Player() {
		this.posX=6;
		this.posY=6;
		this.hitbox=new Rectangle(0,0,16,16);
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
		this.update((KeyHandler)null, (Level)null);
	}
	@Override
	public void update(Level level) {
		this.update((KeyHandler)null, level);
	}
	public void update(KeyHandler keyHandler) {
		this.update(keyHandler, (Level)null);
	}
	public void update(KeyHandler keyHandler, Level level) {
		if(keyHandler.upPressed) {this.yVelocity=-0.127F;};
		if(keyHandler.downPressed) {this.posY+=0.04;};
		if(keyHandler.leftPressed) {this.posX-=0.04;};
		if(keyHandler.rightPressed) {this.posX+=0.04;};
		this.posX+=xVelocity;
		this.yVelocity+=0.007F;//gravity
		if(!level.cc.checkTile(level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, yVelocity)) {
			this.posY+=yVelocity;
		}else {
			this.yVelocity=0;
		}
	}
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(this.sprite,(int)(this.posX*TowerGame.tileSize),(int)(this.posY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize,null);
	}
}