package org.alpha.mongo.dao;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;


public class MongoDaoImpl implements MongoDao {
	private DBCollection dbc;
	
	public MongoDaoImpl(DBCollection dbc){
		this.dbc = dbc;
	}

	@Override
	public DBObject findOne(DBObject obj) {
		return dbc.findOne(obj);
	}

	@Override
	public DBCursor find(DBObject obj) {
		return dbc.find(obj);
	}

	@Override
	public int insert(DBObject...arr){
		WriteResult result = dbc.insert(arr);
		return result.getN();
	}

	@Override
	public int update(DBObject q, DBObject o) {
		WriteResult result = dbc.update(q, o);
		return result.getN();
	}

	@Override
	public int update(DBObject q, DBObject o, boolean upsert, boolean multi) {
		WriteResult result = dbc.update(q, o, upsert, multi);
		return result.getN();
	}

	@Override
	public int remove(DBObject q) {
		WriteResult result = dbc.remove(q);
		return result.getN();
	}

	@Override
	public void clear() {
		dbc.drop();
	}

	@Override
	public int save(DBObject o) {
		WriteResult result = dbc.save(o);
		return result.getN();
	}

	@Override
	public long count(DBObject q) {
		return dbc.count(q);
	}
}
