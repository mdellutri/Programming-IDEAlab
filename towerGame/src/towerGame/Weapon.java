package towerGame;

import towerGame.map.Level;
import towerGame.map.Tile;
import towerGame.npc.LivingEntity;

public class Weapon {
	public String texture;
	public int damage;
	public static final Weapon[] weapons=new Weapon[256];
	public void onAttack(Level level, Player player, boolean isMouseRight, int mouseX, int mouseY) {
		for(Entity e : level.entities) {
			if(e instanceof LivingEntity){
				
			}
		}
	}
	public Weapon(String texture, int damage) {
		this.texture=texture;
		this.damage=damage;
	}
}
