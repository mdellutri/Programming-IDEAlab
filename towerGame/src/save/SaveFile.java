package save;

import java.io.*;

import towerGame.entity.*;
import towerGame.map.Level;
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
			gs.playerX=level.player.posX;
			gs.playerY=level.player.posY;
			gs.playerStartX=4;
			gs.playerStartY=6; // these will be changeable in the future
			gs.playerHealth=level.player.health;
			gs.playerMana=level.player.mana;
			gs.playerArmor=level.player.armor;
			gs.playerWeapon=level.player.weapon;
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
			level.player.posX=gs.playerX;
			level.player.posY=gs.playerY;
			level.player.health=gs.playerHealth;
			level.player.mana=gs.playerMana;
			level.player.armor=gs.playerArmor;
			level.skyColor=gs.skyColor;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			level.entity_lock.unlock();
		}
	}
}
