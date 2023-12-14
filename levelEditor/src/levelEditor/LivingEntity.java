package levelEditor;
import java.awt.Graphics2D;

public class LivingEntity extends Entity {
	public LivingEntity(Level level) {
		super(level);
	}
	public float health;
	public float maxHealth;
	public void loadImages() {
		
	}
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(this.sprite,(int)Math.round(this.posX*LevelEditor.tileSize),(int)Math.round(this.posY*LevelEditor.tileSize),LevelEditor.tileSize,LevelEditor.tileSize,null);
	}
}
