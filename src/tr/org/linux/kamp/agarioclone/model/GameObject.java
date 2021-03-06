package tr.org.linux.kamp.agarioclone.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 
 * @author sevilay
 *
 * {@link GameObject} create and draw game objects.
 */

public abstract class GameObject {
	
	private int x;
	private int y;
	private int radius;
	private Color color;
	
	
	/*
	 * GameObject constuctor
	 */
	public GameObject(int x, int y, int radius, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}
	
	/*
	 * Draw objects
	 */
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.fillOval(getX(), getY(), getRadius(), getRadius());
		
	}
	
	public Rectangle getRectangle() {
		Rectangle rect = new Rectangle(getX(), getY(), getRadius(), getRadius());
		return rect;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	
	
	
	
	

}
