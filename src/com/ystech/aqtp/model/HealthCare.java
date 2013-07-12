package com.ystech.aqtp.model;

// Generated 2013-6-22 16:47:10 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Healthcare generated by hbm2java
 */
public class HealthCare implements java.io.Serializable {

	private Integer dbid;
	private ChickenBatch chickenbatch;
	private String name;
	private Date beginDate;
	private Date endDate;
	private Set healthcaredrags = new HashSet(0);

	public HealthCare() {
	}

	public HealthCare(ChickenBatch chickenbatch, String name, Date beginDate,
			Date endDate, Set healthcaredrags) {
		this.chickenbatch = chickenbatch;
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.healthcaredrags = healthcaredrags;
	}

	public Integer getDbid() {
		return this.dbid;
	}

	public void setDbid(Integer dbid) {
		this.dbid = dbid;
	}

	public ChickenBatch getChickenbatch() {
		return this.chickenbatch;
	}

	public void setChickenbatch(ChickenBatch chickenbatch) {
		this.chickenbatch = chickenbatch;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set getHealthcaredrags() {
		return this.healthcaredrags;
	}

	public void setHealthcaredrags(Set healthcaredrags) {
		this.healthcaredrags = healthcaredrags;
	}

}