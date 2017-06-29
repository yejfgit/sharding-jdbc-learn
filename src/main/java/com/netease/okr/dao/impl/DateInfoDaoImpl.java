package com.netease.okr.dao.impl;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.netease.okr.dao.DateInfoDao;
import com.netease.okr.mapper.okr.DateInfoMapper;
import com.netease.okr.model.entity.DateInfo;
import com.netease.okr.redis.RedisClient;
import com.netease.okr.redis.RedisConstant;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.MyDateUtils;
import com.netease.okr.util.MyStringUtil;

@Repository
public class DateInfoDaoImpl extends SqlSessionDaoSupport implements DateInfoDao {
	
	@Autowired
	private DateInfoMapper dateInfoMapper;
	
	@Override
	public Integer addDateInfo(DateInfo dateInfo){
		return dateInfoMapper.addDateInfo(dateInfo);
	}
	
	@Override
	public List<DateInfo> getClassDateByModel(Integer type){
		return dateInfoMapper.getClassDateByModel(type);
	}
	
	
	@Override
	public List<DateInfo> getAllDateInfo(){
		
		//先从缓存里读取
		String allDateInfo = RedisClient.get(RedisConstant.DATE_INFO_QUERY_KEY);
		
		if(MyStringUtil.isNotBlank(allDateInfo)){
			return JSON.parseArray(allDateInfo, DateInfo.class);
		}else{
			List<DateInfo> di = dateInfoMapper.getAllDateInfo();
			setCurDateInfo(di);
			RedisClient.set(RedisConstant.DATE_INFO_QUERY_KEY, JSON.toJSONString(di));
			RedisClient.expire(RedisConstant.DATE_INFO_QUERY_KEY, RedisConstant.DATE_INFO_QUERY_KEY_EXPIRE);
			return di;
		}
		
	}
	
	private void setCurDateInfo(List<DateInfo> dateInfos){
		DateInfo curDateInfo = getCurDateInfo();
		for(DateInfo dateInfo:dateInfos){
			if(curDateInfo!=null&&curDateInfo.getId().equals(dateInfo.getId())){
				dateInfo.setIsShowCurDate(ConstantsUtil.IS_SHOW_CUR_DATE);
			}
		}
		
	}
	
	@Override
	public DateInfo getCurDateInfo(){
		Date curDate = MyDateUtils.parseYmdDate(MyDateUtils.formatYmdDate(new Date(),null));
		DateInfo dateInfo = new DateInfo();
		List<DateInfo> di = dateInfoMapper.getCurDateInfo(curDate);
		if(di!=null&&di.size()>0){
			dateInfo = findCurDateInfo(di.get(0));
		}
		return dateInfo;
		
	}
	
	/**
	 * 周三之前的显示上周，之后【包含周三】显示本周
	 * */
	private DateInfo findCurDateInfo(DateInfo dateInfo){
		Date curDate = new Date();
		int days = MyDateUtils.getDaysIgnoreTime(dateInfo.getStartDate(),curDate);
		
		//周三的日期减去周日的日期 如果>2【周三及之后】;<2【周日到周二】
		if(days>2){
			return dateInfo;
		}else{
			List<DateInfo> di = dateInfoMapper.getCurDateInfo(MyDateUtils.dateAddDays(curDate, -7));
			
			if(di!=null&&di.size()>0){
				dateInfo = di.get(0);
			}
			return dateInfo;
		}
		
	}
	
	
	public static void  main(String[] arg){
		int days = MyDateUtils.getDaysIgnoreTime(MyDateUtils.parseYmdDate("2017-03-26"),MyDateUtils.parseYmdDate("2017-03-28"));
		
		System.out.println(days); 
		System.out.println(MyDateUtils.formatYmdDate(MyDateUtils.dateAddDays(new Date(), -7), null)); 
	}
}
