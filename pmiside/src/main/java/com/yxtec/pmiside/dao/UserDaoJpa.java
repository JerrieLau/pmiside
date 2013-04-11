/**
 * 
 */
package com.yxtec.pmiside.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.yxtec.pmiside.mode.User;

/**
 * @author Jerrie
 * 
 */
@Repository("userDao")
public class UserDaoJpa implements IUserDao {

	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yxtec.pmisaddons.dao.IUserDao#findByUserID(long)
	 */
	@Override
	public User findByUserId(String userId) throws DataAccessException {
		return entityManager.find(User.class, userId);
	}

	@Override
	public User findUser(User user) {
		TypedQuery<User> query = entityManager.createQuery(
				"select u from User u where u.userId=:userId and u.password=:password", User.class);
		query.setParameter("userId", user.getUserId());
		query.setParameter("password", user.getPassword());
		try {
			User pUser = query.getSingleResult();
			return pUser;
		} catch (NoResultException e) {
			LoggerFactory.getLogger(UserDaoJpa.class).trace("用户名或密码错误，登录失败！");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yxtec.pmisaddons.dao.IUserDao#finadAllUsers()
	 */
	@Override
	public List<User> finadAllUsers() {
		TypedQuery<User> query = entityManager.createQuery("from " + User.class.getName(), User.class);
		List<User> users = query.getResultList();
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxtec.pmisaddons.dao.IUserDao#update(com.yxtec.pmisaddons.mode.User)
	 */
	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxtec.pmisaddons.dao.IUserDao#delete(com.yxtec.pmisaddons.mode.User)
	 */
	@Override
	public void delete(User user) {
		User needDeleteUser = entityManager.merge(user);
		entityManager.remove(needDeleteUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxtec.pmisaddons.dao.IUserDao#save(com.yxtec.pmisaddons.mode.User)
	 */
	@Override
	public void save(User user) {
		entityManager.persist(user);
	}

	@PersistenceContext(unitName="pmisaddonsPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
