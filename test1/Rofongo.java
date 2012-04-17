package test1;

import java.io.Serializable;
import java.util.Random;

public class Rofongo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3152026230527083938L;

	
	static public int[] attack(int rows, int columns) {
		Random random = new Random();
		int[] coordinates = new int[2];
		coordinates[0] = random.nextInt(rows);
		coordinates[1] = random.nextInt(columns); 
		
		return coordinates;
	}
	
}
