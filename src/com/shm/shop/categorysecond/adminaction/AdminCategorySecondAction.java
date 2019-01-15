package com.shm.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shm.shop.category.service.CategoryService;
import com.shm.shop.category.vo.Category;
import com.shm.shop.categorysecond.service.CategorysecondService;
import com.shm.shop.categorysecond.vo.Categorysecond;
import com.shm.shop.utils.PageBean;
/**
 * 二级分类管理的action类
 * @author SHM
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<Categorysecond> {

	//模型驱动使用的对象
	private Categorysecond categorysecond = new Categorysecond();
	public Categorysecond getModel() {		
		return categorysecond;
	}

	//注入二级分类的service
	private CategorysecondService categorysecondService;
	public void setCategorysecondService(CategorysecondService categorysecondService) {
		this.categorysecondService = categorysecondService;
	}
	//注入一级分类service
	private CategoryService categoryService;
	
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//接收page
	private Integer  page;
	
	
	public void setPage(Integer page) {
		this.page = page;
	}


	//查询二级分类的方法
	public String findAllByPage() {
		PageBean<Categorysecond> pageBean =  categorysecondService.findByPage(page);
		//将pageBean的数据保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	
	//跳转到添加页面
	public String addPage() {
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		//把数据显示到页面的下拉列表中
		ActionContext.getContext().getValueStack().set("cList", cList);
		//页面跳转
		return "addPage";
	}
	
	//保存二级分类的方法
	public String save() {
		categorysecondService.save(categorysecond);		
		return "save";
	}
	
	//删除二级分类的方法
	public String delete() {
		//如果要级联删除，先查询再删除，配置cascade
		categorysecond = categorysecondService.findByCsid(categorysecond.getCsid());
		categorysecondService.delete(categorysecond);
		return "delete";
	}
	
	//编辑二级分类的方法
	public String edit() {
		//根据二级分类的ID查询二级分类
		categorysecond = categorysecondService.findByCsid(categorysecond.getCsid());
		//查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "edit";
	}
	
	//修改二级分类的方法
	public String update(){
		categorysecondService.update(categorysecond);
		return "update";
	}
	
}
