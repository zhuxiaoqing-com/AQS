package com.youxi.ranker.rank.entity;

public class RankObjRedis<T> {

	private long value;

	private double score;

	private int rank;

	protected T data;

	public RankObjRedis(long value, double score, int rank, T data) {
		this.value = value;
		this.score = score;
		this.rank = rank;
		this.data = data;
	}


	public RankObjRedis(long value, int score, T data) {
		this.value = value;
		this.score = score;
		this.data = data;
	}


	public RankObjRedis() {
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RankObjRedis{" +
				"value=" + value +
				", score=" + score +
				", rank=" + rank +
				", data=" + data +
				'}';
	}
}
