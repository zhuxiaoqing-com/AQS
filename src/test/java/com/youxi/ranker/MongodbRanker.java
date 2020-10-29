package com.youxi.ranker;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/17 14:01
 * @Description:
 */
public class MongodbRanker implements Ranker<Object> {
	private static MongoClient client;
	private static MongoDatabase db;

	static {
		initClient();
	}

	private static void initClient() {
		try {
			/*
			 * MongoCredential credential =
			 * MongoCredential.createMongoCRCredential("username", "dbname",
			 * "password".toCharArray());
			 */
			MongoClientOptions.Builder build = new MongoClientOptions.Builder();
			build.threadsAllowedToBlockForConnectionMultiplier(30); // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			build.serverSelectionTimeout(10000); // 设置服务器选择超时以毫秒为间隔，这定义了在抛出异常之前，驱动程序等待服务器选择成功的时间
			/*
			 * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
			 * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
			 * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
			 */
			build.maxWaitTime(1000 * 60);
			build.connectTimeout(1000 * 60 * 1); // 与数据库建立连接的timeout设置为1分钟

			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017", build));
			db = client.getDatabase("test_rank");
		} catch (Exception e) {
		}
	}


	/**
	 * 根据排名区间
	 *
	 * @param key
	 * @param startRank
	 * @param endRank
	 */
	@Override
	public Set zrange(String key, int startRank, int endRank) {
		MongoCollection<Document> rank = db.getCollection(key);
		FindIterable<Document> score = rank.find().sort(new BasicDBObject("score", 1)).skip(startRank).limit(endRank - startRank).batchSize(10000);
		MongoCursor<Document> iterator = score.iterator();
		Set<Object> objects = new HashSet<>();
		while (iterator.hasNext()) {
			Document next = iterator.next();
			objects.add(next);
		}
		return objects;
	}

	/**
	 * 根据分数区间
	 *
	 * @param key
	 * @param startScore
	 * @param endScore
	 */
	@Override
	public Set zrangeWithScores(String key, int startScore, int endScore) {
		MongoCollection<Document> rank = db.getCollection(key);
		Bson bson = Filters.and(Filters.gte("score", startScore), Filters.lte("score", endScore));
		FindIterable<Document> score = rank.find(bson).sort(new BasicDBObject("score", 1)).batchSize(10000);
		MongoCursor<Document> iterator = score.iterator();
		Set<Object> objects = new HashSet<>();
		while (iterator.hasNext()) {
			Document next = iterator.next();
			objects.add(next);
		}
		return objects;
	}

	/**
	 * 获取该元素的排名
	 *
	 * @param key
	 * @param member
	 */
	@Override
	public long zrank(String key, String member) {
		double zscore = zscore(key, member);
		if (zscore == -1) {
			return -1;
		}
		// 分数小于某个值; 并且
		MongoCollection<Document> rank = db.getCollection(key);
		FindIterable<Document> sort = rank.find(Filters.lte("score", zscore)).sort(new BasicDBObject("score", -1));
		MongoCursor<Document> iterator = sort.iterator();

		long count = rank.count(Filters.lte("score", zscore));

		int reduceCount = 0;
		while (iterator.hasNext()) {
			if (iterator.next().get("name").toString().equals(member)) {
				break;
			}
			reduceCount++;
		}

		return count - reduceCount;
	}

	/**
	 * 获取该元素的分数
	 *
	 * @param key
	 * @param member
	 */
	@Override
	public double zscore(String key, String member) {
		MongoCollection<Document> rank = db.getCollection(key);
		FindIterable<Document> score = rank.find(Filters.eq("name", member));
		Document first = score.first();
		if (first == null) {
			return -1;
		}
		return Double.parseDouble(first.get("name").toString());
	}

	@Override
	public void zadd(String key, double score, String member) {
		MongoCollection<DBObject> table = db.getCollection("rank", DBObject.class);
		BasicDBObject doc = new BasicDBObject();
		doc.append("member", member).append("score", score);
		table.insertOne(doc);
	}

	@Override
	public void zadd(String key, Map<String, Double> scoreMembers) {
		MongoCollection<DBObject> table = db.getCollection("rank", DBObject.class);
		for (Map.Entry<String, Double> entry : scoreMembers.entrySet()) {
			BasicDBObject doc = new BasicDBObject();
			String member = entry.getKey();
			Double score = entry.getValue();
			doc.append("member", member).append("score", score);
			table.insertOne(doc);
		}
	}

	@Override
	public void del(String key) {
		MongoCollection<Document> collection = db.getCollection(key);
		collection.deleteMany(new Document());
	}
}
