package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.Dept;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface DeptMapper {
	
	/**
	 * 部门信息
	 * @return
	 * @throws DataAccessException
	 */
	public Dept getDeptById(@Param(value = "id") String id)  throws DataAccessException;

	/**
	 * 部门信息
	 * @param level
	 * @returnList<Dept>
	 * @throws DataAccessException
	 */
	public List<Dept> getDeptListByLevel(@Param(value = "level") Integer level)  throws DataAccessException;
	
	/**
	 * 部门信息
	 * @param parentId
	 * @returnList<Dept>
	 * @throws DataAccessException
	 */
	public List<Dept> getDeptListByParentId(@Param(value = "parentId") String parentId)  throws DataAccessException;
	
	
	
	/**
	 * 删除所有部门
	 * @return
	 * @throws DataAccessException
	 */
	public Integer deleteDept() throws DataAccessException;
	
	
	/**
	 * 添加部门信息
	 * @return
	 * @throws DataAccessException
	 */
	public Integer addDept(Dept dept) throws DataAccessException;
	
	
}
