package towerGame.npc;

import towerGame.map.Level;

public class Enemy extends LivingEntity{
	public float attackDamage = 1.0F;
	public Enemy(Level level) {
		super(level);
		this.damageCooldown=5;
	}

}
