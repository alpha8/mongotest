package org.alpha.mongo.thread.config;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TestProfile {
	private int threadSize;
	private long thinkTime;
	private long runTimes;
	private long runEndTime;
	
	public boolean isTimes() {
		return (runEndTime == 0);
	}

	public int getThreadSize() {
		return threadSize;
	}

	public void setThreadSize(int threadSize) {
		this.threadSize = threadSize;
	}

	public long getThinkTime() {
		return thinkTime;
	}

	public void setThinkTime(long thinkTime) {
		this.thinkTime = thinkTime;
	}

	public long getRunTimes() {
		return runTimes;
	}

	public void setRunTimes(long runTimes) {
		this.runTimes = runTimes;
	}

	public long getRunEndTime() {
		return runEndTime;
	}

	public void setRunEndTime(long runKeepTime) {
		this.runEndTime = System.currentTimeMillis() + runKeepTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
