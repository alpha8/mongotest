package org.alpha.mongo.thread;

import org.alpha.mongo.thread.config.Constants;
import org.alpha.mongo.thread.config.TestProfile;
import org.alpha.mongo.thread.testcase.DownloadTestCase;
import org.alpha.mongo.thread.testcase.UploadTestCase;

public class Main {
	public static void main(String[] args) {
		TestProfile profile = new TestProfile();
		profile.setThinkTime(Constants.THINK_TIME);
		profile.setThreadSize(Constants.THREAD_SIZE);
		profile.setRunEndTime(Constants.ONE_SECOND*20);
//		profile.setRunTimes(100);
		
		new UploadTestCase(profile).execute();
		new DownloadTestCase(profile).execute();
	}
}
