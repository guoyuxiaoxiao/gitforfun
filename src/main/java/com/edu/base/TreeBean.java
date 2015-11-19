package com.edu.base;

import java.util.ArrayList;
import java.util.List;

public class TreeBean {
	private String id;
	private String name;
	private List<TreeChildItemBean> children = new ArrayList<TreeChildItemBean>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TreeBean(String name, List<TreeChildItemBean> children) {
		super();
		this.name = name;
		this.children = children;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TreeBean(String id, String name, List<TreeChildItemBean> children) {
		super();
		this.id = id;
		this.name = name;
		this.children = children;
	}
	public List<TreeChildItemBean> getChildren() {
		return children;
	}
	public void setChildren(List<TreeChildItemBean> children) {
		this.children = children;
	}
	public TreeBean() {
		super();
	}
	
	
}

