package towerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import towerGame.map.Level;
import towerGame.map.Tile;
import towerGame.npc.LivingEntity;

public class PlayerProjectile extends Entity {
	public float xVelocity;
	public float yVelocity;
	public Player player;
	public long createTime;
	public int size;
	public PlayerProjectile(Level level) {
		this(level,null);
	}

	public PlayerProjectile(Level level, Player player) {
		super(level);
		this.player=player;
		this.posX=this.player.posX;
		this.posY=this.player.posY;
		this.hitbox=CollisionChecker.getHitbox(7,7,8,8);
		this.size=1;
	}
	public void update() {
		int[] positions;
		if(this.level.cc.checkTile(this.level, this, (xVelocity<0)?Direction.LEFT:Direction.RIGHT, (xVelocity<0)?-xVelocity:xVelocity)) {
			this.markedForRemoval=true;
			if(this.size>2) {
				positions=this.level.cc.getTilePositions(this.level, this, (xVelocity<0)?Direction.LEFT:Direction.RIGHT, (xVelocity<0)?-xVelocity:xVelocity);
				if(Tile.isCracked(this.level.getTileForeground(positions[0], positions[2]))) {
					this.level.setTileForeground(positions[0], positions[2],0);
				}
				if(Tile.isCracked(this.level.getTileForeground(positions[1], positions[2]))) {
					this.level.setTileForeground(positions[1], positions[2],0);
				}
				if(Tile.isCracked(this.level.getTileForeground(positions[0], positions[3]))) {
					this.level.setTileForeground(positions[0], positions[3],0);
				}
				if(Tile.isCracked(this.level.getTileForeground(positions[1], positions[3]))) {
					this.level.setTileForeground(positions[1], positions[3],0);
				}
			}
		}
		this.posX+=xVelocity;
		if(this.level.cc.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity)) {
			this.markedForRemoval=true;
			if(this.size>2) {
				positions=this.level.cc.getTilePositions(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity);
				if(Tile.isCracked(this.level.getTileForeground(positions[0], positions[2]))) {
					this.level.setTileForeground(positions[0], positions[2],0);
				}
				if(Tile.isCracked(this.level.getTileForeground(positions[1], positions[2]))) {
					this.level.setTileForeground(positions[1], positions[2],0);
				}
				if(Tile.isCracked(this.level.getTileForeground(positions[0], positions[3]))) {
					this.level.setTileForeground(positions[0], positions[3],0);
				}
				if(Tile.isCracked(this.level.getTileForeground(positions[1], positions[3]))) {
					this.level.setTileForeground(positions[1], positions[3],0);
				}
			}
		}
		for(Entity e : this.level.entities) {
			if( e instanceof LivingEntity) {
				if(this.level.cc.checkEntities(this, e)) {
					((LivingEntity) e).damage(0.5F + (0.5F*this.size));
					this.markedForRemoval=true;
				}
			}
		}
		this.posY+=yVelocity;
		this.yVelocity+=0.009F;
		if(this.posY>500) {
			this.markedForRemoval=true;
		}
		
	}
	public void render(Graphics2D g2) {
		g2.setColor(new Color(222,215,180));
		g2.fillOval((int)(this.posX*TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize))+7*TowerGame.scale,(int)(this.posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize))+7*TowerGame.scale,2*TowerGame.scale*this.size,2*TowerGame.scale*this.size);
	}
}
