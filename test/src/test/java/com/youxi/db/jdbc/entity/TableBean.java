package com.youxi.db.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

public class TableBean {
	private String tableName = "";
	private List<Field> fieldList = new ArrayList<>();

	public TableBean(String tableName, List<Field> fieldList) {
		this.tableName = tableName;
		this.fieldList = fieldList;
	}

	public TableBean() {

	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	public String getTableName() {
		return tableName;
	}

	public List<Field> getFieldList() {
		return fieldList;
	}

	@Override
	public String toString() {
		return "TableBean{" +
				"tableName='" + tableName + '\'' +
				", fieldList=" + fieldList +
				'}';
	}

	public String creatTableSQL() {
		String a= "CREATE TABLE IF NOT EXISTS " + this.tableName + "(%s) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;";

		StringBuilder fieldStr = new StringBuilder();
		for (int i = 0; i < fieldList.size(); i++) {
			fieldStr.append(fieldList.get(i).createFieldSql(i == 0)).append(",");
		}
		String s = fieldStr.deleteCharAt(fieldStr.length() - 1).toString();
		String format = String.format(a, s);
		return format;
	}
}
