package com.netease.okr.service.impl;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.netease.cloud.ClientException;
import com.netease.cloud.ServiceException;
import com.netease.cloud.auth.BasicCredentials;
import com.netease.cloud.services.nos.NosClient;
import com.netease.cloud.services.nos.model.GeneratePresignedUrlRequest;

@Service("nosService")
public class NosBaseServiceImpl implements InitializingBean {

	final static Logger logger = Logger.getLogger(NosBaseServiceImpl.class);
	
	private String accessKey;
	private String bucketName;
	private String username;
	private String secretKey;
	private String nosHostName;

	private NosClient nosClient = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("初始化nosclient");
		logger.info("accessKey：" + accessKey);
		logger.info("username：" + username);
		logger.info("secretKey：" + secretKey);
		logger.info("bucketName：" + bucketName);
		logger.info("nosHostName：" + nosHostName);
		nosClient = new NosClient(new BasicCredentials(accessKey, secretKey));
		logger.info("nosclient done！");
	}

	/**
	 * @param bucketName
	 * @param objectName
	 * @param file
	 * @return
	 */
	public String upload(String nosKey, File file) {
		logger.info("上传文件至nos，key：" + nosKey);
		try {
			nosClient.putObject(bucketName, nosKey, file);
		} catch (ServiceException e1) {
			// 捕捉nos服务器异常错误
			logger.error("Error Message:    " + e1.getMessage(), e1); // 错误描述信息
			logger.error("HTTP Status Code: " + e1.getStatusCode(), e1); // 错误http状态码
			logger.error("NOS Error Code:   " + e1.getErrorCode(), e1); // NOS服务器定义错误码
			logger.error("Error Type:       " + e1.getErrorType(), e1); // NOS服务器定义错误类型
			logger.error("Request ID:       " + e1.getRequestId(), e1); // 请求ID,非常重要，有利于nos开发人员跟踪异常请求的错误原因
			logger.info("nos服务异常");
			return "nos服务异常";
		} catch (ClientException e2) {
			// 捕捉客户端错误
			logger.error("Error Message: " + e2.getMessage(), e2);
			logger.info("nos客户端异常");
			e2.printStackTrace();
			return "nos客户端异常";
		}
		return null;
	}

	/**
	 * 生成url
	 * 
	 * @param time
	 * @param nosKey
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public URL generatePresignedUrl(String nosKey, String fileName) throws UnsupportedEncodingException {
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, nosKey);
		// 设置可下载URL的过期时间为10*365天后
		generatePresignedUrlRequest.setExpiration(new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365*10));
		generatePresignedUrlRequest.setDownload(fileName);
		URL url = nosClient.generatePresignedUrl(generatePresignedUrlRequest);
		return url;
	}

	/**
	 * 下载文件
	 * 
	 * @param nosKey
	 * @return
	 */
	public InputStream download(String nosKey) {
		return null;
	}

	public String delete(String nosKey) {
		logger.info("删除nos文件，key:" + nosKey);
		try {
			nosClient.deleteObject(bucketName, nosKey);
		} catch (ServiceException e1) {
			// 捕捉nos服务器异常错误
			logger.error("Error Message:    " + e1.getMessage(), e1); // 错误描述信息
			logger.error("HTTP Status Code: " + e1.getStatusCode(), e1); // 错误http状态码
			logger.error("NOS Error Code:   " + e1.getErrorCode(), e1); // NOS服务器定义错误码
			logger.error("Error Type:       " + e1.getErrorType(), e1); // NOS服务器定义错误类型
			logger.error("Request ID:       " + e1.getRequestId(), e1); // 请求ID,非常重要，有利于nos开发人员跟踪异常请求的错误原因
			return "nos服务异常";
		} catch (ClientException e2) {
			// 捕捉客户端错误
			logger.error("Error Message: " + e2.getMessage(), e2);
			return "nos客户端异常";
		}
		logger.info("删除完成");
		return null;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setNosHostName(String nosHostName) {
		this.nosHostName = nosHostName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
