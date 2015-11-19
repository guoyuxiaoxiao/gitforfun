package com.edu.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Service;

import com.edu.base.TreeBean;
import com.edu.base.TreeChildItemBean;
import com.edu.imodelfunction.IRoleBean;


@Table(name="r_role")
@Entity
@Service
public class RoleBean extends BaseBean<RoleBean> implements IRoleBean{
	private Integer id;
	private String rolename;
	private Set<FunctionBean> functionBeans = new HashSet<FunctionBean>();
	public RoleBean(Integer id, String rolename) {
		super();
		this.id = id;
		this.rolename = rolename;
	}
	
	public RoleBean(String rolename) {
		super();
		this.rolename = rolename;
	}

	public RoleBean() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="rolename")
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
	public RoleBean(Integer id, String rolename, Set<FunctionBean> functionBeans) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.functionBeans = functionBeans;
	}



	@Transient
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinTable(name = "rf_rolefunction",joinColumns ={@JoinColumn(name = "roleid", referencedColumnName = "id") },   
    inverseJoinColumns = { @JoinColumn(name = "functionid", referencedColumnName = "id")})  
	public Set<FunctionBean> getFunctionBeans() {
		return functionBeans;
	}



	public void setFunctionBeans(Set<FunctionBean> functionBeans) {
		this.functionBeans = functionBeans;
	}
	
	
	@Override
	public Map<String, Object> GetPowerTree(int page,int pageSize) {
		List<RoleBean> list =  this.GetPageBean(RoleBean.class, page, pageSize);
		List<TreeBean> temp = new ArrayList<TreeBean>();
		int total = 0;
		for(int i=0;i<list.size();i++){
			RoleBean roleBean = list.get(i);
			Set<FunctionBean> set = roleBean.getFunctionBeans();
			if (set.size()!=0) {
				total++;
				TreeBean treeBean = new TreeBean();
				treeBean.setId(roleBean.getId()+"");
				treeBean.setName(roleBean.getRolename());
				for(FunctionBean functionBean : set){
					treeBean.getChildren().add(new TreeChildItemBean(roleBean.getId()+"_"+functionBean.getId(),
							functionBean.getFunctionname()));
				}
				temp.add(treeBean);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", temp);
		map.put("total", total);
		return map;
	}

	@Override
	public List<RoleBean> GetAllRole() {
		List<RoleBean> list = this.GetAllBean(RoleBean.class);
		List<RoleBean> temp  = new  ArrayList<RoleBean>();
		for(int i =0;i<list.size();i++){
			RoleBean role = list.get(i);
			RoleBean one = new RoleBean(role.getId(), role.getRolename());
			temp.add(one);
		}
		return temp;
	}
	
}
