/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.PageBean;
import com.hik.entity.User;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @author jed
 * @date 2017年3月21日下午9:42:11
 *
 */
public interface UserService {
	
	/**
	 * 
	 * @MethodName: saveUser
	 * @Description: 保存用户信息
	 * @author jed
	 * @date 2017年3月21日下午9:43:14
	 * @param @param user
	 * @param @return    
	 * @return User    返回类型
	 * @param user
	 * @return
	 *
	 */
	public void saveUser(User user);
	
	/**
	 * 
	 * @MethodName: existUserwithByUserName
	 * @Description: 是否已存在该用户
	 * @author jed
	 * @date 2017年3月21日下午9:44:30
	 * @param @return    
	 * @return boolean    返回类型
	 * @return
	 *
	 */
	public boolean existUserwithByUserName(String userName); 
	
	/**
	 * 
	 * @MethodName: login
	 * @Description: 用户登录
	 * @author jed
	 * @date 2017年3月27日下午10:31:04
	 * @param @param user
	 * @param @return    
	 * @return User    返回类型
	 * @param user
	 * @return
	 *
	 */
	public User login(User user);
	
	/**
	 * 
	 * @MethodName: findUserList
	 * @Description: 分页查询用户
	 * @author jed
	 * @date 2017年4月30日下午11:57:30
	 * @param @param user
	 * @param @param pageBean
	 * @param @return    
	 * @return List<User>    返回类型
	 * @param user
	 * @param pageBean
	 * @return
	 *
	 */
	public List<User> findUserList(User user,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getUserCount
	 * @Description: 用户总数量
	 * @author jed
	 * @date 2017年4月30日下午11:59:51
	 * @param @param user
	 * @param @return    
	 * @return Long    返回类型
	 * @param user
	 * @return
	 *
	 */
	public Long getUserCount(User user);
	
	/**
	 * 
	 * @MethodName: deleteUser
	 * @Description: 删除用户
	 * @author jed
	 * @date 2017年5月1日上午11:01:39
	 * @param @param user    
	 * @return void    返回类型
	 * @param user
	 *
	 */
	public void deleteUser(User user);
	
	/**
	 * 
	 * @MethodName: getUserById
	 * @Description: 根据编号查询用户
	 * @author jed
	 * @date 2017年5月1日上午11:02:24
	 * @param @param id
	 * @param @return    
	 * @return User    返回类型
	 * @param id
	 * @return
	 *
	 */
	public User getUserById(int id);
	
	/**
	 * 
	 * @MethodName: updateUser
	 * @Description: 更新用户信息
	 * @author jed
	 * @date 2017年5月1日下午3:08:21
	 * @param @param user
	 * @param @return    
	 * @return void    返回类型
	 * @param user
	 * @return
	 *
	 */
	public void updateUser(User user);

}
