package saveFile.entity;

import towerGame.map.Level;
import towerGame.Entity;
import towerGame.npc.LivingEntity;

public class LivingEntitySerializable extends EntitySerializable {
	private static final long serialVersionUID = 3440397950556460118L;
	public float health;
	public float maxHealth;
	public int damageCooldown;
	public LivingEntitySerializable(LivingEntity e) {
		super((Entity)e);
		this.health=e.health;
		this.maxHealth=e.maxHealth;
		this.damageCooldown=e.damageCooldown;
	}

	public static LivingEntity deserialize(LivingEntitySerializable se, Level level) {
		LivingEntity e = new LivingEntity(level);
		e.posX=se.posX;
		e.posY=se.posY;
		e.id=se.id;
		e.health=se.health;
		e.maxHealth=se.maxHealth;
		e.damageCooldown=se.damageCooldown;
		return e;
	}
}
