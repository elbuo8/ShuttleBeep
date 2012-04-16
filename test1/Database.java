package test1;

import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;

/**
 * 
 * @author yamilasusta
 *
 */
public class Database {
	
	//Instance variables
	private MongoURI mongo;
	private DB db;

	/**
	 * Default constructor
	 */
	public Database() {
		mongo = new MongoURI("mongodb://scorer:123456@staff.mongohq.com:10005/shuttlebeep");
		try {
			mongo.connect();
		} catch (MongoException e1) {
		} catch (UnknownHostException e1) {}
		try {
			db = mongo.connectDB();
		} catch (MongoException e1) {
		} catch (UnknownHostException e1) {
		}
		char[] passwd = {'1','2','3','4','5','6'};
		db.authenticate("scorer", passwd);
	}
	
	/**
	 * Shows the top 10 scores on the screen
	 */
	public void showHigh() {
		DBCollection collection = db.getCollection("scores");
		DBCursor cursor = collection.find().sort(new BasicDBObject("shots", 1)).limit(10);
		String parser = "";
		int i = 1;
		while (cursor.hasNext()) {
			DBObject scores = cursor.next();
			parser += i + ". " + scores.get("name") + " - " + scores.get("shots") + "\n";
			i++;
		}
		JOptionPane.showMessageDialog(null, parser);
	}
	
	/**
	 * Updates the database with the winners information.
	 * @param winner Winners name.
	 * @param shots Amount of shots taken by the winner.
	 */
	public void update(String winner, int shots) {
		DBCollection collection = db.getCollection("scores");
		BasicDBObject player = new BasicDBObject();
		player.put("name", winner);
		player.put("shots", shots);
		collection.insert(player);
	}
	
	/**
	public void save(Tiles[][] grid1, Tiles[][] grid2, String name) {
		DBCollection collection = db.getCollection("saves");
		BasicDBObject player1 = new BasicDBObject();
		player1.put("Game", name);
		BasicDBObject plane1 = new BasicDBObject();
		BasicDBObject plane2 = new BasicDBObject();
		BasicDBObject index = new BasicDBObject();
		for (int i = 0; i < grid1.length; i++) {
			for (int j = 0; j < grid1[0].length; j++) {
				index.put("indexi", i);
				index.put("indexj", j);
				BasicDBObject parse = new BasicDBObject();
				parse.put("X", grid1[i][j].getX());
				parse.put("Y", grid1[i][j].getY());
				parse.put("Rect", grid1[i][j].getRect());
				parse.put("isHit", grid1[i][j].isHit());
				parse.put("hasAship", grid1[i][j].hasAship());
				//parse.put("boatSerial", grid1[i][j].getSerial());
				index.put("tiles", parse);
			}
		}
		plane1.put("plane1", index);
		
	}
	**/

}
