package towerGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class KeyHandler implements KeyListener, MouseListener{
	public boolean upPressed=false;
	public boolean downPressed=false;
	public boolean leftPressed=false;
	public boolean rightPressed=false;
	public boolean debugPressed=false;
	public boolean mousePressed=false;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W) {
			this.upPressed=true;
		}
		if(code==KeyEvent.VK_A) {
			this.leftPressed=true;
		}
		if(code==KeyEvent.VK_S) {
			this.downPressed=true;
		}
		if(code==KeyEvent.VK_D) {
			this.rightPressed=true;
		}
		if(code==KeyEvent.VK_UP) {
			this.upPressed=true;
		}
		if(code==KeyEvent.VK_LEFT) {
			this.leftPressed=true;
		}
		if(code==KeyEvent.VK_DOWN) {
			this.downPressed=true;
		}
		if(code==KeyEvent.VK_RIGHT) {
			this.rightPressed=true;
		}
		if(code==KeyEvent.VK_F3) {
			this.debugPressed=!debugPressed;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W) {
			this.upPressed=false;
		}
		if(code==KeyEvent.VK_A) {
			this.leftPressed=false;
		}
		if(code==KeyEvent.VK_S) {
			this.downPressed=false;
		}
		if(code==KeyEvent.VK_D) {
			this.rightPressed=false;
		}
		if(code==KeyEvent.VK_UP) {
			this.upPressed=false;
		}
		if(code==KeyEvent.VK_LEFT) {
			this.leftPressed=false;
		}
		if(code==KeyEvent.VK_DOWN) {
			this.downPressed=false;
		}
		if(code==KeyEvent.VK_RIGHT) {
			this.rightPressed=false;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.mousePressed=true;
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.mousePressed=true;
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.mousePressed=false;
		
	}
}