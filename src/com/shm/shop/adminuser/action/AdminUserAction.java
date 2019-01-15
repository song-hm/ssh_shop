package com.shm.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shm.shop.adminuser.service.AdminUserService;
import com.shm.shop.adminuser.vo.AdminUser;
/**
 * 后台管理员模块的动作类
 * @author SHM
 *
 */
public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {

	//模型驱动使用的对象
	private AdminUser adminUser = new AdminUser();
	//注入service
	private AdminUserService adminUserService;
	public AdminUser getModel() {
		return adminUser;
	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	/**
	 * 后台登录的方法
	 */
	public String login() {
		//调用service 完成登录
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null){
			//登录失败
			this.addActionError("亲！您的用户名或密码错误！");
			return "loginFail";
		}else{
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
}
