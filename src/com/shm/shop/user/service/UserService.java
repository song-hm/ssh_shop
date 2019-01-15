package com.shm.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.shm.shop.user.dao.UserDao;
import com.shm.shop.user.vo.User;
import com.shm.shop.utils.MailUtils;
import com.shm.shop.utils.UUIDUtils;

/**
 * 用户名模块业务层代码
 * @author SHM
 *
 */
@Transactional
public class UserService {

	//注入UserDao
	private UserDao userDao;
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	//按名字查询用户的方法
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}


	public void save(User user) {
		//将数据存入数据库
		user.setState(0);//0:代表用户没激活	1：代表用户已激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);	
		// 发送激活邮件;
		MailUtils.sendMail(user.getEmail(), code);
	}


	//业务根据激活码查询用户
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}


	//修改用户状态的方法
	public void update(User existUser) {
		userDao.update(existUser);
		
	}


	//用户登录的方法
	public User login(User user) {		
		return userDao.login(user);
	}
}
