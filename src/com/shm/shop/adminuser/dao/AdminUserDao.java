package com.shm.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shm.shop.adminuser.vo.AdminUser;
/**
 * 管理员模块的持久层对象
 * @author SHM
 *
 */
public class AdminUserDao extends HibernateDaoSupport {

	/**
	 * 持久层的登录方法
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
