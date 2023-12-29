package towerGame.map;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import towerGame.CollisionChecker;
import towerGame.Direction;
import towerGame.Entity;
import towerGame.Player;
import towerGame.TowerGame;
import towerGame.map.Level;
import towerGame.npc.LivingEntity;

public class FallingBoulder extends Entity {
	public float xVelocity;
	public float yVelocity;
	public boolean onGround=false;
	public FallingBoulder(Level level) {
		super(level);
		this.hitbox=CollisionChecker.getHitbox(0,0,16,16);
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
				this.markedForRemoval=true;
				for(Entity e : this.level.entities) {
					if( e instanceof LivingEntity) {
						if(this.level.cc.checkEntities(this, e)) {
							((LivingEntity) e).damage(5.0F);
						}
					}
				}
				Player p=this.level.player;
				if(this.level.cc.checkEntities(this, p)) {
					p.damage(5.0F);
				}
				this.level.setTileForeground((int)this.posX, (int)Math.floor(this.posY+0.1), Tile.boulder.id);
			}else {
				this.onGround=false;
			}
		}
	}
	@Override
	public void render(Graphics2D g2) {
		int frameX = 11*16;
		int frameY = 0;
		g2.drawImage(level.tilemap, (int)(posX*TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize)), (int)(posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize)), (int)(posX*TowerGame.tileSize+TowerGame.tileSize), (int)(posY*TowerGame.tileSize+TowerGame.tileSize), frameX, frameY, frameX+16, frameY+16, (ImageObserver)null);
		
	}
}
