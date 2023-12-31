package levelEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import levelEditor.entity.FireEnemy;
import saveFile.SaveFile;

public class LevelEditor extends JPanel implements Runnable, ActionListener {
	public static int scale=2;
	public static int tileSize=16*scale;
	Thread gameThread;
	public static JFrame frame;
	EventHandler eventHandler = new EventHandler(frame);
	protected boolean debug=false;
	double currentTime, remainingTime, finishedTime;
	Level level = new Level(16,16);
    Point mousePos;	
    static JMenuBar menuBar;
	
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
			if(eventHandler.editBackground) {
				level.renderBackground(g2);
			}else {
				level.render(g2);
			}
			int frameX = (Tile.tiles[eventHandler.tileBrush].getTextureId() % 16) * 16;
			int frameY = (Tile.tiles[eventHandler.tileBrush].getTextureId() / 16) * 16;
			if(mousePos!=null) {
				g2.drawImage(level.tilemap, mousePos.x-LevelEditor.frame.getLocation().x-(int)(LevelEditor.tileSize*0.5), mousePos.y-LevelEditor.frame.getLocation().y-menuBar.getHeight()-(int)(LevelEditor.tileSize*1.5), mousePos.x-LevelEditor.frame.getLocation().x+(int)(LevelEditor.tileSize*0.5), mousePos.y-LevelEditor.frame.getLocation().y-menuBar.getHeight()-(int)(LevelEditor.tileSize*0.5), frameX, frameY, frameX+16, frameY+16, (ImageObserver)null);
			}
		}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e.getClass()+": "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    		e.printStackTrace();
		}
		
		g2.dispose();
	};
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		String fileName;
		if(e.getActionCommand()=="Save") {
			int returnVal = fc.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				SaveFile.save(level, fc.getSelectedFile().getPath());
			}
		}
		if(e.getActionCommand()=="Load") {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				SaveFile.load(level, fc.getSelectedFile().getPath());
			}
		}
	}
	public void startGameThread() {
		gameThread=new Thread(this);
		gameThread.start();
	};

	public void run() {
		double drawInterval=1000000000/60;
		int frames=0;
    	FireEnemy test2=new FireEnemy(level,true);
    	test2.baseY=6;
    	test2.posY=6;
    	test2.posX=8;
    	level.addEntity(test2);
    	
		while (gameThread!=null) {
			currentTime=System.nanoTime();
			double nextDrawTime=System.nanoTime()+drawInterval;
			//System.out.println("It's running");
			mousePos= MouseInfo.getPointerInfo().getLocation();
			if(eventHandler.mouse1Pressed && mousePos!=null) {
				if(eventHandler.editBackground) {
					level.setTileBackground((int)(mousePos.x-LevelEditor.frame.getLocation().x)/LevelEditor.tileSize,(int)(mousePos.y-LevelEditor.frame.getLocation().y-menuBar.getHeight())/LevelEditor.tileSize-1,eventHandler.tileBrush);
				}else {
					level.setTileForeground((int)(mousePos.x-LevelEditor.frame.getLocation().x)/LevelEditor.tileSize,(int)(mousePos.y-LevelEditor.frame.getLocation().y-menuBar.getHeight())/LevelEditor.tileSize-1,eventHandler.tileBrush);
				}
				
			}
			if(eventHandler.mouse2Pressed) {
				if(eventHandler.editBackground) {
					eventHandler.tileBrush=level.getTileBackground((int)(mousePos.x-LevelEditor.frame.getLocation().x)/LevelEditor.tileSize,(int)(mousePos.y-LevelEditor.frame.getLocation().y)/LevelEditor.tileSize-1);
				}else {
					if((eventHandler.tileBrush=level.getTileForeground((int)(mousePos.x-LevelEditor.frame.getLocation().x)/LevelEditor.tileSize,(int)(mousePos.y-LevelEditor.frame.getLocation().y)/LevelEditor.tileSize-1))==0) {
						eventHandler.tileBrush=level.getTileBackground((int)(mousePos.x-LevelEditor.frame.getLocation().x)/LevelEditor.tileSize,(int)(mousePos.y-LevelEditor.frame.getLocation().y)/LevelEditor.tileSize-1);
					}
				}
				
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
	    JMenu menu, submenu;
	    JMenuItem menuItemSave, menuItemLoad;
		frame = new JFrame("Level Editor");
		LevelEditor gamePanel=new LevelEditor();
		gamePanel.setFocusable(true);
		frame.getContentPane().add(gamePanel,BorderLayout.CENTER);
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		menuItemSave=new JMenuItem("Save", KeyEvent.VK_S);
		menu.add(menuItemSave);
        menuItemSave.addActionListener(gamePanel);
		
		menuItemLoad=new JMenuItem("Load", KeyEvent.VK_L);
		menu.add(menuItemLoad);
        menuItemLoad.addActionListener(gamePanel);
		
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    	gamePanel.startGameThread();
	}
}
