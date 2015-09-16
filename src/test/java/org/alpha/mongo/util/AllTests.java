package org.alpha.mongo.util;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	public static Test suite(){
		TestSuite suite = new TestSuite("GridFS Test");
		suite.addTest(new JUnit4TestAdapter(GridFsUtilsTest.class));
		return suite;
	}
}
