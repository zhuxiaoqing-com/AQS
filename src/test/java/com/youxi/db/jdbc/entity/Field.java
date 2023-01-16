package com.youxi.db.jdbc.entity;

public class Field {
	private String name;
	private String type;

	public Field(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public Field(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Field{" +
				"name='" + name + '\'' +
				", type='" + type + '\'' +
				'}';
	}

	public String createFieldSql() {
		// todo 先默认都用varchar
		switch (type) {
			case "int":
		//		return name+" int(11) NULL DEFAULT NULL";
			case "double":
		//		return name+" double(255, 0) NULL DEFAULT NULL";
			case "string":
				return name+" varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL";
		}
		System.out.println("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return null;
	}

}
