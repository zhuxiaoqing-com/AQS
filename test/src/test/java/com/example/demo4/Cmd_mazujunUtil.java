package com.example.demo4;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 解析表工具
 * 
 * @author Eric.Ma
 *
 */
public class Cmd_mazujunUtil {
	public static void main(String[] args) {
		convert();
	}

	public static void convert() {
		try {
			// 创建一个 DocumentBuilderFactory 对象
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// 创建DocumentBuilder对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 创建 Document 对象
			Document document = db.newDocument();

			// 隐藏 XML文件 standalone 属性
			document.setXmlStandalone(true);
			// 创建根节点
			Element school = document.createElement("school");
			// 创建子节点
			Element student = document.createElement("student");
			// 创建student的子节点
			Element name = document.createElement("name");
			Element age = document.createElement("age");
			Element address = document.createElement("address");

			// 给 name 节点添加 文本内容
			name.setTextContent("小明");
			age.setTextContent("18");
			address.setTextContent("北京市海定区五道口");

			// 把子节点 添加到 student 节点下
			student.appendChild(name);
			student.appendChild(age);
			student.appendChild(address);

			// 向 studet 节点添加属性和属性值
			student.setAttribute("id", "1");
			// 向 school 添加子节点
			school.appendChild(student);
			// 将 根节点(已经包含子节点 )添加到dom树中
			document.appendChild(school);

			// ..将 dom树转换为 XML文件
			// 创建 TransformerFactory 对象
			TransformerFactory tff = TransformerFactory.newInstance();
			// 创建 Transformer 对象
			Transformer tf = tff.newTransformer();
			// 设置生成的 XML 文件自动换行
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
			tf.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			// 使用 Transformer 的 transform 方法把Dom树转换成 XML 文件
			tf.transform(new DOMSource(document), new StreamResult(new File("School_01.xml")));

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
