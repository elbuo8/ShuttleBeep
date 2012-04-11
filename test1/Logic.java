package test1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * 
 * @author yamilasusta
 *
 */
public class Logic extends JPanel implements ActionListener, MouseListener{

	/**
	 * Mandatory modification by eclipse
	 */
	private static final long serialVersionUID = -1144062643362290194L;

	// Instance variables
	public JMenuBar bar;
	public JTextField inputField;
	public JButton tryButton;
	public JPanel southJPanel;
	private HighScores hs;
	private TheGrid grid1;
	private TheGrid grid2;
	private Status status;
	private static final int FRAMEX = 600;
	private static final int FRAMEY = 300;
	private BufferedImage hits2;
	private BufferedImage hits3;
	private BufferedImage hits4;
	private BufferedImage hits5;
	private BufferedImage hits6;
	private BufferedImage hits7;
	private BufferedImage hits8;
	private BufferedImage hits9;
	private BufferedImage hits10;
	private int areax = 20; // modificar cuando cesar haga new
	private int areay = 10; // modificar cuando cesar haga new
	//private String player1; // mod cesar new
	//private String player2; // mod cesar new/default roboto
	//private int ships; // mod cesar new
	
	/**
	 * Default constructor
	 * Generates the menus, the grid, and the input options
	 */
	public Logic() {

		//Initialize menu
		bar = new JMenuBar();
		bar.setBackground(Color.DARK_GRAY);
		JMenu menu = new JMenu("Menu");
		menu.setBackground(Color.DARK_GRAY);
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
		inputField = new JTextField(30);
		inputField.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				String input = inputField.getText();
				if(verifyInput(input)) {
					inputField.setText("");
				}
			}
		});

		//JButton setup
		tryButton = new JButton("Try");
		tryButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				String input = inputField.getText();
				if (verifyInput(input)) {
					inputField.setText("");
				}
			}
		});

		//Input panel setup
		southJPanel = new JPanel();
		southJPanel.add(inputField);
		southJPanel.add(tryButton);
		
		//TheGrid preparation
		grid1 = new TheGrid();
		grid2 = new TheGrid();
		
		//Initialize mouse listener
		addMouseListener(this);
		
		//Initialize images
		try {
			hits2 = ImageIO.read(getClass().getResource("2hits.png"));
			hits3 = ImageIO.read(getClass().getResource("3hits.png"));
			hits4 = ImageIO.read(getClass().getResource("4hits.png"));
			hits5 = ImageIO.read(getClass().getResource("5hits.png"));
			hits6 = ImageIO.read(getClass().getResource("6hits.png"));
			hits7 = ImageIO.read(getClass().getResource("7hits.png"));
			hits8 = ImageIO.read(getClass().getResource("8hits.png"));
			hits9 = ImageIO.read(getClass().getResource("9hits.png"));
			hits10 = ImageIO.read(getClass().getResource("10hits.png"));
		} catch (IOException e1) {
			System.out.println("Images were not found.");
		}

	}

	/**
	 * Modification of the paintComponent to draw in the JPanel
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		drawBorders(g2);
		drawGrid(g2);
		drawBottom(g2);
	}
	
	/**
	 * Draws the actual playing field
	 * @param g2 Graphical painter
	 */
	public void drawGrid(Graphics2D g2) {
		Color background = Color.white;
		g2.setColor(background);
		Rectangle gridRectangle = new Rectangle(31, 16, 601-((int)FRAMEX/FRAMEY), 301-((int)FRAMEY/FRAMEY));
		g2.fill(gridRectangle);
		
		for (int i = 0; i < grid1.theGrid.length; i++) {
			for (int j = 0; j < grid1.theGrid[0].length; j++) {
				g2.setColor(Color.BLACK);
				g2.draw(grid1.theGrid[i][j].getRect());
				
				if(grid1.theGrid[i][j].isHit() && !grid1.theGrid[i][j].hasAship())
					g2.drawString("X", (int)grid1.theGrid[i][j].getRect().getCenterX() -3 , (int)grid1.theGrid[i][j].getRect().getCenterY()+3);
				else if(grid1.theGrid[i][j].isHit() && grid1.theGrid[i][j].hasAship()) {
					g2.setColor(Color.RED);
					g2.drawString("X", (int)grid1.theGrid[i][j].getRect().getCenterX() -3, (int)grid1.theGrid[i][j].getRect().getCenterY()+3);					
				}
			}
		}
		
		for (int i = 0; i < grid2.theGrid.length; i++) {
			for (int j = 0; j < grid2.theGrid[0].length; j++) {
				g2.setColor(Color.BLACK);
				g2.draw(grid2.theGrid[i][j].getRect());
				
				if(grid2.theGrid[i][j].isHit() && !grid2.theGrid[i][j].hasAship())
					g2.drawString("X", (int)grid2.theGrid[i][j].getRect().getCenterX()-3, (int)grid2.theGrid[i][j].getRect().getCenterY()+3);
				else if(grid2.theGrid[i][j].isHit() && grid2.theGrid[i][j].hasAship()) {
					g2.setColor(Color.RED);
					g2.drawString("X", (int)grid2.theGrid[i][j].getRect().getCenterX()-3, (int)grid2.theGrid[i][j].getRect().getCenterY()+3);
				}
			}
		}
		g2.setColor(Color.RED);
		g2.drawLine(FRAMEX/2+FRAMEX/areax, 0, FRAMEX/2+FRAMEX/areax, FRAMEY+15);

	}

	/**
	 * Draws the margin borders and the legend
	 * @param g2 Graphical painter
	 */
	public void drawBorders(Graphics2D g2) {
		//Borders of game
		Color borde = Color.BLACK;
		Color fill = Color.GRAY;
		g2.setColor(fill);
		Rectangle background = new Rectangle(0, 0, FRAMEX+FRAMEX/areax, 15);
		g2.fill(background);

		g2.setColor(borde);
		Rectangle corner = new Rectangle(0, 0, FRAMEX/areax, 15);
		g2.draw(corner);

		char letter = 'A';
		for (int i = 1; i <= areax; i++) {
			corner = new Rectangle(i*(FRAMEX/areax), 0, FRAMEX/areax, 15);
			g2.draw(corner);
			g2.drawString(letter + "", (int)corner.getCenterX()-3, (int)(corner.getCenterY()+5 ));
			letter += 1;
		}

		background = new Rectangle(0, 15, FRAMEX/areax, FRAMEY);
		g2.setColor(fill);
		g2.fill(background);

		for (int i = 1; i <= areay; i++) {
			g2.setColor(borde);
			corner = new Rectangle(0, i*(FRAMEY/areay)-15, FRAMEX/areax, FRAMEY/areay);
			g2.draw(corner);
			g2.drawString(Integer.toString(i), (int)corner.getCenterX()-4, (int)corner.getCenterY()+4);
		}
	}
	
	public void drawBottom(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		Font font = new Font("sansserif", Font.BOLD, 24);
		g2.setFont(font);
		g2.drawString("Yamil", 150, 340);
		g2.drawString("Cesar", 450, 340);
		
		//Player 2
		if (true) 
			g2.drawImage(hits10, null, 0, 330);
		if (true) 
			g2.drawImage(hits9, null, 0, 380);
		if (true) 
			g2.drawImage(hits8, null, 0, 430);
		if (true) 
			g2.drawImage(hits7, null, 0, 470);
		if (true) 
			g2.drawImage(hits2, null, 200, 370);
		if (true) 
			g2.drawImage(hits3, null, 190, 385);
		if (true) 
			g2.drawImage(hits4, null, 190, 420);
		if (true) 
			g2.drawImage(hits5, null, 175, 450);
		if (true) 
			g2.drawImage(hits6, null, 175, 480);
		
		//player1
		if (true) 
			g2.drawImage(hits10, null, 330, 330);
		if (true) 
			g2.drawImage(hits9, null, 330, 380);
		if (true) 
			g2.drawImage(hits8, null, 330, 430);
		if (true) 
			g2.drawImage(hits7, null, 330, 470);
		if (true) 
			g2.drawImage(hits2, null, 550, 370);
		if (true) 
			g2.drawImage(hits3, null, 520, 385);
		if (true) 
			g2.drawImage(hits4, null, 520, 420);
		if (true) 
			g2.drawImage(hits5, null, 505, 450);
		if (true) 
			g2.drawImage(hits6, null, 505, 480);
		
		
		
		
	}


	public void mouseClicked(MouseEvent e) {
		
		for (int i = 0; i < grid1.theGrid.length; i++) {
			for (int j = 0; j < grid1.theGrid[0].length; j++) {
				if(grid1.theGrid[i][j].getRect().contains(e.getPoint())) {
					grid1.theGrid[i][j].hit();
					repaint();
				}
			}
		}
		
		for (int i = 0; i < grid2.theGrid.length; i++) {
			for (int j = 0; j < grid2.theGrid[0].length; j++) {
				if(grid2.theGrid[i][j].getRect().contains(e.getPoint())) {
					grid2.theGrid[i][j].hit();
					repaint();
				}
			}
		}
		
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	/**
	 * Waits for the input in the menu bar
	 */
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

		if (e.getActionCommand().equals("New Game")) {

		}

		if (e.getActionCommand().equals("Open Saved Game")) {

		}

	}

	/**
	 * Verifies that the input is a valid one
	 * @param input Input from the user
	 * @return If the input is valid
	 */
	static boolean verifyInput(String input) {
		input = input.toUpperCase();
		char[] parse = input.toCharArray();
		if(!Character.isLetter(parse[0]))
			return false;
		for (int i = 1; i < parse.length; i++) 
			if (!Character.isDigit(parse[i])) 
				return false;
		return true;

	}


}
