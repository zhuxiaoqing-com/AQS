package com.youxi.dataStructure.behaviourTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Node {
	public enum NodeState {
		RUNNING,
		SUCCESS,
		FAILURE
	}

	protected NodeState state;
	public Node parent;
	protected List<Node> children = new ArrayList<>();
	private Map<String, Object> _dataContext = new HashMap<>();

	public Node() {
		parent = null;
	}

	public Node(List<Node> children) {
		for (Node child : children) {
			_attach(child);
		}
	}

	private void _attach(Node node) {
		node.parent = this;
		children.add(node);
	}

	public abstract NodeState evaluate();

	public void setData(String key, Object value) {
		_dataContext.put(key, value);
	}

	public Object getData(String key) {
		Object value = null;
		value = _dataContext.get(key);
		if (value != null) {
			return value;
		}
		Node node = parent;
		if (node != null) {
			value = node.getData(key);
		}
		return value;
	}

	public boolean clearData(String key) {
		Object value = null;
		value = _dataContext.get(key);
		if (value != null) {
			_dataContext.remove(key);
			return true;
		}
		Node node = parent;
		if (node != null) {
			return node.clearData(key);
		}
		return false;
	}
}
