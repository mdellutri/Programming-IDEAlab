package levelEditor;

import java.awt.Rectangle;

import levelEditor.entity.Entity;

public class CollisionChecker {
	
	public static Rectangle getHitbox(int x0,int y0,int x1,int y1) {
		return new Rectangle(x0,y0,x1-x0,y1-y0);
	}
	public static boolean checkAABB(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
		return (x0<=x3)&&(x1>=x2)&&(y0<=y3)&&(y1>=y2);
	}
	public static boolean checkHitboxes(Rectangle h1, Rectangle h2, float h1x, float h1y, float h2x, float h2y) {
		return checkAABB(h1x+((float)h1.x/16), h1y+((float)h1.y/16), h1x+((float)h1.x/16)+((float)h1.width/16), h1y+((float)h1.y/16)+((float)h1.height/16), h2x+((float)h2.x/16), h2y+((float)h2.y/16), h2x+((float)h2.x/16)+((float)h2.width/16), h2y+((float)h2.y/16)+((float)h2.height/16));
	}
	public static boolean checkEntities(Entity e1, Entity e2) {
		return checkHitboxes(e1.hitbox,e2.hitbox,e1.posX,e1.posY,e2.posX,e2.posY);
	}
	
}
