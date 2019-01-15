package com.shm.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shm.shop.category.dao.CategoryDao;
import com.shm.shop.category.vo.Category;

/**
 * 一级分类的业务层类
 * @author SHM
 *
 */
@Transactional
public class CategoryService {

	//注入categoryDao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//查询所有一级分类的方法
	public List<Category> findAll() {		
		return categoryDao.findAll();
	}

	//业务层保存一级分类的方法
	public void save(Category category) {
		categoryDao.save(category);		
	}

	//业务层根据cid查询一级分类
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	//业务层删除一级分类的方法
	public void delete(Category category) {
		categoryDao.delete(category);		
	}

	//业务层后台修改一级分类的方法
	public void update(Category category) {
		categoryDao.update(category);
		
	}
	
}
