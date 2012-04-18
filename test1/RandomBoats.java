package test1;

import java.util.Random;

/**
 * 
 * @author yamilasusta
 *
 */
public class RandomBoats {

	/**
	 * Randomly places the boats accros the board
	 * @param rows X Dimension
	 * @param columns Y Dimension
	 * @param diagonal Level 1 or Level 2
	 * @param boats Boats to be added
	 * @param grid Grid to be modified
	 */
	static public void placeBoats(int rows, int columns, boolean diagonal, int boats, TheGrid grid) {
		int boatSerial = boats + 1;
		Random random = new Random();
		for (int i = 0; i < boats; i++) {
			boolean placed = false;
			while (!placed) {
				int menu = random.nextInt(3);
				if(!placed && menu == 0) { //horizontal.
					int x = random.nextInt(columns);
					int y = random.nextInt(rows);
					if(boatSerial > 6) 	
						y = random.nextInt(3);
					int y2 = y + boatSerial - 1;
					grid.setSerial(boatSerial);
					placed = grid.addBoatHorizontal(x, y, y2);
				}
				if (!placed && menu == 1) { //vertical
					int x = random.nextInt(columns);
					int y = random.nextInt(rows);
					if(boatSerial > 6)
						x = random.nextInt(3);
					int x2 = x + boatSerial - 1;
					grid.setSerial(boatSerial);
					placed = grid.addBoatVertical(y, x, x2);
				}
				if (!placed && diagonal && menu == 2) {//diagonal
					int x = random.nextInt(5);
					int y = random.nextInt(5);
					grid.setSerial(boatSerial);
					placed = grid.addBoatDiagonal(x, y, true);
					if (!placed) 
						placed = grid.addBoatDiagonal(x, y, false);
				}	
			}			
			boatSerial--;
		}

	}

}
