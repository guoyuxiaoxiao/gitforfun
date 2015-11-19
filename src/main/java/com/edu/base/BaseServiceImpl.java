package com.edu.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("baseService")
public class BaseServiceImpl<T> implements IBaseService<T>{

	@SuppressWarnings("rawtypes")
	@Resource
	private IBaseDao baseDao;
	
	
	/**
	 * 获取分页实体
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> GetPageBean(Class clz, int page, int pageSize) {
		return baseDao.getPageEntity(clz, page, pageSize);
	}

	@SuppressWarnings("rawtypes")
	public int GetPageBeanTotal(Class clz){
		return baseDao.GetPageBeanTotal(clz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int DeleteBatch(Class clz, int[] ids) {
		try {
			baseDao.deleteBatch(clz, ids);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int AddBean(Object object) {
		baseDao.addEntity(object);
		return 1;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> GetAllBean(Class clz) {
		return baseDao.getAllEntity(clz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int DeleteByid(Class clz, int id) {
		try {
			baseDao.deleteEntitybyId(clz, id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> GetPageBeanFilter(Class clz, int page, int pageSize,
			String selectname, String value) {
		return baseDao.getPageBeanFilter(clz, page, pageSize, selectname, value);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int GetPageBeanFilterTotal(Class clz, int page, int pageSize,
			String selectname, String value) {
		return baseDao.getPageBeanFilter(clz, page, pageSize, selectname, value).size();
	}

	@Override
	public int UpdataBean(Object object) {
		baseDao.updateEntity(object);
		return 1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int DeleteBean(Class clz,String tablename, Map<String, String> require) {
			try {
				Integer id = baseDao.SqlgetObjectId(tablename, require);
				baseDao.deleteEntitybyId(clz, id);
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
	}
	
}
