package com.example.custom_annotation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

@Table(
        primaryKey = "id"
)
public abstract class AbstractLog {
    private static Logger LOGGER = LoggerFactory.getLogger(AbstractLog.class);
    private static final Map<Class<?>, TableDesc> tableDescMap = new HashMap();

    public AbstractLog() {
    }

    void init() throws Exception {
        Class<?> clazz = this.getClass();
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (table != null) {
            TableDesc desc = new TableDesc();
            //desc.setCycle(table.cycle());
            String tableName = table.tableName();
            if ("".equals(tableName)) {
                tableName = clazz.getSimpleName();
            }

            desc.setName(tableName);
            desc.setPrimaryKey(table.primaryKey());


            // todo  新加的代码 ======= 开始 =======
            HashMap<String, IndexDesc> map = new HashMap<>();
            Index[] indexes = table.indexes();
            for (Index index : indexes) {
                String[] columnNames = index.columnNames();
                if(columnNames.length == 0){
                    continue;
                }
                IndexDesc indexDesc = new IndexDesc();
                indexDesc.setUnique(index.unique());
                indexDesc.setName(index.name());
                indexDesc.setColumnNames(index.columnNames());
                map.put(index.name(), indexDesc);
            }
            desc.setIndexDescs(new ArrayList<>(map.values()));
            // todo  新加的代码 ======= 结束 =======


            ArrayList clazzList;
            for (clazzList = new ArrayList(); clazz.getSuperclass() != null && clazz != Object.class; clazz = clazz.getSuperclass()) {
                clazzList.add(0, clazz);
            }

            Iterator var6 = clazzList.iterator();

            while (var6.hasNext()) {
                Class<?> cl = (Class) var6.next();
                Field[] fields = cl.getDeclaredFields();
                Field[] var9 = fields;
                int var10 = fields.length;

                for (int var11 = 0; var11 < var10; ++var11) {
                    Field field = var9[var11];
                    Column column = (Column) field.getAnnotation(Column.class);
                    if (column != null) {
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), cl);
                        Method readMethod = pd.getReadMethod();
                        if (readMethod != null) {
                            ColumnDesc colDesc = new ColumnDesc();
                            colDesc.setAllowNull(column.allowNull());
                            colDesc.setAutoIncrement(column.autoIncrement());
                            String colName = column.colName();
                            if ("".equals(colName)) {
                                colName = field.getName();
                            }

                            colDesc.setName(colName.toLowerCase());
                            colDesc.setReadMethod(readMethod);
                            colDesc.setSize(column.size());
                            colDesc.setType(column.fieldType().name().toLowerCase());
                            colDesc.setCommit(column.commit());
                            desc.addCol(colDesc);
                        }
                    }
                }
            }

            desc.init();
            tableDescMap.put(this.getClass(), desc);
            desc.init();
            System.out.println(desc.getCreateSql());
            this.checkTable();
        }
    }

    String buildCreateSql() {
        return String.format(((TableDesc) tableDescMap.get(this.getClass())).getCreateSql(), ((TableDesc) tableDescMap.get(this.getClass())).buildName(System.currentTimeMillis()));
    }

    String buildInsertSQL() {
        return String.format(((TableDesc) tableDescMap.get(this.getClass())).getInsertSql(), ((TableDesc) tableDescMap.get(this.getClass())).buildName(System.currentTimeMillis()));
    }

    Object[] buildInsertParam() {
        return ((TableDesc) tableDescMap.get(this.getClass())).buildInsertParam(this);
    }

    private void checkTable() throws Exception {
        String buildName = ((TableDesc) tableDescMap.get(this.getClass())).buildName(System.currentTimeMillis());
        LOGGER.info("检测查表" + buildName);
        Connection connection = null;
        Statement statement = null;
/*
        try {
            connection = LogService.template.getPool().getConnection();
            List<String> tableNames = this.getTableName(connection);
            if (!tableNames.contains(buildName)) {
                if (((TableDesc) tableDescMap.get(this.getClass())).getCycle() == TableCycle.SINGLE) {
                    String createSql = this.buildCreateSql();

                    try {
                        LogService.template.update(createSql, new Object[0]);
                    } catch (Exception var19) {
                        var19.printStackTrace();
                    }
                }
            } else {
                List<ColumnDesc> columnDefine = this.getColumnDefine(connection, buildName);
                List<String> primaryKeys = this.getTablePrimaryKeys(connection, buildName);
                Iterator iterator = columnDefine.iterator();

                while (iterator.hasNext()) {
                    ColumnDesc next = (ColumnDesc) iterator.next();
                    if (primaryKeys.contains(next.getName())) {
                        iterator.remove();
                    }
                }

                List<ColumnDesc> newColumns = new ArrayList();
                Iterator var9 = ((TableDesc) tableDescMap.get(this.getClass())).getColumns().iterator();

                while (var9.hasNext()) {
                    ColumnDesc col = (ColumnDesc) var9.next();
                    if (!((TableDesc) tableDescMap.get(this.getClass())).getPrimaryKey().equals(col.getName())) {
                        newColumns.add(col);
                    }
                }

                List<String> compartor = TableCompareUtil.compare(buildName, newColumns, columnDefine);
                if (compartor.size() > 0) {
                    statement = connection.createStatement();
                    Iterator var24 = compartor.iterator();

                    while (var24.hasNext()) {
                        String string = (String) var24.next();
                        LOGGER.info("检查到变更：" + string);
                        statement.addBatch(string);
                    }

                    statement.executeBatch();
                }
            }

            LOGGER.info(buildName + "检查结束");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var18) {
                    LOGGER.error("关闭statement失败", var18);
                }
            }

            if (connection != null) {
                LogService.template.getPool().release(connection);
            }

        }*/

    }

    private List<ColumnDesc> getColumnDefine(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet columns = metaData.getColumns((String) null, "%", tableName, "%");
        ArrayList infos = new ArrayList();

        while (columns.next()) {
            ColumnDesc info = new ColumnDesc();
            info.setName(columns.getString("COLUMN_NAME").toLowerCase());
            info.setType(columns.getString("TYPE_NAME").toLowerCase());
            info.setSize(Integer.valueOf(columns.getInt("COLUMN_SIZE")));
            info.setAllowNull(Boolean.valueOf(columns.getBoolean("IS_NULLABLE")));
            infos.add(info);
        }

        return infos;
    }

    private List<String> getTablePrimaryKeys(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getPrimaryKeys((String) null, "%", tableName);
        ArrayList ret = new ArrayList();

        while (rs.next()) {
            ret.add(rs.getString("COLUMN_NAME"));
        }

        return ret;
    }

    private List<String> getTableName(Connection conn) throws SQLException {
        ResultSet tableRet = conn.getMetaData().getTables((String) null, (String) null, (String) null, (String[]) null);
        ArrayList tablenames = new ArrayList();

        while (tableRet.next()) {
            tablenames.add(tableRet.getString("TABLE_NAME"));
        }

        return tablenames;
    }
}
