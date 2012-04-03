package test1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScores {

	public HighScores() {

	}

	public void generateFile() throws IOException {
		FileWriter fw;
		try {
			fw = new FileWriter("highscores.txt", true);
		} 
		catch (IOException e) {
			return;
		}

		for (int i = 1; i <= 5; i++) {
			fw.write(i + ".\n");
		}

		fw.close();
	}
	
	public void update() {
		
	}

	public String getString() {
		scores = "";
		Scanner reader = null;
		try {
			reader = new Scanner(new FileReader("highscores.txt"));
		}
		catch(Exception e) {
			System.out.println("Not found");
		}
		
		while (reader.hasNextLine()) {
			scores += reader.nextLine();
			scores += "\n";
		}
		
		reader.close();
		return scores;
	}
	
	String scores;
}
