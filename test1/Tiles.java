/**
 * 
 */


/**
 * @author cesarcruz
 *
 */
public class Tiles {
	
	private int locationX = 0;
	private int locationY = 0;
	private boolean hasAship = false;
	private boolean isHit = false;
	
	
	
	
	/**
	 * Each object contains its location in the 2D array that is represented in the grid
	 * and if it contains a piece of the ship or not.
	 * @param x location of the tile in the 2D array 
	 * @param y location of the tile in the 2D array
	 */
	public Tiles(int x, int y){
		locationX = x;
		locationY = y;
		hasAship = false;
	}
	
	/**
	 * @param
	 * Action to be taken if a tile is pressed or clicked.
	 */
	public void hit(){
		if(isHit == false)
			isHit = true;
	}
	/**
	 * 
	 * @return the y location of the current tile in the 2D array or Grid.
	 */
	public int getY(){
		return locationY;
	}
	/**
	 * @return the x location of the current tile in the 2D array or Grid. 
	 */
	public int getX(){
		return locationX;
	}
	
	/**
	 * Places a boat (or a piece of a boat) in the selected tile.
	 */
	public void placeBoat(int x, int y){
		hasAship = true;
	}
	public void addLocation(int x, int y){
		locationX = x;
		locationY = y;
	}
}
