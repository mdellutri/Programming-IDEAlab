package towerGame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		this.update((KeyHandler)null);
	}
	public void update(KeyHandler keyHandler) {
		if(keyHandler.upPressed) {this.posY-=0.04;this.yVelocity=-0.127F;};
		if(keyHandler.downPressed) {this.posY+=0.04;};
		if(keyHandler.leftPressed) {this.posX-=0.04;};
		if(keyHandler.rightPressed) {this.posX+=0.04;};
		this.posX+=xVelocity;
		this.yVelocity+=0.007F;//gravity
		this.posY+=yVelocity;
	}
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(this.sprite,(int)(this.posX*TowerGame.tileSize),(int)(this.posY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize,null);
	}
}