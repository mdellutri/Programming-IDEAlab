package saveFile;

import java.io.*;

import towerGame.map.Level;
import towerGame.Entity;
import towerGame.npc.*;
import towerGame.npc.FireEnemy;
import saveFile.entity.*;

public class SaveFile {
	public static void save(Level level, String fileName) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
			GameSerializable gs = new GameSerializable();
			for ( Entity e : level.entities) {
				if(!e.markedForRemoval && e!=null) {
					if( e instanceof LivingEntity) {
						if( e instanceof Enemy) {
							if( e instanceof FireEnemy) {
								gs.entities.add(new FireEnemySerializable((FireEnemy)e));
							}else {
								gs.entities.add(new EnemySerializable((Enemy)e));
							}
						}else {
							if( e instanceof NPC) {
								gs.entities.add(new NPCSerializable((NPC)e));
							}else {
								gs.entities.add(new LivingEntitySerializable((LivingEntity)e));
							}
						}
					}else {
						gs.entities.add(new EntitySerializable(e));
					}
				}
			}
			gs.mapTilesBackground=level.mapTilesBackground;
			gs.mapTilesForeground=level.mapTilesForeground;
			gs.levelSizeX=level.sizeX;
			gs.levelSizeY=level.sizeY;
			gs.playerX=4;
			gs.playerY=6;
			gs.playerStartX=4;
			gs.playerStartY=6; // these will be changeable in the future
			gs.playerHealth=10.0f;
			gs.playerMana=10.0f;
			gs.playerArmor=0.0f;
			output.writeObject(gs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void load(Level level, String fileName) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(fileName)));
			GameSerializable gs = (GameSerializable)input.readObject();
			level.entities.clear();
			for( EntitySerializable se : gs.entities) {
				if( se instanceof LivingEntitySerializable) {
					if( se instanceof EnemySerializable) {
						if( se instanceof FireEnemySerializable) {
							level.addEntity(FireEnemySerializable.deserialize((FireEnemySerializable)se, level));
						}else {
							level.addEntity(EnemySerializable.deserialize((EnemySerializable)se, level));
						}
					}else {
						if( se instanceof NPCSerializable) {
							level.addEntity(NPCSerializable.deserialize((NPCSerializable)se, level));
						}else {
							level.addEntity(LivingEntitySerializable.deserialize((LivingEntitySerializable)se, level));
						}
					}
				}else {
					level.addEntity(EntitySerializable.deserialize(se, level));
				}
			}
			level.mapTilesBackground=gs.mapTilesBackground;
			level.mapTilesForeground=gs.mapTilesForeground;
			level.sizeX=gs.levelSizeX;
			level.sizeY=gs.levelSizeY;
			//level.player.posX=gs.playerX;
			//level.player.posY=gs.playerY;
			//level.player.health=10.0f;
			//level.player.mana=10.0f;
			//level.player.armor=0.0f;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
