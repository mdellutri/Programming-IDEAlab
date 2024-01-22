package towerGame.entity;

import java.awt.Graphics2D;
import java.util.List;

import towerGame.CollisionChecker;
import towerGame.EntityRegistry;
import towerGame.Player;
import towerGame.TowerGame;
import towerGame.map.Level;

public class ManaOrb extends Entity {
	
	public ManaOrb(Level level) {
		super(level);
		this.hitbox=CollisionChecker.getHitbox(2,2,13,13);
	}
	public String getSprite() {
		return "manaorb.png";
	}
	public void update() {
		Player p = this.level.player;
		if(CollisionChecker.checkEntities(this, p)) {
			p.mana=15.0f;
			this.markedForRemoval=true;
		}
	}
	public void render(Graphics2D g2) {
		int[] positions = this.getPositionOnScreen();
		g2.drawImage(this.sprite,positions[0],positions[1],TowerGame.tileSize,TowerGame.tileSize,null);
	}

	public static List<Object> serialize(ManaOrb e2) {
		return Entity.serialize(e2);
	}
	public static Entity deserialize(Level level, List<Object> attr){
		ManaOrb e = new ManaOrb(level);
		return Entity.deserialize(level, attr, e);
	}

}
