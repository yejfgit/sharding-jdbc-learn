package com.netease.okr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.dao.DeptDao;
import com.netease.okr.model.entity.Dept;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.DeptService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.UserContextUtil;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;
	
	@Override
	public Dept getDeptById(String id){
		return deptDao.getDeptById(id);
	}
	
	@Override
	public Integer addDept(Dept dept){
		return deptDao.addDept(dept);
	}
	
	@Override
	public Integer deleteDept(){
		return deptDao.deleteDept();
	}
	
	
	@Override
	public List<Dept> getAllDept(){
		
		User user = (User) UserContextUtil.getUserContext().getUser();
		//全部部门
		/*List<Dept> deptL1List = deptDao.getDeptListByLevel(ConstantsUtil.DEPT_LEVEL_L1);
		if(deptL1List!=null&&deptL1List.size()>0){
			for(Dept deptl1:deptL1List){
				List<Dept> deptL2List = deptDao.getDeptListByParentId(deptl1.getId());
				
				if(deptL2List!=null&&deptL2List.size()>0){
					for(Dept deptl2 : deptL2List){
						List<Dept> deptL3List = deptDao.getDeptListByParentId(deptl2.getId());
						deptl2.setChildren(deptL3List);
					}
					
					deptl1.setChildren(deptL2List);
				}
				
			}
		}*/
		
		//hr部门
		List<Dept> deptL1List = new ArrayList<Dept>();
		
		Dept deptl1 = deptDao.getDeptById(user.getDeptL2Id());
				
		List<Dept> deptL2List = deptDao.getDeptListByParentId(deptl1.getId());
		
		if(deptL2List!=null&&deptL2List.size()>0){
			for(Dept deptl2 : deptL2List){
				List<Dept> deptL3List = deptDao.getDeptListByParentId(deptl2.getId());
				deptl2.setChildren(deptL3List);
			}
			
			deptl1.setChildren(deptL2List);
		}
		
		deptL1List.add(deptl1);
		
		return deptL1List;
	}
	
	
}
