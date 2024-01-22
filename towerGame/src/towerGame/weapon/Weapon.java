package towerGame.weapon;

import towerGame.CollisionChecker;
import towerGame.Player;
import towerGame.entity.Entity;
import towerGame.entity.LivingEntity;
import towerGame.map.Level;

public class Weapon {
	public int id;
	public String texture;
	public float damage;
	public static final Weapon[] weapons=new Weapon[256];
	public void onAttack(Level level, Player player, boolean isMouseRight, int mouseX, int mouseY) {
		for(Entity e : level.entities) {
			if(e instanceof LivingEntity){
				if(CollisionChecker.checkHitboxes(player.hitbox,e.hitbox,player.posX,player.posY,e.posX,e.posY)) {
					((LivingEntity) e).damage(this.damage);
				}
			}
		}
	}
	public void onMouseDown(Level level, Player player, boolean isMouseRight, int mouseX, int mouseY) {};
	public void onMouseUp(Level level, Player player, boolean isMouseRight, int mouseX, int mouseY) {};
	public Weapon(int id, String texture, float damage) {
		this.id=id;
		this.texture=texture;
		this.damage=damage;
		weapons[id] = this;
	}
	public static final Weapon sword = new Weapon(0, "sword.png",1.0f);
	public static final Weapon staff = new Staff(1, "staff.png",1);
	public static final Weapon staffUpgraded = new Staff(2, "staff2.png",2);
}
