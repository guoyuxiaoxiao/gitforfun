package com.edu.daoimpl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.dao.IRoleDao;
import com.edu.model.RoleBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-hibernate.xml","classpath:spring.xml","classpath:spring-mvc.xml"}) 

public class RoleDaoImplTest {

	@Resource
	private IRoleDao roleDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetAll() {
		List<RoleBean> list = roleDao.getAllEntity(RoleBean.class);
		RoleBean roleBean  = list.get(0);
		System.out.println(roleBean);
	}

}
