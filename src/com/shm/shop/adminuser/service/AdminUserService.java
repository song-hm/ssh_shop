package com.shm.shop.adminuser.service;

import com.shm.shop.adminuser.dao.AdminUserDao;
import com.shm.shop.adminuser.vo.AdminUser;

/**
 * 管理员模块的业务层对象
 * @author SHM
 *
 */
public class AdminUserService {

	//注入dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 业务层登录的方法
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}
	
}
