package com.ystech.aqtp.model;

// Generated 2013-11-12 15:43:35 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Newstype generated by hbm2java
 */
public class NewsType implements java.io.Serializable {
	public static Integer INTRO=2;//人安简介
	public static Integer ACHIEVE=3;//中心成果
	public static Integer PRACTICE=4;//创新实践
	public static Integer REASERCH=5;//创新研究
	public static Integer TOPIC=6;//重大专题
	public static Integer DATA=7;//资料库
	public static Integer CON=8;//联系我们
	public static Integer NEWESDT=9;//咨询动态
	
	private Integer dbid;
	private String name;
	private String note;
	private NewsType parent;
	private Set newses = new HashSet(0);

	public NewsType() {
	}

	public NewsType(String name) {
		this.name = name;
	}

	public NewsType(String name, String note, Integer parentId, Set newses) {
		this.name = name;
		this.note = note;
		this.newses = newses;
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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public NewsType getParent() {
		return parent;
	}

	public void setParent(NewsType parent) {
		this.parent = parent;
	}

	public Set getNewses() {
		return this.newses;
	}

	public void setNewses(Set newses) {
		this.newses = newses;
	}

}