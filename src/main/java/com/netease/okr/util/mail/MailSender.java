package com.netease.okr.util.mail;

import org.springframework.stereotype.Controller;
@Controller
public class MailSender {

	/*final static Logger logger = Logger.getLogger(MailSender.class);
	@Resource(name = "failSendMailRecordServiceImpl")
	private  FailSendMailRecordService fail;
	
	private static FailSendMailRecordService failSendMailRecordService;

	private static PropertiesUtils properties = new PropertiesUtils("config", null);
	 @PostConstruct  
	    public void init(){  
		 MailSender.failSendMailRecordService = fail;  
	    }  
	 *//**
	 * Use @ as args separator.
	 * 
	 * @param templateName
	 * @param sendArgs
	 * @param subject
	 * @param to
	 * @param cc
	 *//*
	public static void send(String templateName, Map<String, String> sendArgs, String subject, String to, String cc) {
		// String url = "http://mail.admin.netease.com/service/addTask.do";
		final String separator = "@";
		String url = properties.getPropery("mail_url");
		Map<String, String> map = new HashMap<String, String>();
		map.put("productName", "entrant");
		map.put("separator", separator);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		map.put("startTime", f.format(MyDateUtils.addSeconds(new Date(), 10)));
		map.put("templateName", templateName);
		if (!MyStringUtil.isBlank(subject)) {
			map.put("subject", subject);
		}
		if (!MyStringUtil.isBlank(to)) {
			map.put("to", to);
		}
		if (!MyStringUtil.isBlank(cc)) {
			map.put("cc", cc);
		}
		// process send args
		StringBuilder sb = new StringBuilder();
		for (String key : sendArgs.keySet()) {
			sb.append(key).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), separator);
		for (String key : sendArgs.keySet()) {
			sb.append(sendArgs.get(key)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		map.put("sendArgs", sb.toString());

		String result = "send mail is disabled";
		String sendMail = properties.getPropery("send_mail_enabled");
		if ("1".equals(sendMail)) {
			result = HttpClientUtil.post(url, map);
		}
		String log = "[templateName]:" + templateName + ",[subject]:" + subject + ",[to]:" + to + ",[cc]:" + cc;
		System.out.println("send mail:" + result + ",info:" + log);
		logger.info("send mail:" + result + ",info:" + log);
		if(!result.equals("ok")){
			FailSendMailRecordDTO dto = new FailSendMailRecordDTO();
			dto.setFailPoint(subject);
			dto.setIsSuccess(0);
			dto.setContent(sendArgs.get("content"));
			dto.setIsResend(0);
			dto.setMethod("send");
			dto.setSubject(subject);
			dto.setTemplateName(templateName);
			dto.setToMail(to);
			dto.setCc(cc);
			dto.setIsValid(1);
			dto.setResult(result);
			int c = failSendMailRecordService.addFailSendMailRecord(dto);
			if(c <0) logger.info("添加邮件记录失败：" + dto);
			else logger.info("添加邮件失败记录成功：" + dto);
		}
		
	}

	*//**
	 * Use ; as args separator.
	 * 
	 * @param templateName
	 * @param sendArgs
	 * @param subject
	 * @param to
	 * @param cc
	 *//*
	public static void send2(String templateName, Map<String, String> sendArgs, String subject, String to, String cc) {
		final String separator = ";";

		String url = properties.getPropery("mail_url");
		Map<String, String> map = new HashMap<String, String>();
		map.put("productName", "entrant");
		map.put("separator", separator);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		map.put("startTime", f.format(MyDateUtils.addSeconds(new Date(), 10)));
		map.put("templateName", templateName);
		if (!MyStringUtil.isBlank(subject)) {
			map.put("subject", subject);
		}
		if (!MyStringUtil.isBlank(to)) {
			map.put("to", to);
		}
		if (!MyStringUtil.isBlank(cc)) {
			map.put("cc", cc);
		}
		// process send args
		StringBuilder sb = new StringBuilder();
		for (String key : sendArgs.keySet()) {
			sb.append(key).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), separator);
		for (String key : sendArgs.keySet()) {
			sb.append(sendArgs.get(key)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		map.put("sendArgs", sb.toString());

		String result = "send mail is disabled";
		String sendMail = properties.getPropery("send_mail_enabled");
		if ("1".equals(sendMail)) {
			result = HttpClientUtil.post(url, map);
		}
		String log = "[templateName]:" + templateName + ",[subject]:" + subject + ",[to]:" + to + ",[cc]:" + cc;
		System.out.println("send mail:" + result + ",info:" + log);
		logger.info("send mail:" + result + ",info:" + log);
		if(!result.equals("ok")){
			FailSendMailRecordDTO dto = new FailSendMailRecordDTO();
			dto.setFailPoint(subject);
			dto.setIsSuccess(0);
			dto.setContent(sendArgs.get("content"));
			dto.setIsResend(0);
			dto.setMethod("send2");
			dto.setSubject(subject);
			dto.setCc(cc);
			dto.setIsValid(1);
			dto.setResult(result);
			dto.setTemplateName(templateName);
			dto.setToMail(to);
			int c = failSendMailRecordService.addFailSendMailRecord(dto);
			if(c <0) logger.info("添加邮件记录失败：" + dto);
			else logger.info("添加邮件失败记录成功：" + dto);
		}
		
		
	}

	*//**
	 * Use ~ as args separator.
	 * 
	 * @param templateName
	 * @param sendArgs
	 * @param subject
	 * @param to
	 * @param cc
	 *//*
	public static void send3(String templateName, Map<String, String> sendArgs, String subject, String to, String cc) {
		final String separator = "~";

		String url = properties.getPropery("mail_url");
		Map<String, String> map = new HashMap<String, String>();
		map.put("productName", "entrant");
		map.put("separator", separator);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		map.put("startTime", f.format(MyDateUtils.addSeconds(new Date(), 10)));
		map.put("templateName", templateName);
		if (!MyStringUtil.isBlank(subject)) {
			map.put("subject", subject);
		}
		if (!MyStringUtil.isBlank(to)) {
			map.put("to", to);
		}
		if (!MyStringUtil.isBlank(cc)) {
			map.put("cc", cc);
		}
		// process send args
		StringBuilder sb = new StringBuilder();
		for (String key : sendArgs.keySet()) {
			sb.append(key).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), separator);
		for (String key : sendArgs.keySet()) {
			sb.append(sendArgs.get(key)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		map.put("sendArgs", sb.toString());

		String result = "send mail is disabled";
		String sendMail = properties.getPropery("send_mail_enabled");
		if ("1".equals(sendMail)) {
			result = HttpClientUtil.post(url, map);
		}
		String log = "[templateName]:" + templateName + ",[subject]:" + subject + ",[to]:" + to + ",[cc]:" + cc;
		System.out.println("send mail:" + result + ",info:" + log);
		logger.info("send mail:" + result + ",info:" + log);
		if(!result.equals("ok")){
			FailSendMailRecordDTO dto = new FailSendMailRecordDTO();
			dto.setFailPoint(subject);
			dto.setIsSuccess(0);
			dto.setContent(sendArgs.get("content"));
			dto.setIsResend(0);
			dto.setMethod("send3");
			dto.setSubject(subject);
			dto.setTemplateName(templateName);
			dto.setToMail(to);
			dto.setIsValid(1);
			dto.setCc(cc);
			dto.setResult(result);
			int c = failSendMailRecordService.addFailSendMailRecord(dto);
			if(c <0) logger.info("添加邮件失败记录失败：" + dto);
			else logger.info("添加邮件失败记录成功：" + dto);
		}
		
	}
	
	
	public static String sendWithFailId(String templateName, Map<String, String> sendArgs, String subject, String to, String cc) {
		// String url = "http://mail.admin.netease.com/service/addTask.do";
		final String separator = "@";
		String url = properties.getPropery("mail_url");
		Map<String, String> map = new HashMap<String, String>();
		map.put("productName", "entrant");
		map.put("separator", separator);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		map.put("startTime", f.format(MyDateUtils.addSeconds(new Date(), 10)));
		map.put("templateName", templateName);
		if (!MyStringUtil.isBlank(subject)) {
			map.put("subject", subject);
		}
		if (!MyStringUtil.isBlank(to)) {
			map.put("to", to);
		}
		if (!MyStringUtil.isBlank(cc)) {
			map.put("cc", cc);
		}
		// process send args
		StringBuilder sb = new StringBuilder();
		for (String key : sendArgs.keySet()) {
			sb.append(key).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), separator);
		for (String key : sendArgs.keySet()) {
			sb.append(sendArgs.get(key)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		map.put("sendArgs", sb.toString());

		String result = "send mail is disabled";
		String sendMail = properties.getPropery("send_mail_enabled");
		if ("1".equals(sendMail)) {
			result = HttpClientUtil.post(url, map);
		}
		String log = "[templateName]:" + templateName + ",[subject]:" + subject + ",[to]:" + to + ",[cc]:" + cc;
		System.out.println("send mail:" + result + ",info:" + log);
		logger.info("send mail:" + result + ",info:" + log);
		return result;
		
	}
	
	
	
	public static String send2WithFailId( String templateName, Map<String, String> sendArgs, String subject, String to, String cc) {
		// String url = "http://mail.admin.netease.com/service/addTask.do";
		final String separator = "@";
		String url = properties.getPropery("mail_url");
		Map<String, String> map = new HashMap<String, String>();
		map.put("productName", "entrant");
		map.put("separator", separator);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		map.put("startTime", f.format(MyDateUtils.addSeconds(new Date(), 10)));
		map.put("templateName", templateName);
		if (!MyStringUtil.isBlank(subject)) {
			map.put("subject", subject);
		}
		if (!MyStringUtil.isBlank(to)) {
			map.put("to", to);
		}
		if (!MyStringUtil.isBlank(cc)) {
			map.put("cc", cc);
		}
		// process send args
		StringBuilder sb = new StringBuilder();
		for (String key : sendArgs.keySet()) {
			sb.append(key).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), separator);
		for (String key : sendArgs.keySet()) {
			sb.append(sendArgs.get(key)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		map.put("sendArgs", sb.toString());

		String result = "send mail is disabled";
		String sendMail = properties.getPropery("send_mail_enabled");
		if ("1".equals(sendMail)) {
			result = HttpClientUtil.post(url, map);
		}
		String log = "[templateName]:" + templateName + ",[subject]:" + subject + ",[to]:" + to + ",[cc]:" + cc;
		System.out.println("send mail:" + result + ",info:" + log);
		logger.info("send mail:" + result + ",info:" + log);
		return result;
		
	}
	
	
	
	public static String send3WithFailId(String templateName, Map<String, String> sendArgs, String subject, String to, String cc) {
		// String url = "http://mail.admin.netease.com/service/addTask.do";
		final String separator = "@";
		String url = properties.getPropery("mail_url");
		Map<String, String> map = new HashMap<String, String>();
		map.put("productName", "entrant");
		map.put("separator", separator);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		map.put("startTime", f.format(MyDateUtils.addSeconds(new Date(), 10)));
		map.put("templateName", templateName);
		if (!MyStringUtil.isBlank(subject)) {
			map.put("subject", subject);
		}
		if (!MyStringUtil.isBlank(to)) {
			map.put("to", to);
		}
		if (!MyStringUtil.isBlank(cc)) {
			map.put("cc", cc);
		}
		// process send args
		StringBuilder sb = new StringBuilder();
		for (String key : sendArgs.keySet()) {
			sb.append(key).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), separator);
		for (String key : sendArgs.keySet()) {
			sb.append(sendArgs.get(key)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		map.put("sendArgs", sb.toString());

		String result = "send mail is disabled";
		String sendMail = properties.getPropery("send_mail_enabled");
		if ("1".equals(sendMail)) {
			result = HttpClientUtil.post(url, map);
		}
		String log = "[templateName]:" + templateName + ",[subject]:" + subject + ",[to]:" + to + ",[cc]:" + cc;
		System.out.println("send mail:" + result + ",info:" + log);
		logger.info("send mail:" + result + ",info:" + log);
		return result;
		
	}
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
}
