package test1;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * @author cesarcruz
 *
 */
public class NewGame extends JFrame implements ActionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel rows, columns, player1, player2, shuttle, gameSetting, levelSelection, mode;
	private JTextField text1, text2, text3, text4, text5;
	private Checkbox optionOne, optionTwo, levelOne, levelTwo, marked, unmarked;
	private JButton okButton, cancelButton;
	private CheckboxGroup gameSelection = new CheckboxGroup();
	private CheckboxGroup level = new CheckboxGroup();
	private CheckboxGroup gameMode = new CheckboxGroup();
	private String numberOfRows, numberOfColumns, numberOfShuttles;
	private String player1Name, player2Name;
	private int xRows, xColumns, xShuttles;
	private boolean rand = false, levelState = false, markedOrNot = false;

	public NewGame(){
		setSize(400, 400);
		setLocation(500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GroupLayout layout = new GroupLayout(getContentPane());

		/**
		 * Declaration of all the components
		 */
		rows = new JLabel("Number of Rows:");
		optionOne = new Checkbox("Random", gameSelection, false);
		optionTwo = new Checkbox("User Defined", gameSelection, false);
		text1 = new JTextField(10);
		shuttle = new JLabel("Number of Shuttles: ");
		columns = new JLabel("Number of Columns: ");
		text2 = new JTextField(10);
		text3 = new JTextField(10);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.addKeyListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		player1 = new JLabel("Player One: ");
		player2 = new JLabel("Player Two");
		text4 = new JTextField(10);
		text5 = new JTextField(10);
		gameSetting = new JLabel("Boat Placement: ");
		levelSelection = new JLabel("Select Level: ");
		levelOne = new Checkbox("Level 1", level, false);
		levelTwo = new Checkbox("Level 2", level, false);
		mode = new JLabel("Game Mode: ");
		marked = new Checkbox("Marked", gameMode, false);
		unmarked = new Checkbox("Unmarked" , gameMode, false);
		
		/**
		 * Horizontal arrangement of all the components in the JPanel
		 */

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(player1)
						.addComponent(player2)
						.addComponent(shuttle)
						.addComponent(rows)
						.addComponent(columns)
						.addComponent(gameSetting)
						.addComponent(levelSelection)
						.addComponent(mode))
				.addGroup(layout.createParallelGroup()
						.addComponent(text1)
						.addComponent(text2)
						.addComponent(text3)
						.addComponent(text4)
						.addComponent(text5)
						.addGroup(layout.createSequentialGroup()
								.addComponent(optionOne)
								.addComponent(optionTwo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(levelOne)
								.addComponent(levelTwo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(marked)
								.addComponent(unmarked))
						.addGroup(layout.createSequentialGroup()
								.addComponent(okButton)
								.addComponent(cancelButton)))
				);

		/**
		 * Vertical arragement of the components in the JPanel
		 */

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(player1)
						.addComponent(text1))
				.addGroup(layout.createParallelGroup()
						.addComponent(player2)
						.addComponent(text2))
				.addGroup(layout.createParallelGroup()
						.addComponent(shuttle)
						.addComponent(text3))
				.addGroup(layout.createParallelGroup()
						.addComponent(rows)
						.addComponent(text4))
				.addGroup(layout.createParallelGroup()
						.addComponent(columns)
						.addComponent(text5))
				.addGroup(layout.createParallelGroup()
						.addComponent(gameSetting)
						.addGroup(layout.createParallelGroup()
								.addComponent(optionOne)
								.addComponent(optionTwo)))
				.addGroup(layout.createParallelGroup()
						.addComponent(levelSelection)
						.addGroup(layout.createParallelGroup()
								.addComponent(levelOne)
								.addComponent(levelTwo)))
				.addGroup(layout.createParallelGroup()
						.addComponent(mode)
						.addGroup(layout.createParallelGroup()
								.addComponent(marked)
								.addComponent(unmarked)))
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(okButton)
								.addComponent(cancelButton)))
				);

		/**
		 * Create a JFrame with the desired specifications using the GroupLayout class
		 * from the javax.swing package.
		 */
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		setTitle("New Game");
		pack();
		setVisible(true);
	}

	public int getColumns(){
		if(xColumns < 10){
			xColumns = 10;
		}
		return xColumns;
	}

	public int getRows(){
		if(xRows < 10){
			xRows = 10;
		}
		return xRows;
	}

	public int getBoats() {
		if (xShuttles > 8) {
			xShuttles = 8;
		}
		return xShuttles;
	}

	public String playerOne(){
		return player1Name;
	}

	public String playerTwo(){
		return player2Name;
	}
	/**
	 * 
	 * @return true if boats are to be placed at random, false if user defined
	 */
	public boolean gameType(){
		return rand;
	}
	/**
	 * 
	 * @return true if level 2 is selected, false if level 1 is selected
	 */
	public boolean levelType(){
		return levelState;
	}
	public boolean gameMode(){
		return markedOrNot;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK")){

			if(text1.getText().equals(null) || text1.getText().equals("")){
				player1Name = "Player 1";
			}
			else
				player1Name = text1.getText();

			if(text2.getText().equals(null) || text2.getText().equals(""))
				player2Name = "Rofongo";
			else
				player2Name = text2.getText();

			if(text3.getText().equals(null) || text3.getText().equals(""))
				xShuttles = 9;
			else{
				numberOfShuttles = text3.getText();
				xShuttles = Integer.parseInt(numberOfShuttles);
			}
			if(text4.getText().equals(null) || text4.getText().equals("")){
				xRows = 10;
			}
			else{
				numberOfRows = text4.getText();
				xRows = Integer.parseInt(numberOfRows);
			}
			if(text5.getText().equals(null) || text5.getText().equals("")){
				xColumns = 10;
			}
			else{
				numberOfColumns = text5.getText();
				xColumns = Integer.parseInt(numberOfColumns);
			}
			if(optionOne.getState()){
				rand = true;
			}
			else if(optionTwo.getState()){
				rand = false;
			}
			if(levelOne.getState()){
				levelState = false;
			}
			else if(levelTwo.getState()){
				levelState = true;
			}
			if(marked.getState())
				markedOrNot = true;
			else if(unmarked.getState())
				markedOrNot = false;
			dispose();
		}
		if(e.getActionCommand().equals("Cancel")){
				dispose();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			player1Name = text1.getText();

			if(text2.getText().equals(null) || text2.getText().equals(""))
				player2Name = "Rofongo";
			else
				player2Name = text2.getText();

			if(text3.getText().equals(null) || text3.getText().equals(""))
				xShuttles = 9;
			else{
				numberOfShuttles = text3.getText();
				xShuttles = Integer.parseInt(numberOfShuttles);
			}
			if(text4.getText().equals(null) || text4.getText().equals("")){
				xRows = 10;
			}
			else{
				numberOfRows = text4.getText();
				xRows = Integer.parseInt(numberOfRows);
			}
			if(text5.getText().equals(null) || text5.getText().equals("")){
				xColumns = 10;
			}
			else{
				numberOfColumns = text5.getText();
				xColumns = Integer.parseInt(numberOfColumns);
			}
			dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		
	}

}
