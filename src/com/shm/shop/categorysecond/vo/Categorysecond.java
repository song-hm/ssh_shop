package com.shm.shop.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.shm.shop.category.vo.Category;
import com.shm.shop.product.vo.Product;

/**
 * 二级分类所属的实体
 * @author SHM
 *
 */
public class Categorysecond implements Serializable{

	private Integer csid;
	private String csname;
	
	//所属一级分类，存的是一级分类的对象
	private Category category;

	//配置商品集合
	private Set<Product> products = new HashSet<Product>();
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
