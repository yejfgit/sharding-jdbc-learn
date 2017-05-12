/**
 * hzyejinfu
 * */
package com.netease.okr.enums;

public enum KeyResultStatusEnum {
	
    STATUS1(0, "未开始"),
    STATUS2(1, "已开始"),
    STATUS3(2, "已结束"),
	STATUS4(3, "已终止");


    private int id;

    private String name;

    private KeyResultStatusEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static KeyResultStatusEnum codeOf(int id) {
        for (KeyResultStatusEnum dos : KeyResultStatusEnum.values()) {
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
