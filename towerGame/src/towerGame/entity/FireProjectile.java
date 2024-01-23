package towerGame.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import towerGame.CollisionChecker;
import towerGame.Direction;
import towerGame.EntityRegistry;
import towerGame.Player;
import towerGame.TowerGame;
import towerGame.entity.LivingEntity;
import towerGame.map.Level;
import towerGame.map.Tile;

public class FireProjectile extends Entity {
	public float xVelocity;
	public float yVelocity;
	public long createTime;
	public boolean isBlue;
	public FireProjectile(Level level) {
		super(level);
		this.hitbox=CollisionChecker.getHitbox(6,6,9,9);
	}
	public FireProjectile(Level level, boolean isBlue) {
		this(level);
		this.isBlue=isBlue;
	}
	@Override
	public void update() {
		int[] positions;
		if(CollisionChecker.checkTile(this.level, this, (xVelocity<0)?Direction.LEFT:Direction.RIGHT, (xVelocity<0)?-xVelocity:xVelocity)) {
			this.markedForRemoval=true;
			if(this.isBlue) {
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
			if(this.isBlue) {
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
		Player p = this.level.player;
		if(CollisionChecker.checkEntities(this, p)) {
			p.damage(this.isBlue ? 2.0f : 1.5f);
			this.markedForRemoval=true;
		}
		this.posY+=yVelocity;
		this.yVelocity+=0.009F;
		if(this.posY>500) {
			this.markedForRemoval=true;
		}
		
	}
	@Override
	public String getSprite() {
		if(this.isBlue) {
			return "bluefireparticle.png";
		}else {
			return "fireparticle.png";
		}
	}
	@Override
	public void render(Graphics2D g2) {
		g2.setColor(new Color(252,71,21));
		g2.drawImage(this.sprite,(int)Math.round(this.posX*TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize))+6*TowerGame.scale,(int)Math.round(this.posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize))+6*TowerGame.scale,4*TowerGame.scale,4*TowerGame.scale,null);
	}
	public static List<Object> serialize(Entity e2) {
		List<Object> attr = Entity.serialize(e2);
		FireProjectile e = (FireProjectile)e2;
		attr.add(e.xVelocity);
		attr.add(e.yVelocity);
		attr.add(e.createTime);
		attr.add(e.isBlue);
		return attr;
	}
	public static Entity deserialize(Level level, List<Object> attr){
		FireProjectile e = new FireProjectile(level);
		Entity.deserialize(level, attr, e);
		return deserialize(level, attr, e);
	}
	public static Entity deserialize(Level level, List<Object> attr, FireProjectile e) {
		e.xVelocity=(float)attr.remove(0);
		e.yVelocity=(float)attr.remove(0);
		e.createTime=(long)attr.remove(0);
		e.isBlue=(boolean)attr.remove(0);
		return e;
	}
}
