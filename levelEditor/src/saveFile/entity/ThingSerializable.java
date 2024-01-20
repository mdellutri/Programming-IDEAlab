package saveFile.entity;

import levelEditor.Level;
import levelEditor.entity.Thing;


public class ThingSerializable extends EnemySerializable {
	private static final long serialVersionUID = -4386605737797832530L;
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
