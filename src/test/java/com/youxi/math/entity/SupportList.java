package com.youxi.math.entity;

import com.youxi.entity.Position;

import java.util.ArrayList;
import java.util.List;

public class SupportList {

	private List<SupportBean> list = new ArrayList<>();

	public void add(Position supportPos, Position fromA, Position fromB) {
		list.add(new SupportBean(supportPos, fromA, fromB));
	}


	public void add(SupportBean supportBean) {
		list.add(supportBean);
	}

	public void remove(int index) {
		list.remove(index);
	}

	public List<SupportBean> getList() {
		return list;
	}

	public static class SupportBean {
		private Position support;
		private Position fromA;
		private Position fromB;

		public SupportBean(Position support, Position fromA, Position fromB) {
			this.support = support;
			this.fromA = fromA;
			this.fromB = fromB;
		}

		public Position getSupport() {
			return support;
		}

		public Position getFromA() {
			return fromA;
		}

		public Position getFromB() {
			return fromB;
		}
	}
}
