package com.lepus.im.socket.server.app;

import com.lepus.im.socket.server.message.MessageManager;

/**
 * 
 * @author lepus
 * @date 2018-1-16 下午9:25:58
 */
public class Application {

	public static void main(String[] args) {
		try {
			new MessageManager(8081);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
