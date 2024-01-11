package levelEditor.entity;
import java.awt.Graphics2D;

import levelEditor.LevelEditor;
import levelEditor.Level;

public class LivingEntity extends Entity {
	public float health;
	public float maxHealth;
	public int damageTimer;
	public int damageCooldown=15;
	public float xVelocity;
	public float yVelocity;
	public boolean onGround=false;
	public LivingEntity(Level level) {
		super(level);
	}
	@Override
	public void update() {
		super.update();
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
		g2.drawImage(this.sprite,(int)Math.round(this.posX*LevelEditor.tileSize),(int)Math.round(this.posY*LevelEditor.tileSize),LevelEditor.tileSize,LevelEditor.tileSize,null);
	}
}