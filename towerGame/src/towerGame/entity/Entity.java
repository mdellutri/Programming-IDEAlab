package towerGame.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import towerGame.EventHandler;
import towerGame.TowerGame;
import towerGame.map.Level;

import java.awt.Rectangle;

public class Entity {
	public BufferedImage sprite;
	public boolean customSprite = false;
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
	public static List<Object> serialize(Entity e) {
		List<Object> attr = new ArrayList<Object>();
		attr.add(e.customSprite);
		if(e.customSprite) {
			attr.add(e.sprite);
		}
		attr.add(e.posX);
		attr.add(e.posY);
		attr.add(e.id);
		attr.add(e.hitbox);
		return attr;
	}
	public static Entity deserialize(Level level, List<Object> attr) {
		return deserialize(level, attr, new Entity(level));
	}
	public static Entity deserialize(Level level, List<Object> attr, Entity e) {
		e.customSprite = (boolean) attr.remove(0);
		if(e.customSprite) {
			e.sprite = (BufferedImage) attr.remove(0);
		}
		e.posX = (float) attr.remove(0);
		e.posY = (float) attr.remove(0);
		e.id = (int) attr.remove(0);
		e.hitbox = (Rectangle) attr.remove(0);
		return e;
	}
}
