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
import com.netease.okr.service.UserService;

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
	public List<User> getUsers(UserQuery user){
		
		return userDao.getUsers(user);
		
	}
	
	@Override
	public PageJsonResponse<User> getUsersPage(UserQuery user,PageBean<User> pageBean){
		
		 PageJsonResponse<User> userPage = userDao.getUsersPage(user, pageBean);
		 setUserOkrNum(userPage);
		 
		 return userPage;
		
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
				WeekReport newWR = weekReportDao.getNewWeekReport(userId);
				
				if(newWR!=null){
					okrNum.setWeek(newWR.getWeek());
					okrNum.setStartDate(newWR.getStartDate());
					okrNum.setEndDate(newWR.getEndDate());
				}
				
				
			}
		}
		
	}

}
