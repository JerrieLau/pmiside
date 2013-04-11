/**
 * 
 */
package com.yxtec.pmiside.service;

import java.util.List;

import com.yxtec.pmiside.mode.User;

/**
 * @author Jerrie
 * 
 */
public interface IUserService {

	public User findByUserId(String userid);
	
	public User findUser(User user);

	public List<User> finadAllUsers();

	public void update(User user);

	public void delete(User user);

	public void save(User user);

}
