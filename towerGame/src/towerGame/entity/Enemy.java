package towerGame.entity;

import java.util.List;

import towerGame.CollisionChecker;
import towerGame.map.Level;

public class Enemy extends LivingEntity{
	public float attackDamage;
	public int attackCooldown;
	public Enemy(Level level) {
		super(level);
		this.damageCooldown = 4;
		this.attackDamage = 1.0F;
	}
	public void update() {
		super.update();
		if(this.level.player!=null) {
			if(CollisionChecker.checkEntities(this,this.level.player)) {
				this.level.player.damage(this.attackDamage);
			}
		}
	}
	public static List<Object> serialize(Entity e2) {
		List<Object> attr = LivingEntity.serialize(e2);
		Enemy e = (Enemy)e2;
		attr.add(e.attackDamage);
		attr.add(e.attackCooldown);
		return attr;
	}
	public static Entity deserialize(Level level, List<Object> attr){
		Enemy e = new Enemy(level);
		Entity.deserialize(level, attr, e);
		LivingEntity.deserialize(level, attr, e);
		return deserialize(level, attr, e);
	}
	public static Entity deserialize(Level level, List<Object> attr, Enemy e) {
		e.attackDamage=(float)attr.remove(0);
		e.attackCooldown=(int)attr.remove(0);
		return e;
	}

}
