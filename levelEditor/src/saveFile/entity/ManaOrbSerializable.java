package saveFile.entity;

import levelEditor.Level;
import levelEditor.entity.ManaOrb;

public class ManaOrbSerializable extends EntitySerializable {
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
