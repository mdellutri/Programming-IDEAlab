package towerGame.entity;

import java.util.List;

import towerGame.map.Level;

public class NPC extends LivingEntity {
	public boolean killable;
	public List<String> dialog;
	public NPC(Level level) {
		super(level);
		// TODO Auto-generated constructor stub
	}
	public void damage(float damage) {
		if(this.killable) {
			super.damage(damage);
		}
	}
	public static List<Object> serialize(NPC e2) {
		List<Object> attr = LivingEntity.serialize(e2);
		NPC e = (NPC)e2;
		attr.add(e.killable);
		attr.add(e.dialog);
		return attr;
	}
	public static Entity deserialize(Level level, List<Object> attr){
		NPC e = new NPC(level);
		Entity.deserialize(level, attr, e);
		LivingEntity.deserialize(level, attr, e);
		return deserialize(level, attr, e);
	}
	public static Entity deserialize(Level level, List<Object> attr, NPC e) {
		e.killable=(boolean)attr.remove(0);
		e.dialog=(List<String>)attr.remove(0);
		return e;
	}

}
