package tr.org.linux.kamp.agarioclone.model;

import java.awt.Color;

/**
 * 
 * @author sevilay
 * Created for making enemy objects
 */

public class Enemy extends GameObject{
	
	private int speed;
	
	
	public Enemy(int x, int y, int radius,int speed, Color color) {
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
		this.speed = speed;
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


	
	

}
