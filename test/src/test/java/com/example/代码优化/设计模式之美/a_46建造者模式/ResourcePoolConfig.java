package com.example.代码优化.设计模式之美.a_46建造者模式;

import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/8 10:35
 * @Description:
 */
public class ResourcePoolConfig {
	private String name;
	private int maxTotal;
	private int maxIdle;
	private int minIdle;

	private ResourcePoolConfig(Builder builder) {

	}

	public static  ResourcePoolConfig.Builder builder() {
		return new ResourcePoolConfig.Builder();
	}

	public static class Builder {
		private String name;
		private int maxTotal;
		private int maxIdle;
		private int minIdle;

		public ResourcePoolConfig build() {
			// 校验逻辑都放到这里来做，包括必填项检验、依赖关系校验、约束条件校验等
			return new ResourcePoolConfig(this);
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setMaxTotal(int maxTotal) {
			this.maxTotal = maxTotal;
			return this;
		}

		public Builder setMaxIdle(int maxIdle) {
			this.maxIdle = maxIdle;
			return this;
		}

		public Builder setMinIdle(int minIdle) {
			this.minIdle = minIdle;
			return this;
		}
	}

	@Test
	public void test(){
		ResourcePoolConfig build = ResourcePoolConfig.builder().setMaxTotal(1).setMaxIdle(1).build();
	}
}
