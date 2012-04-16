/**
 * 
 */
package test1;

import java.util.Random;

/**
 * @author cesarcruz
 *
 */
public class RandomBoat {

	private Random rand;
	private int boatSerial;
	private boolean check = false;
	public RandomBoat(){
		rand = new Random();
	}


	public void placeRandomBoats(int k, int rows, int columns, TheGrid currentGrid){
		for(int i = 0; i < k; i++){
			boatSerial = 2;
			int boatAlignment = rand.nextInt(2) + 1;
			if(boatAlignment == 1){
				int x = rand.nextInt(rows);
				int y1 = 0;
				int y2 = 0;

				while(!check){

					do{
						y1 = rand.nextInt(columns);
						y2 = rand.nextInt(columns);
					}while(y1 > y2 && (y2 - y1)+1 != boatSerial && ((y2 - y1)) < k+2);
					check = currentGrid.addBoatHorizontal(x, y1, y2);
					System.out.println("Horizontal OK");
				}
				boatSerial = (y2 - y1)+1;
			}
			else if(boatAlignment == 2){

				int y = rand.nextInt(columns);
				int x1 = 0;
				int x2 = 0;

				while(!check){

					do{
						x1 = rand.nextInt(rows);
						x2 = rand.nextInt(rows);
					}while(x1 > x2 && (x2 - x1)+1 != boatSerial && ((x2 - x1)) < k+2);
					check = currentGrid.addBoatVertical(y, x1, x2);
					System.out.println("Vertical OK");
				}
				boatSerial = (x2 - x1) + 1;
			}
			else if(boatAlignment == 3){
				int x = rand.nextInt(rows);
				int y = rand.nextInt(columns);
				currentGrid.addBoatDiagonal(x, y, true);
				System.out.println("Diagonal OK");
			}
		}
	}

}
