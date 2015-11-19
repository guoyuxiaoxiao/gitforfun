package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.imodelfunction.IRoleBean;
import com.edu.model.RoleBean;
import com.edu.model.UserBean;
import com.edu.table.RoleTable;
import com.edu.table.UserTable;
import com.edu.util.FastJsonTool;

@Controller
@Lazy(true)
@RequestMapping("/role.do")
public class RoleController {

	@Resource
	private IRoleBean mRoleBean;
	
	@ResponseBody
	@RequestMapping(params="method=getallrole")
	public Map<String, Object> GetAllRole(){
		List<RoleBean> list = mRoleBean.GetAllRole();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roles", list);
		return map;
	}
	
	
	/**
	 * 获取分页列表
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(params = "method=getRolebypage")
	@ResponseBody
	public Map<String, Object> JsonGetPageRole(
			@RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int pageSize,
			@RequestParam(value="selectname",defaultValue="id")String selectname,
			@RequestParam(value="value",defaultValue="")String value) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RoleBean> list = mRoleBean.GetPageBeanFilter(RoleBean.class, page,
				pageSize,selectname,value);
		int total = mRoleBean.GetPageBeanFilterTotal(RoleBean.class, page, pageSize, selectname, value);
		map.put("rows", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 删除角色
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=deleteRole")
	@ResponseBody
	public String JsonDeleteRole(@RequestParam(value = "ids") String ids) {
		String[] id = ids.split(",");
		int[] temp = new int[id.length];
		for (int i = 0; i < id.length; i++) {
			temp[i] = Integer.parseInt(id[i]);
		}
		int result = mRoleBean.DeleteBatch(RoleBean.class, temp);
		if (result == 1)
			return "true";
		return "error";
	}
	
	
	/**
	 * 添加角色
	 * @param rowstr
	 * @return
	 */
	@RequestMapping(params="method=addRole")
	@ResponseBody
	public int JsonAddUser(@RequestParam(value="data") String data){
		try {
			//data = URLDecoder.decode(data, "utf-8");
			data = data.substring(1,data.length()-1);
			System.out.println(data);
			JSONObject jsonObject = new JSONObject(data);
			String rolename = jsonObject.getString(RoleTable.ROLENAME);
			RoleBean roleBean = new RoleBean(rolename);
			mRoleBean.AddBean(roleBean);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	/**
	 * 更新
	 * @param rowstr
	 * @return
	 */
	@RequestMapping(params="method=updateRole")
	@ResponseBody
	public String JsonUpdate(@RequestParam(value="data") String data){
		data = data.substring(1,data.length()-1);
		System.out.println(data);
		JSONObject jsonObject = new JSONObject(data);
		int id = jsonObject.getInt(RoleTable.ID);
		String rolename = jsonObject.getString(RoleTable.ROLENAME);
		RoleBean roleBean = new RoleBean(id,rolename);
		mRoleBean.UpdataBean(roleBean);
		return "true";
	}
}
