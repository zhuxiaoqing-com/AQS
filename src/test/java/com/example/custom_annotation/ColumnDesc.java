package com.example.custom_annotation;

import java.lang.reflect.Method;

public class ColumnDesc {
    private Method readMethod;
    private String name;
    private String type;
    private int size;
    private boolean allowNull;
    private boolean autoIncrement;
    private String commit;

    ColumnDesc() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAllowNull() {
        return this.allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public boolean isAutoIncrement() {
        return this.autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public Method getReadMethod() {
        return this.readMethod;
    }

    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }

    public String getCommit() {
        return this.commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    private String getFieldType() {
        return !this.type.equalsIgnoreCase("text") && !this.type.equalsIgnoreCase("longtext") && !this.type.equalsIgnoreCase("blob") ? this.type + "(" + this.size + ")" : this.type;
    }

    private String getNullable() {
        return this.allowNull ? "" : "\tnot null";
    }

    public String toDDL() {
        String ddl = "`" + this.name + "`\t" + this.getFieldType() + this.getNullable() + this.getAutoIncrementable() + this.getCommitStr();
        return ddl;
    }

    private String getAutoIncrementable() {
        return this.autoIncrement ? "\tAUTO_INCREMENT" : "";
    }

    private String getCommitStr() {
        return this.commit.equals("") ? "" : "\tCOMMENT '" + this.commit + "'";
    }
}

