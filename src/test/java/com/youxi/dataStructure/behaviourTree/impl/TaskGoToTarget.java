package com.youxi.dataStructure.behaviourTree.impl;

import com.youxi.dataStructure.behaviourTree.Node;
import com.youxi.entity.Position;

public class TaskGoToTarget extends Node {
	private Position position;

	public TaskGoToTarget(Position position) {
		this.position = position;
	}
	@Override
	public NodeState evaluate() {
		Position target = (Position) getData("target");
		if(position.getDirectDistance(target)>0.01f) {
			// 继续移动
		}
		state = NodeState.RUNNING;
		return state;
	}
}
