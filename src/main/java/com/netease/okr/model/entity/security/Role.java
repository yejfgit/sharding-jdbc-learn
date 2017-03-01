package com.netease.okr.model.entity.security;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = -2189780884361999626L;

	private Integer id;
	private String name;
	private String nameCn;

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

}
