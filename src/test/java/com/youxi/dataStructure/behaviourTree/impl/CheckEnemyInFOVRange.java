package com.youxi.dataStructure.behaviourTree.impl;

import com.youxi.dataStructure.behaviourTree.Node;
import com.youxi.entity.Position;

import java.util.List;

public class CheckEnemyInFOVRange extends Node {
	private Position position;

	public CheckEnemyInFOVRange(Position position) {
		this.position = position;
	}

	@Override
	public NodeState evaluate() {
		Object target = getData("target");
		if (target == null) {
			// 搜索目标
			target = new Object();
			if (target != null) {
				state = NodeState.SUCCESS;
				return state;
			}
			state = NodeState.FAILURE;
			return state;
		}

		state = NodeState.SUCCESS;
		return null;
	}
}
