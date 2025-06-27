package com.youxi.dataStructure.behaviourTree;

import java.util.List;

public class Sequence extends Node{
	public Sequence() {
	}

	public Sequence(List<Node> children) {
		super(children);
	}

	@Override
	public NodeState evaluate() {
		boolean anyChildIsRunning = false;
		for (Node node : children) {
			NodeState evaluate = node.evaluate();
			switch (evaluate) {
				case FAILURE:
					state = evaluate;
					return state;
				case SUCCESS:
					break;
				case RUNNING:
					anyChildIsRunning = true;
					break;
				default:
					state = NodeState.SUCCESS;
					return state;
			}
			state = anyChildIsRunning ? NodeState.RUNNING : NodeState.SUCCESS;
		}
		return null;
	}
}
