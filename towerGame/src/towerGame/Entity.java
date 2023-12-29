package towerGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import towerGame.map.Level;

import java.awt.Rectangle;

public class Entity {
	public BufferedImage sprite;
	public float posX;
	public float posY;
	public int id;
	public Rectangle hitbox;
	public Level level;
	public boolean markedForRemoval;
	public Entity(Level level) {
		this.level=level;
	}
	public void update() {}
	public void render(Graphics2D g2) {}
	public void renderDebug(Graphics2D g2) {}
	public void update(EventHandler eventHandler) {
		this.update();
	}
	public String getSprite(){return "";}
	public void setSprite(BufferedImage sprite) {this.sprite=sprite;}
	public void setPosition(float x, float y) {
		this.posX=x;
		this.posY=y;
	}
	public int[] getPositionOnScreen() {
		int[] positions = {(int) (this.posX*TowerGame.tileSize-this.level.cameraX*TowerGame.tileSize),(int) (this.posY*TowerGame.tileSize-this.level.cameraY*TowerGame.tileSize)};
		return positions;
	}
}
