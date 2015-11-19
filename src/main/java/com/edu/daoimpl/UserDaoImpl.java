/**
 * 
 */
package com.edu.daoimpl;



import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.edu.base.BaseDaoImpl;
import com.edu.dao.IUserDao;
import com.edu.model.UserBean;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserBean> implements IUserDao {

	@Override
	public boolean isExist(UserBean user) {
		String hql = "from UserBean where username=? and password=?";
		 Query query = getSession().createQuery(hql);
		 query.setString(0, user.getUsername());
		 query.setString(1, user.getPassword());
		 if(query.list().size()>=1){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	


}
