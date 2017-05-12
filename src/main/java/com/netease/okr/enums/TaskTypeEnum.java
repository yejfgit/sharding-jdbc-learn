package com.netease.okr.enums;

/**
 * hzyejinfu
 * */
public enum TaskTypeEnum {
	
	DEPT_SYNC("dept_sync", "同步部门信息"),
    USER_SYNC("user_sync", "同步用户信息"),
	USER_NEW_WEEK_REPORT("user_new_week_report", "更新用户新周报"),
	USER_INDEX("user_index", "同步索引信息");

    private String name;

    private String remark;

    private TaskTypeEnum(String name, String remark){
        this.name = name;
        this.remark = remark;
    }

    public static TaskTypeEnum codeOf(String name) {
        for (TaskTypeEnum dos : TaskTypeEnum.values()) {
            if (dos.getName().equalsIgnoreCase(name)) {
                return dos;
            }
        }
        return null;
    }
  
    public static String getRemarkOfName(String name) {
    	TaskTypeEnum doc = codeOf(name);
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
