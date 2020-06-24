package com.example.redis.redisson;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.example.redis.entity.RUser;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 14:52
 * @Description:
 */
public class RedissonTest {
	private static final Logger logger = LoggerFactory.getLogger(RedissonTest.class);
	RedissonClient client;

	{
	/*	LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(lc);
		lc.reset();

		try {
			File file = new File("src/logback.xml");
			System.out.println(file.getAbsolutePath());
			configurator.doConfigure(file);
		} catch (JoranException e) {
			e.printStackTrace();
		}*/

		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		client = Redisson.create(config);
	}

	@Test
	public void test01() {
		RScoredSortedSet<RUser> set = client.getScoredSortedSet("key");
		/*set.add(11, new RUser(1, "user1"));
		set.add(12, new RUser(2, "user2"));
		set.add(13, new RUser(3, "user3"));*/
		/*set.add(11, 1);
		set.add(12, 2);
		set.add(13, 3);*/

		System.out.println(0);
	}
}
