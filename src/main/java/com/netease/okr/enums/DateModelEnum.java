/**
 * hzyejinfu
 * */
package com.netease.okr.enums;

public enum DateModelEnum {
	
	TYPE1(1, "月度总结"),
	TYPE2(2, "季度总结"),
	TYPE3(3, "半年度总结"),
	TYPE4(4, "年度总结");


    private int id;

    private String name;

    private DateModelEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static DateModelEnum codeOf(int id) {
        for (DateModelEnum dos : DateModelEnum.values()) {
            if (dos.getId()==id) {
                return dos;
            }
        }
        return null;
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
