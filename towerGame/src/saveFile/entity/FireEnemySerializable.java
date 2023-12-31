package saveFile.entity;

import towerGame.map.Level;
import towerGame.npc.Enemy;
import towerGame.npc.FireEnemy;

public class FireEnemySerializable extends EnemySerializable {
	private static final long serialVersionUID = 8881434238747569994L;
	boolean isBlue;
	public FireEnemySerializable(FireEnemy e) {
		super(e);
		this.posY=e.baseY;
		this.isBlue=e.isBlue;
	}
	public static FireEnemy deserialize(FireEnemySerializable se, Level level) {
		FireEnemy e = new FireEnemy(level,se.isBlue);
		e.posX=se.posX;
		e.posY=se.posY;
		e.baseY=(int)se.posY;
		e.id=se.id;
		e.health=se.health;
		e.maxHealth=se.maxHealth;
		e.damageCooldown=se.damageCooldown;
		e.attackDamage=se.attackDamage;
		return e;
	}
	
}
