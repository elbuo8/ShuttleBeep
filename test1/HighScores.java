package test1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

	public void update(int total, String name) throws IOException {
		if(!new File("highscores.txt").exists()) {
			generateFile();
			FileWriter fw;
			try {
				fw = new FileWriter("highscores.txt", true);
			} 
			catch (IOException e) {
				return;
			}
			fw.write("1. " + total +", "+ name);
			fw.close();
		}
		else {
			ArrayList<String> top = new ArrayList<String>();
			Scanner reader;
			try
			{
				reader = new Scanner(new FileReader("highscores.txt"));
			}
			catch(Exception e)
			{
				System.out.println("Not found");
				return;
			}

			while (reader.hasNextLine()) {
				scores = reader.nextLine();
				scores = scores.substring(2);
				top.add(scores);
			}
			reader.close();

			for(int i = 0; i < top.size(); i++) {
				String[] parse = top.get(i).split(",");
				if(Integer.parseInt(parse[0]) < total) {
					top.add(i, total + ", " + name);
					top.remove(5);
				}
			}

			for (int i = 0; i < top.size(); i++) 
				top.set(i, i+1 + ". " + top.get(i));

			FileWriter fw;
			try {
				fw = new FileWriter("mail_listing.txt", true);
			} 
			catch (IOException e) {
				return;
			}
			
			for (int i = 0; i < top.size(); i++) 
				fw.write(top.get(i));
			
			
			fw.close();
		}
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

	private String scores;
}
