package com.ystech.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 针对单个Entity对象的操作定义.不依赖于具体ORM实现方案.
 *
 * @author shusanzhan
 */
public interface EntityDao<T> {

	T get(Serializable id);

	List<T> getAll();

	void save(T o);

	void delete(T o);

	void deleteById(Serializable id);
	/**
	 * 获取Entity对象的主键名.
	 */
	String getIdName(Class<T> clazz);
	
	public void flush();
	    
	public void clear();
}
