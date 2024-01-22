package towerGame.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import towerGame.CollisionChecker;
import towerGame.Direction;
import towerGame.EntityRegistry;
import towerGame.Player;
import towerGame.TowerGame;
import towerGame.map.Level;
import towerGame.map.Tile;

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
		if(CollisionChecker.checkTile(this.level, this, (xVelocity<0)?Direction.LEFT:Direction.RIGHT, (xVelocity<0)?-xVelocity:xVelocity)) {
			this.markedForRemoval=true;
			if(this.size>2) {
				positions=CollisionChecker.getTilePositions(this.level, this, (xVelocity<0)?Direction.LEFT:Direction.RIGHT, (xVelocity<0)?-xVelocity:xVelocity);
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
		if(CollisionChecker.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity)) {
			this.markedForRemoval=true;
			if(this.size>2) {
				positions=CollisionChecker.getTilePositions(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity);
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
				if(CollisionChecker.checkEntities(this, e)) {
					((LivingEntity) e).damage(1.0F + (0.5F*this.size));
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
		g2.setColor(new Color(227, 216, 177));
		g2.fillOval((int)(this.posX*TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize))+7*TowerGame.scale,(int)(this.posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize))+7*TowerGame.scale,(int)(TowerGame.scale*(1+1.4*this.size)),(int)(TowerGame.scale*(1+1.4*this.size)));
	}
	public static List<Object> serialize(PlayerProjectile e2) {
		List<Object> attr = Entity.serialize(e2);
		PlayerProjectile e = (PlayerProjectile)e2;
		attr.add(e.xVelocity);
		attr.add(e.yVelocity);
		attr.add(e.createTime);
		return attr;
	}
	public static Entity deserialize(Level level, List<Object> attr){
		PlayerProjectile e = new PlayerProjectile(level);
		Entity.deserialize(level, attr, e);
		return deserialize(level, attr, e);
	}
	public static Entity deserialize(Level level, List<Object> attr, PlayerProjectile e) {
		e.xVelocity=(float)attr.remove(0);
		e.yVelocity=(float)attr.remove(0);
		e.createTime=(long)attr.remove(0);
		return e;
	}
}
