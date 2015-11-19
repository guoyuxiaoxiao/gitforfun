package com.edu.daoimpl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.edu.base.BaseDaoImpl;
import com.edu.dao.IFunctionDao;
import com.edu.model.FunctionBean;
@Lazy(true)
@Repository("functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<FunctionBean> implements IFunctionDao{

}
