package towerGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import towerGame.map.Level;

public class TowerGame extends JPanel implements Runnable {
	
	Thread gameThread;
	KeyHandler keyHandler = new KeyHandler();
	static Player player = new Player();
	Level level= new Level(16);
	int fpsCap = 60;
	/*@Override
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.fillOval(0, 0, 30, 30);
		g2d.drawOval(0, 50, 30, 30);		
		g2d.fillRect(50, 0, 30, 30);
		g2d.drawRect(50, 50, 30, 30);

		g2d.draw(new Ellipse2D.Double(0, 100, 30, 30));
	}*/
	public TowerGame() {
		this.addKeyListener(keyHandler);
	}
	public void update() {
		level.update();;
		player.update(keyHandler);
	};
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, 640, 480);
		//g2.setColor(Color.red);
		//g2.fillRect(player.posX-1, player.posY-1, 34, 34);
		level.render(g2);
		player.render(g2);
		g2.dispose();
	};
	public void startGameThread() {
		gameThread=new Thread(this);
		gameThread.start();
	};

	@Override
	public void run() {
		double drawInterval=1000000000/fpsCap;
		int frames=0;
		while (gameThread!=null) {
			long currentTime=System.nanoTime();
			double nextDrawTime=System.nanoTime()+drawInterval;
			//System.out.println("It's running");
			update();
			repaint();
			if(++frames%480==0){
				System.gc();
			}
			try {
				double remainingTime=(nextDrawTime-System.nanoTime())/1000000;
				if(remainingTime<0) {
					remainingTime=0;
				}
				Thread.sleep((long) remainingTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Failed to sleep thread");
			}
		}
	};
	
	public static void main(String[] args) {
    	//BufferedImage screenBuffer=new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
    	//int[] screenData=((DataBufferInt)screenBuffer.getRaster().getDataBuffer()).getData();
    	//boolean running=true;
    	//int b=0;
    	player.loadImages();
    	//Level level = new Level(16);
		JFrame frame = new JFrame("Tower Game");
		TowerGame gamePanel=new TowerGame();
		gamePanel.setFocusable(true);
		frame.add(gamePanel);
		frame.pack();
		frame.setSize(640,480);
		frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	gamePanel.startGameThread();
	}
}
