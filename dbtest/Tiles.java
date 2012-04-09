package dbtest;

import java.awt.Rectangle;

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
	private int boatSerial;
	private Rectangle rect = new Rectangle();
	
	/**
	 * Each object contains its location in the 2D array that is represented in the grid
	 * and if it contains a piece of the ship or not.
	 * @param x location of the tile in the 2D array 
	 * @param y location of the tile in the 2D array
	 */
	public Tiles(){
		locationX = 0;
		locationY = 0;
		hasAship = false;
		boatSerial = 0;
		rect.setBounds(0,0,30,30);
		isHit = false;
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
	/**
	 * Stores the location of the tile within the Grid in the respective tile
	 * @param x
	 * @param y
	 */
	public void addLocation(int x, int y){
		locationX = x;
		locationY = y;
	}
	/**
	 * Sets a specific serial to each of the tiles containing a ship.
	 * @param x
	 */
	public void setSerial(int x){
		boatSerial = x;
	}
	
	/**
	 * Given the top x and top y coordinates, modifies this values for the rectangle
	 * in each of the tiles.
	 * @param topX 
	 * @param topY
	 */
	public void setRectangleBounds(int topX, int topY){
		rect.setBounds(topX, topY, 30, 30);
	}
	
	/**
	 * @return the rectangle data for the given tile
	 */
	public Rectangle getRect(){
		return rect;
	}
	/**
	 * 
	 * @return whether or not the specific tile has been hit or not.
	 */
	public boolean isHit(){
		boolean hitNoHit = false;
		if(isHit == true)
			hitNoHit = true;
		return hitNoHit;
	}
	
	public boolean hasAship(){
		if(hasAship == true)
			return true;
		else
			return false;
	}
}