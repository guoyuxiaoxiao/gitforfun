package com.edu.daoimpl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.edu.base.BaseDaoImpl;
import com.edu.dao.IUserRoleDao;
import com.edu.model.UserRoleBean;
@Lazy(true)
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRoleBean> implements IUserRoleDao{

	
}
