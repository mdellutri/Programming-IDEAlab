package towerGame.npc;
import towerGame.Entity;
import towerGame.map.Level;

public class LivingEntity extends Entity {
	public LivingEntity(Level level) {
		super(level);
	}
	public float health;
	public float maxHealth;
}
