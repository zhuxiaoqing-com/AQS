package com.example.demo1.util.lock;
public abstract interface IEntity<T extends Comparable<T>> {
	public abstract T getIdentity();
}