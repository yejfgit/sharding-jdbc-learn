package com.netease.okr.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.WeekReportDao;
import com.netease.okr.mapper.okr.UserMapper;
import com.netease.okr.mapper.okr.WeekReportMapper;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.WeekReportRel;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.WeekReportQuery;
import com.netease.okr.redis.RedisClient;
import com.netease.okr.redis.RedisConstant;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.RedisUserContextUtil;

@Repository
public class WeekReportDaoImpl extends SqlSessionDaoSupport implements WeekReportDao {
	
	
	
	@Autowired
	private WeekReportMapper weekReportMapper;
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * @author yejf
	 * @param keyResultId
	 * @return List<WeekReport>
	 * @throws DataAccessException
	 */
	@Override
	public List<WeekReport> getWeekReportListByKeyResultId(Integer keyResultId) {
		return weekReportMapper.getWeekReportListByKeyResultId(keyResultId);
	}

	@Override
	public Integer addWeekReport(WeekReport weekReport) {
		Integer c = weekReportMapper.addWeekReport(weekReport);
		
		updateWeekReportCountOfRedis();
		
		//更新是否有最新周报【暂存的不更新】
		if(c>0&&!ConstantsUtil.OPREATE_TYPE_SAVE_STATUS.equals(weekReport.getStatus())){
			updateHasNewWeekReportOfRedis(weekReport);
		}

		return c;
	}

	@Override
	public Integer getWeekReportCount(Integer userId) {

		// 先从缓存里读取
		String weekReportCount = RedisClient.get(RedisConstant.WEEK_REPORT_COUNT_KEY + userId);
		if (MyStringUtil.isNotBlank(weekReportCount)) {
			return Integer.parseInt(weekReportCount);
		} else {
			Integer wrc = weekReportMapper.getWeekReportCount(userId);
			RedisClient.set(RedisConstant.WEEK_REPORT_COUNT_KEY + userId, wrc.toString());
			RedisClient.expire(RedisConstant.WEEK_REPORT_COUNT_KEY + userId, RedisConstant.WEEK_REPORT_COUNT_EXPIRE);
			return wrc;
		}
	}

	/**
	 * 
	 * 是否有最新周报 1:有 ;0:无
	 */
	@Override
	public Integer hasNewWeekReport(Integer userId) {
		String hasNewWeekReport = RedisClient.get(RedisConstant.HAS_NEW_WEEK_REPORT_KEY + userId);
		if (MyStringUtil.isNotBlank(hasNewWeekReport)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public WeekReport getNewWeekReport(Integer userId) {
		return weekReportMapper.getNewWeekReport(userId);
	}

	@Override
	public Integer updateWeekReport(WeekReport weekReport) {
		Integer c = weekReportMapper.updateWeekReport(weekReport);
		//更新是否有最新周报【暂存的不更新】
		if(c>0&&!ConstantsUtil.OPREATE_TYPE_SAVE_STATUS.equals(weekReport.getStatus())){
			updateHasNewWeekReportOfRedis(weekReport);
		}
		return c;
	}

	/**
	 * 添加周报和关键事件结果关系
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public Integer addWeekReportRel(List<WeekReportRel> list) {
		if (list == null || list.size() < 1) {
			return 0;
		}
		return weekReportMapper.addWeekReportRel(list);
	}

	/**
	 * 删除周报和关键事件结果关系
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public Integer deleteWeekReport(Integer id) {
		Integer c = weekReportMapper.deleteWeekReport(id);
		
		updateWeekReportCountOfRedis();
		
		return c;
	}
	
	
	/**
	 * 删除周报和关键事件结果关系
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public Integer deleteWeekReportRel(Integer weekReportId) {
		return weekReportMapper.deleteWeekReportRel(weekReportId);
	}

	@Override
	public List<WeekReport> getWeekReportList(WeekReportQuery weekReportQuery) {
		return weekReportMapper.getWeekReportList(weekReportQuery);
	}

	@Override
	public List<WeekReportRel> getWeekReportRelList(WeekReportRel weekReportRel) {
		return weekReportMapper.getWeekReportRelList(weekReportRel);
	}

	@Override
	public WeekReport getWeekReportById(Integer id) {
		return weekReportMapper.getWeekReportById(id);
	}
	
	/**
	 * 
	 * 更新redis 周报数量
	 * */
	private Integer updateWeekReportCountOfRedis(){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		Integer userId = user.getId();
		Integer weekReportCount = weekReportMapper.getWeekReportCount(userId);
		RedisClient.set(RedisConstant.WEEK_REPORT_COUNT_KEY + userId, weekReportCount.toString());
		RedisClient.expire(RedisConstant.WEEK_REPORT_COUNT_KEY + userId, RedisConstant.WEEK_REPORT_COUNT_EXPIRE);
		return weekReportCount;
	}
	
	/**
	 * 
	 * 更新redis 最新周报
	 * */
	private void updateHasNewWeekReportOfRedis(WeekReport weekReport){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		//查询之前最新周报，本次修改周早于最新不更新
		User userWeekReport = userMapper.getUserById(user.getId());
		WeekReport beWeekReport  = weekReportMapper.getWeekReportById(userWeekReport.getWeekReportId());
		
		weekReport = weekReportMapper.getWeekReportById(weekReport.getId());
		
		if(weekReport!=null&&weekReport.getDateId()>(beWeekReport==null?0:beWeekReport.getDateId())){
			//更新用户最新周报信息
			User up = new User();
			up.setId(user.getId());
			up.setWeekReportId(weekReport.getId());
			userMapper.updateUser(up);
			
			
			//更新redis是否有最新周报
			Integer userId = user.getId();
			RedisClient.set(RedisConstant.HAS_NEW_WEEK_REPORT_KEY + userId, ConstantsUtil.HAS_NEW_WEEK_REPORT.toString());
			RedisClient.expire(RedisConstant.HAS_NEW_WEEK_REPORT_KEY + userId, RedisConstant.HAS_NEW_WEEK_REPORT_EXPIRE);
		}
		
		
	}

}
