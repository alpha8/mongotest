package org.alpha.mongo.thread.task;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.alpha.mongo.cache.MongoIdCache;
import org.alpha.mongo.thread.config.Constants;
import org.alpha.mongo.util.GridFsUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

public class DownloadStrategy implements IStrategy {	
	private static Logger logger = Logger.getLogger(DownloadStrategy.class);

	@Override
	public void execute(Context ctx) {
		try {
			String id = MongoIdCache.getFileId();
			if(id == null){
				logger.debug("id must not be null.");
				return;
			}
			File destFile = new File("C:/output/"+UUID.randomUUID().toString().replace("-", "")+".jpg");
			GridFsUtils.download(ctx.getDb(), new ObjectId(id), destFile);
			Constants.SUCCESS_DOWNLOADED.addAndGet(1);
			TimeUnit.MILLISECONDS.sleep(ctx.getProfile().getThinkTime());
		} catch (IOException | InterruptedException e) {
			Constants.FAIL_DOWNLOADED.addAndGet(1);
			logger.error("download failed caused by " +e.getMessage(), e);
		}
	}
	
}
