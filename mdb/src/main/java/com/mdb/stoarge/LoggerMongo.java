package com.mdb.stoarge;

import com.mdb.exception.MdbError;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mongodb
 * 
 * @author Eric.Ma
 *
 */
public class LoggerMongo implements LoggerEngine {

	private static Logger logger = LoggerFactory.getLogger(LoggerMongo.class);

	private final MongoClient client;

	private String dataBase;

	public LoggerMongo(MdbMetadata mdb) {
		this.dataBase = mdb.getDataBase();
		try {
			/*MongoCredential credential = MongoCredential.createMongoCRCredential("username", "dbname",
					"password".toCharArray());*/
			MongoClientOptions.Builder build = new MongoClientOptions.Builder();
			build.threadsAllowedToBlockForConnectionMultiplier(30); // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			build.serverSelectionTimeout(10000); //设置服务器选择超时以毫秒为间隔，这定义了在抛出异常之前，驱动程序等待服务器选择成功的时间
			/*
			 * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
			 * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
			 * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
			 */
			build.maxWaitTime(1000 * 60);
			build.connectTimeout(1000 * 60 * 1); // 与数据库建立连接的timeout设置为1分钟

			client = new MongoClient(new MongoClientURI(mdb.getDbHome(), build));
		} catch (Exception e) {
			throw new MdbError(e);
		}
	}

	@Override
	public void close() {
		try {
			client.close();
		} catch (Exception e) {
			logger.error("close connection", e);
		}
	}

	@Override
	public void dropTables(String[] tableNames) throws Exception {
	}

	public MongoClient getMongoClient() {
		return client;
	}

	public String getDataBase() {
		return dataBase;
	}

}
