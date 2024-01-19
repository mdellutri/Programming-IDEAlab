package saveFile.entity;

import java.io.Serializable;

import towerGame.map.Level;
import towerGame.npc.Enemy;
import towerGame.npc.LivingEntity;
import towerGame.npc.Thing;

public class ThingSerializable extends EnemySerializable {
	private static final long serialVersionUID = 2017524676486334348L;
	float attackDamage;
	public boolean isAttacking;
	public ThingSerializable(Thing e) {
		super(e);
		this.isAttacking=e.isAttacking;
	}
	public static Thing deserialize(ThingSerializable se, Level level) {
		Thing e = new Thing(level);
		e.posX=se.posX;
		e.posY=se.posY;
		e.id=se.id;
		e.health=se.health;
		e.maxHealth=se.maxHealth;
		e.damageCooldown=se.damageCooldown;
		e.attackDamage=se.attackDamage;
		e.isAttacking=se.isAttacking;
		return e;
	}

}
