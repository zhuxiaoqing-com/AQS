package com.example.socketTest;

import com.example.demo1.demo.HttpUtil;
import com.example.demo1.demo.SignUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/29 14:25
 * @Description:
 */
public class ClientTest01 {
	@Test
	public void test01() throws Exception {
		for (int i = 0; i < 1; i++) {
			a();
		}
	}

	public void a() throws IOException, InterruptedException {
		Socket socket = new Socket();
		try {
			socket.bind(new InetSocketAddress(8888));
			//socket.connect(new InetSocketAddress("10.0.0.86", 9999));
			socket.connect(new InetSocketAddress("www.baidu.com", 80));
			OutputStream outputStream = socket.getOutputStream();
			/*for (int a = 1; a <= 10; a++) {
				Thread.sleep(500);
				outputStream.write((a + "").getBytes());
				outputStream.flush();
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 测试方法
	 *
	 * @param map
	 */
	private String post(Map<String, String> map, String uri) {
		//String url = "http://192.168.5.128:11001" + uri; // 内网一
		//String url = "http://10.40.2.68:11000" + uri; // 俞樟鹏
		String url = "http://10.0.0.86" + uri; // 本机 游戏服务器
		//String url = "http://10.42.0.35:11000" + uri;
		//String url = "http://10.42.0.50:8089" + uri; // 本机 gmweb
		//String url = "http://106.52.215.79:8015" + uri;// XY 线上
		Collection<String> values = map.values();
		String sign = SignUtil.getMD5ForGM(values.toArray(new String[values.size()]));
		map.put("sign", sign);
		System.out.println("参数: " + map);
		System.out.println(url);
		return HttpUtil.doPost(url, map);
	}


}
