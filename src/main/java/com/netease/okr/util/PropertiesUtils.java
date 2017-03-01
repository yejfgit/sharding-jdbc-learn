package com.netease.okr.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	public static String application = "config";

	private ResourceBundle bundle = null;

	public PropertiesUtils(String baseName, Locale local) {
		if (baseName == null || "".equals(baseName))
			baseName = application;
		if (local == null)
			local = new Locale("zh", "CN");
		try {
			bundle = ResourceBundle.getBundle(baseName, local);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public String getPropery(String key) {
		String value = "";
		try {
			value = bundle.getString(key);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return value;
	}

}
