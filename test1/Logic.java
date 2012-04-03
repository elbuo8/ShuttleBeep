package test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

public class Logic extends JPanel implements ActionListener, MouseInputListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1144062643362290194L;

	public Logic() {
		
		//Initialize menu.
		bar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		bar.add(menu);
		JMenuItem newgame = new JMenuItem("New Game");
		menu.add(newgame);
		newgame.addActionListener(this);
		JMenuItem opengame = new JMenuItem("Open Saved Game");
		menu.add(opengame);
		opengame.addActionListener(this);
		menu.addSeparator();
		JMenuItem highscores = new JMenuItem("View High Scores");
		menu.add(highscores);
		highscores.addActionListener(this);

		//Initialize high scores
		hs = new HighScores();

		//Text field setup
		inputField = new JTextField();
		inputField.setText("Enter");
		inputField.addActionListener(this);

	}

	JMenuBar bar;
	JTextField inputField;
	private HighScores hs;
	private int framex = 600;
	private int framey = 300;
	private int areax = 20; // modificar cuando cesar haga new
	private int areay = 10; // modificar cuando cesar haga new


	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		drawBorders(g2);

	}

	public void drawBorders(Graphics2D g2) {
		//Borders of game
		Color borde = Color.BLACK;
		Color fill = Color.GRAY;
		g2.setColor(fill);
		Rectangle background = new Rectangle(0, 0, framex+framex/areax, 15);
		g2.fill(background);

		g2.setColor(borde);
		Rectangle corner = new Rectangle(0, 0, framex/areax, 15);
		g2.draw(corner);

		char letter = 'A';
		for (int i = 1; i <= areax; i++) {
			corner = new Rectangle(i*(framex/areax), 0, framex/areax, 15);
			g2.draw(corner);
			g2.drawString(letter + "", (int)corner.getCenterX()-3, (int)(corner.getCenterY()+4 ));
			letter += 1;
		}

		background = new Rectangle(0, 15, framex/areax, framey);
		g2.setColor(fill);
		g2.fill(background);

		for (int i = 1; i <= areay; i++) {
			g2.setColor(borde);
			corner = new Rectangle(0, i*(framey/areay)-15, framex/areax, framey/areay);
			g2.draw(corner);
			g2.drawString(Integer.toString(i), (int)corner.getCenterX()-4, (int)corner.getCenterY()+4);
		}
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("View High Scores")) {
			if(!new File("highscores.txt").exists()) {
				try {
					hs.generateFile();
				} catch (IOException e1) {}
			
			}
			JFrame highscores = new JFrame();
			highscores.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			highscores.setTitle("High Scores");
			JTextArea scores = new JTextArea(hs.getString());
			highscores.add(scores);
			highscores.setSize(400, 400);
			highscores.setVisible(true);
		}

	}



}
