package org.alpha.mongo.thread.testcase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.alpha.mongo.thread.config.Constants;
import org.alpha.mongo.thread.config.TestProfile;
import org.alpha.mongo.thread.task.Context;
import org.alpha.mongo.thread.task.Functions;
import org.alpha.mongo.thread.task.IStrategy;
import org.alpha.mongo.thread.task.StrategyFactory;
import org.alpha.mongo.thread.task.WorkerTask;
import org.alpha.mongo.thread.test.TestCaseAdapter;
import org.alpha.mongo.util.MongoUtils;
import org.apache.log4j.Logger;

import com.mongodb.DB;

public class DownloadTestCase extends TestCaseAdapter{
	private static Logger logger = Logger.getLogger(DownloadTestCase.class);
	private TestProfile profile;
	public TestProfile getProfile() {
		return profile;
	}
	public void setProfile(TestProfile profile) {
		this.profile = profile;
	}
	
	public DownloadTestCase(TestProfile profile){
		this.profile = profile;
	}

	@Override
	protected void run(){
		DB db = MongoUtils.getInstance().getDB("onetiger");
		Context ctx = new Context();
		ctx.setDb(db);
		ctx.setProfile(profile);
		
		IStrategy strategy = StrategyFactory.getStrategy(Functions.DOWNLOAD);
		ExecutorService pool = Executors.newFixedThreadPool(Constants.THREAD_SIZE);
		for(int i=0; i<Constants.THREAD_SIZE; i++){
			pool.execute(new WorkerTask(strategy, ctx));
		}
		pool.shutdown();
	}
	
	@Override
	public void printSummary() {
		logger.debug("total download success: " + Constants.SUCCESS_DOWNLOADED.get());
		logger.debug("total download failure: " + Constants.FAIL_DOWNLOADED.get());
	}
}
