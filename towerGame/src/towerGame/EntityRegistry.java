package towerGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import towerGame.entity.*;

import java.util.Objects;

public class EntityRegistry {
	private static final HashMap<String, Class> entityTypes = new HashMap<String, Class>();
	public static void registerClass(String estring, Class eclass) {
		entityTypes.putIfAbsent(eclass.getName().replaceFirst("towerGame.entity.", ""), eclass);
	}
	public static Class getClass(String estring) {
		return entityTypes.get(estring);
	}
	public static String getRegistryFromClass(Entity e) {
		return getKeyByValue(entityTypes, e.getClass());
	}
				
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}
	static {
		registerClass("Entity",Entity.class);
		registerClass("LivingEntity",LivingEntity.class);
		registerClass("Enemy",Enemy.class);
		registerClass("FireEnemy",FireEnemy.class);
		registerClass("Thing",Thing.class);
		registerClass("NPC",NPC.class);
		registerClass("ManaOrb",ManaOrb.class);
		registerClass("FireProjectile",FireProjectile.class);
		registerClass("PlayerProjectile",PlayerProjectile.class);
		registerClass("FallingBoulder",FallingBoulder.class);
	}
}
