package levelEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import java.io.IOException;

import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LevelEditor extends JPanel implements Runnable {
	public static int scale=2;
	public static int tileSize=16*scale;
	Thread gameThread;
	public static JFrame frame;
	EventHandler eventHandler = new EventHandler(frame);
	protected boolean debug=false;
	double currentTime, remainingTime, finishedTime;
	Level level = new Level(16,16);
    Point mousePos;	
	
	public LevelEditor() {
		this.addKeyListener(eventHandler);
		this.addMouseListener(eventHandler);
		this.setPreferredSize(new Dimension(320*scale,240*scale));
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(new Color(98,204,249));
		g2.fillRect(0, 0, 320*scale, 240*scale);
		//g2.setColor(Color.red);
		//g2.fillRect(player.posX-1, player.posY-1, 34, 34);
		try {
			level.render(g2);
			int frameX = (Tile.tiles[eventHandler.tileBrush].getTextureId() % 16) * 16;
			int frameY = (Tile.tiles[eventHandler.tileBrush].getTextureId() / 16) * 16;
			g2.drawImage(level.tilemap, mousePos.x-LevelEditor.frame.getLocation().x-(int)(LevelEditor.tileSize*0.5), mousePos.y-LevelEditor.frame.getLocation().y-(int)(LevelEditor.tileSize*1.5), mousePos.x-LevelEditor.frame.getLocation().x+(int)(LevelEditor.tileSize*0.5), mousePos.y-LevelEditor.frame.getLocation().y-(int)(LevelEditor.tileSize*0.5), frameX, frameY, frameX+16, frameY+16, (ImageObserver)null);
		}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e.getClass()+": "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		g2.dispose();
	};
	public void startGameThread() {
		gameThread=new Thread(this);
		gameThread.start();
	};

	@Override
	public void run() {
		double drawInterval=1000000000/60;
		int frames=0;
    	FireEnemy test2=new FireEnemy(level,true);
    	test2.baseY=6;
    	test2.posY=6;
    	test2.posX=8;
    	test2.loadImages();
    	level.addEntity(test2);
    	
		while (gameThread!=null) {
			currentTime=System.nanoTime();
			double nextDrawTime=System.nanoTime()+drawInterval;
			//System.out.println("It's running");
			mousePos= MouseInfo.getPointerInfo().getLocation();
			if(eventHandler.mouse1Pressed) {
				level.setTileForeground((int)(mousePos.x-LevelEditor.frame.getLocation().x)/LevelEditor.tileSize,(int)(mousePos.y-LevelEditor.frame.getLocation().y)/LevelEditor.tileSize-1,eventHandler.tileBrush);
			}
			repaint();
			if(++frames%480==0){
				System.gc();
			}
			try {
				finishedTime=System.nanoTime();
				remainingTime=(nextDrawTime-System.nanoTime())/1000000;
				if(remainingTime<0) {
					remainingTime=0;
				}
				Thread.sleep((long) remainingTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
	    		JOptionPane.showMessageDialog(null, "Error: Failed to sleep thread", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	
	public static void main(String[] args) {
		frame = new JFrame("Level Editor");
		JFrame frame2 = new JFrame("Tiles");
		frame2.getContentPane().add(new JPanel(),BorderLayout.CENTER);
		frame2.pack();
		frame2.setVisible(true);
		LevelEditor gamePanel=new LevelEditor();
		gamePanel.setFocusable(true);
		frame.getContentPane().add(gamePanel,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	gamePanel.startGameThread();
	}
}
