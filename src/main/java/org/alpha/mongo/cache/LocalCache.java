package org.alpha.mongo.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCache implements ICache {
	private static Map<String, Object> map = new ConcurrentHashMap<String, Object>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		return (T)map.get(key);
	}

	@Override
	public <T> void set(String key, T val) {
		map.put(key, val);
	}
}
