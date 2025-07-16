package com.mdb.stoarge;

import com.mdb.exception.MdbError;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class LoggerMysql implements LoggerEngine {

	private static Logger logger = LoggerFactory.getLogger(LoggerMysql.class);

	private final String url;
	private String database;
	private boolean autoCreate;
	private HikariDataSource readConnection = null;
	private HikariDataSource writeConnection = null;

	//private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private  String DRIVER_CLASS = System.getProperty("mysql.jdbc.driver","com.mysql.jdbc.Driver");

	public LoggerMysql(MdbMetadata mdb) {
		try {
			this.autoCreate = mdb.isAutoCreate();
			this.database = mdb.getCache().getDataBase();
			if(autoCreate) {
				this.url = mdb.getDbHome().replaceAll(database, "");
			}else {
				this.url = mdb.getDbHome();
			}
			this.writeConnection = new HikariDataSource(InitHikariConfig("mdb-write-pool", this.url,
					mdb.getCache().getMinDbPoolSize(), mdb.getCache().getMaxDbPoolSize()));
			this.readConnection = new HikariDataSource(InitHikariConfig("mdb-read-pool", this.url,
					mdb.getCache().getMinDbPoolSize(), mdb.getCache().getMaxDbPoolSize()));
			checkDatabase(database);
			if (readConnection == null || writeConnection == null) {
				logger.error("MySQL Connection Pool Init Error, System Exit!");
				Runtime.getRuntime().halt(0);
			} else {
				logger.info("MySQL Connection Pool Init OK!");
			}
		} catch (Exception e) {
			logger.error("LoggerMysql error", e);
			Runtime.getRuntime().halt(0);
			throw new MdbError(e);
		}
	}
	
	public boolean isAutoCreate() {
		return autoCreate;
	}

	private void checkDatabase(String dataBase) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = writeConnection.getConnection();
			stat = conn.prepareStatement("show databases like ?");
			stat.setString(1, dataBase);
			rs = stat.executeQuery();
			if (!rs.next()) {
				if(autoCreate) {
					try (Statement st = conn.createStatement()) {
						st.execute("create database " + dataBase);
						logger.info("database {} not exsits, created successfully", dataBase);
					}
				}else {
					throw new MdbError("database is not inited, please execute initDB.sql into mysql.");
				}
			}

		} catch (SQLException e) {
			throw new MdbError(e);
		} finally {
			release(rs, stat, conn);
		}
	}

	public String getTableName(String tableName) {
		return database + "." + tableName;
	}

	private HikariConfig InitHikariConfig(String poolName, String url, int minPool, int maxPool) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setPoolName(poolName);
		hikariConfig.setDriverClassName(DRIVER_CLASS);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", 512);
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		hikariConfig.setConnectionTimeout(4000); // 4 Second
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setAutoCommit(true);
		hikariConfig.setMinimumIdle(minPool);
		hikariConfig.setMaximumPoolSize(maxPool);
		return hikariConfig;
	}

	@Override
	public void close() {
		try {
			writeConnection.close();
			readConnection.close();
		} catch (Exception e) {
			logger.error("close connection", e);
		}
	}

	@Override
	public void dropTables(String[] tableNames) throws Exception {
	}

	public Connection getReadConnection() throws SQLException {
		return readConnection.getConnection();
	}

	public Connection getWriteConnection() throws SQLException {
		return writeConnection.getConnection();
	}

	public static void release(AutoCloseable... res) {
		try {
			for (AutoCloseable ac : res) {
				if (ac != null)
					ac.close();
			}
		} catch (Exception e) {
		}
	}
}
