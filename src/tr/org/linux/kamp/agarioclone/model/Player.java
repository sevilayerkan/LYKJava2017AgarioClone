package tr.org.linux.kamp.agarioclone.model;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author sevilay
 * Created player object
 */

public class Player extends GameObject{
	
	private int speed;
	private String playerName;
	private BufferedImage image;

	public Player(int x, int y, int radius,int speed, Color color,String playerName) {
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
		this.speed = speed;
		this.playerName = playerName;
		
	}
	
	/*
	 * 
	 * @see tr.org.linux.kamp.agarioclone.model.GameObject#setRadius(int)
	 * Prevents enemys radius increase unplayable number.
	 */
	@Override
	public void setRadius(int radius) {
		// TODO Auto-generated method stub
		super.setRadius(radius);
		if(getRadius() >= 350) {
			setRadius(getRadius() - 2);
		}else if(getRadius() < 5) {
			setRadius(getRadius() + 1);
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/*
	 * 
	 * @see tr.org.linux.kamp.agarioclone.model.GameObject#draw(java.awt.Graphics2D)
	 * Draws player object and choosen player name.
	 */
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		super.draw(g2d);
		
		FontMetrics fontMetrics = g2d.getFontMetrics(g2d.getFont());
		int width = fontMetrics.stringWidth(playerName);
		int nameX = getX() + (getRadius() - width) / 2;
		int nameY = getY() - fontMetrics.getHeight();
		g2d.drawString(playerName, nameX, nameY);
		
	}
	
	

	
}
