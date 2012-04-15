package test1;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * @author cesarcruz
 *
 */
public class NewGame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel gameType, rows, columns, player1, player2, shuttle;
	private JTextField text1, text2, text3, text4, text5;
	private Checkbox optionOne, optionTwo;
	private JButton okButton, cancelButton;
	private CheckboxGroup gameSelection = new CheckboxGroup();
	private String numberOfRows, numberOfColumns, numberOfShuttles;
	private String player1Name, player2Name;
	private int xRows, xColumns, xShuttles;
	private boolean check = false;

	public NewGame(){
		setSize(400, 400);
		setLocation(500, 250);
		//setDefaultCloseOperation(okButton.getMnemonic());

		GroupLayout layout = new GroupLayout(getContentPane());

		/**
		 * Declaration of all the components
		 */
		gameType = new JLabel("Game Type: ");
		rows = new JLabel("Number of Rows:");
		optionOne = new Checkbox("PvP", gameSelection, false);
		optionTwo = new Checkbox("PvR", gameSelection, false);
		text1 = new JTextField(10);
		shuttle = new JLabel("Number of Shuttles: ");
		columns = new JLabel("Number of Columns: ");
		text2 = new JTextField(10);
		text3 = new JTextField(10);
		okButton = new JButton("Ok");
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		player1 = new JLabel("Player One: ");
		player2 = new JLabel("Player Two");
		text4 = new JTextField(10);
		text5 = new JTextField(10);
		//text1.setSize(50, 50);

		/**
		 * Horizontal arrangement of all the components in the JPanel
		 */

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(player1)
						.addComponent(player2)
						.addComponent(shuttle)
						.addComponent(rows)
						.addComponent(columns))
						.addGroup(layout.createParallelGroup()
								.addComponent(text1)
								.addComponent(text2)
								.addComponent(text3)
								.addComponent(text4)
								.addComponent(text5)
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
		return xShuttles;
	}

	public String playerOne(){
		return player1Name;
	}

	public String playerTwo(){
		return player2Name;
	}

	public boolean stateOfFrame(){
		return check;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Ok")){

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
		if(e.getActionCommand().equals("Cancel")){
			setVisible(false);
		}
	}

}
