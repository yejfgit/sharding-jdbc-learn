package com.netease.okr.model.entity.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.netease.okr.model.entity.OkrNum;
import com.netease.okr.util.ReflectionUtils;


public class User implements Serializable {
	private static final long serialVersionUID = -4168412066004667856L;

	private Integer id;
	private String name;
	private String uNo;
	/**
	 * 主管工号
	 */
	private String chargeId;
	private String idCardNo;
	private String nameCn;
	private String phone;
	private String corpMail;
	private Integer isValid;
	private String deptL1Id;
	private String deptL2Id;
	private String deptL3Id;
	private String stationName;
	private String photoUrl;
	private String nosKey;
	private Date modifyTime;
	private Date createTime;

	private String empType;
	private Integer state;
	
	private Integer weekReportId;//最新周报ID
	
	private String deptL1Name;
	private String deptL2Name;
	private String deptL3Name;
	
	private OkrNum okrNum;

	private List<Role> userRoles = new ArrayList<Role>();
	
	@SuppressWarnings("unused")
	private List<Integer> roleIds = new ArrayList<Integer>();
	
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getRoleIds() throws Exception{
		return ReflectionUtils.fetchElementPropertyToList(userRoles, "id");
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getuNo() {
		return uNo;
	}

	public void setuNo(String uNo) {
		this.uNo = uNo;
	}

	public String getCorpMail() {
		return corpMail;
	}

	public void setCorpMail(String corpMail) {
		this.corpMail = corpMail;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}


	public String getDeptL1Id() {
		return deptL1Id;
	}

	public void setDeptL1Id(String deptL1Id) {
		this.deptL1Id = deptL1Id;
	}

	public String getDeptL2Id() {
		return deptL2Id;
	}

	public void setDeptL2Id(String deptL2Id) {
		this.deptL2Id = deptL2Id;
	}

	public String getDeptL3Id() {
		return deptL3Id;
	}

	public void setDeptL3Id(String deptL3Id) {
		this.deptL3Id = deptL3Id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getNosKey() {
		return nosKey;
	}

	public void setNosKey(String nosKey) {
		this.nosKey = nosKey;
	}

	public String getDeptL1Name() {
		return deptL1Name;
	}

	public void setDeptL1Name(String deptL1Name) {
		this.deptL1Name = deptL1Name;
	}

	public String getDeptL2Name() {
		return deptL2Name;
	}

	public void setDeptL2Name(String deptL2Name) {
		this.deptL2Name = deptL2Name;
	}

	public String getDeptL3Name() {
		return deptL3Name;
	}

	public void setDeptL3Name(String deptL3Name) {
		this.deptL3Name = deptL3Name;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public OkrNum getOkrNum() {
		return okrNum;
	}

	public void setOkrNum(OkrNum okrNum) {
		this.okrNum = okrNum;
	}

	public Integer getWeekReportId() {
		return weekReportId;
	}

	public void setWeekReportId(Integer weekReportId) {
		this.weekReportId = weekReportId;
	}


}
