/**
 * 
 */
package com.edu.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import com.edu.base.TreeBean;
import com.edu.base.TreeChildItemBean;
import com.edu.dao.IRoleDao;
import com.edu.dao.IUserDao;
import com.edu.dao.IUserRoleDao;
import com.edu.imodelfunction.IUserBean;
import com.edu.table.UserRoleTable;

/**
 * 用户表
 * @author Christy
 *
 */
@Table(name="u_user")
@Entity
@Service
public class UserBean extends BaseBean<UserBean> implements IUserBean{
	@Resource
	private IUserDao userDao;
	@Resource
	private IRoleDao roleDao;
	@Resource
	private IUserRoleDao userRoleDao;
	private Integer id;
	private String username;
	private String password;
	private Set<RoleBean> roleBeans = new HashSet<RoleBean>();
	
	@Transient
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinTable(name = "ur_userrole",joinColumns ={@JoinColumn(name = "userid", referencedColumnName = "id") },   
    inverseJoinColumns = { @JoinColumn(name = "roleid", referencedColumnName = "id")})  
	public Set<RoleBean> getRoleBeans() {
		return roleBeans;
	}

	public void setRoleBeans(Set<RoleBean> roleBeans) {
		this.roleBeans = roleBeans;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [ \nid : " + this.id + "\nusername : " + this.username
				+ "\npassword : " + this.password + "\n]";
	}

	public UserBean() {
		super();
	}

	public UserBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserBean(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public boolean isExist(UserBean user) {
		return userDao.isExist(user);
	}
	
	
	@Override
	public Map<String, Object> GetUserTree(int page, int pageSize) {
		List<UserBean> list = this.GetPageBean(UserBean.class, page, pageSize);
		List<TreeBean> temp = new ArrayList<TreeBean>();
		int total = 0;
		for(int i=0;i<list.size();i++){
			UserBean userBean = list.get(i);
			Set<RoleBean> set =userBean.getRoleBeans();
			if (set.size()!=0) {
				total++;
				TreeBean treeBean = new TreeBean();
				treeBean.setId(userBean.getId()+"");
				treeBean.setName(userBean.getUsername());
				for(RoleBean  roleBean : set){
					treeBean.getChildren().add(new TreeChildItemBean(userBean.getId()+"_"+roleBean.getId(),
							roleBean.getRolename()));
				}
				temp.add(treeBean);
			}
		}
		//String treeString= FastJsonTool.createJsonString(temp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", temp);
		map.put("total", total);
		return map;
	}

	
	@Override
	public int AddUserRole(int  userid,int roleid) {
		int fontResult = IsExitUserRole(userid, roleid);
		if(fontResult==0){
			UserBean user = (UserBean) userDao.getEntitybyId(UserBean.class, userid);
			RoleBean role = (RoleBean) roleDao.getEntitybyId(RoleBean.class,roleid);
			UserRoleBean userRoleBean = new UserRoleBean(user, role);
			userRoleDao.addEntity(userRoleBean);
			return 2;
		}else if(fontResult==1){
			return 1;
		}else{
			return -1;
		}
	}
	
	@Override
	public int IsExitUserRole(int userid, int roleid) {
		Map<String, String> require = new HashMap<String, String>();
		require.put(UserRoleTable.USERID, userid+"");
		require.put(UserRoleTable.ROLEID, roleid+"");
		try {
			Integer id  = userRoleDao.SqlgetObjectId(UserRoleTable.TABLENAME, require);
			if(null == id)
				return 0;
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<FunctionBean> GetUserFunctions(int userid) {
		UserBean userBean = (UserBean) userDao.getEntitybyId(UserBean.class, userid);
		Set<RoleBean> roleset= userBean.getRoleBeans();
		List<FunctionBean> functionBeans = new ArrayList<FunctionBean>();
		for(RoleBean roleBean: roleset){
			Set<FunctionBean> functionSet = roleBean.getFunctionBeans();
			for(FunctionBean functionBean:functionSet){
				functionBeans.add(functionBean);
			}
		}
		return functionBeans;
	}
}
