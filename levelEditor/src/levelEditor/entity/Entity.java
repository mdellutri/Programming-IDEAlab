package levelEditor.entity;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import levelEditor.Level;
import levelEditor.LevelEditor;

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
	public String getSprite(){return "";}
	public void setSprite(BufferedImage sprite) {this.sprite=sprite;}
	public void render(Graphics2D g2) {}
	public void renderDebug(Graphics2D g2) {}
	public void setPosition(float x, float y) {
		this.posX=x;
		this.posY=y;
	}
	public void update() {}
	public int[] getPositionOnScreen() {
		int[] positions = {(int) (this.posX*LevelEditor.tileSize-this.level.cameraX*LevelEditor.tileSize),(int) (this.posY*LevelEditor.tileSize-this.level.cameraY*LevelEditor.tileSize)};
		return positions;
	}
}
