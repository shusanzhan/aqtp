package com.ystech.aqtp.model;

// Generated 2013-6-22 16:47:10 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Drag generated by hbm2java
 */
public class Drag implements java.io.Serializable {

	private Integer dbid;
	private DragType dragtype;
	private String name;
	private String generateBatch;
	private String effect;
	private String specification;
	private String directions;
	private String note;
	private Integer recordId;
	private Set immunedrags = new HashSet(0);
	private Set healthcaredrags = new HashSet(0);

	public Drag() {
	}

	public Drag(DragType dragtype, String name, String generateBatch,
			String effect, String specification, String directions,
			String note, Integer recordId, Set immunedrags, Set healthcaredrags) {
		this.dragtype = dragtype;
		this.name = name;
		this.generateBatch = generateBatch;
		this.effect = effect;
		this.specification = specification;
		this.directions = directions;
		this.note = note;
		this.recordId = recordId;
		this.immunedrags = immunedrags;
		this.healthcaredrags = healthcaredrags;
	}

	public Integer getDbid() {
		return this.dbid;
	}

	public void setDbid(Integer dbid) {
		this.dbid = dbid;
	}

	public DragType getDragtype() {
		return this.dragtype;
	}

	public void setDragtype(DragType dragtype) {
		this.dragtype = dragtype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenerateBatch() {
		return this.generateBatch;
	}

	public void setGenerateBatch(String generateBatch) {
		this.generateBatch = generateBatch;
	}

	public String getEffect() {
		return this.effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDirections() {
		return this.directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Set getImmunedrags() {
		return this.immunedrags;
	}

	public void setImmunedrags(Set immunedrags) {
		this.immunedrags = immunedrags;
	}

	public Set getHealthcaredrags() {
		return this.healthcaredrags;
	}

	public void setHealthcaredrags(Set healthcaredrags) {
		this.healthcaredrags = healthcaredrags;
	}

}