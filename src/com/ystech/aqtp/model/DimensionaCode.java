package com.ystech.aqtp.model;

// Generated 2013-6-22 16:47:10 by Hibernate Tools 4.0.0

/**
 * Dimensionacode generated by hbm2java
 */
public class DimensionaCode implements java.io.Serializable {

	private Integer dbid;
	private Dimensiona dimensiona;
	private Integer code;
	private String photo;

	public DimensionaCode() {
	}

	public DimensionaCode(Dimensiona dimensiona, Integer code, String photo) {
		this.dimensiona = dimensiona;
		this.code = code;
		this.photo = photo;
	}

	public Integer getDbid() {
		return this.dbid;
	}

	public void setDbid(Integer dbid) {
		this.dbid = dbid;
	}

	public Dimensiona getDimensiona() {
		return this.dimensiona;
	}

	public void setDimensiona(Dimensiona dimensiona) {
		this.dimensiona = dimensiona;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
