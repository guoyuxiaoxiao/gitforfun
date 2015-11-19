package com.edu.imodelfunction;

import java.util.List;
import java.util.Map;

import com.edu.base.IBaseBo;
import com.edu.model.FunctionBean;
import com.edu.model.RoleBean;
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
	
	
	/**
	 * 用户添加角色
	 * @param userBean
	 * @param roleBean
	 * @return
	 */
	public int AddUserRole(int  userid,int roleid);


	/**
	 * 判断用户是否拥有该角色
	 * @param userid
	 * @param roleid
	 * @return
	 */
	int IsExitUserRole(int userid, int roleid);


	/**
	 * 获取用户对应的权限
	 * @param userid
	 * @return
	 */
	List<FunctionBean> GetUserFunctions(int userid);
}
