package com.ystech.aqtp.model;

// Generated 2013-6-22 16:47:10 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Dragtype generated by hbm2java
 */
public class DragType implements java.io.Serializable {

	private Integer dbid;
	private String name;
	private String code;
	private Set drags = new HashSet(0);

	public DragType() {
	}

	public DragType(String name, String code, Set drags) {
		this.name = name;
		this.code = code;
		this.drags = drags;
	}

	public Integer getDbid() {
		return this.dbid;
	}

	public void setDbid(Integer dbid) {
		this.dbid = dbid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set getDrags() {
		return this.drags;
	}

	public void setDrags(Set drags) {
		this.drags = drags;
	}

}
