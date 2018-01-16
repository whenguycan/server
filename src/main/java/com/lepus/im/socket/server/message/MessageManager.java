package com.lepus.im.socket.server.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.lepus.im.socket.server.build.ThreadPool;

/**
 * 
 * @author lepus
 * @date 2018-1-16 下午9:30:35
 */
public class MessageManager {
	
	private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
	
	public MessageManager(int port) throws IOException{
		final ServerSocket server = new ServerSocket(port);
		new Thread(){
			public void run() {
				while(true){
					try {
						Socket socket = server.accept();
						BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String message = "";
						String line = null;
						while((line = reader.readLine()) != null){
							message += line;
						}
						if(message.length() != 0){
							receive(message);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		new Thread(){
			public void run() {
				while(true){
					int limit = 10;
					while(!queue.isEmpty() && limit != 0){
						send(queue.poll());
						limit--;
					}
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	public static void receive(final String message){
		serlalize(message);
		ThreadPool.execute(new Runnable() {
			public void run() {
				queue.add(message);
			}
		});
	}
	
	public static void send(final String message){
		ThreadPool.execute(new Runnable() {
			public void run() {
				System.out.println("xxx send message : " + message);
			}
		});
	}
	
	public static void serlalize(final String message){
		ThreadPool.execute(new Runnable() {
			public void run() {
				System.out.println("xxx serialize message : " + message);
			}
		});
	}
	
}
