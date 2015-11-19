package com.edu.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name="ur_userrole")
public class UserRoleBean {
	private Integer id;
	private UserBean user;
	private RoleBean role;
	public UserRoleBean(Integer id, UserBean user, RoleBean role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}
	public UserRoleBean() {
		super();
	}
	
	public UserRoleBean(UserBean user, RoleBean role) {
		super();
		this.user = user;
		this.role = role;
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
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="userid")
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="roleid")
	public RoleBean getRole() {
		return role;
	}
	public void setRole(RoleBean role) {
		this.role = role;
	}
	
}
