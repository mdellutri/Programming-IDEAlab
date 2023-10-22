package towerGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
	public float health;
	public float xVelocity;
	public float yVelocity;
	public Player() {
		health=10.0F;
		posX=320;
		posY=240;
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
		if(keyHandler.upPressed) {this.posY--;this.yVelocity=-2;};
		if(keyHandler.downPressed) {this.posY++;};
		if(keyHandler.leftPressed) {this.posX--;};
		if(keyHandler.rightPressed) {this.posX++;};
		this.posX+=xVelocity;
		this.yVelocity+=0.082F;//gravity
		this.posY+=yVelocity;
	}
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(this.sprite,(int)this.posX,(int)this.posY,32,32,null);
	}
}