package towerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import towerGame.map.Level;

public class PlayerProjectile extends Entity {
	public Player player;
	public float xVel;
	public float yVel;
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
	public void update() {
		if(this.level.cc.checkTile(this.level, this, (xVel<0)?Direction.LEFT:Direction.RIGHT, (xVel<0)?-xVel:xVel)) {
			this.markedForRemoval=true;
		}
		this.posX+=xVel;
		if(this.level.cc.checkTile(this.level, this, (yVel<0)?Direction.UP:Direction.DOWN, (yVel<0)?-yVel:yVel)) {
			this.markedForRemoval=true;
		}
		this.posY+=yVel;
		this.yVel+=0.009F;
		
	}
	public void render(Graphics2D g2) {
		g2.setColor(new Color(222,215,180));
		g2.fillOval((int)(this.posX*TowerGame.tileSize)+7*TowerGame.scale,(int)(this.posY*TowerGame.tileSize)+7*TowerGame.scale,2*TowerGame.scale,2*TowerGame.scale);
	}

}
