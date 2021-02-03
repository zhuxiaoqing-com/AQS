package com.youxi.AOI.tower;

import java.util.*;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/2/3 10:40
 * @Description:
 */
public class Tower {
	private Set<Integer> ids = new HashSet<>();

	private Map<Integer, Set<Integer>> watchers = new HashMap<>();

	private Map<Integer, Set<Integer>> typeMap = new HashMap<>();

	private int size;

	/**
	 * add an object to tower
	 */

	public boolean add(TowerObj towerObj) {
		int id = towerObj.getId();
		int type = towerObj.getType();

		Set<Integer> typeObjList = typeMap.computeIfAbsent(type, a -> new HashSet<>());
		if (typeObjList.contains(id)) {
			return false;
		}

		typeObjList.add(id);
		ids.add(towerObj.getId());
		return true;
	}

	/**
	 * Add watcher to tower
	 */
	public void addWatcher(TowerObj towerObj) {
		int id = towerObj.getId();
		int type = towerObj.getType();
		Set<Integer> integers = watchers.computeIfAbsent(type, a -> new HashSet<>());
		integers.add(id);
	}

	/**
	 * Remove watcher from tower
	 */
	public void removeWatcher(TowerObj towerObj) {
		int id = towerObj.getId();
		int type = towerObj.getType();

		Set<Integer> integers = watchers.get(type);
		if(integers == null || integers.isEmpty()) {
			return;
		}
		integers.remove(id);
	}

	/**
	 * Get all watchers by the given types in this tower
	 */
	public Map<Integer, Set<Integer>> getWatchers(Set<Integer> types) {
		if(types.isEmpty()) {
			return Collections.emptyMap();
		}
		HashMap<Integer,  Set<Integer>> result = new HashMap<>();

		for (Integer type : types) {
			result.put(type, new HashSet<>(watchers.get(type)));
		}
		return result;
	}

	/**
	 * Remove an object from this tower
	 */
	public void remove(TowerObj towerObj) {
		int id = towerObj.getId();
		int type = towerObj.getType();

		Set<Integer> integers = typeMap.get(type);
		if(integers == null || integers.isEmpty()) {
			return;
		}
		integers.remove(id);
		ids.remove(id);
	}

	/**
	 * Get object ids of given types in this tower
	 */
	public Map<Integer, Set<Integer>> getIdsByTypes(Set<Integer> types) {
		if(types.isEmpty()) {
			return Collections.emptyMap();
		}
		HashMap<Integer,  Set<Integer>> result = new HashMap<>();

		for (Integer type : types) {
			result.put(type, new HashSet<>(typeMap.get(type)));
		}
		return result;
	}
}
























