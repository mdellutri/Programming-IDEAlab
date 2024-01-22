package saveFile.entity;
import java.io.Serializable;

import levelEditor.Level;
import levelEditor.entity.Entity;
import levelEditor.entity.ManaOrb;

public class EntitySerializable implements Serializable {
	private static final long serialVersionUID = -2820278841533918266L;
	public float posX;
	public float posY;
	public int id;
	public EntitySerializable(Entity e) {
		this.posX=e.posX;
		this.posY=e.posY;
		this.id=e.id;
	}
	public static Entity deserialize(EntitySerializable se, Level level) {
		Entity e = new Entity(level);
		e.posX=se.posX;
		e.posY=se.posY;
		e.id=se.id;
		return e;
	}
}
