package towerGame.weapon;

import java.awt.MouseInfo;
import java.awt.Point;

import towerGame.CollisionChecker;
import towerGame.Player;
import towerGame.TowerGame;
import towerGame.entity.Entity;
import towerGame.entity.LivingEntity;
import towerGame.entity.PlayerProjectile;
import towerGame.map.Level;

public class Shield extends Weapon {
	public Shield(int id, String texture) {
		super(id, texture, 0);
	}
	public void onMouseHeld(Level level, Player player, int mouseX, int mouseY) {
		
	}

}
