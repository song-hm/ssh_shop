package com.shm.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shm.shop.product.vo.Product;
import com.shm.shop.utils.PageHibernateCallback;

/**
 * 商品的持久层对象
 * @author SHM
 *
 */
public class ProductDao extends HibernateDaoSupport{

	//首页上热门商品的查询
	public List<Product> findHot() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询热门商品，条件就是is_hot = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序排序输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}

	//首页上最新商品的查询
	public List<Product> findNew() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按日期倒序排序输出
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	//根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	//根据分类的ID查询商品的个数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorysecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//根据分类ID查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		//select p.* from category c,categorysecond cs ,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 1
		//select p from Category c, Categorysecond cs, Product p where c.cid = cs.category.cid and cs.csid = p.categorysecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorysecond cs join cs.category c where c.cid= ?";
		//分页的另一种写法：
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	//根据二级分类查询商品个数
		public int findCountCsid(Integer csid) {
			String hql = "select count(*) from Product p where p.categorysecond.csid = ?";
			List<Long> list = this.getHibernateTemplate().find(hql, csid);
			if(list != null && list.size() > 0){
				return list.get(0).intValue();
			}
			return 0;
		}

	//根据二级分类查询商品信息
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorysecond cs where cs.csid = ? ";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	//统计商品个数的方法
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);		
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//带分页查询商品的方法
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}else{
			return null;
		}		
	}

	//保存商品到数据库的方法
	public void save(Product product) {
		this.getHibernateTemplate().save(product);		
	}

	//删除商品从数据库中
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);		
	}

	//修改商品的方法
	public void update(Product product) {
		this.getHibernateTemplate().update(product);		
	}

	

}
