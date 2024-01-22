package saveFile;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import levelEditor.Level;
import levelEditor.entity.Entity;
import saveFile.entity.EntitySerializable;

public class GameSerializable implements Serializable {
	private static final long serialVersionUID = 4842026392412575873L;
	List<EntitySerializable> entities = new ArrayList<EntitySerializable>();
	float playerX;
	float playerY;
	float playerStartX;
	float playerStartY;
	float playerHealth;
	float playerMana;
	float playerArmor;
	int mapTilesForeground[][];
	int mapTilesBackground[][];
	int levelSizeX;
	int levelSizeY;
	Color skyColor;
}
