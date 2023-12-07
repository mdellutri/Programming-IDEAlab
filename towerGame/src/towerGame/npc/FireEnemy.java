package towerGame.npc;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.Rectangle;

import towerGame.Entity;
import towerGame.Player;
import towerGame.map.Level;

public class FireEnemy extends Enemy {
	public boolean isBlue;
	public int baseY;
	public FireEnemy(Level level, boolean isBlue) {
		super(level);
		this.isBlue=isBlue;
		this.hitbox=new Rectangle(0,0,16,16);
	}
	public FireEnemy(Level level) {
		this(level,false);
	}
	@Override
	public void update() {
		this.posY=baseY+(float) Math.sin(System.nanoTime()/500000000.0D);
		for(Entity e : this.level.entities) {
			if(e instanceof Player) {
				if(this.level.cc.checkAABB(this.posX+this.hitbox.x, this.posY+this.hitbox.y, this.posX+this.hitbox.x+this.hitbox.width, this.posY+this.hitbox.y+this.hitbox.height, e.posX+e.hitbox.x, e.posY+e.hitbox.y, e.posX+e.hitbox.x+e.hitbox.width, e.posY+e.hitbox.y+e.hitbox.height)) {
					JOptionPane.showMessageDialog(null, "q", "q", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	@Override
	public void loadImages() {
		try {
			if(this.isBlue) {
				this.sprite=ImageIO.read(getClass().getResourceAsStream("/bluefiresprite.png"));
			}else {
				this.sprite=ImageIO.read(getClass().getResourceAsStream("/firesprite.png"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load fire sprite", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
