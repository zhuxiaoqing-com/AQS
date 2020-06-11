package com.example.代码优化.设计模式之美.a_57观察者模式.EventBus;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/11 14:03
 * @Description:
 */
public class ObserverRegistry {

	private Map<Class<?>, CopyOnWriteArraySet<ObserverAction>> registry = new ConcurrentHashMap<>();

	public void register(Object observer) {
		Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);
		for (Map.Entry<Class<?>, Collection<ObserverAction>> entry : observerActions.entrySet()) {
			Class<?> eventType = entry.getKey();
			Collection<ObserverAction> value = entry.getValue();
			CopyOnWriteArraySet<ObserverAction> copyOnWriteArraySet = registry.computeIfAbsent(eventType, key -> new CopyOnWriteArraySet<>());
			copyOnWriteArraySet.addAll(value);
		}
	}

	private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer) {
		Map<Class<?>, CopyOnWriteArraySet<ObserverAction>> observerActions = new HashMap<>();
		Class<?> clazz = observer.getClass();
		return null;
	}


}
