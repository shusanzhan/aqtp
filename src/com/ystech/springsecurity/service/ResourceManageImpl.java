package com.ystech.springsecurity.service;

import org.springframework.stereotype.Component;

import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.springsecurity.model.Resource;
@Component("resourceManageImpl")
public class ResourceManageImpl extends HibernateEntityDao<Resource>{

}
