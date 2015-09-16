package org.alpha.mongo.thread.test;

public abstract class AbstractTestCase {
	protected abstract void init();
	protected abstract void run();
	protected abstract void destroy();
}
