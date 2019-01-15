package com.shm.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shm.shop.category.service.CategoryService;
import com.shm.shop.category.vo.Category;

/**
 * 一级分类的后台管理的动作类
 * @author SHM
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	//模型驱动使用的对象
	private Category category = new Category();
	//注入service
	private CategoryService categoryService;
	
	public Category getModel() {
		return category;
	}
	

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	//执行查询所有一级分类的方法
	public String findAll() {
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		//将集合的数据显示到页面上
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//后台保存一级分类的方法
	public String save() {
		//调用service进行保存
		categoryService.save(category);
		//页面跳转
		return "saveSuccess";
		
	}
	
	//后台删除一级分类的方法
	public String delete() {
		//接收cid，可以使用模型驱动，删除一级分类，同时删除二级分类，必须先根据ID查询在进行删除
		category = categoryService.findByCid(category.getCid());
		//删除一级分类
		categoryService.delete(category);
		//页面跳转
		return "deleteSuccess";
	}
	
	//后台编辑一级分类的方法
	public String edit() {
		//根据一级分类的ID查询一级分类
		category = categoryService.findByCid(category.getCid());
		//页面跳转
		return "editSuccess";
	}
	
	//后台修改一级分类的方法
	public String update() {
		categoryService.update(category);
		//页面的跳转
		return "updateSuccess";
	}
}
