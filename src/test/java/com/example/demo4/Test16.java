package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.youxi.building.Misc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test16 {

	class Obj {
		private int obj1;
		private int obj2;

		public int getObj1() {
			return obj1;
		}

		public void setObj1(int obj1) {
			this.obj1 = obj1;
		}

		public int getObj2() {
			return obj2;
		}

		public void setObj2(int obj2) {
			this.obj2 = obj2;
		}
	}
	class ChildObj extends Obj {
		private int childObj1;
		private int childObj2;

		public int getChildObj1() {
			return childObj1;
		}

		public void setChildObj1(int childObj1) {
			this.childObj1 = childObj1;
		}

		public int getChildObj2() {
			return childObj2;
		}

		public void setChildObj2(int childObj2) {
			this.childObj2 = childObj2;
		}
	}

	class ObjList<T extends Obj>{
		private T a;

		public T getA() {
			return a;
		}

		public void setA(T a) {
			this.a = a;
		}
	}



	@Test
	public void test01() throws InterruptedException {
		ChildObj childObj = new ChildObj();
		childObj.setObj1(1);
		childObj.setObj2(2);
		childObj.setChildObj1(11);
		childObj.setChildObj2(22);

		ObjList objObjList = new ObjList<>();
		objObjList.setA(childObj);


		String o = JSON.toJSONString(childObj);
		System.out.println(o);
	}
}

/**
 * abcdefghijklmnopqrst
 */













