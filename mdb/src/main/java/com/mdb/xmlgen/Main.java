package com.mdb.xmlgen;

import com.mdb.utils.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import org.w3c.dom.Element;


import java.io.File;

public class Main {

	public static String outputEncoding = "UTF-8";
	public static String inputEncoding = "UTF-8";

	public static String inputXml = "mdb\\src\\main\\mdb.xml";
	public static String outputPath = "mdb\\src\\main\\beans";
	public static String sqlPath = "";
	

	public static void main(String[] args) throws Exception {
		// 解析xml, 构建xbean
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("-inputpath"))
				inputXml = ARGS(args, ++i);
			else if (args[i].equals("-outputpath"))
				outputPath = ARGS(args, ++i);
			else if (args[i].equals("-sqlpath"))
				sqlPath = ARGS(args, ++i);
			else
				usage();
		}
		Element element = XMLUtils.getRootElement(new File(inputXml));
		MdbMetadata mdb = new MdbMetadata();
		mdb.initialize(element);
		Mdbgen.make(mdb, new File(outputPath));
		if(sqlPath == null || sqlPath.length() == 0)
			return;
		Mdbgen.genSql(mdb, new File(sqlPath));
		
	}

	static void usage() {
		System.out.println("Usage: java -cp mdb.jar warrior.mdb.xmlgen.Main");
		System.out.println("    -inputPath     mdb.xml path");
		System.out.println("    -outputpath    gen java code");
		System.out.println("    -sqlpath       gen sql script");
		System.exit(0);
	}

	private static String ARGS(String args[], int i) {
		if (i < args.length)
			return args[i];
		usage();
		return null;
	}
}
