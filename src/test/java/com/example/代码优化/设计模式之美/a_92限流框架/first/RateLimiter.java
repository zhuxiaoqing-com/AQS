package com.example.代码优化.设计模式之美.a_92限流框架.first;

import com.example.代码优化.设计模式之美.a_92限流框架.first.alg.RateLimitAlg;
import com.example.代码优化.设计模式之美.a_92限流框架.first.config.ApiLimit;
import com.example.代码优化.设计模式之美.a_92限流框架.first.config.RateLimitRule;
import com.example.代码优化.设计模式之美.a_92限流框架.first.config.RuleConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/1 20:04
 * @Description:
 */
public class RateLimiter {
	// 为每个api在内存中存储限流计数器
	private Map<String, RateLimitAlg> counters = new ConcurrentHashMap<>();
	private RateLimitRule rule;

	public RateLimiter() {
		// 将限流规则配置文件 ratelimiter-rule.yaml 中的内容读取到 RuleConfig 中
		InputStream in = null;
		RuleConfig ruleConfig = null;
		try {
			in = this.getClass().getResourceAsStream("/ratelimiter-rule.yaml");
			if (in != null) {
				Yaml yaml = new Yaml();
				yaml.loadAs(in, RuleConfig.class);
			}
		} finally {
			{
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
			}
		}
		// 将限流规则构建成支持快速查找的数据结构 RateLimitRule
		this.rule = new RateLimitRule(ruleConfig);
	}

	public boolean limit(String appId, String url) {
		ApiLimit apiLimit = rule.getLimit(appId, url);
		if (apiLimit == null) {
			return true;
		}

		// 获取api对应在内存中的限流计数器(rateLimitCounter)
		String counterKey = appId + ":" + apiLimit.getApi();
		RateLimitAlg rateLimitAlg = counters.get(counterKey);
		if (rateLimitAlg == null) {
			rateLimitAlg = counters.computeIfAbsent(counterKey, a -> new RateLimitAlg(apiLimit.getLimit()));
		}
		// 判断是否限流
		return rateLimitAlg.tryAcquire();
	}
}
