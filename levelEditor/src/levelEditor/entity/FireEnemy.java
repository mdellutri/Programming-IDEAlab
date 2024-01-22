package levelEditor.entity;

import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.awt.Graphics2D;
import java.awt.Rectangle;


import levelEditor.LevelEditor;
import levelEditor.EntityRegistry;
import levelEditor.Level;

public class FireEnemy extends Enemy {
	public boolean isBlue;
	public float baseY;
	public int attackCooldown=200;
	public FireEnemy(Level level, boolean isBlue) {
		super(level);
		this.isBlue=isBlue;
		this.hitbox=new Rectangle(0,0,16,16);
		this.maxHealth=10.0f;
		this.health=this.maxHealth;
		if(this.isBlue) {
			this.attackDamage+=0.5F;
			this.maxHealth+=2.5F;
			this.health=this.maxHealth;
		}
	}
	public FireEnemy(Level level) {
		this(level,false);
	}
	public void update() {
		super.update();
	}

	public String getSprite() {
		if(this.isBlue) {
			return "bluefiresprite.png";
		} else {
			return "redfiresprite.png";
		}
	}
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.baseY=y;
	}
	public static List<Object> serialize(FireEnemy e2) {
		List<Object> attr = Enemy.serialize(e2);
		FireEnemy e = (FireEnemy)e2;
		attr.add(e.isBlue);
		attr.add(e.baseY);
		return attr;
	}
	public static Entity deserialize(Level level, List<Object> attr){
		FireEnemy e = new FireEnemy(level);
		Entity.deserialize(level, attr, e);
		LivingEntity.deserialize(level, attr, e);
		Enemy.deserialize(level, attr, e);
		return deserialize(level, attr, e);
	}
	public static Entity deserialize(Level level, List<Object> attr, FireEnemy e) {
		e.isBlue=(boolean)attr.remove(0);
		e.baseY = (float)attr.remove(0);
		return e;
	}
}