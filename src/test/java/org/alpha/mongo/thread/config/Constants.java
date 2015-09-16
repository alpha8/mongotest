package org.alpha.mongo.thread.config;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试常量类， 配置多线程
 * 
 * @author Alpha Tan
 *
 */
public class Constants {
	/** 并发线程数 */
	public static final int THREAD_SIZE = 10;
	/** 任务间隔时间，单位ms */
	public static final long THINK_TIME = 20;
	
	/** 线程运行时间 */
	public static long ONE_SECOND = 1000L;
	public static long ONE_MINUTE = 60 * ONE_SECOND;
	public static long ONE_HOUR = 60 * ONE_MINUTE;
	public static long ONE_DAY = 24 * ONE_HOUR;
	
	/** 线程运行次数　*/
	public static final long RUN_TIMES = 1000;	
	
	/** 成功任务数 */
	public static final AtomicLong SUCCESS_UPLOADED = new AtomicLong(0);
	public static final AtomicLong SUCCESS_DOWNLOADED = new AtomicLong(0);
	
	/** 失败任务数  */
	public static final AtomicLong FAIL_UPLOADED = new AtomicLong(0);
	public static final AtomicLong FAIL_DOWNLOADED = new AtomicLong(0);

	/** 正在运行的测试用例数 */
	public static AtomicInteger RUNNING_CASES = new AtomicInteger(0);
}
