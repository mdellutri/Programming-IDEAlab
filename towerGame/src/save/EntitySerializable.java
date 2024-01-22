package save;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import towerGame.EntityRegistry;
import towerGame.entity.Entity;
import towerGame.map.Level;

// NEW WORLD FORMAT : COMING SOON

public class EntitySerializable implements Serializable {
	private static final long serialVersionUID = 5661980278666850919L;
	public String type;
	public List<Object> attr;
	public static EntitySerializable serialize(Entity e) {
		EntitySerializable es = new EntitySerializable();
		es.type = EntityRegistry.getRegistryFromClass(e);
		try {
			Class c = e.getClass();
			es.attr = (List<Object>) c.getMethod("serialize", c).invoke(e, (Entity)e);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return es;
	}
	public static Entity deserialize(Level level, EntitySerializable es) {
		try {
			Class c = EntityRegistry.getClass(es.type);
			Entity e = (Entity) (c.getMethod("deserialize", Level.class, List.class).invoke(null, level, es.attr));
			return e;
		} catch (Exception e2) {
			e2.printStackTrace();
			return null;
		}
	}
}
