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

public class Staff extends Weapon {
	int particleSize;
	public Staff(int id, String texture, int particleSize) {
		super(id, texture, particleSize);
		this.particleSize=particleSize;
	}
	public void onAttack(Level level, Player player, boolean isMouseRight, int mouseX, int mouseY) {
		if(isMouseRight) {
			if(player.mana>=1) {
				Point mousePos= MouseInfo.getPointerInfo().getLocation();
				float angle=(float)Math.atan2((mousePos.x-TowerGame.frame.getLocation().x)-Math.round(player.posX*TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize)+0.5*TowerGame.tileSize), (mousePos.y-TowerGame.frame.getLocation().y)-Math.round(player.posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize)+0.5*TowerGame.tileSize));
				PlayerProjectile p = new PlayerProjectile(level, player);
				p.xVelocity=(float) Math.sin(angle)/5;
				p.yVelocity=(float) (Math.cos(angle)/5)-0.1F;
				p.size=particleSize+2;
				level.addEntity(p);
				player.mana -= 1F;
				player.mana = Math.round(player.mana *10.0d) / 10.0f;
			}
		}else {
			if(player.mana>=0.1) {
				Point mousePos= MouseInfo.getPointerInfo().getLocation();
				float angle=(float)Math.atan2((mousePos.x-TowerGame.frame.getLocation().x)-Math.round(player.posX*TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize)+0.5*TowerGame.tileSize), (mousePos.y-TowerGame.frame.getLocation().y)-Math.round(player.posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize)+0.5*TowerGame.tileSize));
				PlayerProjectile p = new PlayerProjectile(level, player);
				p.xVelocity=(float) Math.sin(angle)/5;
				p.yVelocity=(float) (Math.cos(angle)/5)-0.1F;
				p.size=particleSize;
				level.addEntity(p);
				player.mana -= 0.1F;
				player.mana = Math.round(player.mana *10.0d) / 10.0f;
			}
		}
	}

}
