package com.edu.imodelfunction;


import java.util.List;
import java.util.Map;

import com.edu.base.IBaseBo;
import com.edu.model.RoleBean;

public interface IRoleBean extends IBaseBo<RoleBean>{
	/**
	 * 得到树状结构
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> GetPowerTree(int page,int pageSize);
	/**
	 * 得到所有的角色
	 * @return
	 */
	public List<RoleBean> GetAllRole();
	
}
