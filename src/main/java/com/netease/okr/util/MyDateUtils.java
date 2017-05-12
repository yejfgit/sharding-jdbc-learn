package com.netease.okr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public class MyDateUtils extends DateUtils {

		// 2015
		private static final SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
		// 2015-09
		private static final SimpleDateFormat YEAR_MONTH = new SimpleDateFormat("yyyy-MM");
		// 2015-09-01
		private static final SimpleDateFormat YEAR_MONTH_DAY = new SimpleDateFormat("yyyy-MM-dd");
		// 2015年9月1日
		private static final SimpleDateFormat YEAR_MONTH_DAY_chinese = new SimpleDateFormat("yyyy年MM月dd日");
		// 20150901
		private static final SimpleDateFormat YEAR_MONTH_DAY_ORI = new SimpleDateFormat("yyyyMMdd");
		// 2015-09-01 10:00
		private static final SimpleDateFormat YEAR_MONTH_DAY_HM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 2015-09-01 10:11:11
		private static final SimpleDateFormat YEAR_MONTH_DAY_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 2015-09-01 10:11:11.0
		private static final SimpleDateFormat YEAR_MONTH_DAY_HMSMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		// 2015-8-1
		private static final SimpleDateFormat YEAR_MONTH_DAY_SIMPLE = new SimpleDateFormat("yyyy-M-d");
		// 16/05/2007
		private static final SimpleDateFormat DAY_MONTH_YEAR = new SimpleDateFormat("dd/MM/yyyy");
		// 20161214
		private static final SimpleDateFormat YEARMONTHDAY = new SimpleDateFormat("yyyyMMdd");

		/**
		 * java.util.Date类型转String，格式为 yyyy
		 * 
		 * @param date
		 * @return
		 */
		public static String formatYDate(Date date, String forNullVal) {
			return formatDate(YEAR, date, forNullVal);
		}

		/**
		 * java.util.Date类型转String，格式为 yyyy
		 *
		 * @param date
		 * @return
		 */
		public static String formatYMHDate(Date date, String forNullVal) {
			return formatDate(YEARMONTHDAY, date, forNullVal);
		}


		/**
		 * java.util.Date类型转String，格式为 yyyy-MM
		 * 
		 * @param date
		 * @return
		 */
		public static String formatYmDate(Date date, String forNullVal) {
			return formatDate(YEAR_MONTH, date, forNullVal);
		}

		/**
		 * java.util.Date类型转String，格式为 yyyy-MM-dd
		 * 
		 * @param date
		 * @return
		 */
		public static String formatYmdDate(Date date, String forNullVal) {
			return formatDate(YEAR_MONTH_DAY, date, forNullVal);
		}

		/**
		 * java.util.Date类型转String，格式为 yyyy年MM月dd日
		 * 
		 * @param date
		 * @return
		 */
		public static String formatYmdDateChinese(Date date, String forNullVal) {
			return formatDate(YEAR_MONTH_DAY_chinese, date, forNullVal);
		}

		/**
		 * java.util.Date类型转String，格式为 yyyyMMdd
		 *
		 * @param date
		 * @return
		 */
		public static String formatYmdDateOri(Date date, String forNullVal) {
			return formatDate(YEAR_MONTH_DAY_ORI, date, forNullVal);
		}


		/**
		 * java.util.Date类型转String，格式为 yyyy-MM-dd HH:mm
		 * 
		 * @param date
		 * @param forNullVal
		 * @return
		 */
		public static String formatHmTime(Date date, String forNullVal) {
			return formatDate(YEAR_MONTH_DAY_HM, date, forNullVal);
		}

		/**
		 * java.util.Date类型转String，格式为 yyyy-MM-dd HH:mm:ss
		 * 
		 * @param date
		 * @return
		 */
		public static String formatHmsTime(Date date, String forNullVal) {
			return formatDate(YEAR_MONTH_DAY_HMS, date, forNullVal);
		}

		/**
		 * java.util.Date类型转Srting，格式为 yyyy-M-d
		 * 
		 * @param date
		 * @return
		 */
		public static String formatSimpleYmdDate(Date date) {
			return formatDate(YEAR_MONTH_DAY_SIMPLE, date, "");
		}

		/**
		 * java.util.Date类型转Srting，格式为 dd/MM/yyyy
		 * 
		 * @param date
		 * @return
		 */
		public static String formatDmyDate(Date date) {
			return formatDate(DAY_MONTH_YEAR, date, "");
		}

		/**
		 * 格式为 yyyy-MM的String转java.util.Date。错误返回null
		 * 
		 * @param date
		 * @return
		 */
		public static Date parseYmDate(String date) {
			if (StringUtils.isEmpty(date))
				return null;
			return parseDate(YEAR_MONTH, date);
		}

		/**
		 * @param sdf
		 * @param date
		 * @param forNullVal
		 * @return
		 */
		private static String formatDate(SimpleDateFormat sdf, Date date, String forNullVal) {
			if (date == null)
				return forNullVal;
			return sdf.format(date);
		}

		/**
		 * 格式为 yyyy-MM-dd的String转java.util.Date。错误返回null
		 * 
		 * @param date
		 * @return
		 */
		public static Date parseYmdDate(String date) {
			if (StringUtils.isEmpty(date))
				return null;
			return parseDate(YEAR_MONTH_DAY, date);
		}

		/**
		 * 格式为 yyyy-MM-dd HH:mm 的String转java.util.Date。错误返回null
		 * 
		 * @param date
		 * @return
		 */
		public static Date parseHmTime(String date) {
			if (StringUtils.isEmpty(date))
				return null;
			return parseDate(YEAR_MONTH_DAY_HM, date);
		}

		/**
		 * 格式为 yyyy-MM-dd HH:mm:ss的String转java.util.Date。错误返回null
		 * 
		 * @param date
		 * @return
		 */
		public static Date parseHmsTime(String date) {
			if (StringUtils.isEmpty(date))
				return null;
			return parseDate(YEAR_MONTH_DAY_HMS, date);
		}

		/**
		 * 格式为 yyyy-MM-dd HH:mm:ss.S的String转java.util.Date。错误返回null
		 *
		 * @param date
		 * @return
		 */
		public static Date parseHmsMsTime(String date) {
			if (StringUtils.isEmpty(date))
				return null;
			return parseDate(YEAR_MONTH_DAY_HMSMS, date);
		}

		/**
		 * 对应字符串转日期
		 * 
		 * @param sdf
		 * @param date
		 * @return
		 */
		private static Date parseDate(SimpleDateFormat sdf, String date) {
			try {
				Date d = sdf.parse(date);
				return d;
			} catch (ParseException e) {
				LoggerUtil.error("日期转换错误", e);
			}
			return null;
		}

		/**
		* @Description  获取当前小时
		* @author liulei
		* @date 2016/12/15
		*/
		public static Integer getNowhour() {
			Calendar cal=Calendar.getInstance();
			return cal.get(Calendar.HOUR_OF_DAY);
		}

		
		public static java.util.Date stringSimpleToDate(String date) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				return (java.util.Date) format.parseObject(date);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static String dateToString(java.util.Date date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(date);
		}
		
		public static String dateToString(Date date, String formatter) {
			try {
				SimpleDateFormat format = new SimpleDateFormat(formatter);
				return format.format(date);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		public static Date dateAddDays(Date date, int addDays) {
			Date newDate = null;
			try {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DATE, addDays);
				newDate = cal.getTime();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return newDate;
		}
		
		
		public static String getDateString(java.util.Date date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(date);
		}
		
		
		public static int getDaysIgnoreTime(Date beginDate, Date endDate) {
			if (beginDate == null || endDate == null)
				return 0;
			Date begin = stringToDate(dateToString(beginDate, "yyyy-MM-dd").concat(" 09:00:00"),
					"yyyy-MM-dd HH:mm:ss");
			Date end = stringToDate(dateToString(endDate, "yyyy-MM-dd").concat(" 09:00:00"),
					"yyyy-MM-dd HH:mm:ss");
			return getDays(begin, end);
		}

		public static int getDays(Date beginDate, Date endDate) {
			if (beginDate == null || endDate == null)
				return 0;

			long beginTime = beginDate.getTime();
			long endTime = endDate.getTime();

			int days = (int) ((endTime - beginTime) / (1000 * 3600 * 24));

			return days;
		}
		
		/** Added by dale_wang. */
		public static java.util.Date stringToDate(String date) {
			try {
				SimpleDateFormat format = null;
				if (date.trim().length() == "yyyy-MM-dd".length()) {
					format = new SimpleDateFormat("yyyy-MM-dd");
				} else {
					format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				}
				return (java.util.Date) format.parseObject(date);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		/** Added by jackhuo. */
		public static java.util.Date stringToDate(String date, String format_str) {
			try {
				SimpleDateFormat format = new SimpleDateFormat(format_str);
				return (java.util.Date) format.parseObject(date);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

}

