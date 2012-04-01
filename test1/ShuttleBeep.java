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
		/*
		Toolkit toolk = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = (screenSize.height/2);
		int screenWidth = (screenSize.width/2);
		*/
		Logic logic = new Logic();
		frame.add(logic);
		frame.addKeyListener(logic);
		frame.addMouseListener(logic);
		frame.setVisible(true);

	}

}
