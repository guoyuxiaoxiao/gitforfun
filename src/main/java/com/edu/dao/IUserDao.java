/**
 * 
 */
package com.edu.dao;

import com.edu.base.IBaseDao;
import com.edu.model.UserBean;


public interface IUserDao extends IBaseDao<UserBean>{
	public boolean isExist(UserBean user);
}
