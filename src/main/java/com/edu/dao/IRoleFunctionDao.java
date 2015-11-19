package com.edu.dao;

import com.edu.base.IBaseDao;
import com.edu.model.RoleFunctionBean;

public interface IRoleFunctionDao extends IBaseDao<RoleFunctionBean>{
	
	/**
	 * 判断是否存在
	 * @param roleid
	 * @param functionid
	 * @return
	 */
	public int IsExitRoleFunction(int roleid,int functionid);
	
	/**
	 * 删除
	 * @param roleid
	 * @param functionid
	 * @return
	 */
	public int DeleteByRoleIdAndFunctionId(int roleid,int functionid);
}
