package com.youxi.match.game.match;

/**
 * a 区间
 * 
 * @author Eric.Ma
 * 
 */
public class Range<T extends Number> extends Pair<T, T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Range(T min, T max) {
		super(min, max);
	}

	public boolean isIn(T val) {
		return val.intValue() >= key.intValue() && val.intValue() <= value.intValue();
	}

	public T getMin() {
		return getKey();
	}

	public T getMax() {
		return getValue();
	}
}
