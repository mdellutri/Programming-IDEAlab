package levelEditor;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Thing extends Enemy {

	public Thing(Level level) {
		super(level);
	}
	@Override
	public void loadImages() {

		try {
			this.sprite=ImageIO.read(getClass().getResourceAsStream("/thing.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load sprite", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
