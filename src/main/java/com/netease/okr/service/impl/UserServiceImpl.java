package com.netease.okr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.common.PageBean;
import com.netease.okr.common.PageJsonResponse;
import com.netease.okr.dao.ObjectivesDao;
import com.netease.okr.dao.UserDao;
import com.netease.okr.dao.WeekReportDao;
import com.netease.okr.model.entity.OkrNum;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.UserQuery;
import com.netease.okr.redis.RedisClient;
import com.netease.okr.redis.RedisConstant;
import com.netease.okr.service.UserService;
import com.netease.okr.util.ConstantsUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ObjectivesDao objectivesDao;
	
	
	@Autowired
	private WeekReportDao weekReportDao;
	
	@Override
	public User getUserByUNo(String uNo){
		return userDao.getUserByUNo(uNo);
	}
	
	@Override
	public User getUserByUsername(String userName, boolean isLock) {
		return userDao.getUserByUsername(userName, isLock);
	}
	
	@Override
	public User getUserByEmail(String corpMail) {
		return userDao.getUserByEmail(corpMail);
	}
	
	@Override
	public User getUserById(Integer id){
		return userDao.getUserById(id);
	}
	
	
	@Override
	public Integer addUser(User user){
		return userDao.addUser(user);
	}
	
	@Override
	public List<User> getUserListOfDept(String deptId){
		return userDao.getUserListOfDept(deptId);
	}
	
	@Override
	public Integer updateUser(User user){
		return userDao.updateUser(user);
	}
	
	
	@Override
	public List<User> queryUsers(UserQuery user){
		
		return userDao.queryUsers(user);
		
	}
	
	@Override
	public PageJsonResponse<User> getUsersOkrPage(UserQuery user,PageBean<User> pageBean){
		
		 PageJsonResponse<User> userPage = userDao.getUsersOkrPage(user, pageBean);
		 setUserOkrNum(userPage);
		 
		 return userPage;
		
	}
	
	
	@Override
	public void updateUserNewWeekReport(){
		UserQuery userQuery = new UserQuery();
		userQuery.setDeptId(ConstantsUtil.HR_DEPT_CODE);
		List<User> users = userDao.queryUsers(userQuery);
		
		for(User user:users){
			WeekReport weekReport = weekReportDao.getNewWeekReport(user.getId());
			if(weekReport!=null){
				user.setWeekReportId(weekReport.getId());
				userDao.updateUser(user);
			}
			
		}
		
	}
	
	
	@Override
	public void updateUserNewWeekReport(Integer userId){
		User user = new User();
		user.setId(userId);
		WeekReport weekReport = weekReportDao.getNewWeekReport(userId);
		if(weekReport!=null){
			user.setWeekReportId(weekReport.getId());
			userDao.updateUser(user);
			
			Long dt = System.currentTimeMillis()-weekReport.getCreateTime().getTime();
			if(dt>RedisConstant.HAS_NEW_WEEK_REPORT_EXPIRE){
				//失效
				RedisClient.expire(RedisConstant.HAS_NEW_WEEK_REPORT_KEY + userId,0);
			}
		}
		
	}
	
	private void setUserOkrNum(PageJsonResponse<User> userPage){
		if(userPage!=null&&userPage.getObjList()!=null&&userPage.getObjList().size()>0){
			List<User> users = userPage.getObjList();
			for(User user:users){
				OkrNum okrNum = user.getOkrNum();
				if(okrNum==null){
					okrNum = new OkrNum();
					user.setOkrNum(okrNum);
				} 
				
				Integer userId = user.getId();
				
				okrNum.setObjectivesNum(objectivesDao.getObjectivesCount(userId));
				okrNum.setWeekReportNum(weekReportDao.getWeekReportCount(userId));
				okrNum.setHasNewWeekReport(weekReportDao.hasNewWeekReport(userId));
				
			}
		}
		
	}

}
