package test1;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * @author cesarcruz
 *
 */
public class NewGame extends JFrame{

	private JLabel gameType, rows, columns, players;
	private JTextField text1, text2, text3;
	private Checkbox optionOne, optionTwo;
	private JButton okButton, cancelButton;
	private CheckboxGroup gameSelection = new CheckboxGroup();

	public NewGame(){
		setSize(400, 400);
		setLocation(500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GroupLayout layout = new GroupLayout(getContentPane());
		   getContentPane().setLayout(layout);
	        layout.setAutoCreateGaps(true);
	        layout.setAutoCreateContainerGaps(true);

		/**
		 * Declaration of all the components
		 */
		gameType = new JLabel("Game Type: ");
		rows = new JLabel("Number of Rows:");
		optionOne = new Checkbox("PvP", gameSelection, false);
		optionTwo = new Checkbox("PvR", gameSelection, false);
		text1 = new JTextField();
		players = new JLabel("Number of Players: ");
		columns = new JLabel("Number of Columns: ");
		text2 = new JTextField();
		text3 = new JTextField();
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		
		/**
		 * Horizontal arrangement of all the components in the JPanel
		 */
	
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(gameType)
						.addGroup(layout.createSequentialGroup()
								.addComponent(optionOne)
								.addComponent(optionTwo))
						.addComponent(optionTwo)
						.addComponent(players)
						.addComponent(text2))
				.addGroup(layout.createParallelGroup()
						.addComponent(rows)
						.addComponent(text1)
						.addComponent(columns)
						.addComponent(text3)
						.addGroup(layout.createSequentialGroup()
								.addComponent(okButton)
								.addComponent(cancelButton)))
				);
		
		/**
		 * Vertical arragement of the components in the JPanel
		 */
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(gameType)
						.addComponent(rows))
				.addGroup(layout.createParallelGroup()
						.addComponent(optionOne)
						.addComponent(optionTwo)
						.addComponent(text1))
				.addGroup(layout.createParallelGroup()
						.addComponent(players)
						.addComponent(columns))
				.addGroup(layout.createParallelGroup()
						.addComponent(text2)
						.addComponent(text3))
				.addGroup(layout.createParallelGroup()
						.addComponent(okButton)
						.addComponent(cancelButton)
						)
				);
		
		/**
		 * Create a JFrame with the desired specifications using the GroupLayout class
		 * from the javax.swing package.
		 */
		
		setTitle("New Game");
		pack();
		setVisible(true);

	}
	public static void main(String[] args){
		NewGame game = new NewGame();
	}
}
