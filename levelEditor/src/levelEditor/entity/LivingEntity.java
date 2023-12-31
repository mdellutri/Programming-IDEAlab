package levelEditor.entity;
import java.awt.Graphics2D;

import levelEditor.Level;
import levelEditor.LevelEditor;

public class LivingEntity extends Entity {
	public float health;
	public float maxHealth;
	public int damageCooldown;
	public LivingEntity(Level level) {
		super(level);
	}
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(this.sprite,(int)Math.round(this.posX*LevelEditor.tileSize),(int)Math.round(this.posY*LevelEditor.tileSize),LevelEditor.tileSize,LevelEditor.tileSize,null);
	}
}
