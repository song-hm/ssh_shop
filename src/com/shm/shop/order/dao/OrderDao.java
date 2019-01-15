package com.shm.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shm.shop.order.vo.Order;
import com.shm.shop.order.vo.OrderItem;
import com.shm.shop.utils.PageHibernateCallback;

/**
 * 订单模块的持久层
 * @author SHM
 *
 */
public class OrderDao extends HibernateDaoSupport{

	//持久层的保存订单的方法
	public void save(Order order) {
		this.getHibernateTemplate().save(order);		
	}

	//持久层的我的订单的个数统计
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}		
		return null;
	}

	//持久层的我的订单的查询 
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));		
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//持久层的根据订单ID查询订单
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	//持久层修改订单的方法
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);		
	}

	//后台统计订单个数的方法
	public int findByCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//后台分页查询所有订单的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order o order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	//根据订单ID查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

}
