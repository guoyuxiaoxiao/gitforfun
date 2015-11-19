package com.edu.imodelfunction;

import java.util.Map;

import com.edu.base.IBaseBo;
import com.edu.model.UserBean;

public interface IUserBean extends IBaseBo<UserBean>{
	
	/**
	 * 判断用户是否存在
	 * @param user
	 * @return
	 */
	public boolean isExist(UserBean user);
	
	
	/**
	 * 获取树状
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String , Object> GetUserTree(int page,int pageSize);
}
