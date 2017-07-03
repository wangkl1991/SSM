package com.array.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.array.dao.GirlsMapper;
import com.array.model.Girls;
import com.array.utils.PageBeanUtil;
import com.array.utils.PagedResult;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class GirlsService {

	/*
	 * 约定大于配置
	 */
	@Resource
	private GirlsMapper girlsMapper;
	/**
	 * 宫女姓氏列表查询
	 * @return
	 */
	public List<Girls> getAll() { 
		List<Girls> glist = girlsMapper.getAll();
		return glist;
	}
	/*
	 * 分页显示秀女列表
	 */
	public PagedResult<Girls> getAllByPage(Integer pageNumber, Integer pageSize) {
		
		// 1.调用分页插件
		PageHelper.startPage(pageNumber, pageSize);
		// 2.查询数据库，获取数据
		List<Girls> glist = girlsMapper.getAll();
		// 3.通过分页工具类加载分页数据		
		return PageBeanUtil.toPagedResult(glist);
	}
	
	/**
	 * 秀女入宫插入操作
	 * 使用ajax，所以要用String
	 */
	public int insert(Girls g) { 
		 
		int i = girlsMapper.insert(g);
		
		return i; 
	}
	
	/**
	 * 秀女的删除后台操作
	 * 使用ajax
	 */
	public int del(String id) {
		int i = 0;
		if(id!=null&&id!="") {
			Integer sid = Integer.valueOf(id);
			i = girlsMapper.deleteByPrimaryKey(sid);
		}
		return i;
	}
	/**
	 * 更新操作to
	 * 回显示
	 */
	public Girls toUpdateByid(String id) { 
		 
		Girls girls = new Girls();
		if (id!=null&&id!="") {
			Integer sid = Integer.valueOf(id);
			  girls = girlsMapper.toUpdateByid(sid);
			   
		}
		return girls;
	}
	
	/**
	 * 更新操作 do
	 * 回显示
	 */
	public int doUpdateByid(Girls g) { 

		int i  = girlsMapper.doUpdateByid(g);
		
		return i;
	}

}
