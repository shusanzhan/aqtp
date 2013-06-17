package com.ystech.springsecurity.service;

import org.springframework.stereotype.Component;

import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.springsecurity.model.Role;
@Component("roleManageImpl")
public class RoleManageImpl extends HibernateEntityDao<Role>{

}
