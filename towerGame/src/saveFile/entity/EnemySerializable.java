package saveFile.entity;

import java.io.Serializable;

import towerGame.map.Level;
import towerGame.npc.Enemy;
import towerGame.npc.LivingEntity;

public class EnemySerializable extends LivingEntitySerializable {
	private static final long serialVersionUID = 2017524676486334348L;
	float attackDamage;
	public EnemySerializable(Enemy e) {
		super(e);
		this.attackDamage=e.attackDamage;
	}
	public static Enemy deserialize(EnemySerializable se, Level level) {
		Enemy e = new Enemy(level);
		e.posX=se.posX;
		e.posY=se.posY;
		e.id=se.id;
		e.health=se.health;
		e.maxHealth=se.maxHealth;
		e.damageCooldown=se.damageCooldown;
		e.attackDamage=se.attackDamage;
		return e;
	}

}
