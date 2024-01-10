package towerGame;

import java.awt.Graphics2D;

import towerGame.map.Level;

public class GravityEntity extends Entity {
	public float xVelocity;
	public float yVelocity;
	public boolean onGround=false;
	public GravityEntity(Level level) {
		super(level);
		this.hitbox=this.level.cc.getHitbox(0,0,16,16);
	}
	public void update() {
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
	}
}
