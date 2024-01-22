package towerGame.entity;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.List;

import towerGame.TowerGame;
import towerGame.map.Level;

public class Thing extends Enemy {
	public boolean isAttacking;
	public Thing(Level level) {
		super(level);
		this.health=5;
		this.maxHealth=5;
		this.attackDamage = 5.0F;
	}
	public String getSprite() {
		return "thing.png";
	}
	public void render(Graphics2D g2) {
		int[] positions = this.getPositionOnScreen();
		g2.drawImage(this.sprite, positions[0], positions[1], positions[0]+TowerGame.tileSize, positions[1]+TowerGame.tileSize, this.isAttacking?16:0, 0, this.isAttacking?32:16, 16, (ImageObserver)null);
	}
	public static List<Object> serialize(Thing e2) {
		List<Object> attr = Enemy.serialize(e2);
		Thing e = (Thing)e2;
		attr.add(e.isAttacking);
		return attr;
	}
	public static Entity deserialize(Level level, List<Object> attr){
		Thing e = new Thing(level);
		Entity.deserialize(level, attr, e);
		LivingEntity.deserialize(level, attr, e);
		Enemy.deserialize(level, attr, e);
		return deserialize(level, attr, e);
	}
	public static Entity deserialize(Level level, List<Object> attr, Thing e) {
		e.isAttacking=(boolean)attr.remove(0);
		return e;
	}
}
