package com.example.custom_annotation;

public class IndexDesc {
    private String name;

    private String[] columnNames;

    private boolean unique;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String toIndexDDL() {
        StringBuilder builder = new StringBuilder();
        builder.append(indexType());
        builder.append(" ");
        builder.append(name);
        builder.append(" ");
        builder.append(columnName());
        return builder.toString();
    }

    private String indexType() {
        return unique ? "UNIQUE INDEX" : "INDEX";
    }

    private String columnName() {
        if (columnNames.length < 1) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (String columnName : columnNames) {
            builder.append(columnName);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(")");
        return builder.toString();
    }
}
