package test1;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputListener;

public class Logic extends JPanel implements ActionListener, KeyListener, MouseInputListener{

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
		
		//Paint current grid
		repaint();
	}
	
	JMenuBar bar;
	HighScores hs;
	


	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
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
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("View High Scores")){
			if(!new File("highscores.txt").exists()) {
				try {
					hs.generateFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
