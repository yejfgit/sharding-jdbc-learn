/**
 * hzyejinfu
 * */
package com.netease.okr.enums;

public enum AppendixTypeEnum {
	
	TYPE1(1, "我的总结目标附件"),
	TYPE2(2, "我的总结其他工作项附件");

    private int id;

    private String name;

    private AppendixTypeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static AppendixTypeEnum codeOf(int id) {
        for (AppendixTypeEnum dos : AppendixTypeEnum.values()) {
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
