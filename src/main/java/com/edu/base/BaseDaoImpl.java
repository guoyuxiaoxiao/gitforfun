package com.edu.base;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.model.RoleFunctionBean;



/**
 * baseDaoImpl 基础类
 * @author Gy
 */
@Transactional
@Repository("baseDao")
public class BaseDaoImpl<T> implements IBaseDao<T>{
	
	@Resource
	protected SessionFactory sessionFactory;
 
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addEntity(Object object) {
		getSession().save(object);
	}
	
	public BaseDaoImpl() {
		super();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getEntitybyId(Class clz,Integer id) {
		return getSession().load(clz, id);
	}

	public void deleteEntity(Object object){
		getSession().delete(object);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void deleteEntitybyId(Class clz, Integer id) throws Exception{
		Object object = null;
		object = clz.newInstance();
		Method method = clz.getDeclaredMethod("setId", Integer.class);
		method.invoke(object, id);
		getSession().delete(object);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> getAllEntity(Class clz){
		String hql ="";
		List<T> list = null;
		try {
			hql = "from "+clz.newInstance().getClass().getName();
			System.out.println(hql);
			list = getSession().createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> getPageEntity(Class clz, int page, int pageSize) {
		String hql ="";
		List<T> list = null;
		try {
			hql = "from "+clz.newInstance().getClass().getName();
			System.out.println(hql);
			Query query = getSession().createQuery(hql);
			query.setFirstResult((page-1)*pageSize); 
			query.setMaxResults(pageSize); 
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void sqlWithNone(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> sqlWithList(String sql,Class clz) {
		List<T> list = getSession().createSQLQuery(sql).addEntity(clz).list();
		return list;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T getObjectbyRequir(Class clz,Map<String, String>require) throws Exception{
		String hql = "from "+ clz.newInstance().getClass().getName()+" temp where 1=1 ";
		Iterator<Map.Entry<String, String>> enIterators = require.entrySet().iterator();
		while(enIterators.hasNext()){
			Map.Entry<String, String> entry = enIterators.next(); 
			hql+=" and temp."+entry.getKey()+"= "+entry.getValue();
		}
		return (T) getSession().createQuery(hql).list().get(0);
	}

	@Override
	public void updateEntity(Object object) {
		getSession().update(object);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int GetPageBeanTotal(Class clz) {
		return getAllEntity(clz).size();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteBatch(Class clz, int[] Ids) throws Exception {
		String hql = "";
        for(int i=0;i<Ids.length;i++) {
            if(i==0) {
                hql = "id="+Ids[i];
            } else {
                hql =hql + " or id="+Ids[i];
            }
        }   
        Session session= getSession();
        Query q= session.createQuery("delete from "+clz.newInstance().getClass().getName()+" where "+hql);
        q.executeUpdate();		
	}

	@Override
	public Object sqlWithObject(String sql) {
		return this.getSession().createSQLQuery(sql).list().get(0);
	}

	@Override
	public List<T> getPageBeanFilter(Class clz, int page, int pageSize,
			String selectname, String value) {
		String hql ="";
		List<T> list = null;
		try {
			hql = "from "+clz.newInstance().getClass().getName()+" where "+selectname+" like '%"+value+"%'";
			System.out.println(hql);
			Query query = getSession().createQuery(hql);
			query.setFirstResult((page-1)*pageSize); 
			query.setMaxResults(pageSize); 
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer SqlgetObjectId(String tablename, Map<String, String> require)
			throws Exception {
		String sql = "select id from "+ tablename+" temp where 1=1 ";
		Iterator<Map.Entry<String, String>> enIterators = require.entrySet().iterator();
		while(enIterators.hasNext()){
			Map.Entry<String, String> entry = enIterators.next(); 
			sql+=" and temp."+entry.getKey()+"= "+entry.getValue();
		}
		List<Object> list = getSession().createSQLQuery(sql).list();
		if(list.size()==0){
			return null;
		}else{
			return (Integer) list.get(0);
		}
		
	}

	

	

	
	
}
