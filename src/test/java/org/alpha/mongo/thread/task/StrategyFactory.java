package org.alpha.mongo.thread.task;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {
	private static Map<Functions, IStrategy> map = new HashMap<Functions, IStrategy>();
	static{
		map.put(Functions.UPLOAD, new UploadStrategy());
		map.put(Functions.DOWNLOAD, new DownloadStrategy());
	}
	public static IStrategy getStrategy(Functions func){
		return map.get(func);
	}
}
