package com.edu.model;

import java.beans.Transient;

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

@Entity
@Table(name="rf_rolefunction")
public class RoleFunctionBean {
	private Integer id;
	private RoleBean role;
	private FunctionBean function;
	public RoleFunctionBean(Integer id, RoleBean role, FunctionBean function) {
		super();
		this.id = id;
		this.role = role;
		this.function = function;
	}
	public RoleFunctionBean() {
		super();
	}
	public RoleFunctionBean(RoleBean role, FunctionBean function) {
		super();
		this.role = role;
		this.function = function;
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
	@JoinColumn(name="roleid")
	public RoleBean getRole() {
		return role;
	}
	public void setRole(RoleBean role) {
		this.role = role;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="functionid")
	public FunctionBean getFunction() {
		return function;
	}
	public void setFunction(FunctionBean function) {
		this.function = function;
	}
	
	
}
