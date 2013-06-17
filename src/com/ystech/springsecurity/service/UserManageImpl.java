package com.ystech.springsecurity.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.springsecurity.model.User;
@Component("userManageImpl")
public class UserManageImpl extends HibernateEntityDao<User>{

	/**
	 * @param userName
	 * @param pa
	 */
	public boolean checkUser(String userName, String pa) {
		return true;
	}
	/**
	 * 功能描述：获取人员选择器，所有人员的下来框
	 * @return
	 */
	public String getAllPerson(){
		List<User> users = find("from User ", new Object[]{});
		String userSelect="";
		if (null!=users&&users.size()>0) {
			for (User user : users) {
				userSelect+="<option value='"+user.getDbid()+"us'>"+user.getRealName()+"</option>";
			}
		}
		return userSelect;
	}
}
