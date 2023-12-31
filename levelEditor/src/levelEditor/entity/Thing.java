package levelEditor.entity;

import levelEditor.Level;

public class Thing extends Enemy {

	public Thing(Level level) {
		super(level);
	}
	public String getSprite() {
		return "thing.png";
	}
}
