package com.netease.okr.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;


public class LoggerUtil{
		
    public static void error(String msg,Exception e) {
		
    	printError(Logger.getLogger("errorLogger"),msg,e);
	}
	
	public static void info(Object msg) {
		Logger.getLogger("rootLogger").info(msg);
	}
	
	public static void uploadLogInfo(Object msg) {
		Logger.getLogger("uploadLogger").info(msg);
	}
	
	public static void uploadLogError(String msg,Exception e) {
		
		printError(Logger.getLogger("uploadLogger"),msg,e);
	}
	
    public static void printError(Logger logger,String msg,Exception e) {
		if(msg != null){
		   logger.error(msg);
		}
		
		if(e != null){
		    ByteArrayOutputStream  buf = new ByteArrayOutputStream();  
	        e.printStackTrace(new PrintWriter(buf,true));  
		    logger.error(buf.toString());
		}
	}

}
