package test1;


/**
 * @author cesarcruz
 *
 */
public class TheGrid {

	public Tiles[][]  theGrid;
	private static int numberOfGrids = 2;
	private static final int WIDTH_OF_GRID = 600;
	private static final int HEIGHT_OF_GRID = 300;
	private static int numberOfColumns = 10;
	private static int numberOfRows = 10;
	private static int gridObjects = 0;
	private static int boatSerial;

	/**
	 * Creates a Grid object that in itself is an array of Tiles, each contains its location. 
	 */
	public TheGrid(){
		int topX = ((WIDTH_OF_GRID/numberOfGrids)/(numberOfRows));
		int topY = (HEIGHT_OF_GRID)/(numberOfColumns);
		int offSet = (WIDTH_OF_GRID/numberOfGrids)*gridObjects;
		theGrid = new Tiles[numberOfColumns][numberOfRows];
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				theGrid[i][j] = new Tiles();
				theGrid[i][j].addLocation(i, j);
				theGrid[i][j].setRectangleBounds((i+1)*topX + offSet, ((j+1)*topY) - 15, (WIDTH_OF_GRID/numberOfGrids)/(numberOfRows), (WIDTH_OF_GRID/numberOfGrids)/(numberOfRows));
			}
		}
		gridObjects++;
	}

	/**
	 * @param x will be the size of the matrix (x*y)
	 */
	public TheGrid(int x, int y){
		int topX = ((WIDTH_OF_GRID/numberOfGrids)/(numberOfRows));
		int topY = (HEIGHT_OF_GRID)/(numberOfColumns);
		int offSet = (WIDTH_OF_GRID/numberOfGrids)*gridObjects;
		theGrid = new Tiles[x][y];
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				theGrid[i][j] = new Tiles();
				theGrid[i][j].addLocation(i, j);
				theGrid[i][j].setRectangleBounds((i+1)*topX + offSet, ((j+1)*topY) - 15, (WIDTH_OF_GRID/numberOfGrids)/(numberOfRows), (WIDTH_OF_GRID/numberOfGrids)/(numberOfRows));
			}
		}
		gridObjects++;
	}

	/**
	 * 
	 * @param x1 starting point of the boat in the x coordinate
	 * @param x2 ending point of the boat in the x coordinate
	 * @param y the number of the row
	 */
	public void addBoatVertical(int y, int x1, int x2){
		boatSerial = x2 - x1;
		if(checkIfBoatVertical(y, x1, x2)){	
			for(int i = 0; i < 10; i++){
				for(int j = 0; j < 10; j++){
					if(x1 >= i && x2 <= i && j == y)
						theGrid[i][j].placeBoat(i, j);
					theGrid[i][j].setSerial(boatSerial);
				}
			}
		}
		else{
			System.out.println("The boat cannot be placed in the desired spot.");
		}
	}
	/**
	 * @param x the number of the column
	 * @param y1 the starting point of the boat in the y coordinate
	 * @param y2 the ending point of the boat in the y coordinate
	 */
	public void addBoatHorizontal(int x, int y1, int y2){
		boatSerial = y2 - y1;
		if(checkIfBoatHorizontal(x, y1, y2)){
			for(int i = 0; i < 10; i++){
				for(int j = 0; j < 10; j++){
					if(y1 >= j && y2 <= j && x == i)
						theGrid[i][j].placeBoat(i, j);
					theGrid[i][j].setSerial(boatSerial);
				}
			}
		}
		else{
			System.out.println("Boat cannot be placed in the desired spot.");
		}
	}
	/**
	 * The three parameters indicate the location and the size of the boat
	 * that will be place
	 * @param x 
	 * @param y1
	 * @param y2
	 * @return true if the boat can be placed in the desired spot, false if it can't
	 */
	public boolean checkIfBoatHorizontal(int x, int y1, int y2){
		int check = 0;
		for(int i = 0; i < numberOfColumns; i++){
			for(int j = 0; j < numberOfRows; j++){
				if(y1 >= j && y2 <= j && x == i){
					if(!theGrid[i][j].hasAship())
						check++;
				}
			}
		}
		if(check == boatSerial)
			return true;
		else
			return false;
	}

	/**
	 * The three parameters indicate the location and the size of the boat
	 * that will be place
	 * @param y 
	 * @param x1
	 * @param x2
	 * @return true if the boat can be placed in the desired spot, false if it can't
	 */
	public boolean checkIfBoatVertical(int y, int x1, int x2){
		int check = 0;
		for(int i = 0; i < numberOfColumns; i++){
			for(int j = 0; j < numberOfRows; j++){
				if(x1 >= i && x2 <= i && j == y){
					if(!theGrid[i][j].hasAship())
						check++;
				}
			}
		}
		if(check == boatSerial)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param x amount of grids that you want to add to the game
	 */
	public void numberOfGrids(int x){
		numberOfGrids = x;
	}
}
