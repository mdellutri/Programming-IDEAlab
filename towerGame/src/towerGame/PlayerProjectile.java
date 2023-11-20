package towerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import towerGame.map.Level;

public class PlayerProjectile extends Entity {
	Player player;
	float xVel;
	float yVel;
	Rectangle hitbox=new Rectangle(7,7,1,1);
	public PlayerProjectile(Level level) {
		this(level,null);
	}

	public PlayerProjectile(Level level, Player player) {
		super(level);
		this.player=player;
	}
	public void update() {
		this.posX+=xVel;
		this.posY+=yVel;
		this.yVel+=0.009F;
	}
	public void render(Graphics2D g2) {
		g2.setColor(new Color(222,215,180));
		g2.fillOval((int)(this.posX*TowerGame.tileSize)+14,(int)(this.posY*TowerGame.tileSize)+14,4,4);
	}

}
