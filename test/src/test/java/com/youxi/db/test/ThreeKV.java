package com.youxi.db.test;

import java.util.Objects;

/**
 * kv
 *
 * @param <K>
 * @param <V>
 * @author Eric.Ma
 */
public class ThreeKV<K, V,P> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private K key;

	private V value;

	private P p;


	public ThreeKV(K key, V value, P p) {
		this.key = key;
		this.value = value;
		this.p = p;
	}

	public K getK() {
		return key;
	}

	public void setK(K k) {
		this.key = k;
	}

	public V getV() {
		return value;
	}

	public void setV(V v) {
		this.value = v;
	}

	public P getP() {
		return p;
	}

	public void setP(P p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "KV{" +
				"key=" + key +
				", value=" + value +
				", p=" + p +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		ThreeKV<?, ?, ?> threeKV = (ThreeKV<?, ?, ?>) o;
		return Objects.equals(key, threeKV.key) && Objects.equals(value, threeKV.value) && Objects.equals(p, threeKV.p);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value, p);
	}
}
