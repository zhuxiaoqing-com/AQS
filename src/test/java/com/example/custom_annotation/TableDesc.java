package com.example.custom_annotation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TableDesc {
    private static SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("_yyyy_MM_dd");
    private static SimpleDateFormat YYYY_MM = new SimpleDateFormat("_yyyy_MM");
    private static SimpleDateFormat YYYY = new SimpleDateFormat("_yyyy");
    private String createSql;
    private String insertSql;
    private String name;
    private String primaryKey;
    //private TableCycle cycle;
    private List<ColumnDesc> columns = new ArrayList();
    private List<IndexDesc> indexDescs = new ArrayList();
    private int noAutoIncrementColumnCount;

    TableDesc() {
    }

    public List<IndexDesc> getIndexDescs() {
        return indexDescs;
    }

    public void setIndexDescs(List<IndexDesc> indexDescs) {
        this.indexDescs = indexDescs;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public TableCycle getCycle() {
        return this.cycle;
    }

    public void setCycle(TableCycle cycle) {
        this.cycle = cycle;
    }*/

    public List<ColumnDesc> getColumns() {
        return this.columns;
    }

    public void setColumns(List<ColumnDesc> columns) {
        this.columns = columns;
    }

    public String getPrimaryKey() {
        return this.primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getCreateSql() {
        return this.createSql;
    }

    public void setCreateSql(String createSql) {
        this.createSql = createSql;
    }

    private String buildCreateSql() {
        StringBuffer ddl = new StringBuffer();
        ddl.append("CREATE TABLE IF NOT EXISTS `%s` (\n");
        Iterator var2 = this.columns.iterator();

        while(var2.hasNext()) {
            ColumnDesc col = (ColumnDesc)var2.next();
            ddl.append(col.toDDL()).append(",\n");
        }

        // todo  新加的代码 ======= 开始 =======
        Iterator<IndexDesc> indexIterator = this.indexDescs.iterator();
        while(indexIterator.hasNext()) {
            IndexDesc index = indexIterator.next();
            ddl.append(index.toIndexDDL()).append(",\n");
        }
        // todo  新加的代码 ======= 结束 =======

        ddl.append("PRIMARY KEY (`" + this.primaryKey + "`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8");
        return ddl.toString();
    }

    private String buildInsertSQL() {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into `%s` ");
        StringBuilder fields = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        Iterator var4 = this.columns.iterator();

        while(var4.hasNext()) {
            ColumnDesc col = (ColumnDesc)var4.next();
            if (!col.isAutoIncrement()) {
                fields.append("`").append(col.getName()).append("`,");
                values.append("?,");
            }
        }

        if (fields.length() > 0) {
            fields.deleteCharAt(fields.length() - 1).append(")");
        }

        if (values.length() > 0) {
            values.deleteCharAt(values.length() - 1).append(")");
        }

        sql.append(fields).append(" values ").append(values);
        return sql.toString();
    }

    public Object[] buildInsertParam(Object log) {
        Object[] ret = new Object[this.noAutoIncrementColumnCount];
        int index = 0;

        try {
            Iterator var4 = this.columns.iterator();

            while(var4.hasNext()) {
                ColumnDesc col = (ColumnDesc)var4.next();
                if (!col.isAutoIncrement()) {
                    ret[index] = col.getReadMethod().invoke(log);
                    ++index;
                }
            }

            return ret;
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public String buildName(long time) {
        /*switch(this.cycle) {
            case DAY:
                return this.name + YYYY_MM_DD.format(new Date(time));
            case MONTH:
                return this.name + YYYY_MM.format(new Date(time));
            case YEAR:
                return this.name + YYYY.format(new Date(time));
            case SINGLE:
                return this.name;
            default:
                return this.name;
        }*/
        return "";
    }

    public String getInsertSql() {
        return this.insertSql;
    }

    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }

    public void init() {
        int count = 0;
        Iterator var2 = this.columns.iterator();

        while(var2.hasNext()) {
            ColumnDesc col = (ColumnDesc)var2.next();
            if (!col.isAutoIncrement()) {
                ++count;
            }
        }

        this.noAutoIncrementColumnCount = count;
        this.createSql = this.buildCreateSql();
        this.insertSql = this.buildInsertSQL();
    }

    public void addCol(ColumnDesc newCol) {
        for(int i = 0; i < this.columns.size(); ++i) {
            ColumnDesc col = (ColumnDesc)this.columns.get(i);
            if (col.getName().equals(newCol.getName())) {
                this.columns.remove(i);
                this.columns.add(i, newCol);
                return;
            }
        }

        this.columns.add(newCol);
    }
}
