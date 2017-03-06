package com.netease.okr.redis;

import java.util.HashSet;
import java.util.Set;

import com.netease.okr.util.LoggerUtil;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;


public class JedisSentinel {

	private JedisSentinelPool pool;
	private String masterName;
	private String sentinelAddressList;
	private int timeout = 3000;
	private String password;

	/**
	 * 获取连接池
	 * 
	 * @return
	 */
	public synchronized JedisSentinelPool getSentinelPool() {
		if (pool == null) {

			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(2000);
			config.setMaxWaitMillis(5000);
			config.setMaxIdle(100);
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			config.setTestWhileIdle(true);

			String addressList[] = sentinelAddressList.split(",");
			Set<String> aset = new HashSet<String>();
			for (String s : addressList) {
				aset.add(s);
			}
			this.pool = new JedisSentinelPool(masterName, aset, config, timeout, password);
		}
		return pool;
	}

	public void printPoolInfo() {
		int na = pool.getNumActive();
		int ni = pool.getNumIdle();
		int nw = pool.getNumWaiters();

		LoggerUtil.info("redis连接状态：numactive:" + na + "\tnumidle" + ni + "\tnumwaiters:" + nw);
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public void setSentinelAddressList(String sentinelAddressList) {
		this.sentinelAddressList = sentinelAddressList;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
