package levelEditor.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import levelEditor.Level;
import levelEditor.LevelEditor;
import levelEditor.EntityRegistry;

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
