package com.shm.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shm.shop.adminuser.vo.AdminUser;

/**
 * 后台权限校验的拦截器
 * @author SHM
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	//执行拦截的方法
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否保存了后台用户的信息
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(existAdminUser == null){
			//没有登录
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("亲！你还没有登录，没有权限访问，请去登录");
			return "loginFail";
			
		}else{
			//已经登录过
			return actionInvocation.invoke();
		}
	}

}
