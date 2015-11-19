package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.model.FunctionBean;

/**
 * 功能管理controller
 * @author Gy
 *
 */
@Controller
@Lazy(true)
@RequestMapping("/function.do")
public class FunctionController {
	//@Resource
	//private IFunctionService functionService;

	/*@RequestMapping(params="method=getfunction")
	@ResponseBody
	public List<FunctionBean> JsonGetAllFunction(){
		List<FunctionBean> list = functionService.GetAllFunction();
		return list;
	}*/
	
	/*@RequestMapping(params="method=getfunctionbypage")
	@ResponseBody
	public Map<String, Object> JsonGetPageFunction(@RequestParam(value="page") int page,
			@RequestParam(value="rows") int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		List<FunctionBean> list = functionService.GetPageBean(FunctionBean.class, page, pageSize);
		int total = functionService.GetPageBeanTotal(FunctionBean.class);
		map.put("rows", list);
		map.put("total", total);
		return map;
	}*/
}
