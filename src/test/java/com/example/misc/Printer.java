package com.example.misc;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Sides;
import java.awt.print.PrinterJob;
import java.io.*;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/6 16:24
 * @Description:
 */
public class Printer {
	public static void main(String[] argv) throws Exception {
		File file = new File("E:\\a.jpg");
		String printerName = "HP MFP M436 PCL6";//打印机名包含字串
		JPGPrint(file,printerName);
	}
	// 传入文件和打印机名称
	public static void JPGPrint(File file,String printerName) throws PrintException {
		if (file == null) {
			System.err.println("缺少打印文件");
		}
		InputStream fis = null;
		try {
			// 设置打印格式，如果未确定类型，可选择autosense
			DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
			// 设置打印参数
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(new Copies(1)); //份数
			//aset.add(MediaSize.ISO.A4); //纸张
			// aset.add(Finishings.STAPLE);//装订
			aset.add(Sides.DUPLEX);//单双面
			// 定位打印服务
			PrintService printService = null;
			if (printerName != null) {
				//获得本台电脑连接的所有打印机
				PrintService[] printServices = PrinterJob.lookupPrintServices();
				if(printServices == null || printServices.length == 0) {
					System.out.print("打印失败，未找到可用打印机，请检查。");
					return ;
				}
				//匹配指定打印机
				for (int i = 0;i < printServices.length; i++) {
					System.out.println(printServices[i].getName());
					if (printServices[i].getName().contains(printerName)) {
						printService = printServices[i];
						break;
					}
				}
				if(printService==null){
					System.out.print("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
					return ;
				}
			}
			fis = new FileInputStream(file); // 构造待打印的文件流
			Doc doc = new SimpleDoc(fis, flavor, null);
			DocPrintJob job = printService.createPrintJob(); // 创建打印作业
			job.print(doc, aset);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			// 关闭打印的文件流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


