package org.alpha.mongo.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class DbUploadUtils {
	private MongoUtils utils = MongoUtils.getInstance();
	private DBCollection fileCollection;
	private DBCollection chunkCollection;
	private DB db = utils.getDB("onetiger");
	
	public DbUploadUtils(){
		fileCollection = utils.getCollection(db, "fs.files");
		chunkCollection = utils.getCollection(db, "fs.chunks");
	}
	
	public long totalFiles(){
		return fileCollection.count(new BasicDBObject());
	}
	
	public long totalChunks(){
		return chunkCollection.count(new BasicDBObject());
	}
	
	public void clear(){
		fileCollection.drop();
		chunkCollection.drop();
		db.dropDatabase();
	}
}
