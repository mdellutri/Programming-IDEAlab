package save;

import java.io.*;

import levelEditor.Level;
import levelEditor.entity.*;
import save.EntitySerializable;;

public class SaveFile {
	public static void save(Level level, String fileName) {
		try {
			level.entity_lock.lock();
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
			GameSerializable gs = new GameSerializable();
			for ( Entity e : level.entities) {
				if(!e.markedForRemoval && e!=null) {
					gs.entities.add(EntitySerializable.serialize(e));
				}
			}
			gs.mapTilesBackground=level.mapTilesBackground;
			gs.mapTilesForeground=level.mapTilesForeground;
			gs.levelSizeX=level.sizeX;
			gs.levelSizeY=level.sizeY;
			gs.playerStartX=level.playerSpawnX;
			gs.playerStartY=level.playerSpawnY;
			gs.playerX=gs.playerStartX;
			gs.playerY=gs.playerStartY;
			gs.playerHealth=10.0f;
			gs.playerMana=15.0f;
			gs.playerArmor=0.0f;
			gs.playerWeapon=1;
			gs.skyColor=level.skyColor;
			output.writeObject(gs);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			level.entity_lock.unlock();
		}
	}
	public static void load(Level level, String fileName) {
		try {
			level.entity_lock.lock();
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(fileName)));
			GameSerializable gs = (GameSerializable)input.readObject();
			level.entities.clear();
			for( EntitySerializable se : gs.entities) {
				level.addEntity(EntitySerializable.deserialize(level, se));
			}
			level.mapTilesBackground=gs.mapTilesBackground;
			level.mapTilesForeground=gs.mapTilesForeground;
			level.sizeX=gs.levelSizeX;
			level.sizeY=gs.levelSizeY;
			level.skyColor=gs.skyColor;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			level.entity_lock.unlock();
		}
	}
}
