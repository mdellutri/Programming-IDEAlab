package levelEditor;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.Rectangle;

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
	public void loadImages() {
		try {
			if(this.isBlue) {
				this.sprite=ImageIO.read(getClass().getResourceAsStream("/bluefiresprite.png"));
			}else {
				this.sprite=ImageIO.read(getClass().getResourceAsStream("/redfiresprite.png"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load fire sprite", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
