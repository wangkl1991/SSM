package com.array.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.array.model.Girls;
import com.array.model.Pie;
import com.array.service.GirlsService;
import com.array.utils.PagedResult;
import com.google.gson.Gson;

/**
 * 宫女入册的增删改查控制类
 */
@Controller
public class GirlsController {

	@Autowired
	private GirlsService girlsService;
	
	
	
	
	/**
	 * 查询所有姓氏的宫女列表
	 */
	@RequestMapping(value="getAllOne.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public ModelAndView getAllOne(HttpServletRequest resquest) {
		ModelAndView mv = new ModelAndView();
		List<Girls> findAllList =  girlsService.getAll();
		//resquest.setAttribute("findAllList", findAllList);
		mv.addObject("findAllList", findAllList);
		mv.setViewName("list"); 
	 
		return mv;
		
	}
	
	/**
	 * 查询所有姓氏的宫女列表 json
	 */
	@RequestMapping(value="getAllTwo.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getAllTwo(HttpServletRequest resquest) {
		
		List<Girls> findAllList =  girlsService.getAll();
		Gson gson = new Gson();
		String json = gson.toJson(findAllList);
		return json;
		
	}
	/**
	 * 1.线传统分页
	 *  设置分页的默认值
	 */
	@RequestMapping(value="getAllByPage.do",produces="application/json;charset=utf-8")
	public ModelAndView getAllByPage(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="2")Integer pageSize){
		
		ModelAndView mv = new ModelAndView();
		// 当前页和每页的条数  
		// 传入数据到分页工具类
		PagedResult<Girls> pageResult = girlsService.getAllByPage(pageNumber,pageSize);
		// 数据传递到前台页面展示层
		mv.addObject("pageResult", pageResult);
		// 跳转页面
		mv.setViewName("listpage");
		
		return mv;
		
	}
	
	
	/**
	 * 秀女入宫插入操作
	 * 使用ajax，所以要用String
	 */
	@RequestMapping(value="insert.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public String  insert(Girls g) {
		
		int  i = girlsService.insert(g);
		if(i>0) {
			return "yes";
		} else {
			return "no";
		}
		
	}
	
	/**
	 * 秀女的删除后台操作
	 * 使用ajax
	 */
	@RequestMapping(value="del.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public String del(String id) {
		int i = girlsService.del(id);
		if(i>0) {
			return "yes";
		} else {
			return "no";
		}
	}
	
	/**
	 * 更新操作
	 * 回显示
	 */
	@RequestMapping(value="toUpdateByid.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public ModelAndView toUpdateByid(String id) {
		ModelAndView mv = new ModelAndView();
	    Girls girls = girlsService.toUpdateByid(id);
	    mv.addObject("girls", girls);
		mv.setViewName("toupdate");
		return mv; 
		
	}
	
	/**
	 * 更新操作
	 */
	@RequestMapping(value="doUpdateByid.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public String doUpdateByid(Girls g) {
		
		int i = girlsService.doUpdateByid(g);
		if(i>0) {
			return "yes";
		} else {
			return "no";
		}
	}
	
	/*
	 * 玫瑰图
	 */
	@RequestMapping("getAllByPie.do")
	@ResponseBody
	public String getAllByPie() {
		List<Girls> glist = girlsService.getAll();
		List<Pie> plist = new ArrayList<Pie>();
		for (Girls girls : glist) {
			Pie pie = new Pie();
			pie.setValue(girls.getAge().toString());
			pie.setName(girls.getSname());
			plist.add(pie);
		}
		Gson gson = new Gson();
		String json = gson.toJson(plist);
		return json;
	}
}
