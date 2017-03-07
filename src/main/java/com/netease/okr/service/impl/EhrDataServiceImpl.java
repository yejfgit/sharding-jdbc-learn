package com.netease.okr.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.ehrSDK.Department;
import com.netease.ehrSDK.Employee;
import com.netease.ehrSDK.core.EhrDepartment;
import com.netease.ehrSDK.core.EhrEmployee;
import com.netease.ehrSDK.param.DeptParam;
import com.netease.ehrSDK.param.EmpParam;
import com.netease.okr.dao.DeptDao;
import com.netease.okr.dao.UserDao;
import com.netease.okr.model.entity.Dept;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.EhrDateService;
import com.netease.okr.service.FileService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.FileTool;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.MyStringUtil;

@Service
public class EhrDataServiceImpl implements EhrDateService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private FileService fileService;

	/**
	 * @author yejf
	 * @param 
	 * */
	@Override
	public void syncDept(){
		EhrDepartment ehrDepartment = new EhrDepartment();
		DeptParam param = new DeptParam();
		param.setState(ConstantsUtil.STATUS_YES);
		List<Department> deptList = ehrDepartment.getDept(param);
		
		//保存用户
		LoggerUtil.info("SyncUserTask saveUser begin");
		String result = saveDept(deptList);
		LoggerUtil.info("SyncUserTask saveUser end reuslt = "+result);
	}
	
	/**
	 * @author yejf
	 * @param 
	 * */
	@Override
	public void syncUser(){
		EhrEmployee ehrEmployee = new EhrEmployee();
		//根据查询条件获取员工列表
		EmpParam param = new EmpParam();
		param.setDept1Id(ConstantsUtil.HR_DEPT_CODE);//一级部门为:D002

		List<Employee> empList =  ehrEmployee.getEmployee(param);//基本信息
		
		//保存用户
		LoggerUtil.info("SyncUserTask saveUser begin");
		String result = saveUser(empList);
		LoggerUtil.info("SyncUserTask saveUser end reuslt = "+result);
	}
	
	
	private String saveUser(List<Employee> empList){
		String result = "";
		if(empList!=null&&empList.size()>0){
			for(Employee emp:empList){
				
				User user = userDao.getUserByUNo(emp.getId());
				if(user!=null){
					//组装用户数据
					setUserInfo(emp,user);
					
					if(MyStringUtil.isBlank(user.getPhotoUrl())){
						//创建用户工牌照
						createUserWorkCardImage(emp,user);
					}
					
					int c = userDao.updateUser(user);
					
					LoggerUtil.info(emp.getId()+" updateUser result="+c);
				}else{
					user = new User();
					//组装用户数据
					setUserInfo(emp,user);
					
					//创建用户工牌照
					createUserWorkCardImage(emp,user);
					
					int c = userDao.addUser(user);
					
					LoggerUtil.info(emp.getId()+" addUser result="+c);
				}
				
			}
		}
		
		return  result;
	}
	
	private void setUserInfo(Employee emp,User user){
		user.setName(emp.getName());
		user.setEmpType(emp.getType());
		user.setState(emp.getState());
		user.setuNo(emp.getId());
		user.setCorpMail(emp.getCorpMail());
		user.setDeptL1Id(emp.getDept1()==null?"":emp.getDept1().getCode());
		user.setDeptL2Id(emp.getDept2()==null?"":emp.getDept2().getCode());
		user.setDeptL3Id(emp.getDept3()==null?"":emp.getDept3().getCode());
		user.setStationName(emp.getStation()==null?"":emp.getStation().getName());
	}
	
	private void createUserWorkCardImage(Employee emp,User user){
		byte[] workCardImage = emp.getWorkCardImage();
		if(workCardImage!=null){
			String nosKey = fileService.getNosKey();
			String fileName = emp.getName()+"-"+emp.getId()+emp.getImageExt();
			String filePath = ConstantsUtil.FILE_TMP_DIR_PATH + File.separator+emp.getName()+"-"+emp.getId()+emp.getImageExt();
			
			//创建文件
			try {
				FileTool.createFile(filePath, workCardImage);
				
				String photoUrl = fileService.uploadFile(new File(filePath),fileName,nosKey);
				user.setPhotoUrl(photoUrl);
				user.setNosKey(nosKey);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} 
	}
	
	
	private String saveDept(List<Department> deptList){
		String result = "";
		if(deptList!=null&&deptList.size()>0){
			//清除部门数据
			deptDao.deleteDept();
			
			for(Department department:deptList){
				Dept dept = new Dept();
				//组装部门数据
				setDeptInfo(department,dept);
				int c = deptDao.addDept(dept);
				
				LoggerUtil.info(department.getId()+" saveDept result="+c);
			}
		}
		
		return  result;
	}
	
	private void setDeptInfo(Department department,Dept dept){
		dept.setName(department.getName());
		dept.setId(department.getId());
		dept.setDeptHrUNo(department.getDeptHrId());
		dept.setDeptLeaderUNo(department.getDeptLeaderId());
		dept.setStatus(department.getState());
		dept.setLevel(department.getDeptLevel());
		dept.setParentId(department.getParentId());
	}

}
