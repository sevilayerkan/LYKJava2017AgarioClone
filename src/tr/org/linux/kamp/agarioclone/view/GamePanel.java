package tr.org.linux.kamp.agarioclone.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import tr.org.linux.kamp.agarioclone.model.GameObject;

/**
 * 
 * @author sevilay
 * Created game panel, it draws game objects into the panel
 */

public class GamePanel extends JPanel{
	
	private ArrayList<GameObject> gameObjects;
	
	public GamePanel(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
		
	}
	
	@Override
	protected synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		for (GameObject gameObject : gameObjects) {
			gameObject.draw(g2d);
			
		}
	}

}
