/**
 * 
 */
package com.ystech.aqtp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.NewsType;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-11-12
 */
@Component("newsTypeManageImpl")
public class NewsTypeManageImpl extends HibernateEntityDao<NewsType>{

	/**
	 * @param integer
	 * @return
	 */
	public List<NewsType> queryByNewsType(Integer parentId) {
		String hql="from NewsType where parent.dbid=?";
		List<NewsType> newsTypes = find(hql, new Object[]{parentId});
		return newsTypes;
	}

}
