package com.youxi.dataStructure.behaviourTree;

import java.util.List;

public class Selector extends Node{
	public Selector() {
	}

	public Selector(List<Node> children) {
		super(children);
	}

	@Override
	public NodeState evaluate() {
		boolean anyChildIsRunning = false;
		for (Node node : children) {
			NodeState evaluate = node.evaluate();
			switch (evaluate) {
				case FAILURE:
					break;
				case SUCCESS:
					state = evaluate;
					return state;
				case RUNNING:
					state = evaluate;
					return state;
				default:
					break;
			}
			state = NodeState.FAILURE;
			return state;
		}
		return null;
	}
}
