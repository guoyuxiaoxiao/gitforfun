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
import com.edu.dao.IUserDao;
import com.edu.imodelfunction.IUserBean;

@Table(name="u_user")
@Entity
@Service
public class UserBean extends BaseBean<UserBean> implements IUserBean{
	@Resource
	private IUserDao userDao;
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
}
