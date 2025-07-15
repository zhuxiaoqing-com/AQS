package com.mdb;

import com.mdb.stoarge.StorageEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mdb {
    public static Logger logger = LoggerFactory.getLogger(Mdb.class.getName());
    private static Mdb instance = new Mdb();

    public static Mdb getInstance() {
        return instance;
    }

    private Mdb() {
    }


    private boolean xBeanCheck = false;
    // checkPointModify标识
    private volatile int checkPointCount = 0;
    private StorageEngine storageEngine;


    public boolean isXBeanCheck() {
        return xBeanCheck;
    }

    public void setXBeanCheck(boolean xBeanCheck) {
        this.xBeanCheck = xBeanCheck;
    }

    public int getCheckPointCount() {
        return checkPointCount;
    }

    public boolean isModifyTrue() {
        if ((checkPointCount & 1) == 0) {
            return true;
        }
        return false;
    }

    public void setCheckPointCount(int checkPointCount) {
        this.checkPointCount = checkPointCount;
    }

    public StorageEngine getStorage() {
        return storageEngine;
    }
}
