/**
 * hzyejinfu
 * */
package com.netease.okr.enums;

public enum StatusTypeEnum {
	
	STATUS0(0, "暂存"),
	STATUS1(1, "发布");


    private int id;

    private String name;

    private StatusTypeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static StatusTypeEnum codeOf(int id) {
        for (StatusTypeEnum dos : StatusTypeEnum.values()) {
            if (dos.getId()==id) {
                return dos;
            }
        }
        return null;
    }
    
    public static String getRemarkOfId(int id) {
    	StatusTypeEnum doc = codeOf(id);
    	if(doc==null){
    		return "";
    	}else{
    		return doc.getName();
    	}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
