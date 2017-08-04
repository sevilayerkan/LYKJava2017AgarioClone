package tr.org.linux.kamp.windowbuilder;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * @author sevilay
 * 
 * Created for user interface with help WindowBuilder
 * This interface includes color choosing for player,
 * player name choose,dificulty choose,about section and start buttons.
 */

import net.miginfocom.swing.MigLayout;
import tr.org.linux.kamp.agarioclone.logic.GameLogic;
import tr.org.linux.kamp.agarioclone.model.Difficulty;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class FirstPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	
	
	
	private ButtonGroup buttonGroup;

	/*
	 * Create the panel.
	 */
	public FirstPanel() {
		setBackground(new Color(143, 188, 143));
		setForeground(new Color(0, 0, 0));
		setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][][][][][][][]"));
		
	/*
	 * For username entry
	 */
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(0, 100, 0));
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 14));
		add(lblUsername, "cell 0 1");
		
		textField = new JTextField();
		textField.setBackground(new Color(238,238,238));
		add(textField, "cell 2 1 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setForeground(new Color(0, 100, 0));
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		add(lblPassword, "cell 0 2");
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(238,238,238));
		add(textField_1, "cell 2 2 2 1,growx");
		textField_1.setColumns(10);
		
	/*
	 * Color choose
	 */
		JLabel lblSelectColor = new JLabel("Select Color:");
		lblSelectColor.setForeground(new Color(0, 100, 0));
		lblSelectColor.setFont(new Font("Dialog", Font.BOLD, 14));
		add(lblSelectColor, "cell 0 3");
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("RED");
		comboBox.addItem("ORANGE");
		comboBox.addItem("CYAN");
		comboBox.addItem("DARK_GRAY");
		add(comboBox, "cell 2 3 2 1,growx");
		
	/*
	 * Difficulty choose
	 */
		JLabel lblDifficulty = new JLabel("Difficulty: ");
		lblDifficulty.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDifficulty.setForeground(new Color(0, 100, 0));
		add(lblDifficulty, "cell 0 4");
		
		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.setSelected(true);
		rdbtnEasy.setFont(new Font("Dialog", Font.BOLD, 14));
		rdbtnEasy.setBackground(new Color(143, 188, 143));
		rdbtnEasy.setForeground(new Color(0, 0, 139));
		add(rdbtnEasy, "cell 2 4");
		
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setForeground(new Color(0, 0, 139));
		rdbtnNormal.setFont(new Font("Dialog", Font.BOLD, 14));
		rdbtnNormal.setBackground(new Color(143, 188, 143));
		add(rdbtnNormal, "cell 2 5");
		
		JRadioButton rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.setForeground(new Color(0, 0, 139));
		rdbtnHard.setFont(new Font("Dialog", Font.BOLD, 14));
		rdbtnHard.setBackground(new Color(143, 188, 143));
		add(rdbtnHard, "cell 2 6");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnEasy);
		buttonGroup.add(rdbtnNormal);
		buttonGroup.add(rdbtnHard);
		buttonGroup.getSelection();
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color selectedColor = Color.PINK;
				switch (comboBox.getSelectedItem().toString()) {
				case "RED":
					selectedColor  = Color.red;
					break;
				case "ORANGE":
					selectedColor = Color.ORANGE;
					break;
				case "CYAN":
					selectedColor = Color.cyan;
					break;
				case "DARK_GRAY":
					selectedColor = Color.darkGray;
					break;
				default:
					break;
				}
				
				Difficulty difficulty = Difficulty.EASY;
				
				if(rdbtnEasy.isSelected()) {
					//EASY
					difficulty = Difficulty.EASY;
				}else if(rdbtnNormal.isSelected()) {
					//NORMAL
					difficulty = Difficulty.NORMAL;
				}else if(rdbtnHard.isSelected()) {
					//HARD
					difficulty = Difficulty.HARD;
				}
				
				GameLogic gameLogic = new GameLogic(textField.getText(),selectedColor,difficulty);
				gameLogic.startApplication();
			}
		});
		
		add(btnStart, "cell 2 9,alignx left");
		
		/*
		 * About section
		 */
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(FirstPanel.this, "Bu yazılım GPL lisansı altındadır, \n2017 yılında Linux Yaz Kampında ,\n"
						+ "tamamen özgür bir ortamda,\n özgür yazılımlar kullanılarak,\n özgür bireyler tarafından geliştirilmiştir.\n#LYK2017", "About", 
						JOptionPane.DEFAULT_OPTION);
				
			}
		});
		add(btnAbout, "cell 3 9,alignx left");

	}

}
