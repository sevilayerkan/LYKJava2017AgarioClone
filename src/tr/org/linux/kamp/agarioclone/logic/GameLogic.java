package tr.org.linux.kamp.agarioclone.logic;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import tr.org.linux.kamp.agarioclone.model.Chip;
import tr.org.linux.kamp.agarioclone.model.Difficulty;
import tr.org.linux.kamp.agarioclone.model.Enemy;
import tr.org.linux.kamp.agarioclone.model.GameObject;
import tr.org.linux.kamp.agarioclone.model.Mine;
import tr.org.linux.kamp.agarioclone.model.Player;
import tr.org.linux.kamp.agarioclone.view.GameFrame;
import tr.org.linux.kamp.agarioclone.view.GamePanel;

/**
 * 
 * @author sevilay
 * It controls all game play.Includes how player and enemy
 * eats chips,each other,eaten by mines	.
 *
 */

public class GameLogic {
	
	private boolean isGameRunning;
	private int xTarget;
	private int yTarget;
	
	private Player player;
	private Enemy enemy;
	private ArrayList<GameObject> gameObjects;
	private ArrayList<GameObject> chipsToRemove;
	private ArrayList<GameObject> minesToRemove;
	private ArrayList<GameObject> enemiesToRemove;
	
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	
	private Random random;
	
	
	
	public GameLogic(String playerName, Color selectedColor,Difficulty difficulty) {
		player = new Player(10, 10, 50,1, selectedColor , playerName);
		gameObjects = new ArrayList<GameObject>();
		chipsToRemove = new ArrayList<GameObject>();
		minesToRemove = new ArrayList<GameObject>();
		enemiesToRemove = new ArrayList<GameObject>();
	
		
		gameFrame = new GameFrame();
		gamePanel = new GamePanel(gameObjects);
		
		gameObjects.add(player);
		
		
		
		random = new Random();
		
		/*
		 * For difficulty choose
		 */
		
		switch (difficulty) {
		case EASY:
			fillChips(50);
			fillMines(7);
			fillEnemies(4);
			break;
		case NORMAL:
			fillChips(30);
			fillMines(10);
			fillEnemies(7);
			break;
		case HARD:
			fillChips(20);
			fillMines(12);
			fillEnemies(5);
			break;

		default:
			break;
		}
		
		
		
		addMouseEvents();
	}
	
	/*
	 * Decides what happen when players collide
	 */
	
	private synchronized void checkCollisions() {
		
		for (GameObject gameObject : gameObjects) {
			if(player.getRectangle().intersects(gameObject.getRectangle())){
				if(gameObject instanceof Chip) {
					player.setRadius(player.getRadius() + gameObject.getRadius());
					//gameObjects.remove(gameObject);
					chipsToRemove.add(gameObject);
				}
				if(gameObject instanceof Mine) {
					player.setRadius((int)player.getRadius() / 2); 
					minesToRemove.add(gameObject);
				}
				if(gameObject instanceof Enemy) {
					if(player.getRadius() > gameObject.getRadius()) {
						player.setRadius(player.getRadius() + gameObject.getRadius());
						enemiesToRemove.add(gameObject);
						
					}else if(player.getRadius() < gameObject.getRadius()) {
						gameObject.setRadius(gameObject.getRadius() + player.getRadius());
						//Game Over!!!
						isGameRunning = false;
					}
				}
				
			}
			
			if(gameObject instanceof Enemy) {
				Enemy enemy = (Enemy) gameObject;
				for (GameObject gameObject2 : gameObjects) {
					if(enemy.getRectangle().intersects(gameObject2.getRectangle())) {
						if(gameObject2 instanceof Chip) {
							enemy.setRadius(enemy.getRadius() + gameObject2.getRadius());
							chipsToRemove.add(gameObject2);
						}
						if(gameObject2 instanceof Mine) {
							enemy.setRadius((int) enemy.getRadius()/2);
							minesToRemove.add(gameObject2);
						}
					}
				}
			}
		}
		
		//loop is complete, remove objects from list
		gameObjects.removeAll(chipsToRemove);
		gameObjects.removeAll(minesToRemove);
		gameObjects.removeAll(enemiesToRemove);
		
		
	}
	
	/*
	 * Creates new objects
	 */
	
	private synchronized void addNewObjects() {
		fillChips(chipsToRemove.size());
		fillMines(minesToRemove.size());
		fillEnemies(enemiesToRemove.size());
		
		chipsToRemove.clear();
		minesToRemove.clear();
		enemiesToRemove.clear();
	}
	
	/*
	 * Moves player and enemy
	 */
	
	private synchronized void movePlayer() {
		if(xTarget > player.getX()) {
			player.setX(player.getX() + player.getSpeed());
		}else if(xTarget < player.getX()) {
			player.setX(player.getX() - player.getSpeed());
		}
		
		if(yTarget > player.getY()) {
			player.setY(player.getY() + player.getSpeed());
		}else if(yTarget < player.getY()) {
			player.setY(player.getY() - player.getSpeed());
		}
		
	}
	
	private synchronized void moveEnemies() {
		for (GameObject gameObject : gameObjects) {
			if(gameObject instanceof Enemy) {{
				Enemy enemy = (Enemy) gameObject;
				if(enemy.getRadius() < player.getRadius()) {
					//Kaçacak
					int distance =(int) Point.distance(player.getX(), player.getY(),
							enemy.getX(), enemy.getY());
					
					int newX = enemy.getX() + enemy.getSpeed();
					int newY = enemy.getY() + enemy.getSpeed();
					if(calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;
					}
					
					newX = enemy.getX() + enemy.getSpeed();
					newY = enemy.getY() - enemy.getSpeed();
					if(calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;
					}
					
					newX = enemy.getX() - enemy.getSpeed();
					newY = enemy.getY() + enemy.getSpeed();
					if(calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;
					}
					
					newX = enemy.getX() - enemy.getSpeed();
					newY = enemy.getY() - enemy.getSpeed();
					if(calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;
					}
					
					
				}else {
					//yiyecek
					if(player.getX() > enemy.getX()) {
						enemy.setX(enemy.getX() + enemy.getSpeed());
					}else if(player.getX() < enemy.getX()) {
						enemy.setX(enemy.getX() - enemy.getSpeed());
					}
					
					if(player.getY() > enemy.getY()) {
						enemy.setY(enemy.getY() + enemy.getSpeed());
					}else if(player.getY() < enemy.getY()) {
						enemy.setY(enemy.getY() - enemy.getSpeed());
					}
					
				}
			}
		}
		}
	}
	
	/*
	 * Simple artificial intelligence algoritm for enemy chasing player
	 */
	
	private boolean calculateNewDistanceToEnemy(Enemy enemy, int distance,  int x , int y) {
		int newDistance = (int) Point.distance(player.getX(), player.getY(), x, y);
		if(newDistance > distance) {
			enemy.setX(x);
			enemy.setY(y);
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Fills chips
	 */
	
	private void fillChips(int n) {
		for(int i = 0; i < n ; i++) {
			gameObjects.add(new Chip(random.nextInt(1024),
					random.nextInt(728),10,Color.green));
		}
		
	}
	
	/*private void checkRadius() {
		if(player.getRadius() >= 350) {
			player.setRadius(player.getRadius() - 2);
		}else if(player.getRadius() < 5) {
			player.setRadius(player.getRadius() + 1);
		}
		
	}*/
	
	/*
	 * Fill mines
	 */
	
	private void fillMines(int n) {
		for(int i = 0; i < n ; i++) {
			Mine mine = new Mine(random.nextInt(1024), random.nextInt(728), 20, Color.blue);
			while(player.getRectangle().intersects(mine.getRectangle())) {
				mine.setX(random.nextInt(1024));
				mine.setY(random.nextInt(728));
			}
			
			gameObjects.add(mine);
		}
		
	}
	/*
	 * Fill enemies
	 */
	
	private void fillEnemies(int n) {
		for(int i = 0; i < n ; i++) {
			
			Enemy enemy = new Enemy(random.nextInt(1024) ,random.nextInt(728) ,
					(random.nextInt(10)+ 20),1,Color.black);
			while(player.getRectangle().intersects(enemy.getRectangle())) {
				enemy.setX(random.nextInt(1024));
				enemy.setY(random.nextInt(728));
			}
			gameObjects.add(enemy);
		}
	}
	
	/*
	 * Starts game
	 */
	
	private void startGame() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(isGameRunning) {
					movePlayer();
					moveEnemies();
					checkCollisions();
					addNewObjects();
					//checkRadius();
					gamePanel.repaint();
					try {
						Thread.sleep(24);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}).start();
	}
	
	/*
	 * Start whole application
	 */
	
	public void startApplication() {
		gameFrame.setContentPane(gamePanel);
		gameFrame.setVisible(true);
		isGameRunning = true;
		startGame();
	}
	
	/*
	 * MouseListeners for control player with mouse
	 */
	
	private void addMouseEvents() {
		gameFrame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//Mouse pencerenin dışına çıktığında
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//Mouse pencerenin içine girdiğinde
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//Double click ++
				//Tıklandığında
				
			}
		});
		
		gameFrame.addMouseMotionListener(new MouseMotionListener() {
			
			//Mouse hareket dinleyicisi
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				xTarget = e.getX();
				yTarget = e.getY();
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				//Sürükleme
				
			}
		});
	}

}
