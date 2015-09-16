package org.alpha.mongo.thread.task;

import org.alpha.mongo.thread.config.TestProfile;

public class WorkerTask implements Runnable{
	private IStrategy strategy;
	private Context ctx;
	
	public WorkerTask(IStrategy strategy, Context ctx){
		this.strategy = strategy;
		this.ctx = ctx;
	}

	@Override
	public void run(){
		TestProfile profile = ctx.getProfile();
		if(profile.isTimes()){
			int times = (int)Math.ceil((double)profile.getRunTimes()/profile.getThreadSize());
			for(int i=0; i<times; i++){
				strategy.execute(ctx);
			}
		}else{
			long endTime = profile.getRunEndTime();
			while(System.currentTimeMillis() <= endTime){
				strategy.execute(ctx);
			}
		}
	}
}
