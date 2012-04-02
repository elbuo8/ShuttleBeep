package test1;



import javax.swing.JFrame;


public class ShuttleBeep {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("ShuttleBeep");
		
		/*// Set to the middle of the screen
		Toolkit toolk = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = (screenSize.height/2);
		int screenWidth = (screenSize.width/2);
		*/
		
		//Create the menu bar and its submenus
		
		
		frame.setSize(400, 400);
		
		Logic logic = new Logic();
		frame.setJMenuBar(logic.bar);
		frame.add(logic);
		frame.addKeyListener(logic);
		frame.addMouseListener(logic);
		frame.setVisible(true);

	}

}
