package towerGame.npc;
import java.awt.Graphics2D;

import towerGame.Entity;
import towerGame.GravityEntity;
import towerGame.TowerGame;
import towerGame.map.Level;

public class LivingEntity extends GravityEntity {
	public LivingEntity(Level level) {
		super(level);
	}
	public float health;
	public float maxHealth;
	public void loadImages() {
		
	}
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(this.sprite,(int)Math.round(this.posX*TowerGame.tileSize),(int)Math.round(this.posY*TowerGame.tileSize),TowerGame.tileSize,TowerGame.tileSize,null);
	}
}
