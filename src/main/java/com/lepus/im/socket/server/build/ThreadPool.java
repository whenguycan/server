package com.lepus.im.socket.server.build;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author lepus
 * @date 2018-1-16 下午9:25:22
 */
public class ThreadPool {

	public static ExecutorService pool = Executors.newFixedThreadPool(3);
	
	public static void execute(Runnable command){
		pool.execute(command);
	}
	
}
