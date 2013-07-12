package com.ystech.aqtp.model;

// Generated 2013-6-22 16:47:10 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Feeder generated by hbm2java
 */
public class Feeder implements java.io.Serializable {

	private Integer dbid;
	private String name;
	private String elementsPercentage;
	private String image;
	private String note;
	private Set feedfeeders = new HashSet(0);

	public Feeder() {
	}

	public Feeder(String name, String elementsPercentage, String image,
			String note, Set feedfeeders) {
		this.name = name;
		this.elementsPercentage = elementsPercentage;
		this.image = image;
		this.note = note;
		this.feedfeeders = feedfeeders;
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

	public String getElementsPercentage() {
		return this.elementsPercentage;
	}

	public void setElementsPercentage(String elementsPercentage) {
		this.elementsPercentage = elementsPercentage;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getFeedfeeders() {
		return this.feedfeeders;
	}

	public void setFeedfeeders(Set feedfeeders) {
		this.feedfeeders = feedfeeders;
	}

}
