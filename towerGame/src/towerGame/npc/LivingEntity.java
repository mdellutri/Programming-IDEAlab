package towerGame.npc;
import towerGame.Entity;
import towerGame.GravityEntity;
import towerGame.map.Level;

public class LivingEntity extends GravityEntity {
	public LivingEntity(Level level) {
		super(level);
	}
	public float health;
	public float maxHealth;
}
