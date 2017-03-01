package com.netease.okr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDateUtils extends DateUtils {

	private static Logger logger = LoggerFactory.getLogger(MyDateUtils.class);

	public static String dateToString(java.util.Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static Date getTomorrowNineClock() {
		String tomorrowString = MyDateUtils.getDateString(dateAddDays(new Date(), 1));
		return MyDateUtils.stringToDate(tomorrowString.concat(" 09:00:00"));
	}

	public static Date getNextDay(Date date) {
		return dateAddDays(date, 1);
	}

	public static String getDateString(java.util.Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
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

	public static java.util.Date stringSimpleToDate(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return (java.util.Date) format.parseObject(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Added by leaffall wang. */
	public static java.sql.Date utilDateToSqlDate(java.util.Date date) {
		if (date == null)
			return null;
		return new java.sql.Date(date.getTime());
	}

	/** Added by leaffall wang. */
	public static java.util.Date sqlDateToUtilDate(java.sql.Date date) {
		if (date == null)
			return null;
		return new java.util.Date(date.getTime());
	}

	// Converts a java.util.Date into a java.sql.Timestamp.
	public static java.sql.Timestamp dateToTimestamp(java.util.Date t) {
		if (t == null)
			return null;
		return new java.sql.Timestamp(t.getTime());
	}

	// Converts a java.sql.Timestamp into a java.util.Date
	public static java.util.Date timestampToDate(java.sql.Timestamp t) {
		if (t == null)
			return null;
		java.util.Date d;
		d = new java.util.Date(t.getTime() + (t.getNanos() / 1000000));
		return d;
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

	/***
	 * 将日期转化成'日期字符串'
	 * 
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String dateToSingleQuotesString(Date date, String formatter) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatter);
			String s = format.format(date);
			s = "'".concat(s).concat("'");
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Added by leaffall wang. Get current date of string. */
	public static String getDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return df.format(new Date());
	}

	public static String getTime() {
		Date now = new Date();
		return "" + now.getTime();
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

	public static Date dateAddMonths(Date date, int addMonths) {
		Date newDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, addMonths);
			newDate = cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return newDate;
	}

	public static Date getYesterDay() {
		return dateAddDays(new Date(), -1);
	}

	/**
	 * ��ʱ��
	 * 
	 * @param date
	 * @param addWeaks
	 * @return
	 */
	public static Date dateAddWeeks(Date date, int addWeaks) {
		Date newDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.WEEK_OF_MONTH, addWeaks);
			newDate = cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return newDate;
	}

	/**
	 * ��
	 * 
	 * @param beginDate
	 * @return
	 */
	public static Date getFirstSunday(Date beginDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(beginDate);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int day = c.get(Calendar.DAY_OF_WEEK);
		if (day == 1)
			return beginDate;
		else {
			Date beforeDate = MyDateUtils.dateAddDays(beginDate, -1);
			return getFirstSunday(beforeDate);
		}
	}

	public static Date getFirstMonday(Date beginDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(beginDate);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int day = c.get(Calendar.DAY_OF_WEEK);
		if (day == 2)
			return beginDate;
		else {
			Date beforeDate = MyDateUtils.dateAddDays(beginDate, -1);
			return getFirstMonday(beforeDate);
		}
	}

	public static Date dateAddYears(Date date, int addYears) {
		Date newDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, addYears);
			newDate = cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return newDate;
	}

	public static boolean isDate(String dstr, String format) {
		boolean result = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.parse(dstr);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
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

	public static String getSunday(String strDate) {
		String result = strDate;
		try {
			result = getDateString(getSunday(stringSimpleToDate(strDate)));
		} catch (Exception e) {
			logger.error("ReportGatekeeper.java--getSunday:" + e);
		}

		return result;
	}

	public static java.util.Date getSunday(Date dateTime) {
		Date date = dateTime;
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(dateTime);
			int intWeek = c.get(Calendar.DAY_OF_WEEK);
			c.add(Calendar.DATE, -(intWeek - 1));
			date = (c.getTime());
		} catch (Exception e) {
			logger.error("getSunday:" + e);
		}
		return date;
	}

	public static java.util.Date getNextSunday(Date dateTime) {
		Date sun = getSunday(dateTime);
		sun = dateAddWeeks(sun, 1);
		return sun;
	}

	public static java.util.Date getSaturday(Date dateTime) {
		Date date = null;
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(getSunday(dateTime));
			c.add(Calendar.DATE, 6);
			date = (c.getTime());
		} catch (Exception e) {
			logger.error("ReportGatekeeper.java--getSunday:" + e);
		}

		return date;
	}

	public static java.util.Collection<Date> dateToCollection(Date firstDate, Date secondDate) {
		Collection<Date> date_co = new Vector<Date>();
		Date temp = new Date(firstDate.getTime());
		if (firstDate.getTime() > secondDate.getTime())
			return null;
		try {
			date_co.add(firstDate);
			for (int i = 0; temp.getTime() < secondDate.getTime(); i++) {
				temp = dateAddDays(temp, 1);
				date_co.add(temp);
			}
			return date_co;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getDaysIgnoreTime(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null)
			return 0;
		Date begin = MyDateUtils.stringToDate(MyDateUtils.dateToString(beginDate, "yyyy-MM-dd").concat(" 09:00:00"),
				"yyyy-MM-dd HH:mm:ss");
		Date end = MyDateUtils.stringToDate(MyDateUtils.dateToString(endDate, "yyyy-MM-dd").concat(" 09:00:00"),
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

	public static int getDifferDays(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null)
			return 0;

		long beginTime = getFirstTimeInDay(beginDate).getTime();
		long endTime = getFirstTimeInDay(endDate).getTime();

		int days = (int) ((endTime - beginTime) / (1000 * 3600 * 24));

		return days;
	}

	/**
	 * add by wangjia
	 */
	public static int getQuarterByMonth(int month) {
		if (month >= 1 && month <= 12)
			return ((month + 2) / 3);
		else
			return -1;
	}

	public static java.util.Date getFirstTimeInDay(Date dateTime) {
		Date date = null;
		String dateString = MyDateUtils.getDateString(dateTime);
		date = MyDateUtils.stringSimpleToDate(dateString);
		return date;
	}

	public static String getFirstTimeInDayString(Date dateTime) {
		Date date = getFirstTimeInDay(dateTime);
		return dateToString(date);
	}

	public static java.util.Date getLastTimeInDay(Date dateTime) {
		Date date = null;
		String dateString = MyDateUtils.getDateString(dateTime);
		dateString = dateString.concat(" 23:59:59");
		date = MyDateUtils.stringToDate(dateString);
		return date;
	}

	public static String getLastTimeInDayString(Date dateTime) {
		Date date = getLastTimeInDay(dateTime);
		return dateToString(date);
	}

	public static Date getFirstDayInMonth(Date date) {
		Date rDate = null;
		String dateString = MyDateUtils.getDateString(date);
		dateString = dateString.substring(0, 8);
		dateString = dateString.concat("01");
		rDate = MyDateUtils.stringSimpleToDate(dateString);
		return rDate;
	}
	
	public static Date getFirstDayOfYear(Date date) {
		Date rDate = null;
		String dateString = MyDateUtils.getDateString(date);
		dateString = dateString.substring(0, 5);
		dateString = dateString.concat("01");
		dateString = dateString.concat("-01");
		rDate = MyDateUtils.stringSimpleToDate(dateString);
		return rDate;
	}

	public static Date getLastDayInMonth(Date firstDayInMonth) {
		Date tmp = MyDateUtils.dateAddMonths(firstDayInMonth, 1);
		tmp = MyDateUtils.getFirstDayInMonth(tmp);
		tmp = MyDateUtils.dateAddDays(tmp, -1);
		return tmp;
	}

	/*
	 * public static Date getFirstDayInQuarter(Date date) { Date rDate = null;
	 * String dateString = DateUtils.dateToSimpleString(date); String t =
	 * dateString.substring(5, 7); dateString = dateString.substring(0, 5); int
	 * i = new Integer(t).intValue(); int q = getQuarterByMonth(i); int month =
	 * (q - 1) * 3 + 1; if (month < 10) dateString = dateString.concat("0" +
	 * month); else dateString = dateString.concat("" + month); dateString =
	 * dateString.concat("-01"); rDate =
	 * DateUtils.stringSimpleToDate(dateString); return rDate; }
	 */

	/*
	 * public static Date getFirstDayInYear(Date date) { String dateString =
	 * DateUtils.dateToSimpleString(date); String t = dateString.substring(0,
	 * 4); dateString = t.concat("-01-01"); date =
	 * DateUtils.stringSimpleToDate(dateString); return date; }
	 */

	/*
	 * public static Date getLastDayInYear(Date date) { Date tmp =
	 * DateUtils.getFirstDayInYear(date); tmp = DateUtils.dateAddYears(tmp, 1);
	 * tmp = DateUtils.dateAddDays(tmp, -1); return tmp; }
	 * 
	 * public static Date getLastDayInMonth(Date firstDayInMonth) { Date tmp =
	 * DateUtils.dateAddMonths(firstDayInMonth, 1); tmp =
	 * DateUtils.getFirstDayInMonth(tmp); tmp = DateUtils.dateAddDays(tmp, -1);
	 * return tmp; }
	 * 
	 * public static Date getLastDayInQuarter(Date value) { Date tmp =
	 * DateUtils.getFirstDayInQuarter(value); tmp = DateUtils.dateAddMonths(tmp,
	 * 3); tmp = DateUtils.dateAddDays(tmp, -1); return tmp; }
	 */

	/*
	 * public static Date[] getBeforeDatesByType(Date beginDate, Date endDate,
	 * String reportType) { Date[] beforeDates = new Date[2]; Date
	 * beforeBeginDate = new Date(); Date beforeEndDate = new Date(); if
	 * ("m".equalsIgnoreCase(reportType)) { beforeBeginDate =
	 * dateAddMonths(beginDate, -1); beforeEndDate =
	 * getLastDayInMonth(beforeBeginDate); } else if
	 * ("w".equalsIgnoreCase(reportType)) { beforeBeginDate =
	 * dateAddWeeks(beginDate, -1); beforeEndDate = dateAddDays(beforeBeginDate,
	 * 6); } else if ("d".equalsIgnoreCase(reportType)) { beforeBeginDate =
	 * dateAddDays(beginDate, -1); beforeEndDate = beforeBeginDate; } else if
	 * ("q".equalsIgnoreCase(reportType)) { beforeBeginDate =
	 * dateAddMonths(beginDate, -3); beforeEndDate =
	 * getLastDayInQuarter(beforeBeginDate); } else if
	 * ("y".equalsIgnoreCase(reportType)) { beforeBeginDate =
	 * dateAddYears(beginDate, -1); beforeEndDate =
	 * getLastDayInYear(beforeBeginDate); } beforeDates[0] = beforeBeginDate;
	 * beforeDates[1] = beforeEndDate; return beforeDates; }
	 */

	public static boolean isFirstInMonth(Date date) {
		String dateString = MyDateUtils.getDateString(date);
		boolean flag = false;
		if ("01".equalsIgnoreCase(dateString.substring(8))) {
			flag = true;
		}
		return flag;
	}

	/*public static void main(String[] args) {
											 * System.out.println(MyDateUtils.
											 * dateAddDays
											 * (stringSimpleToDate("2009-10-9"),
											 * 126));
											 * System.out.println(stringSimpleToDate
											 * ("2009-02-31"));
											 * System.out.println
											 * (System.getProperty
											 * ("java.io.tmpdir"));
											 * System.out.println
											 * (Float.MAX_VALUE);
											 * System.out.println
											 * (Integer.MAX_VALUE);
											 
		// System.out.println(getDateString(getSunday(stringSimpleToDate("2009-12-19"))));
		// System.out.println(getDays(stringToDate("2010-09-10 10:00:00"), new
		// Date()));
		// System.out.println(getDifferDays(stringToDate("2010-09-10 10:00:00"),
		// new Date()));
		System.out.println(getDaysIgnoreTime(stringToDate("2013-04-12 18:35:14"), new Date()));
		System.out.println(getDays(stringToDate("2013-04-12 18:35:14"), new Date()));
	}*/

	public static Date getBeforeFiveMinuteDate(Date date) {
		Date rDate = null;
		long fiveMinute = 5 * 60 * 1000;
		rDate = new java.util.Date(date.getTime() - fiveMinute);
		return rDate;
	}

	public static Date getAfterFiveMinuteDate(Date date) {
		Date rDate = null;
		long fiveMinute = 5 * 60 * 1000;
		rDate = new java.util.Date(date.getTime() + fiveMinute);
		return rDate;
	}

	/**
	 * add by dani
	 */
	public static Date getAfterOneHourDate(Date date) {
		Date rDate = null;
		long oneHour = 60 * 60 * 1000;
		rDate = new java.util.Date(date.getTime() + oneHour);
		return rDate;
	}

	public static Date getBeforeOneHourDate(Date date) {
		Date rDate = null;
		long oneHour = 60 * 60 * 1000;
		rDate = new java.util.Date(date.getTime() - oneHour);
		return rDate;
	}

	public static Date getCheckErrorCountDate(Date dateTime) {
		Date rDate = null;
		rDate = getBeforeOneHourDate(dateTime);
		rDate = stringToDate(dateToString(rDate).substring(0, 14).concat("00:00"));
		return rDate;
	}

	public static Date getSameDayInLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		Date dayInLastWeek = cal.getTime();
		return dayInLastWeek;
	}

	public static Date getSameDayInLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date dayInLastMonth = cal.getTime();
		return dayInLastMonth;
	}

	public static Date getSameDayInLastMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		Date dayInLastMonth = cal.getTime();
		return dayInLastMonth;
	}

	public static Date getTheDayBeforeNDays(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -n);
		Date _30daysBefore = cal.getTime();
		return _30daysBefore;
	}

	public static Date dateAddHours(Date date, int addHours) {
		Date newDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.HOUR, addHours);
			newDate = cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return newDate;
	}

	public static Date dateAddMinutes(Date date, int addMins) {
		Date newDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MINUTE, addMins);
			newDate = cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return newDate;
	}

	public static Integer getHourOfDay(Date date) {
		Integer hour = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			hour = calendar.get(Calendar.HOUR_OF_DAY);
		} catch (Exception e) {
			return null;
		}
		return hour;
	}

	public static Integer getMinuteOfHour(Date date) {
		Integer minute = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			minute = calendar.get(Calendar.MINUTE);
		} catch (Exception e) {
			return null;
		}
		return minute;
	}

	public static Date getFirst10MinTimeBefore(Date date) {
		try {

			String dateHour = dateToString(date, "yyyy-MM-dd HH");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int minute = calendar.get(Calendar.MINUTE);
			int tenMinute = (minute / 10) * 10;
			String dateHour10MinTime = dateHour.concat(":").concat(String.valueOf(tenMinute));
			return stringToDate(dateHour10MinTime, "yyyy-MM-dd HH:mm");

		} catch (Exception e) {
			return null;
		}
	}
	
	


	public static void main(String[] args) throws ParseException { 
		/*String s = dateToSingleQuotesString(new Date(),"yyyyMMdd");
		int ret[] = getDateLength( "20050531 ", "20070101 "); 
		System.out.println("两个日期相隔:"+s + "年");*/
		
		//System.out.println(MyDateUtils.getDateString(getFirstDayOfYear(new Date())));
		
		/*String dateString = "2017-01-08";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateString);
				
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekOfMonth = calendar.get(Calendar.WEEK_OF_YEAR);
		System.out.println(weekOfMonth);*/
		
		String dateString = MyDateUtils.getDateString(new Date());
		String year = dateString.substring(0, 4);
		String month = dateString.substring(5, 7);
		
		System.out.println(year+"-"+month.replace("0", ""));
		
		/*Date startDate = stringSimpleToDate("2017-01-01");
		
		Date endDate = stringSimpleToDate("2018-01-01");
		
		Date date = null;
		
		while(startDate.before(endDate)){
			date = dateAddDays(startDate,6);
			System.out.print(getDateString(date)+"---");
			startDate = dateAddDays(date,1);
			System.out.println(getDateString(startDate));
		}*/
		
		
	} 
	
	public static int getMonthsOfData(Date fromDate,Date toDate){
		return getDateLength(dateToString(fromDate,"yyyyMMdd"),dateToString(toDate,"yyyyMMdd"))[0]*12;
	};
	
	public static int[] getDateLength(String fromDate, String toDate) { 
		Calendar c1 = getCal(fromDate); 
		Calendar c2 = getCal(toDate); 
		int[] p1 = { c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH) }; 
		int[] p2 = { c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH) }; 
		return new int[] { p2[0] - p1[0] }; 
	} 
	
	public static Calendar getCal(String date) { 
		Calendar cal = Calendar.getInstance(); 
		cal.clear(); 
		cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8))); 
		return cal; 
	} 

}
