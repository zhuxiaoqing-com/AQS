package com.example.代码优化.设计模式之美.a_92限流框架.first.config;

/**
 * 限流过程中会频繁的查询接口对应的限流规则，为了尽可能的提高查询速度，我们需要将
 * 限流规则组织成一种支持按照接口对应的限流桂策，为了尽可能的提高查询速度，我们需要将
 * 限流规则组织成一种支持按照 URL 快速查询的数据结构。
 * 考虑到 URL 的重复度比较高，且需要按照前缀来匹配，我们这里选择使用 Trie 树这种数据结构。
 */

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/1 20:06
 * @Description:
 */
public class RateLimitRule {
	public RateLimitRule(RuleConfig ruleConfig) {

	}

	public ApiLimit getLimit(String appId, String url) {
		return null;
	}
}
