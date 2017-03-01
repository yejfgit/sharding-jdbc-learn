package com.netease.okr.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.dao.DateInfoDao;
import com.netease.okr.model.entity.DateInfo;
import com.netease.okr.service.DateInfoService;
import com.netease.okr.util.MyDateUtils;

@Service
public class DateInfoServiceImpl implements DateInfoService {

	@Autowired
	private DateInfoDao dateInfoDao;
	
	@Override
	public Integer addDateInfo(DateInfo dateInfo){
		return dateInfoDao.addDateInfo(dateInfo);
	}
	
	
	@Override
	public List<DateInfo> getAllDateInfo(){
		return dateInfoDao.getAllDateInfo();
	}
	
	
	@Override
	public void createDateInfo(String startDateStr,String endDateStr){
		
		
		Date startDate = MyDateUtils.stringSimpleToDate(startDateStr);
		
		Date endDate = MyDateUtils.stringSimpleToDate(endDateStr);
		
		Date date = null;
		int week = 1;
		while(startDate.before(endDate)){
			date = MyDateUtils.dateAddDays(startDate,6);
			
			
			DateInfo dateInfo = new DateInfo();
			dateInfo.setStartDate(startDate);
			dateInfo.setEndDate(date);
			
			String dateString = MyDateUtils.getDateString(startDate);
			String year = dateString.substring(0, 4);
			String month = dateString.substring(5, 7).replace("0", "");
			
			dateInfo.setYear(year);
			dateInfo.setMonth(month);
			dateInfo.setWeek("W"+week);
			dateInfoDao.addDateInfo(dateInfo);
			
			startDate = MyDateUtils.dateAddDays(date,1);
			week++;
		}
		
	}
}
