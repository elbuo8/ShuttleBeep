package test1;

import java.io.Serializable;
import java.util.Random;

/**
 * 
 * @author yamilasusta
 *
 */
public class Rofongo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3152026230527083938L;

	/**
	 * Generates coordinates for Rofongo to attack
	 * @param rows Size of the grid on x
	 * @param columns  Size of the grid on y 
	 * @return Generated points
	 */
	static public int[] attack(int rows, int columns) {
		Random random = new Random();
		int[] coordinates = new int[2];
		coordinates[0] = random.nextInt(rows);
		coordinates[1] = random.nextInt(columns); 
		
		return coordinates;
	}
	
}
