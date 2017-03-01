package com.netease.okr.enums;

/**
 * hzyejinfu
 * */
public enum PhotoTypeEnum {
	
	ID_CARD("idCard", "身份证"),
    WORK_CARD("workCard", "工牌"),
    GRADUATION_CARD("graduationCard", "毕业证"),
    DEGREE_CARD("degreeCard", "学位证"),
    STUDENT_CARD("studentCard", "学生证"),
    FAMILY_REGISTER("familyRegister", "户口本"),
    LEAVE_WORK_PROVE("leaveWorkProve", "离职证明");

    private String name;

    private String remark;

    private PhotoTypeEnum(String name, String remark){
        this.name = name;
        this.remark = remark;
    }

    public static PhotoTypeEnum codeOf(String name) {
        for (PhotoTypeEnum dos : PhotoTypeEnum.values()) {
            if (dos.getName().equalsIgnoreCase(name)) {
                return dos;
            }
        }
        return null;
    }
  
    public static String getRemarkOfName(String name) {
    	PhotoTypeEnum doc = codeOf(name);
    	if(doc==null){
    		return "";
    	}else{
    		return doc.getRemark();
    	}
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
