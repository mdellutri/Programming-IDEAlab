package towerGame.npc;
import java.awt.Graphics2D;

import towerGame.Entity;
import towerGame.GravityEntity;
import towerGame.TowerGame;
import towerGame.map.Level;

public class LivingEntity extends GravityEntity {
	public float health;
	public float maxHealth;
	public int damageTimer;
	public int damageCooldown=15;
	public LivingEntity(Level level) {
		super(level);
	}
	public void loadImages() {
		
	}
	@Override
	public void update() {
		super.update();
		if(this.damageTimer!=0) {
			this.damageTimer--;
		}
	}
	public void damage(float damage) {
		if(this.damageTimer==0) {
			this.health-=damage;
			if(this.health<=0) {
				this.markedForRemoval=true;
			}
			this.damageTimer=damageCooldown;
		}
	}
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(this.sprite,(int)Math.round(this.posX*TowerGame.tileSize),(int)Math.round(this.posY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize,null);
	}
}
