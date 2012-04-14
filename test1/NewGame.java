package test1;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * @author cesarcruz
 *
 */
public class NewGame extends JFrame implements ActionListener{

	private JLabel gameType, rows, columns, players;
	private JTextField text1, text2, text3;
	private Checkbox optionOne, optionTwo;
	private JButton okButton, cancelButton;
	private CheckboxGroup gameSelection = new CheckboxGroup();
	private String numberOfRows, numberOfColumns, numberOfShuttles;
	private int xRows, xColumns, xShuttles;

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
		players = new JLabel("Number of Shuttles: ");
		columns = new JLabel("Number of Columns: ");
		text2 = new JTextField();
		text3 = new JTextField();
		okButton = new JButton("Ok");
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		
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
	
	public int Columns(){
		if(xColumns < 10){
			xColumns = 10;
		}
		return xColumns;
	}
	
	public int Rows(){
		if(xRows < 10){
			xRows = 10;
		}
		return xRows;
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Ok")){
			
			numberOfRows = text1.getText();
			xRows = Integer.parseInt(numberOfRows);
			//System.out.println(xRows);
			
			numberOfShuttles = text2.getText();
			xShuttles = Integer.parseInt(numberOfShuttles);
			//System.out.println(xShuttles);
			
			numberOfColumns = text3.getText();
			xColumns = Integer.parseInt(numberOfColumns);
			//System.out.println(numberOfColumns);
			
			/*not yet implemented
			 * if(gameSelection.getSelectedCheckbox() == optionOne){
				
			}
			else if(gameSelection.getSelectedCheckbox() == optionTwo){
				
			}*/
		}
		if(e.getActionCommand().equals("Cancel")){
			System.exit(0);
		}
	}
	public static void main(String[] args){
		NewGame game = new NewGame();
	}
}
