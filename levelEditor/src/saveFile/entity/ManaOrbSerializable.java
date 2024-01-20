package saveFile.entity;
import java.io.Serializable;

import levelEditor.Level;
import levelEditor.entity.Entity;
import levelEditor.entity.ManaOrb;

public class ManaOrbSerializable extends EntitySerializable implements Serializable {
	private static final long serialVersionUID = -7350000987201728233L;
	public ManaOrbSerializable(ManaOrb e) {
		super(e);
	}
	public static ManaOrb deserialize(ManaOrbSerializable se, Level level) {
		ManaOrb e = new ManaOrb(level);
		e.posX=se.posX;
		e.posY=se.posY;
		e.id=se.id;
		return e;
	}
}
