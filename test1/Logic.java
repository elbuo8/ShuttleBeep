package test1;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 * 
 * @author yamilasusta
 *
 */
public class Logic extends JPanel implements ActionListener, MouseListener, WindowListener, KeyListener{

	/**
	 * Mandatory modification by eclipse
	 */
	private static final long serialVersionUID = -1144062643362290194L;

	// Instance variables
	public JMenuBar bar;
	public JTextField inputField;
	public JButton tryButton;
	public JPanel southJPanel;
	private Database db;
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
	private int areax;
	private int areay;
	private String player1;
	private String player2;
	private int ships; 
	private NewGame game;
	private RandomBoat randomboats1, randomboats2;
	private boolean reset;
	private AudioClip hit;
	private AudioClip miss;
	private boolean placement;
	private boolean diagonal;
	private boolean marked;

	/**
	 * Default constructor
	 * Generates the menus, the grid, and the input options
	 */
	public Logic() {

		//Initialize audio
		java.net.URL urlClick = getClass().getResource("hit.wav");
		hit = Applet.newAudioClip(urlClick);
		urlClick = getClass().getResource("miss.wav");
		miss = Applet.newAudioClip(urlClick);

		areax = 20; //default value
		areay = 10; //default value

		//Initialize menu
		bar = new JMenuBar();
		bar.setBackground(Color.DARK_GRAY);
		bar.setBorder(getBorder());
		JMenu menu = new JMenu("Menu");
		menu.setBackground(Color.DARK_GRAY);
		bar.add(menu);
		JMenuItem newgame = new JMenuItem("New Game");
		menu.add(newgame);
		newgame.addActionListener(this);
		JMenuItem savegame = new JMenuItem("Save Game");
		menu.add(savegame);
		savegame.addActionListener(this);
		JMenuItem opengame = new JMenuItem("Open Saved Game");
		menu.add(opengame);
		opengame.addActionListener(this);
		menu.addSeparator();
		JMenuItem highscores = new JMenuItem("View High Scores");
		menu.add(highscores);
		highscores.addActionListener(this);

		//Initialize high scores
		db = new Database();

		//Text field setup
		inputField = new JTextField(30);
		inputField.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if(grid1 != null || grid2 != null) {
					String input = inputField.getText();
					if(verifyInput(input)) {
						inputField.setText("");
						int y = Character.getNumericValue(input.charAt(0))-10;
						int x = Integer.parseInt(input.substring(1)) - 1;
						if(status.getStatus().equals(player1) && !grid1.theGrid[y][x].isHit()) {
							grid1.theGrid[y][x].hit();
							if(grid1.theGrid[y][x].hasAship())
								hit.play();
							else 
								miss.play();
							status.log(input, grid1.theGrid[y][x].hasAship());
							status.switchStatus();
							status.incrementP1();
							if(marked && !grid1.theGrid[y][x].hasAship())
								grid1.theGrid[y][x].unhit();
							repaint();
						}
						else if (status.getStatus().equals(player2) && !grid2.theGrid[y][x].isHit()) {
							grid2.theGrid[y][x].hit();
							if(grid2.theGrid[y][x].hasAship())
								hit.play();
							else 
								miss.play();
							status.log(input, grid2.theGrid[y][x].hasAship());
							status.switchStatus();
							status.incrementP2();
							if(marked && !grid2.theGrid[y][x].hasAship())
								grid2.theGrid[y][x].unhit();
							repaint();
						}
					}
				}
			}

		});

		//JButton setup
		tryButton = new JButton("Try");
		tryButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if(grid1 != null || grid2 != null) {
					String input = inputField.getText();
					if (verifyInput(input)) {
						inputField.setText("");
						int y = Character.getNumericValue(input.charAt(0));
						int x = Integer.parseInt(input.substring(1));
						if(status.getStatus().equals(player1) && !grid1.theGrid[y][x].isHit()) {
							grid1.theGrid[y][x].hit();
							if(grid1.theGrid[y][x].hasAship())
								hit.play();
							else 
								miss.play();
							status.log(input, grid1.theGrid[y][x].hasAship());
							status.switchStatus();
							status.incrementP1();
							if(marked && !grid1.theGrid[y][x].hasAship())
								grid1.theGrid[y][x].unhit();
							repaint();
						}
						else if (status.getStatus().equals(player2) && !grid2.theGrid[y][x].isHit()) {
							grid2.theGrid[y][x].hit();
							if(grid2.theGrid[y][x].hasAship())
								hit.play();
							else 
								miss.play();
							status.log(input, grid2.theGrid[y][x].hasAship());
							status.switchStatus();
							status.incrementP2();
							if(marked && !grid2.theGrid[y][x].hasAship())
								grid2.theGrid[y][x].unhit();
							repaint();
						}
					}
				}
			}
		});

		//Input panel setup
		southJPanel = new JPanel();
		southJPanel.add(inputField);
		southJPanel.add(tryButton);

		//Grid preparation
		grid1 = null;
		grid2 = null;
		status = null;
		reset = false;

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
		if(reset)
			purge(g2);
		drawBorders(g2);
		if(grid1 != null && grid2 != null) {
			drawBottom(g2);
			drawGrid(g2);
		}
	}

	public void purge(Graphics2D g2) {
		g2.setColor(this.getBackground());
		g2.fillRect(0, 0, 631, 600);
		reset = false;
	}

	/**
	 * Draws the actual playing field
	 * @param g2 Graphical painter
	 */
	public void drawGrid(Graphics2D g2) {
		Font font = new Font("sansserif", Font.BOLD, 9);
		g2.setFont(font);
		for (int i = 0; i < grid1.theGrid.length; i++) {
			for (int j = 0; j < grid1.theGrid[0].length; j++) {
				g2.setColor(Color.BLACK);
				g2.draw(grid1.theGrid[i][j].getRect());
				if(grid1.theGrid[i][j].isHit() && !grid1.theGrid[i][j].hasAship()) {
					g2.setColor(Color.BLACK);
					g2.drawString("X", (int)grid1.theGrid[i][j].getRect().getCenterX() -3 , (int)grid1.theGrid[i][j].getRect().getCenterY()+3);
				}
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

				if(grid2.theGrid[i][j].isHit() && !grid2.theGrid[i][j].hasAship()) {
					g2.setColor(Color.BLACK);
					g2.drawString("X", (int)grid2.theGrid[i][j].getRect().getCenterX()-3, (int)grid2.theGrid[i][j].getRect().getCenterY()+3);
				}
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
		Color back = Color.white;
		g2.setColor(back);
		Rectangle gridRectangle = new Rectangle(0, 0, 631-((int)FRAMEX/FRAMEY), 316-((int)FRAMEY/FRAMEY));
		g2.fill(gridRectangle);		
		Color borde = Color.BLACK;
		Color fill = Color.GRAY;
		g2.setColor(fill);
		Rectangle background = new Rectangle(0, 0, FRAMEX+FRAMEX/areax, 15);
		g2.fill(background);
		g2.setColor(borde);
		Rectangle corner = new Rectangle(0, 0, FRAMEX/areax, 15);
		g2.draw(corner);
		Font font = new Font("sansserif", Font.BOLD, 8);
		g2.setFont(font);
		char letter = 'a';
		for (int i = 0; i < areax; i++) {
			corner = new Rectangle(i*(FRAMEX/areax)+FRAMEX/areax, 0, FRAMEX/areax, 15);
			g2.draw(corner);
			g2.drawString(letter + "", (int)corner.getCenterX()-3, (int)(corner.getCenterY()+5 ));
			if (i == (int)(areax/2)-1) 
				letter = 'a';
			else
				letter += 1;
		}
		background = new Rectangle(0, 15, FRAMEX/areax, FRAMEY);
		g2.setColor(fill);
		g2.fill(background);
		for (int i = 0; i < areay; i++) {
			g2.setColor(borde);
			corner = new Rectangle(0, i*(FRAMEY/areay)+15, FRAMEX/areax, FRAMEY/areay);
			g2.draw(corner);
			g2.drawString(Integer.toString(i+1), (int)corner.getCenterX()-4, (int)corner.getCenterY()+4);
		}
	}

	public void drawBottom(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		Font font = new Font("sansserif", Font.ITALIC, 24);
		g2.setFont(font);
		g2.drawString(player1, 150, 340);
		g2.drawString(player2, 450, 340);
		//Player 2
		if (grid1.isSunken(10)) 
			g2.drawImage(hits10, null, 0, 330);
		if (grid1.isSunken(9)) 
			g2.drawImage(hits9, null, 0, 380);
		if (grid1.isSunken(8)) 
			g2.drawImage(hits8, null, 0, 430);
		if (grid1.isSunken(7)) 
			g2.drawImage(hits7, null, 0, 470);
		if (grid1.isSunken(2)) 
			g2.drawImage(hits2, null, 200, 370);
		if (grid1.isSunken(3)) 
			g2.drawImage(hits3, null, 190, 385);
		if (grid1.isSunken(4)) 
			g2.drawImage(hits4, null, 190, 420);
		if (grid1.isSunken(5)) 
			g2.drawImage(hits5, null, 175, 450);
		if (grid1.isSunken(6)) 
			g2.drawImage(hits6, null, 175, 480);
		//Player 1
		if (grid2.isSunken(10)) 
			g2.drawImage(hits10, null, 330, 330);
		if (grid2.isSunken(9)) 
			g2.drawImage(hits9, null, 330, 380);
		if (grid2.isSunken(8)) 
			g2.drawImage(hits8, null, 330, 430);
		if (grid2.isSunken(7)) 
			g2.drawImage(hits7, null, 330, 470);
		if (grid2.isSunken(2)) 
			g2.drawImage(hits2, null, 550, 370);
		if (grid2.isSunken(3)) 
			g2.drawImage(hits3, null, 520, 385);
		if (grid2.isSunken(4)) 
			g2.drawImage(hits4, null, 520, 420);
		if (grid2.isSunken(5)) 
			g2.drawImage(hits5, null, 505, 450);
		if (grid2.isSunken(6)) 
			g2.drawImage(hits6, null, 505, 480);

		if (grid1.allSunken(ships)) {
			JOptionPane.showMessageDialog(null, player1 + " has won!");
			db.update(player1, status.totalPlayer1());
			System.out.println("End of the game.");
			System.exit(0);
		}
		if (grid2.allSunken(ships)) {
			JOptionPane.showMessageDialog(null, player2 + " has won!");
			db.update(player2, status.totalPlayer2());
			System.out.println("End of the game.");
			System.exit(0);
		}
		if(player2.equals("Rofongo")) {}
		//insert code here.
	}

	/**
	 * ActionListener for the mouseclicked event.
	 */
	public void mouseClicked(MouseEvent e) {
		if (grid1 != null && status.getStatus().equals(player1)) {
			for (int i = 0; i < grid1.theGrid.length; i++) {
				for (int j = 0; j < grid1.theGrid[0].length; j++) {
					if(grid1.theGrid[i][j].getRect().contains(e.getPoint()) && !grid1.theGrid[i][j].isHit()) {
						grid1.theGrid[i][j].hit();
						char parse = (char) ('a' + i);
						String input = parse + "" + (j+1);
						status.log(input, grid1.theGrid[i][j].hasAship());
						if(grid1.theGrid[i][j].hasAship())
							hit.play();
						else 
							miss.play();
						status.switchStatus();
						status.incrementP1();
						if(marked && !grid1.theGrid[i][j].hasAship())
							grid1.theGrid[i][j].unhit();
						repaint();
					}
				}
			}			
		}
		if (grid2 != null && status.getStatus().equals(player2)) {
			for (int i = 0; i < grid2.theGrid.length; i++) {
				for (int j = 0; j < grid2.theGrid[0].length; j++) {
					if(grid2.theGrid[i][j].getRect().contains(e.getPoint()) && !grid2.theGrid[i][j].isHit()) {
						grid2.theGrid[i][j].hit();
						char parse = (char) ('a' + i);
						String input = parse + "" + (j+1);
						status.log(input, grid1.theGrid[i][j].hasAship());
						if(grid2.theGrid[i][j].hasAship())
							hit.play();
						else 
							miss.play();
						status.switchStatus();
						status.incrementP2();
						if(marked && !grid2.theGrid[i][j].hasAship())
							grid2.theGrid[i][j].unhit();
						repaint();
					}
				}
			}			
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

	/**
	 * Waits for the input in the menu bar
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("View High Scores")) 
			db.showHigh();
		if (e.getActionCommand().equals("New Game")) {
			game = new NewGame();
			game.addWindowListener(this);
		}
		if(e.getActionCommand().equals("Save Game")) { 

			try {
				Object[] data = new Object[8];
				data[0] = areax;
				data[1] = areay;
				data[2] = ships;
				data[3] = player1;
				data[4] = player2;
				data[5] = placement;
				data[6] = diagonal;
				data[7] = marked;
				SaveGame.save(grid1, grid2, data);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("Open Saved Game")) {
			SaveGame opengame = new SaveGame();
			try {
				opengame.open();
				Object[] data = opengame.getData();
				areax = (Integer) data[0];
				areay = (Integer) data[1];
				ships = (Integer) data[2];
				player1 = (String) data[3];
				player2 = (String) data[4];
				placement = (Boolean) data[5];
				diagonal = (Boolean) data[6];
				marked = (Boolean) data[7];
				grid1 = opengame.getGrid1();
				grid2 = opengame.getGrid2();
				status = new Status(player1, player2);
				reset = true;
				JOptionPane.showMessageDialog(null, "Welcome back bro");
				repaint();
			} catch (IOException e1) {
				System.out.println("Fail opening game.");
			} catch (ClassNotFoundException e1) {

			}	

		}
	}

	/**
	 * Verifies that the input is a valid one
	 * @param input Input from the user
	 * @return If the input is valid
	 */
	static boolean verifyInput(String input) {
		input = input.toLowerCase();
		if(input.isEmpty())
			return false;
		char[] parse = input.toCharArray();
		if(!Character.isLetter(parse[0]))
			return false;
		for (int i = 1; i < parse.length; i++) 
			if (!Character.isDigit(parse[i])) 
				return false;
		return true;
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent e) {}

	public void windowDeactivated(WindowEvent arg0) {
		areax = game.getColumns()*2;
		areay = game.getRows();
		ships = game.getBoats();
		player1 = game.playerOne();
		player2 = game.playerTwo();
		placement = game.gameType();
		diagonal = game.levelType();
		marked = game.gameMode();
		grid1 = new TheGrid(areax/2, areay);
		grid2 = new TheGrid(areax/2, areay);
		randomboats1 = new RandomBoat();
		randomboats2 = new RandomBoat();
		grid1.resetGridOffset();
		grid2.resetGridOffset();

		if(placement != true && !player2.equals("Rofongo")) {
			JOptionPane.showMessageDialog(null, player2 + " enter your coordinates");
			grid1.placeTheBoats(ships, diagonal);
			JOptionPane.showMessageDialog(null, player1 + " enter your coordinates");
			grid2.placeTheBoats(ships, diagonal);
		}
		else if(placement == true && !player2.equals("Rofongo")) {
			randomboats1.placeRandomBoats(ships, areay, areax/2, grid1, diagonal);
			randomboats2.placeRandomBoats(ships, areay, areax/2, grid2, diagonal);
		}
		else if (placement == true && player2.equals("Rofongo")) {
			randomboats1.placeRandomBoats(ships, areay, areax/2, grid1, diagonal);
			randomboats2.placeRandomBoats(ships, areay, areax/2, grid2, diagonal);
		}
		else if (placement != true && player2.equals("Rofongo")) {
			randomboats1.placeRandomBoats(ships, areay, areax/2, grid1, diagonal);
			JOptionPane.showMessageDialog(null, player1 + " enter your coordinates");
			grid2.placeTheBoats(ships, diagonal);
		}


		status = new Status(player1, player2);
		reset = true;
		repaint();
	}

	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode()) == (KeyEvent.VK_ALT | KeyEvent.VK_F4)) 
			System.exit(0);
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
