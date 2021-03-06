package com.ystech.aqtp.model;

// Generated 2013-6-22 16:47:10 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Dimensiona generated by hbm2java
 */
public class Dimensiona implements java.io.Serializable {

	private Integer dbid;
	private ChickenBatch chickenbatch;
	private String name;
	private Date createDate;
	private Integer quantity;
	private User user;
	private Set dimensionacodes = new HashSet(0);

	public Dimensiona() {
	}

	public Dimensiona(ChickenBatch chickenbatch, String name, Date createDate,
			Integer quantity, Integer userId, Set dimensionacodes) {
		this.chickenbatch = chickenbatch;
		this.name = name;
		this.createDate = createDate;
		this.quantity = quantity;
		this.dimensionacodes = dimensionacodes;
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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Set getDimensionacodes() {
		return this.dimensionacodes;
	}

	public void setDimensionacodes(Set dimensionacodes) {
		this.dimensionacodes = dimensionacodes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
