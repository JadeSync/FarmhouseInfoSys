package inventory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import user.User;

public class MenuMDL {
	private MongoClient client;
	private DB databse;
	private DBCollection collection;
	
	public MenuMDL() {
		this.client = new MongoClient();
		this.databse = client.getDB("farmhouse");
		this.collection = databse.getCollection("menu");
	}
	
	public Boolean createNewMenuItem(String name, int price) {
		DBObject newUser = new BasicDBObject("item", name)
											.append("price", price);
		
		if( this.collection.insert( newUser ) != null ) {
			return true;
		}
		else {
			return false;
		}	
	}
	
//	public Menu getAllItems() {
//		
//	}
}
