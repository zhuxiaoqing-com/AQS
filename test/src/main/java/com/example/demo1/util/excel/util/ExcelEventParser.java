package com.example.demo1.util.excel.util;

import com.example.demo1.util.excel.util.handler.DefaultSheetHandler;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;

/**
 * 解析大数据量Excel工具类
 *
 * @author RobinTime
 */
public class ExcelEventParser implements IExcelParser {
	private static final Logger logger = LoggerFactory.getLogger(ExcelEventParser.class);
	/**
	 * 读取数据
	 */
	private List<SheetObj> sheetObjs = new ArrayList<>();


	/**
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public List<SheetObj> parse(File file, Predicate<String> filter) {
		// 打开表格文件输入流
		OPCPackage pkg = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			pkg = OPCPackage.open(fileInputStream);
			// 创建表阅读器
			XSSFReader reader;
			reader = new XSSFReader(pkg);

			// 根据名称查找sheet
			String fileName = file.getName();
			StylesTable styles = reader.getStylesTable();
			XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator) reader.getSheetsData();
			InputStream shellStream = null;
			while (sheets.hasNext()) {
				try {
					shellStream = sheets.next();
					String sheetName = sheets.getSheetName();
					if (!filter.test(sheetName)) {
						continue;
					}
					System.out.println("filename:" + fileName);
					System.out.println("sheetName:" + sheetName);
					SheetObj sheetObj = new SheetObj(fileName, sheetName);
					parse(styles, shellStream, pkg, sheetObj);
					sheetObjs.add(alignSheetData(sheetObj));
				} catch (SAXException e) {
					logger.error("读取表格出错");
					shellStream.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pkg != null) {
				try {
					pkg.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sheetObjs;

	}

	private void parse(StylesTable styles, InputStream shellStream, OPCPackage pkg, SheetObj sheetObj) throws IOException, SAXException {
		InputSource sheetSource = new InputSource(shellStream);
		ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
		DefaultSheetHandler contentHandler = new DefaultSheetHandler();
		contentHandler.init(sheetObj);// 设置读取出的数据
		// 获取转换器
		XMLReader parser = getSheetParser(styles, strings, contentHandler);
		parser.parse(sheetSource);
	}


	/**
	 * 获取读取表格的转换器
	 *
	 * @return 读取表格的转换器
	 * @throws SAXException SAX错误
	 */
	protected XMLReader getSheetParser(StylesTable styles, ReadOnlySharedStringsTable strings, DefaultSheetHandler contentHandler) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader();
		parser.setContentHandler(new XSSFSheetXMLHandler(styles, strings, contentHandler, false));
		return parser;
	}

	/**
	 * 对齐 sheetData 数据
	 * 事件模式不会读取空数据，
	 * 比如 1行 只有 A,B列有数据
	 * 比如 2行 只有 A,B,C,D,E列有数据
	 * <p>
	 * 那么第一行的 list.size = 2;
	 * 第二行的 list.size = 5;
	 * <p>
	 * 所以为了后面使用者数据的易用性，我们需要将其对齐,填充第一行的list,使其list.size=5
	 *
	 * @param sheet sheet
	 */
	private SheetObj alignSheetData(SheetObj sheet) {
		List<List<String>> data = sheet.getData();
		Optional<List<String>> max = data.stream().max(Comparator.comparingInt(List::size));
		int maxSize = max.orElseGet(Collections::emptyList).size();
		if (maxSize <= 1) {
			return sheet;
		}

		for (List<String> cells : data) {
			if (cells.size() >= maxSize) {
				continue;
			}
			for (int i = cells.size(); i < maxSize; i++) {
				cells.add("");
			}
		}
		return sheet;
	}
}