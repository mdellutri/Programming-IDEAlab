package levelEditor.entity;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import levelEditor.Level;

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
}
