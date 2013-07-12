package com.ystech.aqtp.service;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Resource;
import com.ystech.core.dao.HibernateEntityDao;
@Component("resourceManageImpl")
public class ResourceManageImpl extends HibernateEntityDao<Resource>{

}
