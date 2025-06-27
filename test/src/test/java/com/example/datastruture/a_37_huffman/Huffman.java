package com.example.datastruture.a_37_huffman;

import com.example.demo1.util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/7/29 11:36
 * @Description:
 */
public class Huffman {
	public void compress(String path) throws IOException {
		File file = new File(path);
		// 文件不存在或者是目录
		if(!file.exists() || file.isDirectory()) {
			return;
		}

		BufferedReader bufferedReader = FileUtil.readFileToReader(file);

	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序。
	 *
	 * @param ary
	 *            byte数组
	 * @param offset
	 *            从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytesToInt(byte[] ary, int offset) {
		int value;
		value = (int) ((ary[offset]&0xFF)
				| ((ary[offset+1]<<8) & 0xFF00)
				| ((ary[offset+2]<<16)& 0xFF0000)
				| ((ary[offset+3]<<24) & 0xFF000000));
		return value;
	}

}
