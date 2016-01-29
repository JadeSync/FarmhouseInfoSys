package user;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class UserMDL {
	
	private MongoClient client;
	private DB databse;
	private DBCollection collection;
	
	public UserMDL() {
		this.client = new MongoClient();
		this.databse = client.getDB("farmhouse");
		this.collection = databse.getCollection("user");
	}
	
	public Boolean createNewUser(User user) {
		DBObject newUser = new BasicDBObject("name", user.getName())
											.append("username", user.getUsername())
											.append("password", user.getPassword())
											.append("userrole", user.getUserRole());
		
		if( this.collection.insert( newUser ) != null ) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public User authenticate( String username, String password ) {
		DBObject query = new BasicDBObject("username", username);
		DBCursor cursor = collection.find(query);
		DBObject result = cursor.one();
		
		if( result != null ) {
			User goodUser = new User();
			
			if( result.get("password").equals(password)) {
				goodUser.setName(result.get("name").toString());
				goodUser.setUsername(result.get("username").toString());
				goodUser.setUserRole(result.get("userrole").toString());
				goodUser.setPassword(result.get("password").toString());
				
				return goodUser;
			}
			
			else {
				return null;
			}
		}
		else {
			return null;
		}
		
	}
}