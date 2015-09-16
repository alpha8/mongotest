package org.alpha.mongo.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.DB;

public class GridFsUtilsTest {
	private static Logger logger = Logger.getLogger(GridFsUtilsTest.class);
	
	private static MongoUtils utils;
	private static DB db = null;
	private static String id = "";
	
	@BeforeClass
	public static void setUp() throws Exception {
		utils = MongoUtils.getInstance();
		db = utils.getDB("onetiger");
		logger.debug("initialized completed and client connected.");
	}

	@Test
	public void testUploadDBFile() {
		File uploadFle = new File("C:/Users/admin/Downloads/money.JPG");
		try {
			id = GridFsUtils.upload(db, uploadFle);
		} catch (IOException e) {
			fail("upload failed, caused by " +e.getMessage());
		}
	}

	@Test
	public void testDownload() {
		File saveFile = new File("c:/output/"+id+".jpg");
		try {
			GridFsUtils.download(db, new ObjectId(id), saveFile);
		} catch (IOException e) {
			fail("download failed, caused by " +e.getMessage());
		}
	}

	@AfterClass
	public static void destroy(){
		if(utils != null){
			utils.getMongoClient().close();
			logger.debug("mongo client disconnected.");
		}
	}
}
