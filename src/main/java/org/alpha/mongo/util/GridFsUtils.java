package org.alpha.mongo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.bson.BSONObject;
import org.bson.types.ObjectId;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class GridFsUtils {
	private static Logger logger = Logger.getLogger(GridFsUtils.class);
	
	public static String upload(DB db, File uploadFile, BSONObject props) throws IOException {
		long start = System.currentTimeMillis();
		
		GridFS fs = new GridFS(db);
		GridFSInputFile upload = fs.createFile(uploadFile);
		if(props != null && !props.keySet().isEmpty()){
			upload.putAll(props);
		}
		upload.save();
		logger.debug("upload id=" + upload.getId().toString());
		logger.debug("upload elapsed time: "+(System.currentTimeMillis()-start)+"ms.");
		return upload.getId().toString();
	}
	
	public static String upload(DB db, File uploadFile) throws IOException{
		return upload(db, uploadFile, null);
	}
	
	public static void download(DB db, ObjectId id, File destFile) throws IOException{
		logger.debug("download document id="+id.toString());
		
		long start = System.currentTimeMillis();
		if(!destFile.getParentFile().exists()){
			destFile.getParentFile().mkdirs();
		}
		
		GridFS fs = new GridFS(db);
		GridFSDBFile dbFile = fs.find(id);
		try(
			BufferedInputStream in = new BufferedInputStream(dbFile.getInputStream());
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile))
		){			
			byte[] buf = new byte[4096];
			int len = 0;
			while((len = in.read(buf)) != -1){
				out.write(buf, 0, len);
			}
			out.flush();
		}
		logger.debug("download elapsed time: "+(System.currentTimeMillis()-start)+"ms.");
	}
}
