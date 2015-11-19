package com.edu.daoimpl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.edu.base.BaseDaoImpl;
import com.edu.dao.IRoleFunctionDao;
import com.edu.model.RoleFunctionBean;
import com.edu.table.RoleFunctionTable;
@Lazy(true)
@Repository("roleFunctionDao")
public class RoleFunctionDaoImpl extends BaseDaoImpl<RoleFunctionBean> implements IRoleFunctionDao{

	
	@Override
	public int IsExitRoleFunction(int roleid, int functionid) {
		List<RoleFunctionBean> list = this.sqlWithList("select * from "
				+ RoleFunctionTable.TABLENAME + " where "
				+ RoleFunctionTable.ROLEID + " = " + roleid + " and "
				+ RoleFunctionTable.FUNCTIONID + " = " + functionid,
				RoleFunctionBean.class);
		if(list.size()>0)
			return 1;
		else 
			return 0;
	}

	@Override
	public int DeleteByRoleIdAndFunctionId(int roleid, int functionid) {
		
		return 0;
	}
	
}
