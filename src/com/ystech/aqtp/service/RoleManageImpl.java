package com.ystech.aqtp.service;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Role;
import com.ystech.core.dao.HibernateEntityDao;
@Component("roleManageImpl")
public class RoleManageImpl extends HibernateEntityDao<Role>{

}
