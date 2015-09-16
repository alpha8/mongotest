package org.alpha.mongo.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MongoIdCache {	
	private static ICache cache = new LocalCache();
	
	public static void setFileId(String id){
		BlockingQueue<String> queue = cache.get("ids");
		if(queue == null){
			queue = new LinkedBlockingQueue<String>();
		}
		queue.offer(id);
		cache.set("ids", queue);
	}
	
	public static String getFileId(){
		BlockingQueue<String> queue = cache.get("ids");
		try {
			while(queue==null || queue.isEmpty()){
				TimeUnit.MILLISECONDS.sleep(200);
				queue = cache.get("ids");
			}
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
