package com.netease.okr.Lucence.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.Lucence.SolrClientUtil;
import com.netease.okr.Lucence.UserIndexServer;
import com.netease.okr.Lucence.entity.UserFields;
import com.netease.okr.common.PageBean;
import com.netease.okr.common.PageJsonResponse;
import com.netease.okr.model.entity.Dept;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.UserQuery;
import com.netease.okr.service.DeptService;
import com.netease.okr.service.UserService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.LoggerUtil;

@Service
public class UserIndexServerImpl implements UserIndexServer {
	
	private static String USER_CORE = "user";
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 创建用户索引
	 * @author yejf
	 * @throws Exception 
	 * */
	@Override
	public void buildUserIndex() throws Exception {
		LoggerUtil.info("buildUserIndex start");
		// 获取一级部门
		List<Dept> deptList = deptService.getDeptListByLevel(ConstantsUtil.DEPT_LEVEL_L1);

		if (deptList != null && deptList.size() > 0) {
			UserQuery userQuery = new UserQuery();
			for (Dept dept : deptList) {
				if(!ConstantsUtil.HR_DEPT_CODE.equals(dept.getId())) continue;
				userQuery.setDeptId(dept.getId());
				List<User> users = userService.queryUsers(userQuery);
				if(users!=null&&users.size()>0){
					SolrClientUtil.deleteIndex(USER_CORE);
					for(User user:users){
						try {
							SolrClientUtil.buildIndex(createDocument(user), USER_CORE);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							LoggerUtil.error(user.getId()+"创建索引异常", e);
						}
					}
				}
				
			}
		} else {
			LoggerUtil.info("同步用户获取一级部门数据为空");
		}
		LoggerUtil.info("buildUserIndex end");
	}

	
	/**
	 * 检索用户
	 * @author yejf
	 * @throws Exception 
	 * */
	@Override
	public PageJsonResponse<User> searchUser(UserQuery userQuery, PageBean<User> page) {
		
		PageJsonResponse<User> pageRes = new PageJsonResponse<User>();
		pageRes.setPage(page);
		try {

			SolrQuery query = new SolrQuery("*:*"); // 定义查询内容
			int currentPage = page.getCurrentPageOfIndex();
			query.setStart(currentPage<1?0:(currentPage-1)*page.getPageSize());// 起始页
			query.setRows(page.getPageSize());// 每页显示数量
			query.setSort(UserFields.USER_ID, ORDER.asc);
			SolrDocumentList docs =SolrClientUtil.search(query,USER_CORE);  
			
			int totalCount = (int) docs.getNumFound();
			page.setTotalResults(totalCount);
			
			List<User> pageList = new ArrayList<User>();
			for (SolrDocument doc : docs) {
				User user = createUserResult(doc);
	            pageList.add(user);
	        }
			
			pageRes.setObjList(pageList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LoggerUtil.error("检索索引异常", e);
		} 
		
		return pageRes;

	}
	
	
	private SolrInputDocument createDocument(User user){
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField(UserFields.USER_ID, user.getId());
		doc.setField(UserFields.NAME, user.getName());
		doc.setField(UserFields.U_NO, user.getuNo());
		doc.setField(UserFields.CORP_MAIL, user.getCorpMail());
		doc.setField(UserFields.DEPT_L1_ID, user.getDeptL1Id());
		doc.setField(UserFields.DEPT_L1_NAME, user.getDeptL1Name());
		doc.setField(UserFields.DEPT_L2_ID, user.getDeptL2Id());
		doc.setField(UserFields.DEPT_L2_NAME, user.getDeptL2Name());
		doc.setField(UserFields.DEPT_L3_ID, user.getDeptL3Id());
		doc.setField(UserFields.DEPT_L3_NAME, user.getDeptL3Name());
		doc.setField(UserFields.EMP_TYPE, user.getEmpType());
		doc.setField(UserFields.IS_VALID, user.getIsValid());
		doc.setField(UserFields.STATE, user.getState());
		doc.setField(UserFields.STATION_NAME, user.getStationName());
		return doc;
		
	}
	
	
	private User createUserResult(SolrDocument doc) {
		User user = new User();
		
		user.setId(Integer.parseInt((String)doc.getFieldValue(UserFields.USER_ID)));
		user.setName((String)doc.getFieldValue(UserFields.NAME));
		user.setuNo((String)doc.getFieldValue(UserFields.U_NO));
		user.setCorpMail((String)doc.getFieldValue(UserFields.CORP_MAIL));
		user.setDeptL1Id((String)doc.getFieldValue(UserFields.DEPT_L1_ID));
		user.setDeptL1Name((String)doc.getFieldValue(UserFields.DEPT_L1_NAME));
		user.setDeptL2Id((String)doc.getFieldValue(UserFields.DEPT_L2_ID));
		user.setDeptL2Name((String)doc.getFieldValue(UserFields.DEPT_L2_NAME));
		user.setDeptL3Id((String)doc.getFieldValue(UserFields.DEPT_L3_ID));
		user.setDeptL3Name((String)doc.getFieldValue(UserFields.DEPT_L3_NAME));
		user.setStationName((String)doc.getFieldValue(UserFields.STATION_NAME));
		
		
		return user;
	}
}
