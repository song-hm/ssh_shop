package com.shm.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shm.shop.cart.vo.Cart;
import com.shm.shop.cart.vo.CartItem;
import com.shm.shop.product.service.ProductService;
import com.shm.shop.product.vo.Product;
/**
 * 购物车的Action
 * @author SHM
 *
 */
public class CartAction extends ActionSupport {

	//接收pid
	private Integer pid;
	//接收数量count
	private Integer count;
	//注入商品的service
	private ProductService productService;
	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	//将购物项添加到购物车执行的方法
	public String addCart() {
		//封装一个CartItem对象
		CartItem cartItem = new CartItem();
		//设置数量
		cartItem.setCount(count);
		//根据ID查询商品
		Product product = productService.findByPid(pid);
		//设置商品
		cartItem.setProduct(product);
		//将购物项添加到购物车
		//购物车应该存在session中
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
				
	}

	//清空购物车执行的方法
	public String clearCart() {
		//获得购物车对象
		Cart cart = getCart();
		//调用购物车中清空方法
		cart.clearCart();
		return "clearCart";
	}
	
	//从购物车中移除购物项的方法
	public String removeCart() {
		//获得购物车对象
		Cart cart = getCart();
		//调用购物车中移除的方法
		cart.removeCart(pid);
		//返回页面
		return "removeCart";
	}
	
	//我的购物车执行的方法
	public String myCart() {
		return "myCart";
	}
	
	/**
	 * 获得购物车的方法
	 * @return
	 */	
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
