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

import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import towerGame.map.Level;
import towerGame.npc.FireEnemy;
public class TowerGame extends JPanel implements Runnable {
	public static int scale=2;
	public static int tileSize=16*scale;
	Thread gameThread;
	public static JFrame frame;
	EventHandler eventHandler = new EventHandler(frame);
	public Level level= new Level(16,16);
	public FireEnemy test = new FireEnemy(level);
	int fpsCap = 60;
	protected boolean debug=false;
	double currentTime, remainingTime, finishedTime;
	
	public TowerGame() {
		this.addKeyListener(eventHandler);
		this.addMouseListener(eventHandler);
		this.setPreferredSize(new Dimension(320*scale,240*scale));
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);
	}
	public void update() {
		try {
			level.update(eventHandler);
		}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e.getClass()+": "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    		e.printStackTrace();
		}
	};
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(new Color(98,204,249));
		g2.fillRect(0, 0, 320*scale, 240*scale);
		try {
			level.render(g2);
		}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e.getClass()+": "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		if(eventHandler.debugPressed) {
			level.cc.renderDebug(level,level.player,g2);
			g2.drawString("H "+String.valueOf(level.sizeY-level.player.posY),10,20);
			g2.drawString("F "+String.valueOf((int)(1/((finishedTime-currentTime)/1000000000))),10,30);
			g2.drawString("F "+String.valueOf(1/((((1000000*remainingTime)+finishedTime-currentTime))/1000000000)),10,40);
			g2.drawString("E "+String.valueOf(level.entities.size()),10,50);
			g2.drawString("H "+String.valueOf(level.player.health),10,60);
			g2.drawString("M "+String.valueOf(level.player.mana),10,70);
		}
		
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
    	test.baseY=6;
    	test.posX=6;
    	level.addEntity(test);
    	FireEnemy test2=new FireEnemy(level,true);
    	test2.baseY=6;
    	test2.posX=8;
    	level.addEntity(test2);
		Player player = new Player(level);
    	player.loadImages();
    	level.setPlayer(player);
    	
		while (gameThread!=null) {
			currentTime=System.nanoTime();
			double nextDrawTime=System.nanoTime()+drawInterval;
			//System.out.println("It's running");
			update();
			repaint();
			if(eventHandler.mouse1Clicked) {
				eventHandler.mouse1Pressed=false;
				eventHandler.mouse1Clicked=false;
			}
			if(eventHandler.mouse2Clicked) {
				eventHandler.mouse2Pressed=false;
				eventHandler.mouse2Clicked=false;
			}
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
		frame = new JFrame("Tower Game");
		TowerGame gamePanel=new TowerGame();
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
