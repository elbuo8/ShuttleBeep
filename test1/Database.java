package test1;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

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
	
	public void saveGame(String name) throws IOException {
		GridFS gridstore = new GridFS(db);
		File gameFile = new File("data.dat");
		GridFSInputFile importer = gridstore.createFile(gameFile);
		importer.setFilename(name);
		importer.save();
		gameFile.delete();
	}
	
	public void openGame(String name) throws IOException {
		GridFS gridstore = new GridFS(db);
		GridFSDBFile export = gridstore.findOne(name);
		export.writeTo("data.dat");
		gridstore.remove(gridstore.findOne(name));
	}

}
