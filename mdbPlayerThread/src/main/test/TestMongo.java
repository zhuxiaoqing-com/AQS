import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.util.JSON;

public class TestMongo {
	private static final String KEY = "key";

	private static final String VALUE = "value";

	public static void main(String[] args) {
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

		MongoClient client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017/?minPoolSize=5", build));
		MongoDatabase database = client.getDatabase("ZMazujun");
		String tableName = "mzjaaaaaaaaaaaaaaaaa";
		database.createCollection(tableName);
//		table = database.getCollection(tableName, DBObject.class);
//		System.err.println(table.count());
//		System.err.println(find("123"));
//		client.close();
//		Player player = FastJson.parseObject("", xbean.lobby.Player.class);
//		System.err.println(player);
	}

	private static MongoCollection<DBObject> table;

	public static String find(String key) {
		BasicDBObject query = new BasicDBObject(KEY, key);
		FindIterable<DBObject> cursor = table.find(query);
		DBObject doc = cursor.first();
		if (doc == null)
			return null;
		Object value = doc.get(VALUE);
		return JSON.serialize(value);
	}

	public static boolean insert(String key, String value) {
		BasicDBObject doc = new BasicDBObject();
		doc.append(KEY, key).append(VALUE, JSON.parse(value));
		table.insertOne(doc);
		return true;
	}

	public static void replace(String key, String value) {
		BasicDBObject keyQuery = new BasicDBObject(KEY, key);

		BasicDBObject doc = new BasicDBObject();
		doc.append(KEY, key);
		Object obj = JSON.parse(value);
		doc.append(VALUE, obj);
		table.replaceOne(keyQuery, doc, new UpdateOptions().upsert(true));
	}
}
