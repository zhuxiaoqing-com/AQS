package com.mdb.xmlgen.utils;

import com.mdb.xmlgen.Main;

import java.io.File;
import java.io.PrintStream;

public final class FileOperation {
	private static File toFile(File base, String name) {
		File file = new File(base, name);
		file.getParentFile().mkdirs();
		if (!file.getParentFile().exists())
			throw new RuntimeException("can not create dirs: " + file.getParent());
		return file;
	}

	public static PrintStream fopen(File path, String name) {
		try {
			return new PrintStream(new CachedFileOutputStream(toFile(path, name)), false, Main.outputEncoding);
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static PrintStream fopen(String name) {
		return fopen(new File(Main.outputPath), name);
	}
}
