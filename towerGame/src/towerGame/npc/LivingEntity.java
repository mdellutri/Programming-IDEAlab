package towerGame.npc;
import java.awt.Graphics2D;

import towerGame.CollisionChecker;
import towerGame.Direction;
import towerGame.Entity;
import towerGame.TowerGame;
import towerGame.map.Level;

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
		this.hitbox=CollisionChecker.getHitbox(0,0,16,16);
	}
	@Override
	public void update() {
		super.update();
		this.yVelocity+=0.007F;//gravity
		if(!this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity)) {
			this.posY+=yVelocity;
			this.onGround=false;
		}else {
	
			if(!this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, ((yVelocity<0)?-yVelocity:yVelocity)/3)) {
				this.posY+=yVelocity/3;
			}
			if(!this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, ((yVelocity<0)?-yVelocity:yVelocity)/7)) {
				this.posY+=yVelocity/7;
			}
			if(this.yVelocity>0) {
				this.onGround=true;
			}else {
				this.onGround=false;
			}
			this.yVelocity=yVelocity>0?-(this.yVelocity/8):-(this.yVelocity); //bounce
		}
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
		g2.drawImage(this.sprite,(int)Math.round(this.posX*TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize)),(int)Math.round(this.posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize)),TowerGame.tileSize,TowerGame.tileSize,null);
	}
}
