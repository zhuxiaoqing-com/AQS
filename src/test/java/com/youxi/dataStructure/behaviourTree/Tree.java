package com.youxi.dataStructure.behaviourTree;

public abstract class Tree {
	private Node _root = null;

	/**
	 * unity 加载的时候调用 Start();
	 */
	protected void start() {
		_root = setUpTree();
	}


	/**
	 * unity 加载的时候调用 Update();
	 */
	private void update() {
		if(_root!= null) {
			_root.evaluate();
		}
	}

	protected abstract Node setUpTree();
}
