package com.netease.okr.mq;

import java.net.URISyntaxException;
import java.util.LinkedList;

import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;


/**
 * @author yefj
 * */
public class MqApolloClient {

	private static String hostUrl;

	private static String userName;

	private static String password;
	
	private static FutureConnection connection = null;

	private static void getConnetion() {
		try {
			MQTT mqtt = new MQTT();
			
			mqtt.setHost(hostUrl);
			mqtt.setUserName(userName);
			mqtt.setPassword(password);

			connection = mqtt.futureConnection();
			//connection.connect().await();//必须拿到连接才执行下去，去掉await()方法
			connection.connect();

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void init(){
		if(connection==null) getConnetion();
	}
	
	/**
	 * messages【消息内容】;destination【订阅号】
	 * @author yejf
	 * */
	public static void sendMessage(String destination,String messages)  {
		init();
		final LinkedList<Future<Void>> queue = new LinkedList<Future<Void>>();
		try {
			
	        UTF8Buffer topic = new UTF8Buffer(destination);
	        
	        queue.add(connection.publish(topic, new AsciiBuffer(messages), QoS.AT_LEAST_ONCE, false));
	
	        while( !queue.isEmpty() ) {
	        	queue.removeFirst().await();
	        }
        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
	}
	
	@SuppressWarnings("unused")
	private static void disConnetion() {
		try {
			connection.disconnect().await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void setHostUrl(String hostUrl) {
		MqApolloClient.hostUrl = hostUrl;
	}

	public static void setUserName(String userName) {
		MqApolloClient.userName = userName;
	}

	public static void setPassword(String password) {
		MqApolloClient.password = password;
	}
}
