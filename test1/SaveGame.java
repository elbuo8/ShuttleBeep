package test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveGame {
	
	/**
	 * Saves the game in the local file system.
	 * @param data Array of objects to be stored.
	 * @throws IOException
	 */
	public static void save(Object[] data) throws IOException {
		FileOutputStream file = new FileOutputStream("data.dat");
		ObjectOutputStream writter = new ObjectOutputStream(file);
		writter.writeObject(data);
	}
	
	/**
	 * Opens the game file and stores it into an Object array.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void open() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("data.dat");
		ObjectInputStream reader = new ObjectInputStream(file);
		data = (Object[]) reader.readObject();			
	}
	
	/**
	 * @return the data
	 */
	public Object[] getData() {
		return data;
	}

	private Object[] data;

}
