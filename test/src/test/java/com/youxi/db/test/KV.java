package com.youxi.db.test;

import java.util.Objects;

/**
 * kv
 *
 * @param <K>
 * @param <V>
 * @author Eric.Ma
 */
public class KV<K, V> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private K key;

	private V value;

	private Object p;

	public KV(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public KV(K key, V value, Object p) {
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
	public Object getP() {
		return p;
	}

	public void setP(Object p) {
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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		KV<?, ?> kv = (KV<?, ?>) o;
		return Objects.equals(key, kv.key) && Objects.equals(value, kv.value) && Objects.equals(p, kv.p);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value, p);
	}
}
