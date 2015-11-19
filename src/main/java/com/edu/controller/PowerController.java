package com.edu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.model.RoleFunctionBean;
import com.edu.table.RoleFunctionTable;

/**
 * 权限管理controller
 * @author Gy
 *
 */
@Controller
@Lazy(true)
@RequestMapping("/power.do")
public class PowerController {

	/*@Resource
	private IRoleService roleService;
	
	@Resource
	private IRoleFunctionService roleFunctionService;*/
	
/*	@ResponseBody
	@RequestMapping(params="method=getpowertree")
	public Map<String, Object> JsonGetPowerTree(@RequestParam(value="page") int page,
			@RequestParam(value="rows") int pageSize){
		Map<String, Object> map  = roleService.GetPowerTree(page, pageSize);
		return map;
	}*/
	
	/*@ResponseBody
	@RequestMapping(params="method=addrolepower")
	public String JsonAddRolePower(@RequestParam(value="roleid") int roleid,@RequestParam(value="functionid") int functionid){
		int result = roleFunctionService.AddRoleFunction(roleid, functionid);
		return result+"";
	}*/
	
	/*
	 * 删除权限
	 */
	/*@ResponseBody
	@RequestMapping(params="method=deleterolepower")
	public String JsonDeleteRolePower(@RequestParam(value="roleid")int roleid,@RequestParam(value="functionid")int functionid){
		Map<String, String> require = new HashMap<String, String>();
		require.put(RoleFunctionTable.ROLEID, roleid+"");
		require.put(RoleFunctionTable.FUNCTIONID, functionid+"");
		int result = roleFunctionService.DeleteBean(RoleFunctionBean.class, RoleFunctionTable.TABLENAME, require );
		return result+"";
	}*/
}
