package levelEditor.entity;

import java.awt.Graphics2D;
import java.util.List;

import levelEditor.EntityRegistry;
import levelEditor.Level;
import levelEditor.LevelEditor;

public class ManaOrb extends Entity {
	
	public ManaOrb(Level level) {
		super(level);
	}
	public String getSprite() {
		return "manaorb.png";
	}
	public void render(Graphics2D g2) {
		int[] positions = this.getPositionOnScreen();
		g2.drawImage(this.sprite,positions[0],positions[1],LevelEditor.tileSize,LevelEditor.tileSize,null);
	}
	public static List<Object> serialize(ManaOrb e2) {
		return Entity.serialize(e2);
	}
	public static Entity deserialize(Level level, List<Object> attr){
		ManaOrb e = new ManaOrb(level);
		return Entity.deserialize(level, attr, e);
	}
	public static Entity deserialize(Level level, List<Object> attr, Entity e){
		return Entity.deserialize(level, attr, e);
	}
}
