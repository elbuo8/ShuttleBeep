package test1;



import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * 
 * @author yamilasusta && cesarcruz
 *
 */
public class ShuttleBeep {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setTitle("ShuttleBeep");

		frame.setSize(631, 600);
		frame.setLocation(325, 100);
		Logic logic = new Logic();
		frame.setJMenuBar(logic.bar);
		frame.add(logic.southJPanel, BorderLayout.PAGE_END);
		frame.add(logic);
		frame.setVisible(true);

	}

}