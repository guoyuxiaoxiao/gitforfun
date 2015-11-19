package com.edu.base;

import java.util.List;
import java.util.Map;


public interface IBaseDao<T> {
	/**
	 * 增加数据或者保存数据。如果存在主键就更新，如果不存在主键则插入
	 * @param object
	 */
	@SuppressWarnings("rawtypes")
	public void addEntity(Object object);

	
	/**
	 * 根据id获取数据
	 * @param clz 实体类的class
	 * @param id 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Object getEntitybyId(Class clz, Integer id);
	
	
	/**
	 * 根据条件获取对应持久化对象
	 * @param clz
	 * @param require
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Object getObjectbyRequir(Class clz,Map<String, String>require) throws Exception;
	
	/**
	 * 根据条件获取对应对象id
	 * @param clz
	 * @param require
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Integer SqlgetObjectId(String tablename,Map<String, String>require) throws Exception;
	
	
	/**
	 * 根据类获取所有数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<T> getAllEntity(Class clz);
	
	/**
	 * 分页
	 * @param claz
	 * @param pagesize
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<T> getPageEntity(Class clz,int page,int pageSize);
	
	
	
	/**
	 * 根据实体删除数据(根据的是实体类中的主键删除)
	 * @param object
	 */
	public void deleteEntity(Object object);

	
	/**
	 * 根据id删除数据
	 * @param clz 实体类的class
	 * @param id
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public void deleteEntitybyId(Class clz, Integer id) throws Exception;
	
	
	/**
	 * 批量删除
	 * @param clz
	 * @param Ids
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public void deleteBatch(Class clz,int[] Ids) throws Exception;
	/**
	 * 更新从getObjectbyRequir函数获取的实体。
	 * @param object
	 */
	public void updateEntity(Object object);

	
	
	/**
	 * 执行不带返回值的sql语句
	 * @param sql
	 */
	public void sqlWithNone(String sql);
	
	/**
	 * sql返回实体类
	 * @param sql
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<T> sqlWithList(String sql,Class clz);
	
	
	/**
	 * 返回id或者单个属性
	 * @param sql
	 * @return
	 */
	public Object sqlWithObject(String sql);
	/**
	 * 获取数量
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int GetPageBeanTotal(Class clz);
	
	
	/**
	 * 过滤分页
	 * @param claz
	 * @param pagesize
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<T> getPageBeanFilter(Class clz,int page,int pageSize,String selectname,String value);
}
