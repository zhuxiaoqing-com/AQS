package com.example.代码优化.设计模式之美.a_92限流框架.first.datasource;

import com.example.代码优化.设计模式之美.a_92限流框架.first.config.RuleConfig;
import com.example.代码优化.设计模式之美.a_92限流框架.first.parser.JsonRuleConfigParser;
import com.example.代码优化.设计模式之美.a_92限流框架.first.parser.RuleConfigParser;
import com.example.代码优化.设计模式之美.a_92限流框架.first.parser.YamlRuleConfigParser;
import com.example.代码优化.设计模式之美.a_92限流框架.first.parser.YmlRuleConfigParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/2 10:32
 * @Description:
 */
public class FileRuleConfigSource implements RuleConfigSource {
	private static final String API_LIMIT_CONFIG_GAME = "ratelimiter-rule";

	private static final String YAML_EXTENSION = "yaml";
	private static final String YML_EXTENSION = "yml";
	private static final String JSON_EXTENSION = "json";

	private static final String[] SUPPORT_EXTENSION = new String[]{YAML_EXTENSION, YML_EXTENSION, JSON_EXTENSION};
	private static final Map<String, RuleConfigParser> PARSER_MAP = new HashMap<>();

	static {
		PARSER_MAP.put(YAML_EXTENSION, new YamlRuleConfigParser());
		PARSER_MAP.put(YML_EXTENSION, new YmlRuleConfigParser());
		PARSER_MAP.put(JSON_EXTENSION, new JsonRuleConfigParser());
	}

	@Override
	public RuleConfig load() {
		for (String extension : SUPPORT_EXTENSION) {
			InputStream in = null;
			try {
				in = this.getClass().getResourceAsStream("/" + getFileNameByExt(extension));
				if(in != null) {
					RuleConfigParser parser = PARSER_MAP.get(extension);
					return parser.parse(in);
				}
			}finally {
				if(in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	private String getFileNameByExt(String extension) {
		return API_LIMIT_CONFIG_GAME + "." + extension;
	}
}
