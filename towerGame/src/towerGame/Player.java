package towerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import towerGame.entity.LivingEntity;
import towerGame.entity.PlayerProjectile;
import towerGame.map.Level;
import towerGame.map.Tile;
import towerGame.weapon.Weapon;

public class Player extends LivingEntity {
	public float xVelocity;
	public float yVelocity;
	public boolean onGround=false;
	public float mana=15.0f;
	public float armor=0.0f;
	public int weapon;
	public List<Integer> weapons = new ArrayList<Integer>();
	BufferedImage swordSprite;
	boolean swordSwing=false;
	public Direction facing = Direction.RIGHT;
	public Player(Level level) {
		super(level);
		this.hitbox=CollisionChecker.getHitbox(1,1,15,15);
		this.posX=4;
		this.posY=6;
		this.maxHealth=10.0f;
		this.health=this.maxHealth;
		this.weapon = Weapon.shield.id;
		this.swordSprite=level.getSprite(Weapon.weapons[this.weapon].texture);
	}
	public String getSprite() {
		return "player.png";
	}
	public void update(EventHandler eventHandler) {
		this.hitbox=CollisionChecker.getHitbox(1,1,15,15);
		if(this.damageTimer!=0) {
			this.damageTimer--;
		}
		if(eventHandler.upPressed&&this.onGround) {this.yVelocity=-0.158F;};
		if(eventHandler.leftPressed) {
			this.facing=Direction.LEFT;
			if(CollisionChecker.checkSpecificTiles(this.level, this, Direction.LEFT, 0.052F,Tile.damage_tiles)) {
				this.health=0;
			}
			if(!CollisionChecker.checkTile(this.level, this, Direction.LEFT, 0.052F)) {
				this.posX-=0.052;
			}else {
				if(!CollisionChecker.checkTile(this.level, this, Direction.LEFT, 0.052F/3)) {
					this.posX-=0.052F/3;
				}
				if(!CollisionChecker.checkTile(this.level, this, Direction.LEFT, 0.052F/7)) {
					this.posX-=0.052F/7;
				}
			}
		}
		if(eventHandler.rightPressed) {
			this.facing=Direction.RIGHT;
			if(CollisionChecker.checkSpecificTiles(this.level, this, Direction.RIGHT, 0.052F, Tile.damage_tiles)) {
				this.health=0;
			}
			if(!CollisionChecker.checkTile(this.level, this, Direction.RIGHT, 0.052F)) {
				this.posX+=0.052;
			}else {
				if(!CollisionChecker.checkTile(this.level, this, Direction.RIGHT, 0.052F/3)) {
					this.posX+=0.052F/3;
				}
				if(!CollisionChecker.checkTile(this.level, this, Direction.RIGHT, 0.052F/7)) {
					this.posX+=0.052F/7;
				}
			}
		}
		if(eventHandler.mouse1Pressed || eventHandler.mouse2Pressed) {
			Point mousePos= MouseInfo.getPointerInfo().getLocation();
			Weapon.weapons[this.weapon].onMouseHeld(level, this, mousePos.x, mousePos.y);
			this.swordSwing=true;
		}else {
			this.swordSwing=false;
		}
		if(eventHandler.mouse1Clicked) {
			Point mousePos= MouseInfo.getPointerInfo().getLocation();
			Weapon.weapons[this.weapon].onAttack(level, this, false, mousePos.x, mousePos.y);
		}
		if(eventHandler.mouse2Clicked) {
			Point mousePos= MouseInfo.getPointerInfo().getLocation();
			Weapon.weapons[this.weapon].onAttack(level, this, true, mousePos.x, mousePos.y);
		}
		this.posX+=xVelocity;
		this.yVelocity+=0.007F;//gravity

		if(CollisionChecker.checkSpecificTiles(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity, Tile.damage_tiles)) {
			this.health=0;
		}

		if(CollisionChecker.checkSpecificTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity, Tile.conveyorLeft)) {
			if(!CollisionChecker.checkTile(this.level, this, Direction.LEFT, 0.075F)) {
				this.posX-=0.075;
			}
		}
		if(CollisionChecker.checkSpecificTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity, Tile.conveyorRight)) {
			if(!CollisionChecker.checkTile(this.level, this, Direction.RIGHT, 0.075F)) {
				this.posX+=0.075;
			}
		}
		if(!CollisionChecker.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, (yVelocity<0)?-yVelocity:yVelocity)) {
			this.posY+=yVelocity;
			this.onGround=false;
		}else {

			if(!CollisionChecker.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, ((yVelocity<0)?-yVelocity:yVelocity)/3)) {
				this.posY+=yVelocity/3;
			}
			if(!CollisionChecker.checkTile(this.level, this, (yVelocity<0)?Direction.UP:Direction.DOWN, ((yVelocity<0)?-yVelocity:yVelocity)/7)) {
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
	public void render(Graphics2D g2) {
		if(this.facing==Direction.LEFT) {
			g2.drawImage(this.sprite,(int)Math.round(this.posX*TowerGame.tileSize+TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize)),(int)Math.round(this.posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize)),-TowerGame.tileSize,TowerGame.tileSize,null);
			g2.drawImage(this.swordSprite, (int)(posX*TowerGame.tileSize-0.5*TowerGame.tileSize)-(int)(level.cameraX*TowerGame.tileSize), (int)(posY*TowerGame.tileSize)-(int)(level.cameraY*TowerGame.tileSize), (int)(posX*TowerGame.tileSize+0.5*TowerGame.tileSize)-(int)(level.cameraX*TowerGame.tileSize), (int)(posY*TowerGame.tileSize)+TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize),16, this.swordSwing?16:0, 0, this.swordSwing?32:16, (ImageObserver)null);
		} else {
			g2.drawImage(this.sprite,(int)Math.round(this.posX*TowerGame.tileSize-(int)(level.cameraX*TowerGame.tileSize)),(int)Math.round(this.posY*TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize)),TowerGame.tileSize,TowerGame.tileSize,null);
			g2.drawImage(this.swordSprite, (int)(posX*TowerGame.tileSize+0.5*TowerGame.tileSize)-(int)(level.cameraX*TowerGame.tileSize), (int)(posY*TowerGame.tileSize)-(int)(level.cameraY*TowerGame.tileSize), (int)(posX*TowerGame.tileSize+1.5*TowerGame.tileSize)-(int)(level.cameraX*TowerGame.tileSize), (int)(posY*TowerGame.tileSize)+TowerGame.tileSize-(int)(level.cameraY*TowerGame.tileSize),0, this.swordSwing?16:0, 16, this.swordSwing?32:16, (ImageObserver)null);
		}
	}
	public void renderDebug(Graphics2D g2) {
	}
	public void damage(float damage) {
		super.damage(damage/(1+this.armor));
	}
}