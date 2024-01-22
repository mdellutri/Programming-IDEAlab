package levelEditor.entity;
import java.awt.Graphics2D;
import java.util.List;

import levelEditor.LevelEditor;
import levelEditor.EntityRegistry;
import levelEditor.Level;

public class LivingEntity extends Entity {
	public float health;
	public float maxHealth;
	public int damageTimer;
	public int damageCooldown=15;
	public float xVelocity;
	public float yVelocity;
	public boolean onGround=false;
	public LivingEntity(Level level) {
		super(level);
	}
	@Override
	public void update() {
		super.update();
	}
	public void damage(float damage) {
		if(this.damageTimer==0) {
			this.health-=damage;
			if(this.health<=0) {
				this.markedForRemoval=true;
			}
			this.damageTimer=damageCooldown;
		}
	}
	@Override
	public void render(Graphics2D g2) {
		int[] positions = this.getPositionOnScreen();
		g2.drawImage(this.sprite,positions[0],positions[1],LevelEditor.tileSize,LevelEditor.tileSize,null);
	}
	public static List<Object> serialize(LivingEntity e) {
		List<Object> attr = Entity.serialize(e);
		attr.add(e.health);
		attr.add(e.maxHealth);
		attr.add(e.damageTimer);
		attr.add(e.damageCooldown);
		attr.add(e.xVelocity);
		attr.add(e.yVelocity);
		attr.add(e.onGround);
		return attr;
	}
	public static Entity deserialize(Level level, List<Object> attr){
		LivingEntity e = new LivingEntity(level);
		Entity.deserialize(level, attr, e);
		return deserialize(level, attr, e);
	}
	public static Entity deserialize(Level level, List<Object> attr, LivingEntity e) {
		
		e.health=(float)attr.remove(0);
		e.maxHealth=(float)attr.remove(0);
		e.damageTimer=(int)attr.remove(0);
		e.damageCooldown=(int)attr.remove(0);
		e.xVelocity=(float)attr.remove(0);
		e.yVelocity=(float)attr.remove(0);
		e.onGround=(boolean)attr.remove(0);
		
		return e;
	}
}