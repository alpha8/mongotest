package org.alpha.redis.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * http://www.cnblogs.com/edisonfeng/p/3571870.html
 * @author Alpha Tan
 *
 */
public class RedisClient {
	private Jedis jedis;	//非切片客户端连接
	private JedisPool jedisPool;	//非切片连接池
	private ShardedJedis shardedJedis;	//切片客户端连接
	private ShardedJedisPool shardedJedisPool;	//切片连接池
	
	public RedisClient(){
		initialPool();
		initialShardedPool();
		
		shardedJedis = shardedJedisPool.getResource();
		jedis = jedisPool.getResource();
	}
	
	private void initialShardedPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000L);
		config.setTestOnBorrow(true);
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	private void initialPool(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000L);
		config.setTestOnBorrow(true);
		
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}
	
	public void releaseResource(){
		jedisPool.returnResource(jedis);
		shardedJedisPool.returnResource(shardedJedis);
	}
	
	public Jedis getJedis() {
		return jedis;
	}

	public ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

	public void keyOperate(){
		System.out.println("================KEY================");
		
		//清空数据
		System.out.println("清空库中的所有数据："+jedis.flushDB());
		
		System.out.println("系统中所有的Keys:");
		Iterator<String> keys = jedis.keys("*").iterator();
		while(keys.hasNext()){
			String key = keys.next();
			System.out.println(key);
		}
		
		String appKey = "appName";
		System.out.println("新增appName键值：" + shardedJedis.set(appKey, "一虎一席"));
		System.out.println("判断appName是否存在：" + shardedJedis.exists(appKey));
		System.out.println("新增author键值：" + shardedJedis.set("author", "Alpha Tan"));
		System.out.println("查询author内容："+shardedJedis.get("author"));
		System.out.println("删除author:"+jedis.del("author"));
		System.out.println("判断author是否存在：" + shardedJedis.exists("author"));
		
		System.out.println("设置appName过期时间为5秒:" + jedis.expire(appKey, 5));
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("查看appName剩余生存时间："+jedis.ttl(appKey));
		System.out.println("移除appName的生存时间："+jedis.persist(appKey));
		System.out.println("查看appName剩余生存时间："+jedis.ttl(appKey));
		System.out.println("查看appName所储存值类型："+jedis.type(appKey));
	}
	
	public static void main(String[] args) {
		RedisClient client = new RedisClient();
		client.keyOperate();
	}
}
