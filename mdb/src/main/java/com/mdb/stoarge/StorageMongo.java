package com.mdb.stoarge;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * mongodb實現
 * 
 * @author Eric.Ma
 *
 */
public class StorageMongo implements StorageEngine {

	private static final Logger log = LoggerFactory.getLogger(StorageMongo.class);

	private  LoggerMongo logger;


	private static final String KEY = "key";

	private static final String VALUE = "value";

	private MongoDatabase database;

	private MongoCollection<DBObject> table;

	public StorageMongo(LoggerMongo logger) {
		this.logger = logger;
	}

	@Override
	public void detect(String tableName) {
		// 創建集合
		database = this.logger.getMongoClient().getDatabase(logger.getDataBase());
		boolean firstCreate = false;
		if (!database.listCollectionNames().into(new ArrayList<>()).contains(tableName)) {
			database.createCollection(tableName);
			firstCreate = true;
		}
		table = database.getCollection(tableName, DBObject.class);
		if (firstCreate) {
			BasicDBObject index = new BasicDBObject("key", 1);
			table.createIndex(index, new IndexOptions().unique(true).name("key_index"));
		}
	}

	@Override
	public String find(String tableName, String key) {
		long begin = System.nanoTime();
		try {
			BasicDBObject query = new BasicDBObject(KEY, key);
			FindIterable<DBObject> cursor = table.find(query);
			DBObject doc = cursor.first();
			if (doc == null)
				return null;
			Object value = doc.get(VALUE);
			return JSON.serialize(value);
		} finally {
			log.debug("{} find key : {}, cost : {} ms", tableName, key, (System.nanoTime() - begin) * 1e-6);
		}
	}

	@Override
	public boolean exist(String tableName, String key) {
		long begin = System.nanoTime();
		try {
			BasicDBObject query = new BasicDBObject(KEY, key);
			FindIterable<DBObject> cursor = table.find(query);
			DBObject doc = cursor.first();
			return doc != null;
		} finally {
			log.debug("{} exist key : {} cost : {} ms", tableName, key, (System.nanoTime() - begin) * 1e-6);
		}
	}

	@Override
	public boolean insert(String tableName, String key, String value) {
		long begin = System.nanoTime();
		try {
			BasicDBObject doc = new BasicDBObject();
			doc.append(KEY, key).append(VALUE, JSON.parse(value));
			table.insertOne(doc);
			return true;
		} finally {
			log.debug("{} insert key : {} cost: {} ms", tableName, key, (System.nanoTime() - begin) * 1e-6);
		}
	}

	@Override
	public void replace(String tableName, String key, String value) {
		long begin = System.nanoTime();
		try {
			BasicDBObject keyQuery = new BasicDBObject(KEY, key);
			BasicDBObject doc = new BasicDBObject();
			doc.append(KEY, key);
			Object obj = JSON.parse(value);
			doc.append(VALUE, obj);
			table.replaceOne(keyQuery, doc, new UpdateOptions().upsert(true));
		} finally {
			log.debug("{} replace key : {} value: {} cost : {} ms", tableName, key, value,
					(System.nanoTime() - begin) * 1e-6);
		}
	}

	@Override
	public void remove(String tableName, String key) {
		long begin = System.nanoTime();
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(KEY, key);
			table.deleteOne(searchQuery);
		} finally {
			log.debug("{} remove key : {} cost : {} ms", tableName, key, (System.nanoTime() - begin) * 1e-6);
		}
	}

	@Override
	public void close() {
		logger.close();
	}

	public static void main(String[] args) {
		Object obj = JSON.parse("");
		System.err.println(obj);
	}

}
