package com.ystech.aqtp.model;

// Generated 2013-6-22 16:47:10 by Hibernate Tools 4.0.0

/**
 * Healthcaredrag generated by hbm2java
 */
public class HealthCareDrag implements java.io.Serializable {

	private Integer dbid;
	private Drag drag;
	private HealthCare healthcare;
	private String name;
	private String dose;

	public HealthCareDrag() {
	}

	public HealthCareDrag(Drag drag, HealthCare healthcare, String name,
			String dose) {
		this.drag = drag;
		this.healthcare = healthcare;
		this.name = name;
		this.dose = dose;
	}

	public Integer getDbid() {
		return this.dbid;
	}

	public void setDbid(Integer dbid) {
		this.dbid = dbid;
	}

	public Drag getDrag() {
		return this.drag;
	}

	public void setDrag(Drag drag) {
		this.drag = drag;
	}

	public HealthCare getHealthcare() {
		return this.healthcare;
	}

	public void setHealthcare(HealthCare healthcare) {
		this.healthcare = healthcare;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDose() {
		return this.dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

}
