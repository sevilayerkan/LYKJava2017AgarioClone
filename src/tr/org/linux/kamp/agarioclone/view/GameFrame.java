package tr.org.linux.kamp.agarioclone.view;

import java.awt.Color;

/**
 * @author sevilay
 * 
 * Created game frame
 */

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	public GameFrame() {
		setTitle("Agar.io Clone");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,728);
		setLocationRelativeTo(null);
		
	}

}
