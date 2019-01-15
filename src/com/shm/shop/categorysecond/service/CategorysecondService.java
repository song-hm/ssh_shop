package com.shm.shop.categorysecond.service;

import java.util.List;

import com.shm.shop.categorysecond.dao.CategorysecondDao;
import com.shm.shop.categorysecond.vo.Categorysecond;
import com.shm.shop.utils.PageBean;

/**
 * 二级分类管理的业务层类
 * @author SHM
 *
 */
public class CategorysecondService {

	//注入二级分类的dao
	private CategorysecondDao categorysecondDao;

	public void setCategorysecondDao(CategorysecondDao categorysecondDao) {
		this.categorysecondDao = categorysecondDao;
	}

	//业务层分页查询二级分类的方法
	public PageBean<Categorysecond> findByPage(Integer page) {
		PageBean<Categorysecond> pageBean = new PageBean<Categorysecond>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = categorysecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示数据集合
		int begin = (page - 1) * limit;
		List<Categorysecond> list = categorysecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层保存二级分类的方法
	public void save(Categorysecond categorysecond) {
		categorysecondDao.save(categorysecond);		
	}
			

	//业务层根据二级分类的ID查询二级分类
	public Categorysecond findByCsid(Integer csid) {		
		return categorysecondDao.findByCsid(csid);
	}
	
	
	//业务层删除二级分类的方法
	public void delete(Categorysecond categorysecond) {		
		categorysecondDao.delete(categorysecond);
	}

	//业务层修改二级分类的方法
	public void update(Categorysecond categorysecond) {
		categorysecondDao.update(categorysecond);				
	}

	//业务层查询所有二级分类的方法
	public List<Categorysecond> findAll() {
		return categorysecondDao.findAll();		
	}	
}
