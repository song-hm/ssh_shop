package com.shm.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shm.shop.category.service.CategoryService;
import com.shm.shop.category.vo.Category;
import com.shm.shop.product.service.ProductService;
import com.shm.shop.product.vo.Product;
import com.shm.shop.utils.PageBean;
/**
 * 商品的动作类
 * @author SHM
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	//注入ProductService
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//用于接收数据的模型驱动
	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	
	//接收分类cid
	private Integer cid;
	
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}
	
	//接收二级分类的ID
	private Integer csid;
	

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	//注入一级分类的service
	private CategoryService categoryService;


	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//接受当前的页数
	private int page;
	
	public void setPage(int page) {
		this.page = page;
	}

	//根据商品的ID查询商品详情的方法
	public String findByPid(){
		//调用Service的方法完成查询
		product =  productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据分类的ID查询商品
	public String findByCid() {
//		List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page); //根据一级分类查询商品，带分页查询
		//将PageBean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}	

	//根据二级分类的ID查询商品
	public String findByCsid(){
		//根据二级分类查询商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		//将PageBean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
