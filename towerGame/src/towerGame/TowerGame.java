package towerGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import save.EntitySerializable;
import save.SaveFile;
import towerGame.entity.Entity;
import towerGame.entity.FireEnemy;
import towerGame.map.Level;

public class TowerGame extends JPanel implements Runnable {
	public static int scale=2;
	public static int tileSize=16*scale;
	Thread gameThread;
	public static JFrame frame;
	EventHandler eventHandler = new EventHandler(frame);
	public Level level= new Level(16,16);
	public static int frames = 0;
	public static int fpsCap = 60;
	HealthBarManager hBarManager = new HealthBarManager();
	double currentTime, currentTime2, remainingTime, finishedTime;
	
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
		g2.setColor(level.skyColor);
		g2.fillRect(0, 0, 320*scale, 240*scale);
		try {
			level.render(g2);
			if(level.player!=null) {
				level.entity_lock.lock();
				try {
					hBarManager.render(g2, level.player.health, level.player.mana, level);
				} finally {
					level.entity_lock.unlock();
				}
			}
		}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e.getClass()+": "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    		e.printStackTrace();
		}
		if(eventHandler.debugPressed) {
			level.entity_lock.lock();
			for(Entity e : level.entities) {
				CollisionChecker.renderDebug(level,e,g2);
			}
			level.entity_lock.unlock();
			CollisionChecker.renderDebug(level,level.player,g2);
			g2.setColor(new Color(128,0,0,192));
			g2.drawString("TowerGame version 0.1",10,30);
			g2.drawString("H "+String.valueOf(level.sizeY-level.player.posY),10,40);
			g2.drawString("F "+String.valueOf((((finishedTime-currentTime2)/1000000000))),10,50);
			g2.drawString("F "+String.valueOf(1/((((1000000*remainingTime)+finishedTime-currentTime2))/1000000000)),10,60);
			g2.drawString("E "+String.valueOf(level.entities.size()),10,70);
			g2.drawString("M "+String.valueOf((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000)+ "M",10,80);
			g2.drawString("F "+String.valueOf(frames),10,90);
		}
		if(eventHandler.paused) {
			g2.setColor(new Color(0,0,0,127));
			g2.fillRect(0,0,320*TowerGame.scale,240*TowerGame.scale);
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
		FireEnemy test = new FireEnemy(level);
    	test.baseY=6;
    	test.posX=6;
    	level.addEntity(test);
    	FireEnemy test2=new FireEnemy(level,true);
    	test2.setPosition(8,6);
    	level.addEntity(EntitySerializable.deserialize(level, EntitySerializable.serialize(test2)));
		Player player = new Player(level);
    	level.setPlayer(player);
    	update();
    	SaveFile.load(level, "level1.dat");
    	
		while (gameThread!=null) {
			currentTime=System.nanoTime();
			double nextDrawTime=System.nanoTime()+drawInterval;
			if(!eventHandler.paused) {
				update();
				frames++;
			}
			repaint();
			if(level.player.health<=0.0F) {
		    	SaveFile.load(level, "level1.dat");
		    	hBarManager.refreshBar();
		    	hBarManager.render(null, level.player.health, level.player.mana, level);
			}
			if((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) > 100000000) {
				System.gc();
			}
			if(eventHandler.mouse1Clicked) {
				eventHandler.mouse1Clicked=false;
			}
			if(eventHandler.mouse2Clicked) {
				eventHandler.mouse2Clicked=false;
			}
			currentTime2=currentTime;
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
