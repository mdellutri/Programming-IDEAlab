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

	public void update() {}
	public void update(Level level) {}
	public void render(Graphics2D g2) {}
}
