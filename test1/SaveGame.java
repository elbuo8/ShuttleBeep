package test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveGame {
	
	public static void save(TheGrid grid1, TheGrid grid2, Object[] data) throws IOException {
		FileOutputStream file = new FileOutputStream("grid1.dat");
		ObjectOutputStream writter = new ObjectOutputStream(file);
		writter.writeObject(grid1);
		
		file = new FileOutputStream("grid2.dat");
		writter = new ObjectOutputStream(file);
		writter.writeObject(grid2);

		 
		file = new FileOutputStream("data.dat");
		writter = new ObjectOutputStream(file);
		writter.writeObject(data);
	}
	
	public void open() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("grid1.dat");
		ObjectInputStream reader = new ObjectInputStream(file);
		grid1 = (TheGrid) reader.readObject();
		
		file = new FileInputStream("grid2.dat");
		reader = new ObjectInputStream(file);
		grid2 = (TheGrid) reader.readObject();
		
		file = new FileInputStream("data.dat");
		reader = new ObjectInputStream(file);
		data = (Object[]) reader.readObject();
			
	}
	
	/**
	 * @return the grid1
	 */
	public TheGrid getGrid1() {
		return grid1;
	}

	/**
	 * @return the grid2
	 */
	public TheGrid getGrid2() {
		return grid2;
	}


	/**
	 * @return the data
	 */
	public Object[] getData() {
		return data;
	}

	private Object[] data;
	private TheGrid grid1;
	private TheGrid grid2;
}
