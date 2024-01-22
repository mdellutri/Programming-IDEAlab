package levelEditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import levelEditor.entity.*;

public class EntityRegistry {
	private static final HashMap<String, Class> entityTypes = new HashMap<String, Class>();
	public static void registerClass(String estring, Class eclass) {
		entityTypes.putIfAbsent(estring, eclass);
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
	}
}
