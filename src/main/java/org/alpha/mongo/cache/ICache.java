package org.alpha.mongo.cache;

public interface ICache {
	public <T> T get(String key);
	public <T> void set(String key, T val);
}
