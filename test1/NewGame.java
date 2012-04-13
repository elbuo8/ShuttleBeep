package test1;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author cesarcruz
 *
 */
public class NewGame extends JFrame implements ActionListener, KeyListener, MouseListener{

	JPanel panel = new JPanel();
	JTextField text1, text2, text3;
	CheckboxGroup gameType;
	Checkbox checkbox1, checkbox2;
	JButton a;
	String numberOfRows, numberOfColumns, numberOfPlayers;
	JLabel typeTitle, rowsTitle, columnsTitle, playersTitle;

	public NewGame(){

		setTitle("New Game");
		setSize(500, 300);
		setLocation(450, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);

		panel.setLayout(new GridLayout(5,2));
		
		typeTitle = new JLabel("Type of Game:");
		typeTitle.setLocation(20, 20);
		panel.add(typeTitle);
		
		rowsTitle = new JLabel("Rows:");
		panel.add(rowsTitle);
		
		gameType = new CheckboxGroup();
		checkbox1 = new Checkbox("Player vs. Player", gameType, false);
		panel.add(checkbox1);
		
		text1 = new JTextField();
		text1.setSize(100, 20);
		panel.add(text1);
		
		checkbox2 = new Checkbox("Player vs. Rofongo", gameType, false);
		panel.add(checkbox2);
		
		columnsTitle = new JLabel("Columns: ");
		panel.add(columnsTitle);
		
		playersTitle = new JLabel("Players");
		panel.add(playersTitle);
		
		
		text2 = new JTextField();
		panel.add(text2);
		
		
		text3 = new JTextField();
		panel.add(text3);

		add(panel);
		setVisible(true);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		numberOfPlayers = text1.getText();
		numberOfRows = text2.getText();
		numberOfColumns = text3.getText();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args){
		NewGame game = new NewGame();

	}


}
