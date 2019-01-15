package com.shm.shop.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.shm.shop.categorysecond.vo.Categorysecond;

/**
 * 一级分类的实体类
 * @author SHM
 *
 */
public class Category implements Serializable{

	private Integer cid;
	private String cname;
	//一级分类中存放二级分类的集合
	private Set<Categorysecond> categoryseconds = new HashSet<Categorysecond>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<Categorysecond> getCategoryseconds() {
		return categoryseconds;
	}
	public void setCategoryseconds(Set<Categorysecond> categoryseconds) {
		this.categoryseconds = categoryseconds;
	}
	
}
