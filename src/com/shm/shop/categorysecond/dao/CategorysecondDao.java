package com.shm.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shm.shop.categorysecond.vo.Categorysecond;
import com.shm.shop.utils.PageHibernateCallback;

/**
 * 二级分类管理的持久层对象
 * @author SHM
 *
 */
public class CategorysecondDao extends HibernateDaoSupport{

	//持久层统计二级分类的个数的方法
	public int findCount() {
		String hql = "select count(*) from Categorysecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//持久层分页查询二级分类的方法
	public List<Categorysecond> findByPage(int begin, int limit) {
		String hql = "from Categorysecond order by csid desc";
		List<Categorysecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Categorysecond>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	//持久层保存二级分类的方法
	public void save(Categorysecond categorysecond) {
		this.getHibernateTemplate().save(categorysecond);		
	}

	//根据二级分类的ID查询二级分类的方法
	public Categorysecond findByCsid(Integer csid) {		
		return this.getHibernateTemplate().get(Categorysecond.class, csid);
	}

	//删除二级分类的方法
	public void delete(Categorysecond categorysecond) {		
		this.getHibernateTemplate().delete(categorysecond);
	}

	//修改二级分类的方法
	public void update(Categorysecond categorysecond) {
		this.getHibernateTemplate().update(categorysecond);		
	}
	
	//查询所有二级分类的方法
	public List<Categorysecond> findAll() {
		String hql = "from Categorysecond";
		return this.getHibernateTemplate().find(hql);
	}		

}
