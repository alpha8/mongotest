package org.alpha.mongo.thread.task;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.alpha.mongo.cache.MongoIdCache;
import org.alpha.mongo.thread.config.Constants;
import org.alpha.mongo.util.GridFsUtils;
import org.apache.log4j.Logger;

public class UploadStrategy implements IStrategy {	
	private static Logger logger = Logger.getLogger(UploadStrategy.class);
	
	@Override
	public void execute(Context ctx) {
		try{
			String id = GridFsUtils.upload(ctx.getDb(), ctx.getSrcFile());
			MongoIdCache.setFileId(id);
			
			Constants.SUCCESS_UPLOADED.addAndGet(1);
			TimeUnit.MILLISECONDS.sleep(ctx.getProfile().getThinkTime());
		}catch(IOException | InterruptedException e){
			Constants.FAIL_UPLOADED.addAndGet(1);
			logger.error("upload failed caused by " +e.getMessage(), e);
		}
	}
	
}
