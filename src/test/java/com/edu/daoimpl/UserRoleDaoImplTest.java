package com.edu.daoimpl;


import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.dao.IUserRoleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-hibernate.xml","classpath:spring.xml","classpath:spring-mvc.xml"}) 
public class UserRoleDaoImplTest {

	@Resource
	private IUserRoleDao userRoleDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testSelectAll() {
		List<Object> list = userRoleDao.selectAll(UserRoleBean.class);
		System.out.println(list.size());
		UserRoleBean userRoleBean = (UserRoleBean) list.get(0);
		System.out.println(userRoleBean.getUser().getPassword());
	}*/

}
