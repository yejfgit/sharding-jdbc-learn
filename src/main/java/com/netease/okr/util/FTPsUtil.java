package com.netease.okr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPSClient;
import org.apache.log4j.Logger;

public class FTPsUtil {

	final static Logger logger = Logger.getLogger(FTPsUtil.class);
	private static PropertiesUtils properties = new PropertiesUtils("config", null);

	public static boolean uploadFile(String localFile, String remoteFileName) {

		String ftpsSync = properties.getPropery("ftps_sync");
		if (!"1".equals(ftpsSync)) {
			logger.info("ftps sync is disabled.");
			return false;
		}

		if (MyStringUtil.isBlank(remoteFileName) && !MyStringUtil.isBlank(localFile)) {
			int index = localFile.lastIndexOf(File.separator);
			if (index > -1) {
				remoteFileName = localFile.substring(index + 1);
			}
		}

		String host = properties.getPropery("ftps_host");
		int port = Integer.parseInt(properties.getPropery("ftps_port"));
		String userName = properties.getPropery("ftps_username");
		String password = properties.getPropery("ftps_password");

		FTPSClient ftpsClient = null;
		try {
			ftpsClient = new FTPSClient();
			ftpsClient.connect(host, port);
			ftpsClient.execPROT("P"); // encrypt data channel

			if (ftpsClient.login(userName, password)) {
				ftpsClient.enterLocalPassiveMode();
				File file = new File(localFile);
				if (!file.exists()) {
					logger.error("localFileName doesn't exist!");
					return false;
				}

				FileInputStream in = new FileInputStream(file);
				boolean result = ftpsClient.storeFile(remoteFileName, in);
				if (result) {
					return true;
				} else {
					logger.warn("ftpsClient.storeFile():" + ftpsClient.getReplyString());
				}
			} else {
				logger.warn("ftpsClient.login():" + ftpsClient.getReplyString());
			}

		} catch (SocketException e) {
			e.printStackTrace();
			logger.error("FTPs Failed.", e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("FTPs Failed.", e);
		} finally {
			if (ftpsClient != null) {
				try {
					ftpsClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("ftpsClient.disconnect()!", e);
				}
			}
		}
		return false;
	}

}
