package com.netease.okr.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class HttpClientUtil {
	public static final String CHARSET_UTF_8 = "UTF-8";
	private final static Logger logger = Logger.getLogger(HttpClientUtil.class);
	
	public static String post(String url) {
		return post(url, new HashMap<String, String>());
	}

	/**
	 * 通过httpclient使用post方式 访问url
	 * 
	 * @param url
	 *            : 访问url
	 * @param param
	 *            post访问的参数
	 * @return httpclient 通过post访问 的响应流组成的string
	 */
	@SuppressWarnings("rawtypes")
	public static String post(String url, Map<String, String> param) {
		if (url == null || url.length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean sign = true;
		if (param == null || param.size() == 0) {
			sign = false;
		}
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (sign) {
			Iterator iterator = param.entrySet().iterator();

			Map.Entry entry = null;
			while (iterator.hasNext()) {
				entry = (Map.Entry) iterator.next();
				formparams
						.add(new BasicNameValuePair(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
			}
		}
		try {
			HttpPost httppost = new HttpPost(url);
			if (sign) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, CHARSET_UTF_8);
				httppost.setEntity(entity);
			}
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity httpEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(httpEntity), CHARSET_UTF_8));
			if (httpEntity != null) {
				httpEntity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * 通过httpclient使用post方式 访问url
	 * 
	 * @param url
	 *            : 访问url
	 * @param param
	 *            post访问的参数 List<NameValuePair>
	 * @return httpclient 通过post访问 的响应流组成的string
	 */
	public static String post(String url, List<NameValuePair> param) {
		if (url == null || url.length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean sign = true;
		if (param == null || param.size() == 0) {
			sign = false;
		}
		try {
			HttpPost httppost = new HttpPost(url);
			if (sign) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param, CHARSET_UTF_8);
				httppost.setEntity(entity);
			}
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity httpEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(httpEntity), CHARSET_UTF_8));
			if (httpEntity != null) {
				httpEntity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * @param url
	 *            访问url
	 * @param param
	 *            post参数
	 * @param proxyHost
	 *            代理host
	 * @param port
	 *            代理port
	 * @return 响应内容
	 */
	@SuppressWarnings("rawtypes")
	public static String post(String url, Map<String, String> param, String proxyHost, int port) {
		if (url == null || url.length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean sign = true;
		if (param == null || param.size() == 0) {
			sign = false;
		}
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (sign) {
			Iterator iterator = param.entrySet().iterator();

			Map.Entry entry = null;
			while (iterator.hasNext()) {
				entry = (Map.Entry) iterator.next();
				formparams
						.add(new BasicNameValuePair(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
			}
		}
		try {
			HttpPost httppost = new HttpPost(url);
			if (sign) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, CHARSET_UTF_8);
				httppost.setEntity(entity);
			}
			DefaultHttpClient httpclient = new DefaultHttpClient();

			if (StringUtils.isNotBlank(proxyHost)) {
				HttpHost proxy = new HttpHost(proxyHost, port);
				httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
			}

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity httpEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(httpEntity), CHARSET_UTF_8));
			if (httpEntity != null) {
				httpEntity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static String post(String url, HttpEntity entity, String proxyHost, int port, List<Header> headers) {
		if (url == null || url.length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		try {
			HttpPost httppost = new HttpPost(url);

			httppost.setEntity(entity);

			if (CollectionUtils.isNotEmpty(headers))
				for (Header header : headers) {
					httppost.addHeader(header);
				}

			DefaultHttpClient httpclient = new DefaultHttpClient();

			if (StringUtils.isNotBlank(proxyHost)) {
				HttpHost proxy = new HttpHost(proxyHost, port);
				httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
			}

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity httpEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() / 100 != 2) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(httpEntity), CHARSET_UTF_8));
			if (httpEntity != null) {
				httpEntity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * @param url
	 *            url
	 * @param proxyHost
	 *            proxyHost
	 * @param port
	 *            port
	 * @return 响应内容
	 */
	public static String get(String url, String proxyHost, int port) {
		StringBuilder sb = new StringBuilder();
		DefaultHttpClient httpclient = new DefaultHttpClient();

		if (StringUtils.isNotBlank(proxyHost)) {
			HttpHost proxy = new HttpHost(proxyHost, port);
			httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		}

		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(entity), CHARSET_UTF_8));
			if (entity != null) {
				entity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		return sb.toString();
	}

	public static String get(String url, String proxyHost, int port, List<Header> headers) {
		StringBuilder sb = new StringBuilder();
		DefaultHttpClient httpclient = new DefaultHttpClient();

		if (StringUtils.isNotBlank(proxyHost)) {
			HttpHost proxy = new HttpHost(proxyHost, port);
			httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		}

		HttpGet httpget = new HttpGet(url);

		if (CollectionUtils.isNotEmpty(headers))
			for (Header header : headers) {
				httpget.addHeader(header);
			}

		try {
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(entity), CHARSET_UTF_8));
			if (entity != null) {
				entity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		return sb.toString();
	}

	public static String get(String url, String proxyHost, int port, String encoding) {
		StringBuilder sb = new StringBuilder();
		DefaultHttpClient httpclient = new DefaultHttpClient();

		if (StringUtils.isNotBlank(proxyHost)) {
			HttpHost proxy = new HttpHost(proxyHost, port);
			httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		}

		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(entity), encoding == null ? CHARSET_UTF_8 : encoding));
			if (entity != null) {
				entity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		return sb.toString();
	}

	public static String get(String url, String proxyHost, int port, String encoding, int timeout) {
		StringBuilder sb = new StringBuilder();
		DefaultHttpClient httpclient = new DefaultHttpClient();

		if (StringUtils.isNotBlank(proxyHost)) {
			HttpHost proxy = new HttpHost(proxyHost, port);
			httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		}

		if (timeout > 0) {
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
		}

		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(entity), encoding == null ? CHARSET_UTF_8 : encoding));
			if (entity != null) {
				entity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 通过httpclient,使用url,进行get访问,将response转换成string 返回.
	 * 
	 * @param url
	 *            : 访问url 形如:http://dict.baidu.com/s?wd=test
	 * @return response String
	 */
	public static String get(String url) {
		StringBuilder sb = new StringBuilder();
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(entity), CHARSET_UTF_8));
			if (entity != null) {
				entity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String get(String url, String encoding) {
		StringBuilder sb = new StringBuilder();
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(entity), encoding == null ? CHARSET_UTF_8 : encoding));
			if (entity != null) {
				entity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		return sb.toString();
	}

	static long startTime;

	public static String get(int timeout, String url, String encoding) {
		StringBuilder sb = new StringBuilder();
		DefaultHttpClient httpclient = new DefaultHttpClient();
		if (timeout > 0) {
			HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), timeout);
			HttpConnectionParams.setSoTimeout(httpclient.getParams(), timeout);
		}
		HttpGet httpget = new HttpGet(url);
		try {
			startTime = System.currentTimeMillis();
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(entity), encoding == null ? CHARSET_UTF_8 : encoding));
			if (entity != null) {
				entity.consumeContent();
			}
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			long endTime = System.currentTimeMillis();
			System.out.println("程序运行时间： " + ((endTime - startTime) / 1000) + "秒");
			e.printStackTrace();
		} catch (HttpHostConnectException e) {
			long endTime = System.currentTimeMillis();
			System.out.println("程序运行时间： " + ((endTime - startTime) / 1000) + "秒");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String httpsGet(String url) throws Exception {
		SSLContext sslContext = null;
		sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} }, new SecureRandom());

		SSLSocketFactory sf = new SSLSocketFactory(sslContext);

		DefaultHttpClient httpClient = new DefaultHttpClient();
		Scheme httpsScheme = new Scheme("https", sf, 443);
		httpClient.getConnectionManager().getSchemeRegistry().register(httpsScheme);

		StringBuffer sb = new StringBuffer();
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				// 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			sb.append(new String(EntityUtils.toByteArray(entity), CHARSET_UTF_8));
			if (entity != null) {
				entity.consumeContent();
			}
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static String httpsPost(String url, Map<String, String> param, String oCharset) throws Exception {
		SSLContext sslContext = null;
		sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} }, new SecureRandom());

		SSLSocketFactory sf = new SSLSocketFactory(sslContext);

		DefaultHttpClient httpClient = new DefaultHttpClient();
		Scheme httpsScheme = new Scheme("https", sf, 443);
		httpClient.getConnectionManager().getSchemeRegistry().register(httpsScheme);

		// build HttpEntity
		boolean sign = true;
		if (param == null || param.size() == 0) {
			sign = false;
		}
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		if (sign) {
			Iterator<Map.Entry<String, String>> iterator = param.entrySet().iterator();

			Map.Entry<String, String> entry = null;
			while (iterator.hasNext()) {
				entry = (Map.Entry<String, String>) iterator.next();
				formParams
						.add(new BasicNameValuePair(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
			}
		}

		StringBuffer sb = new StringBuffer();
		HttpPost httpPost = new HttpPost(url);
		if (sign) {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, CHARSET_UTF_8);
			httpPost.setEntity(entity);
		}

		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) { // 这个是非正常访问,返回错误码
				sb.append("error:").append(response.getStatusLine().getStatusCode()).append('\n');
			}
			if (oCharset == null || "".equals(oCharset))
				oCharset = CHARSET_UTF_8;
			sb.append(new String(EntityUtils.toByteArray(entity), oCharset));
			if (entity != null) {
				entity.consumeContent();
			}
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * Post object using json
	 * 
	 * @param url
	 * @param object
	 * @param responseType
	 * @return
	 * @throws IOException
	 */
	public static <T> T postForObject(String url, Object obj, Class<T> responseType) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		String response = "";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(mapper.writeValueAsString(obj), HTTP.UTF_8);
			httpPost.setEntity(stringEntity);
			httpPost.setHeader("Content-type", "application/json");
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			response = httpclient.execute(httpPost, responseHandler);
			logger.info("*****request "+url+":response="+response);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return mapper.readValue(response, responseType);
	}
	
	/*
	public  static void main(String[] arg) {
		try {
			
		
		ObjectMapper mapper = new ObjectMapper();
		String response = "{\"code\":\"\",\"data\":[{\"status\":0,\"ids\":null,\"rejectReasons\":null,\"detailReasons\":null,\"flag\":null,\"id\":\"1\",\"offerStatus\":\"3\",\"medicalStatus\":null,\"applyStatus\":\"CO\"},{\"status\":0,\"ids\":null,\"rejectReasons\":null,\"detailReasons\":null,\"flag\":null,\"id\":\"1\",\"offerStatus\":\"X\",\"medicalStatus\":null,\"applyStatus\":\"CO\"}],\"msg\":null}";
		NesOfferResponse nr = mapper.readValue(response, NesOfferResponse.class);
		
		String response = "{\"offerId\":7511,\"personalInfo\":{\"id\":3,\"baseId\":3,\"name\":\"wrb测试10\",\"gender\":1,\"birthday\":\"1971-12-16\",\"nation\":\"643\","
				+ "\"nationality\":\"N58\",\"married\":1,\"idType\":1,\"idNumber\":\"6c4rf5\",\"idDeadline\":\"2016-12-01\",\"registerAddr\":\"户籍地址\","
				+ "\"phone\":\"13575459182\",\"email\":\"wb.zhaozy@mesg.corp.netease.com\",\"liveAddr\":\"仙居地址\","
				+ "\"communicationAddr\":\"通讯地址\",\"emergencyContacts\":\"紧急联系人\",\"emergencyRelation\":\"爸爸\","
				+ "\"emergencyPhone\":\"13566151212\",\"salaryCardType\":0,\"attId\":319037,\"attRegisterId\":319036,"
				+ "\"attIdFileInfo\":{\"id\":319037,\"name\":\"wrb测试10-身份证.png\","
				+ "\"url\":\"http://nos.netease.com/hzhr.rms/21381-c30e9c189c1e4bec986196059438e111?download/u003dwrb%E6%B5%8B%E8%AF%9510-%E8%BA%AB%E4%BB%BD%E8%AF%81.png\","
				+ "\"nosKey\":\"21381-c30e9c189c1e4bec986196059438e111\"},\"attRegisterFileInfo\":{\"id\":319036,\"name\":\"wrb测试10-户口本.jpg\","
				+ "\"url\":\"http://nos.netease.com/hzhr.rms/21381-48e364633e50464ba2a69325a1e88d1c?download/u003dwrb%E6%B5%8B%E8%AF%9510-%E6%88%B7%E5%8F%A3%E6%9C%AC.jpg\","
				+ "\"nosKey\":\"21381-48e364633e50464ba2a69325a1e88d1c\"}},\"educationInfo\":{\"baseId\":3,\"educationInfo\"{\"id\":3,\"baseId\":3,"
				+ "\"schoolName\":\"浙江XX大学\",\"majorName\":\"XX工程\",\"startDate\":\"2016-12-01\",\"finishDate\":\"2016-12-03\",\"education\":\"L22\","
				+ "\"degree\":\"DG30\"},{\"id\":4,\"baseId\":3,\"schoolName\":\"陕西XX大学\",\"majorName\":\"XX工程2\",\"startDate\":\"2016-12-03\",\"finishDate\":"
				+ "\"2016-12-04\",\"education\":\"L20\",\"degree\":\"DG30\"}],\"attTopGraduateId\":319042,\"attTopDegreeId\":319043,"
				+ "\"attTopGraduateFileInfo\":{\"id\":319042,\"name\":\"wrb测试10-最高学历毕业证.png\","
				+ "\"url\":\"http://nos.netease.com/hzhr.rms/731636-5cd5c22eaacc4c4f9ef3ee013a88879c?download/u003dwrb%E6%B5%8B%E8%AF%9510-%E6%9C%80%E9%AB%98%E5%AD%A6%E5%8E%86%E6%AF%95%E4%B8%9A%E8%AF%81.png\","
				+ "\"nosKey\":\"731636-5cd5c22eaacc4c4f9ef3ee013a88879c\"},"
				+ "\"attTopDegreeFileInfo\":{\"id\":319043,\"name\":\"wrb测试10-最高学位学位证.png\","
				+ "\"url\":\"http://nos.netease.com/hzhr.rms/910414-1d75a7b92db74afc8b11ec839ca323e1?download/u003dwrb%E6%B5%8B%E8%AF%9510-%E6%9C%80%E9%AB%98%E5%AD%A6%E4%BD%8D%E5%AD%A6%E4%BD%8D%E8%AF%81.png\","
				+ "\"nosKey\":\"910414-1d75a7b92db74afc8b11ec839ca323e1\"}},\"workExperienceInfo\":{\"workExperienceInfo\"]},\"familyInfo\"],\"othersInfo\":{\"id\":3,\"baseId\":3,\"diseaseFlag\":false,"
				+ "\"partTimeJobFlag\":false,\"keepSercetFlag\":false,\"crimeFlag\":false,\"workedFlag\":false,\"relativeWorkedFlag\":true,\"relativeName\":\"姓名\",\"relationship\":\"关系\"},"
				+ "\"workCardInfos\":{\"baseId\":3,\"attWorkCardId\":319045,\"attWorkCardFileInfo\":{\"id\":319045,\"name\":\"wrb测试10-工牌照.png\","
				+ "\"url\":\"http://nos.netease.com/hzhr.rms/400600-78ae35136fb54056bc825c2989b809a7?download/u003dwrb%E6%B5%8B%E8%AF%9510-%E5%B7%A5%E7%89%8C%E7%85%A7.png\",\"nosKey\":\"400600-78ae35136fb54056bc825c2989b809a7\"}}}";
		ApplicantData ad = mapper.readValue(response, ApplicantData.class);
		int i=0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}*/

	/**
	 * Mocked interface of corp mail system
	 * 
	 * @param url
	 * @param param
	 * @return 
	 *         {"email":"XXX","password":"XXX","result":"创建成功","resultType":"true"
	 *         }, {\"result\":\"账号已经存在\",\"resultType\":\"false\"}
	 */
	public static String httpsPostTest(String url, Map<String, String> param, String oCharset) {

		String[] results = new String[] {
				"{\"email\":\"XXX\",\"password\":\"XXX\",\"result\":\"创建成功\",\"resultType\":\"true\"}",
				"{\"result\":\"城市名错误\",\"resultType\":\"false\"}", "{\"result\":\"部门名错误\",\"resultType\":\"false\"}",
				"{\"email\":\"XXX\",\"password\":\"XXX\",\"result\":\"创建成功\",\"resultType\":\"true\"}",
				"{\"result\":\"用户名已经存在\",\"resultType\":\"false\"}",
				"{\"result\":\"密码强度不符合要求\",\"resultType\":\"false\"}",
				"{\"email\":\"XXX\",\"password\":\"XXX\",\"result\":\"创建成功\",\"resultType\":\"true\"}",
				"{\"result\":\"账号的长度超长\",\"resultType\":\"false\"}",
				"{\"email\":\"XXX\",\"password\":\"XXX\",\"result\":\"创建成功\",\"resultType\":\"true\"}" };
		Random rand = new Random();
		int index = rand.nextInt(results.length);
		String resultStr = results[index];

		System.out.println("httpsPostTest()...");

		return resultStr;

	}

}
