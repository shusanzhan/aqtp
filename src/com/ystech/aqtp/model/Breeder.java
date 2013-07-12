package com.ystech.aqtp.model;

// Generated 2013-6-22 16:47:10 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Breeder generated by hbm2java
 */
public class Breeder implements java.io.Serializable {

	private Integer dbid;
	private User user;
	private String name;
	private String sex;
	private Date birthday;
	private String photo;
	private String educationalBackground;
	private String graduationSchool;
	private Set breaderbreeds = new HashSet(0);

	public Breeder() {
	}

	public Breeder(User user, String name, String sex, Date birthday,
			String photo, String educationalBackground,
			String graduationSchool, Set breaderbreeds) {
		this.user = user;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.photo = photo;
		this.educationalBackground = educationalBackground;
		this.graduationSchool = graduationSchool;
		this.breaderbreeds = breaderbreeds;
	}

	public Integer getDbid() {
		return this.dbid;
	}

	public void setDbid(Integer dbid) {
		this.dbid = dbid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEducationalBackground() {
		return this.educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public String getGraduationSchool() {
		return this.graduationSchool;
	}

	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}

	public Set getBreaderbreeds() {
		return this.breaderbreeds;
	}

	public void setBreaderbreeds(Set breaderbreeds) {
		this.breaderbreeds = breaderbreeds;
	}


}
