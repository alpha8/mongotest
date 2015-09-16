package org.alpha.mongo.dao;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public interface MongoDao {
	public DBObject findOne(DBObject obj);
	public DBCursor find(DBObject obj);
	public int insert(DBObject...arr);
	public int update(DBObject q , DBObject o);
	public int update(DBObject q , DBObject o, boolean upsert, boolean multi);
	public int remove(DBObject q);
	public int save(DBObject o);
	public long count(DBObject q);
	public void clear();
}
