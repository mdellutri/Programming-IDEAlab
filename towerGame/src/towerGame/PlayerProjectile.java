package towerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import towerGame.map.Level;

public class PlayerProjectile extends GravityEntity {
	public Player player;
	public long createTime;
	public PlayerProjectile(Level level) {
		this(level,null);
	}

	public PlayerProjectile(Level level, Player player) {
		super(level);
		this.player=player;
		this.posX=this.player.posX;
		this.posY=this.player.posY;
		this.hitbox=CollisionChecker.getHitbox(7,7,8,8);
	}
	@Override
	public void update() {
		if(this.level.cc.checkTile(this.level, this, (xVelocity<0)?Direction.LEFT:Direction.RIGHT, (xVelocity<0)?-xVelocity:xVelocity)) {
			this.markedForRemoval=true;
		}
		this.posX+=xVelocity;
		if(this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity)) {
			this.markedForRemoval=true;
		}
		this.posY+=yVelocity;
		this.yVelocity+=0.009F;
		if(this.posY>500) {
			this.markedForRemoval=true;
		}
		
	}
	@Override
	public void render(Graphics2D g2) {
		g2.setColor(new Color(222,215,180));
		g2.fillOval((int)(this.posX*TowerGame.tileSize)+7*TowerGame.scale,(int)(this.posY*TowerGame.tileSize)+7*TowerGame.scale,2*TowerGame.scale,2*TowerGame.scale);
	}

}
