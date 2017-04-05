/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netease.frame.test.apollo;

import org.fusesource.hawtbuf.*;
import org.fusesource.mqtt.client.*;

/**
 * Uses an callback based interface to MQTT. Callback based interfaces are
 * harder to use but are slightly more efficient.
 */
class ListenerMQTT {

	public static void main(String[] args) throws Exception {

		String user = env("APOLLO_USER", "admin");
		String password = env("APOLLO_PASSWORD", "password");
		String host = env("APOLLO_HOST", "10.164.96.36");
		int port = Integer.parseInt(env("APOLLO_PORT", "61613"));
		final String destination = arg(args, 0, "chat/general1");

		MQTT mqtt = new MQTT();
		//mqtt.setHost(host, port);
		mqtt.setHost("tcp://10.164.96.36:61613");
		mqtt.setUserName(user); 
		mqtt.setPassword(password);

		final CallbackConnection connection = mqtt.callbackConnection();
		connection.listener(new org.fusesource.mqtt.client.Listener() {
			long count = 0;
			long start = System.currentTimeMillis();
			
			public void onConnected() {
				System.out.println("onConnected");
			}

			public void onDisconnected() {
				System.out.println("onDisconnected");
			}

			public void onFailure(Throwable value) {
				value.printStackTrace();
				System.out.println("onFailure");
				System.exit(-2);
			}

			public void onPublish(UTF8Buffer topic, Buffer msg, Runnable ack) {
				String body = msg.utf8().toString();
				System.out.println(body);
				if ("SHUTDOWN".equals(body)) {
					long diff = System.currentTimeMillis() - start;
					System.out.println(String.format("Received %d in %.2f seconds", count, (1.0 * diff / 1000.0)));
					connection.disconnect(new Callback<Void>() {
						@Override
						public void onSuccess(Void value) {
							System.exit(0);
						}

						@Override
						public void onFailure(Throwable value) {
							value.printStackTrace();
							System.exit(-2);
						}
					});
				} else {
					if (count == 0) {
						start = System.currentTimeMillis();
					}
					if (count % 1000 == 0) {
						System.out.println(String.format("Received %d messages.", count));
					}
					count++;
				}
			}
		});
		connection.connect(new Callback<Void>() {
			@Override
			public void onSuccess(Void value) {
				System.out.println("onSuccess");
				Topic[] topics = { new Topic(destination, QoS.AT_LEAST_ONCE) };
				connection.subscribe(topics, new Callback<byte[]>() {
					public void onSuccess(byte[] qoses) {
						System.out.println("onSuccess");
					}

					public void onFailure(Throwable value) {
						value.printStackTrace();
						System.exit(-2);
					}
				});
			}

			@Override
			public void onFailure(Throwable value) {
				System.out.println("onFailure");
				value.printStackTrace();
				System.exit(-2);
			}
		});

		// Wait forever..
		synchronized (ListenerMQTT.class) {
			while (true)
				ListenerMQTT.class.wait();
		}
	}

	private static String env(String key, String defaultValue) {
		String rc = System.getenv(key);
		if (rc == null)
			return defaultValue;
		return rc;
	}

	private static String arg(String[] args, int index, String defaultValue) {
		if (index < args.length)
			return args[index];
		else
			return defaultValue;
	}
}
