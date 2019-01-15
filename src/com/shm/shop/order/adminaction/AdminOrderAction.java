package com.shm.shop.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shm.shop.order.service.OrderService;
import com.shm.shop.order.vo.Order;
import com.shm.shop.order.vo.OrderItem;
import com.shm.shop.utils.PageBean;

public class AdminOrderAction extends ActionSupport implements
		ModelDriven<Order> {

	//模型驱动使用的对象
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	//注入订单的service
	private OrderService orderService;
	

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	//接收page参数
	private Integer page;
	

	public void setPage(Integer page) {
		this.page = page;
	}


	//分页查询所有订单的方法
	public String findAllByPage(){
		//分页查询
		PageBean<Order> pageBean = orderService.findByPage(page);
		//通过值栈保存数据到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAllByPage";
	}
	
	//根据订单的ID查询订单项
	public String findOrderItem(){
		//根据订单ID查询订单项
		List<OrderItem> list = orderService.findOrderItem(order.getOid()); 
		//通过值栈显示到页面上
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	//后台修改订单状态的方法
	public String updateState(){
		//1、根据订单ID查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		//修改订单状态
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateState";
	}
}
