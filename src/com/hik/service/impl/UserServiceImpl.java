/**
 * 
 */
package com.hik.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hik.dao.BaseDAO;
import com.hik.entity.PageBean;
import com.hik.entity.User;
import com.hik.service.UserService;
import com.hik.util.StringUtil;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @author jed
 * @date 2017年3月21日下午9:45:34
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private BaseDAO<User> baseDao;


	@Override
	public boolean existUserwithByUserName(String userName) {
		String hql="select count(*) from User where userName = ?";
		long count = baseDao.count(hql, new Object[]{userName});
		if(count>0){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public void saveUser(User user) {
		baseDao.merge(user);
	}


	@Override
	public User login(User user) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer("from User u where u.userName=? and u.password=?");
		param.add(user.getUserName());
		param.add(user.getPassword());
		if(user.getStatus()==2){
			sb.append(" and u.status = 2");
		}
		return baseDao.get(sb.toString(), param);
	}


	@Override
	public List<User> findUserList(User user, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer hql = new StringBuffer(" from User ");
		if(user!=null){
			if(StringUtil.isNotEmpty(user.getUserName())){
				hql.append(" and userName like ? ");
				param.add("%"+user.getUserName()+"%");
			}
		}
		hql.append(" and status = 1 ");
		if(pageBean!=null){
			return baseDao.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}


	@Override
	public Long getUserCount(User user) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer hql = new StringBuffer(" select count(*) from User ");
		if(user!=null){
			if(StringUtil.isNotEmpty(user.getUserName())){
				hql.append(" and userName like ? ");
				param.add("%"+user.getUserName()+"%");
			}
		}
		hql.append(" and status = 1 ");
		return baseDao.count(hql.toString().replaceFirst("and", "where"), param);
	}


	@Override
	public void deleteUser(User user) {
		baseDao.delete(user);
	}

	@Override
	public User getUserById(int id) {
		return baseDao.get(User.class, id);
	}


	@Override
	public void updateUser(User user) {
		 baseDao.update(user);
	}

}
