package towerGame.weapon;

import towerGame.CollisionChecker;
import towerGame.Player;
import towerGame.entity.Entity;
import towerGame.entity.LivingEntity;
import towerGame.map.Level;

public class Staff extends Weapon {

	public Staff(int id, String texture, float damage) {
		super(id, texture, damage);
	}
	public void onAttack(Level level, Player player, boolean isMouseRight, int mouseX, int mouseY) {
		for(Entity e : level.entities) {
			if(e instanceof LivingEntity){
				if(CollisionChecker.checkHitboxes(player.hitbox,e.hitbox,player.posX,player.posY,e.posX,e.posY)) {
					((LivingEntity) e).damage(this.damage);
				}
			}
		}
	}

}
