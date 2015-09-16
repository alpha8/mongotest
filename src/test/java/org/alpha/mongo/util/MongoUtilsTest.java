package org.alpha.mongo.util;

import org.junit.Before;
import org.junit.Test;

public class MongoUtilsTest {
	private MongoUtils util;

	@Before
	public void setUp() throws Exception {
		util = MongoUtils.getInstance();
	}

	@Test
	public void testPrintSchema() {
		util.printSchema();
	}
}
