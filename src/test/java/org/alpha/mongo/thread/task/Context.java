package org.alpha.mongo.thread.task;

import java.io.File;

import org.alpha.mongo.thread.config.TestProfile;

import com.mongodb.DB;

public class Context {
	private DB db;
	private File srcFile;
	private TestProfile profile;
	
	public TestProfile getProfile() {
		return profile;
	}

	public void setProfile(TestProfile profile) {
		this.profile = profile;
	}

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public File getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(File srcFile) {
		this.srcFile = srcFile;
	}
}
