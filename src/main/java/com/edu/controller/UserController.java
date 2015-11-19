/**
 * 
 */
package com.edu.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.imodelfunction.IUserBean;
import com.edu.model.RoleBean;
import com.edu.model.UserBean;
import com.edu.table.UserTable;
import com.edu.util.MD5Util;

/**
 * 用户管理controller
 * @author Gy
 * 
 */
@Controller
@RequestMapping("/user.do")
public class UserController {
	@Resource
	private IUserBean mUserBean;

	/**
	 * 登录功能
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(params = "method=login")
	public String login(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			HttpServletRequest request, HttpServletResponse response,
			@CookieValue(value = "token", required = false) String token) {
		if (null != token) {
			if (null == username) {
				System.out.println(token);
				String[] parts = token.split("\\&");
				System.out.println(parts.length);
				String temp = MD5Util.convertMD5(parts[1]);
				System.out.println(temp);
				if (parts[0].equals(temp))
					return "redirect:/admin/main.jsp";
				return "redirect:/jsp/login.jsp?error=1";
			}
		}
		UserBean user = new UserBean();
		user.setPassword(password);
		user.setUsername(username);
		System.out.println(user.toString());
		boolean loginResult = mUserBean.isExist(user);
		if (loginResult) {
			Cookie cookie = new Cookie("token", username + "&"
					+ MD5Util.convertMD5(username));
			response.addCookie(cookie);
			return "redirect:/admin/main.jsp";
		} else {
			return "redirect:/jsp/login.jsp?error=1";
		}
	}



	/**
	 * 获取分页列表
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(params = "method=getUserbypage")
	@ResponseBody
	public Map<String, Object> JsonGetPageUser(
			@RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int pageSize,
			@RequestParam(value="selectname",defaultValue="id")String selectname,
			@RequestParam(value="value",defaultValue="")String value) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserBean> list = mUserBean.GetPageBeanFilter(UserBean.class, page,
				pageSize,selectname,value);
		int total = mUserBean.GetPageBeanFilterTotal(UserBean.class, page, pageSize, selectname, value);
		map.put("rows", list);
		map.put("total", total);
		return map;
	}

	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=deleteUser")
	@ResponseBody
	public String JsonDeleteUser(@RequestParam(value = "ids") String ids) {
		String[] id = ids.split(",");
		int[] temp = new int[id.length];
		for (int i = 0; i < id.length; i++) {
			temp[i] = Integer.parseInt(id[i]);
		}
		int result = mUserBean.DeleteBatch(UserBean.class, temp);
		if (result == 1)
			return "true";
		return "error";
	}
	
	
	/**
	 * 添加用户
	 * @param rowstr
	 * @return
	 */
	@RequestMapping(params="method=addUser")
	@ResponseBody
	public int JsonAddUser(@RequestParam(value="data") String data){
		try {
			data = data.substring(1,data.length()-1);
			System.out.println(data);
			JSONObject jsonObject = new JSONObject(data);
			String username = jsonObject.getString(UserTable.USERNAME);
			String password = jsonObject.getString(UserTable.PASSWORD);
			UserBean userbean = new UserBean(username, password);
			mUserBean.AddBean(userbean);
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
	@RequestMapping(params="method=updateUser")
	@ResponseBody
	public String JsonUpdate(@RequestParam(value="data") String data){
		data = data.substring(1,data.length()-1);
		System.out.println(data);
		JSONObject jsonObject = new JSONObject(data);
		int id = jsonObject.getInt(UserTable.ID);
		String username = jsonObject.getString(UserTable.USERNAME);
		String password = jsonObject.getString(UserTable.PASSWORD);
		UserBean userBean = new UserBean(id,username, password);
		userBean.UpdataBean(userBean);
		return "true";
	}
	//[{\"id\":1,\"name\":\"C\",\"size\":\"\",\"date\":\"02/19/2010\",\"children\":[{\"id\":2,\"name\":\"Program Files\",\"size\":\"120 MB\",\"date\":\"03/20/2010\",\"children\":[{\"id\":21,\"name\":\"Java\",\"size\":\"\",\"date\":\"01/13/2010\",\"state\":\"closed\",\"children\":[{\"id\":211,\"name\":\"java.exe\",\"size\":\"142 KB\",\"date\":\"01/13/2010\"},{\"id\":212,\"name\":\"jawt.dll\",\"size\":\"5 KB\",\"date\":\"01/13/2010\"}]}]}]}]
	@RequestMapping(params="method=gettest")
	@ResponseBody
	public String JsonGetTree(){
		return "[{\"id\":1,\"name\":\"C\",\"children\":[{\"id\":2,\"name\":\"Program Files\"}]}]";
	}
	
	
	
	/**
	 * 获取分页列表
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(params = "method=getUserTree")
	@ResponseBody
	public Map<String, Object> JsonGetUserTree(
			@RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int pageSize) {
		Map<String, Object> map = mUserBean.GetUserTree(page, pageSize);
		return map;
	}
	
	/**
	 * 获取所有的用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params="method=getalluser")
	public Map<String, Object> GetAllUser(){
		List<UserBean> list = mUserBean.GetAllBean(UserBean.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", list);
		return map;
	}
}
