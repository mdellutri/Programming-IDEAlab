package saveFile.entity;

import towerGame.map.Level;
import towerGame.npc.LivingEntity;
import towerGame.npc.NPC;

public class NPCSerializable  extends LivingEntitySerializable{
	private static final long serialVersionUID = -17541348730117995L;
	boolean killable;
	public NPCSerializable(NPC e) {
		super(e);
		this.killable=e.killable;
	}

	public static NPC deserialize(NPCSerializable se, Level level) {
		NPC e = new NPC(level);
		e.posX=se.posX;
		e.posY=se.posY;
		e.id=se.id;
		e.health=se.health;
		e.maxHealth=se.maxHealth;
		e.damageCooldown=se.damageCooldown;
		e.killable=se.killable;
		return e;
	}

}
