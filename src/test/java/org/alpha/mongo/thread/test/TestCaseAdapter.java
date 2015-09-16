package org.alpha.mongo.thread.test;

import org.alpha.mongo.thread.config.Constants;
import org.alpha.mongo.util.DbUploadUtils;
import org.alpha.mongo.util.MongoUtils;
import org.apache.log4j.Logger;

public abstract class TestCaseAdapter extends AbstractTestCase implements IPrintSummary{
	private static Logger logger = Logger.getLogger(TestCaseAdapter.class);
	
	public void execute(){
		this.init();
		this.run();
		this.destroy();
	}

	@Override
	protected void init() {
		Constants.RUNNING_CASES.incrementAndGet();
	}

	@Override
	protected void destroy() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				printSummary();
				int cases = Constants.RUNNING_CASES.decrementAndGet();
				if(cases > 0){
					return;
				}
				DbUploadUtils utils = new DbUploadUtils();
				logger.debug("total files： " + utils.totalFiles());
				logger.debug("total chunks： " + utils.totalChunks());
				utils.clear();
				if(MongoUtils.getInstance().release()){
					logger.debug("mongo client has been released.");
				}
			}
		}));
	}
	
	@Override
	public void printSummary() {
	}
}
