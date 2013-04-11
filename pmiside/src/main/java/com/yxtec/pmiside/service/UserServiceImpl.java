/**
 * 
 */
package com.yxtec.pmiside.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yxtec.pmiside.dao.IUserDao;
import com.yxtec.pmiside.mode.User;

/**
 * @author 杰
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao dao;
	
	@Override
	public User findByUserId(String userid) {
		return dao.findByUserId(userid);
	}

	@Override
	public User findUser(User user) {
		return dao.findUser(user);
	}
	
	@Override
	public List<User> finadAllUsers() {
		return dao.finadAllUsers();
	}

	@Override
	@Transactional
	public void update(User user) {
		dao.update(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		dao.delete(user);
	}

	@Override
	@Transactional
	public void save(User user) {
		dao.save(user);
	}
}
