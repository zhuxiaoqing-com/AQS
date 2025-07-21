package com.mdb.stoarge;

import com.mdb.exception.MdbError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class StorageMysql implements StorageEngine {

	private static final Logger log = LoggerFactory.getLogger(StorageMysql.class);

	private final LoggerMysql logger;


	// 阈值，超过这个值才批量操作
	private int THRESHOLD = Integer.getInteger("mysql.batch.threshold", 30);
	// 批量操作每批的数量
	private int BATCH_PER_COUNT = Integer.getInteger("mysql.batch.per", 500);

	public static final int COST_MS_WARN = 100;

	public StorageMysql(LoggerMysql logger) {
		this.logger = logger;
	}



	@Override
	public void initTable(String tableName) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = logger.getWriteConnection();
			if(logger.isAutoCreate()) {
				stat = conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + tableName
						+ "(k VARCHAR(128) NOT NULL PRIMARY KEY, v JSON NOT NULL) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI");
				stat.execute();
			}else {
				stat = conn.prepareStatement("SHOW TABLES LIKE ?");
				stat.setString(1, tableName);
				rs = stat.executeQuery();
				if (!rs.next()) {
					throw new MdbError("table is not inited, please execute initTable.sql into mysql.");
				}
			}

		} catch (Exception e) {
			log.error("init error", e);
			throw new MdbError(e);
		} finally {
			LoggerMysql.release(stat, conn);
		}
	}

	@Override
	public void replace(String tableName, String key, String value) {
		long begin = System.nanoTime();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = logger.getWriteConnection();
			stmt = conn.prepareStatement("REPLACE INTO " + tableName + " VALUES(?, ?)");
			stmt.setString(1, key);
			stmt.setString(2, value);
			stmt.executeUpdate();
		} catch (Exception e) {
			log.error("replace error ", e);
			throw new MdbError(e);
		} finally {
			LoggerMysql.release(stmt, conn);

			long costMs = (long) ((System.nanoTime() - begin) * 1e-6);
			if (costMs > COST_MS_WARN) {
				log.warn("{} replace key : {} value: {}  cost: {} ms", tableName, key, value,
						costMs);
			}
		}
	}

	@Override
	public void replaceBatch(String tableName, Map<String, String> kvs) {
		// 单个
		if (kvs.size() < THRESHOLD) {
			kvs.forEach((k, v) -> replace(tableName, k, v));
			return;
		}
		long begin = System.nanoTime();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = logger.getWriteConnection();
			stmt = conn.prepareStatement("REPLACE INTO " + tableName + " VALUES(?, ?)");
			int idx = 0;
			for (Map.Entry<String, String> entry : kvs.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				stmt.setString(1, key);
				stmt.setString(2, value);
				stmt.addBatch();
				idx++;
				log.debug("{} replace key : {} value: {} ", tableName, key, value);
				if (idx == BATCH_PER_COUNT) {
					stmt.executeBatch();
					log.debug("{} execute batch ", tableName);
					stmt.clearBatch();
					idx = 0;
				}
			}
			if (idx != 0) {
				stmt.executeBatch();
				log.debug("{} execute batch ", tableName);
				stmt.clearBatch();
			}

		} catch (Exception e) {
			log.error("replace batch error", e);
			throw new MdbError(e);
		} finally {
			LoggerMysql.release(stmt, conn);
			long costMs = (long) ((System.nanoTime() - begin) * 1e-6);
			if (costMs > COST_MS_WARN) {
				log.warn("{} batch replace cost: {} ms", tableName, (System.nanoTime() - begin) * 1e-6);
			}
		}
	}

	@Override
	public void removeBatch(String tableName, Collection<String> keys) {
		// 单个
		if (keys.size() < THRESHOLD) {
			keys.forEach(k -> remove(tableName, k));
			return;
		}
		long begin = System.nanoTime();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = logger.getWriteConnection();
			stmt = conn.prepareStatement("DELETE FROM " + tableName + " WHERE k = ?");
			int idx = 0;
			for (String key : keys) {
				stmt.setString(1, key);
				stmt.addBatch();
				idx++;
				log.debug("{} batch remove key : {} ", tableName, key);
				if (idx == BATCH_PER_COUNT) {
					stmt.executeBatch();
					log.debug("{} execute batch", tableName);
					stmt.clearBatch();
					idx = 0;
				}
			}
			if (idx != 0) {
				stmt.executeBatch();
				log.debug("{} execute batch ", tableName);
				stmt.clearBatch();
			}

		} catch (Exception e) {
			log.error("remove batch", e);
			throw new MdbError(e);
		} finally {
			LoggerMysql.release(stmt, conn);

			long costMs = (long) ((System.nanoTime() - begin) * 1e-6);
			if (costMs > COST_MS_WARN) {
				log.warn("{} batch remove keys cost : {} ms", tableName, (System.nanoTime() - begin) * 1e-6);
			}
		}
	}

	@Override
	public boolean insert(String tableName, String key, String value) {
		long begin = System.nanoTime();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = logger.getWriteConnection();
			stmt = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?, ?)");
			stmt.setString(1, key);
			stmt.setString(2, value);
			stmt.executeUpdate();
		} catch (Exception e) {
			log.error("insert error", e);
			return false;
		} finally {
			LoggerMysql.release(stmt, conn);

			long costMs = (long) ((System.nanoTime() - begin) * 1e-6);
			if (costMs > COST_MS_WARN) {
				log.warn("{} batch remove keys cost : {} ms", tableName, (System.nanoTime() - begin) * 1e-6);
			}
			log.info("{} insert key : {}  cost : {} ms", tableName, key, (System.nanoTime() - begin) * 1e-6);
		}
		return true;
	}

	@Override
	public void remove(String tableName, String key) {
		long begin = System.nanoTime();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = logger.getWriteConnection();
			stmt = conn.prepareStatement("DELETE FROM " + tableName + " WHERE k = ?");
			stmt.setString(1, key);
			stmt.executeUpdate();
		} catch (Exception e) {
			log.error("remove error", e);
			throw new MdbError(e);
		} finally {
			LoggerMysql.release(stmt, conn);

			long costMs = (long) ((System.nanoTime() - begin) * 1e-6);
			if (costMs > COST_MS_WARN) {
				log.warn("{} remove key : {} cost : {} ms", tableName, key, (System.nanoTime() - begin) * 1e-6);
			}
		}
	}

	@Override
	public boolean exist(String tableName, String key) {
		long begin = System.nanoTime();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = logger.getReadConnection();
			stmt = conn.prepareStatement("SELECT 1 FROM " + tableName + " WHERE k = ?");
			stmt.setString(1, key);
			rs = stmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			log.error("exist error ", e);
			throw new MdbError(e);
		} finally {
			LoggerMysql.release(rs, stmt, conn);
			long costMs = (long) ((System.nanoTime() - begin) * 1e-6);
			if (costMs > COST_MS_WARN) {
				log.warn("{} exist key : {} cost : {} ms", tableName, key.replace("\"", ""), (System.nanoTime() - begin) * 1e-6);
			}
		}
	}

	@Override
	public String find(String tableName, String key) {
		long begin = System.nanoTime();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = logger.getReadConnection();
			stmt = conn.prepareStatement("SELECT v FROM " + tableName + " WHERE k=?");
			stmt.setString(1, key);
			rs = stmt.executeQuery();
			return rs.next() ? rs.getString(1) : null;
		} catch (Exception e) {
			log.error("find error", e);
			throw new MdbError(e);
		} finally {
			LoggerMysql.release(rs, stmt, conn);
			long costMs = (long) ((System.nanoTime() - begin) * 1e-6);
			if (costMs > COST_MS_WARN) {
				log.warn("{} find key : {} cost : {} ms", tableName, key.replace("\"", ""), (System.nanoTime() - begin) * 1e-6);
			}
		}
	}

	@Override
	public boolean detect() {
		Connection writeConnection = null;
		try {
			writeConnection = logger.getWriteConnection();
			if(writeConnection == null) {
				return false;
			}
			return writeConnection.isValid(3000);
		} catch (Exception e) {
			log.error("detect error", e);
			return false;
		} finally {
			LoggerMysql.release(writeConnection);
		}
	}

	@Override
	public void close() {
		logger.close();
	}


}
