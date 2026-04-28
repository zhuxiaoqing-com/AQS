package com.mdb;

import com.mdb.exception.MdbError;
import com.mdb.stoarge.*;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import com.mdb.xmlgen.xmlparse.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Tables {

    public static final Logger log = LoggerFactory.getLogger(Tables.class);

    protected final Map<String, TTable> tables = new LinkedHashMap<>();

    // checkPoint的全局锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    protected ReentrantReadWriteLock.WriteLock writelock = lock.writeLock();
    protected ReentrantReadWriteLock.ReadLock readlock = lock.readLock();

    protected StorageEngine storage;

   // protected List<StorageInterface> storages = new ArrayList<>();

    protected MdbMetadata mdbMetadata;
    protected Mdb mdb;

    void add(TTable table) {
        if (null != tables.put(table.getName(), table))
            throw new MdbError("duplicate table name " + table.getName());
    }

    public void open(Mdb mdb, MdbMetadata mdbMetadata) {
        this.mdb = mdb;
        this.mdbMetadata = mdbMetadata;
        if (null != storage)
            log.warn("tables opened before {}", mdbMetadata.getName());
        switch (mdbMetadata.getDBType()) {
            case MYSQL:
                LoggerMysql loggerMysql = new LoggerMysql(mdbMetadata);
                storage = new StorageMysql(loggerMysql);
                break;
            case MONGO:
                LoggerMongo loggerMongo = new LoggerMongo(mdbMetadata);
                storage = new StorageMongo(loggerMongo);
                break;
        }

        for (TTable table : tables.values()) {
            Table tableMeta = mdbMetadata.getTable(table.getName());
            if (tableMeta == null) {
                throw new MdbError("table metadata not found: " + table.getName());
            }
            table.setMeta(tableMeta);
            table.setTables(this);
        }

        fillStorage();
        // 检测建表
        tables.values().forEach(a -> storage.initTable(a.getName()));
    }

    public void fillStorage() {
    }

    final void close() {
        writelock.lock();
        try {
            tables.values().forEach(table -> table.close());
            if (null != storage) {
                storage.close();
                storage = null;
            }
        } finally {
            writelock.unlock();
        }
    }


    public ReentrantReadWriteLock.WriteLock getWriteLock() {
        return writelock;
    }

    public ReentrantReadWriteLock.ReadLock getReadlock() {
        return readlock;
    }


    public TTable getTable(String table) {
        readlock.lock();
        try {
            return tables.get(table);
        } finally {
            readlock.unlock();
        }
    }

    public StorageEngine getStorage() {
        return storage;
    }

    public Collection<TTable> getTables() {
        return tables.values();
    }

    public String getName() {
        return mdbMetadata.getName();
    }

    public MdbMetadata getMdbMetadata() {
        return mdbMetadata;
    }

    public Mdb getMdb() {
        return mdb;
    }
}
