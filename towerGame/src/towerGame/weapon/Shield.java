package towerGame.weapon;

import java.awt.MouseInfo;
import java.awt.Point;

import towerGame.CollisionChecker;
import towerGame.Direction;
import towerGame.Player;
import towerGame.TowerGame;
import towerGame.entity.Entity;
import towerGame.entity.FireProjectile;
import towerGame.entity.LivingEntity;
import towerGame.entity.PlayerProjectile;
import towerGame.entity.Thing;
import towerGame.map.Level;

public class Shield extends Weapon {
	public Shield(int id, String texture) {
		super(id, texture, 0);
	}
	public void onMouseHeld(Level level, Player player, int mouseX, int mouseY) {
		for ( Entity e : level.entities ) {
			if(e instanceof FireProjectile) {
				if(!((FireProjectile)e).hasBeenReflected) {
					if(CollisionChecker.checkHitboxes(player.hitbox,e.hitbox,player.posX+(player.facing == Direction.LEFT ? -0.5f: 0.5f),player.posY,e.posX,e.posY)) {
						FireProjectile e2=((FireProjectile)e);
						e2.xVelocity=-e2.xVelocity/4;
						e2.yVelocity=-e2.yVelocity/4;
						e2.hasBeenReflected = true;
					}
				}
			}
			if(e instanceof Thing) {
				if(CollisionChecker.checkHitboxes(player.hitbox,e.hitbox,player.posX+(player.facing == Direction.LEFT ? -0.5f: 0.5f),player.posY,e.posX,e.posY)) {
					Thing e2=((Thing)e);
					e2.xVelocity=-e2.xVelocity/4;
					e2.yVelocity=-e2.yVelocity/4;
				}
			}
		}
	}

}
