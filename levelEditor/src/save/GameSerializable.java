package save;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameSerializable implements Serializable {
	private static final long serialVersionUID = 5036639829869363772L;
	List<EntitySerializable> entities = new ArrayList<EntitySerializable>();
	float playerX;
	float playerY;
	float playerStartX;
	float playerStartY;
	float playerHealth;
	float playerMana;
	float playerArmor;
	int playerWeapon;
	int mapTilesForeground[][];
	int mapTilesBackground[][];
	int levelSizeX;
	int levelSizeY;
	Color skyColor;
}
