package org.alpha.mongo.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

public class MongoUtils {
	private static Logger logger = Logger.getLogger(MongoUtils.class);
	private static MongoUtils instance = new MongoUtils();
	private static MongoClient client;
	
	public static MongoUtils getInstance(){
		return instance;
	}
	
	private MongoUtils(){
		try{
			List<ServerAddress> servers = new ArrayList<ServerAddress>();
			servers.add(new ServerAddress("127.0.0.1", 27017));
//			servers.add(new ServerAddress("127.0.0.1", 17017));
//			servers.add(new ServerAddress("127.0.0.1", 37017));
			
//			MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(10).build();
			client = new MongoClient(servers);
			MongoClientOptions options = client.getMongoClientOptions();
			logger.debug("mongo version: " + client.getVersion());
			logger.debug("mongo client options: "+ options.toString());
	//		client.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		}catch(UnknownHostException e){
			logger.error("Connect refused, caused by "+e.getMessage(), e);
		}
	}
	
	public DB getDB(String dbName){
		return client.getDB(dbName);
	}
	
	public DBCollection getCollection(DB db, String collectionName){
		return db.getCollection(collectionName);
	}
	
	public MongoClient getMongoClient(){
		return client;
	}
	
	public boolean release(){
		if(client != null){
			client.close();
			return true;
		}
		return false;
	}
	
	public void printSchema(){
		List<String> dbs = client.getDatabaseNames();
		for (String dbName : dbs) {
			logger.debug("===============DB========" +dbName);
			
			DB db = client.getDB(dbName);
			Set<String> cols = db.getCollectionNames();
			for (String col : cols) {
				logger.debug("Collection Name: " + col);
				DBCollection dbCollection = db.getCollection(col);
				logger.debug("total records: " + dbCollection.count() + "\n");
			}
			logger.debug("============END DB=========\n");
		}
	}
}
