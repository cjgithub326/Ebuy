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
 * @date 2017��3��21������9:42:11
 *
 */
public interface UserService {
	
	/**
	 * 
	 * @MethodName: saveUser
	 * @Description: �����û���Ϣ
	 * @author jed
	 * @date 2017��3��21������9:43:14
	 * @param @param user
	 * @param @return    
	 * @return User    ��������
	 * @param user
	 * @return
	 *
	 */
	public void saveUser(User user);
	
	/**
	 * 
	 * @MethodName: existUserwithByUserName
	 * @Description: �Ƿ��Ѵ��ڸ��û�
	 * @author jed
	 * @date 2017��3��21������9:44:30
	 * @param @return    
	 * @return boolean    ��������
	 * @return
	 *
	 */
	public boolean existUserwithByUserName(String userName); 
	
	/**
	 * 
	 * @MethodName: login
	 * @Description: �û���¼
	 * @author jed
	 * @date 2017��3��27������10:31:04
	 * @param @param user
	 * @param @return    
	 * @return User    ��������
	 * @param user
	 * @return
	 *
	 */
	public User login(User user);
	
	/**
	 * 
	 * @MethodName: findUserList
	 * @Description: ��ҳ��ѯ�û�
	 * @author jed
	 * @date 2017��4��30������11:57:30
	 * @param @param user
	 * @param @param pageBean
	 * @param @return    
	 * @return List<User>    ��������
	 * @param user
	 * @param pageBean
	 * @return
	 *
	 */
	public List<User> findUserList(User user,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getUserCount
	 * @Description: �û�������
	 * @author jed
	 * @date 2017��4��30������11:59:51
	 * @param @param user
	 * @param @return    
	 * @return Long    ��������
	 * @param user
	 * @return
	 *
	 */
	public Long getUserCount(User user);
	
	/**
	 * 
	 * @MethodName: deleteUser
	 * @Description: ɾ���û�
	 * @author jed
	 * @date 2017��5��1������11:01:39
	 * @param @param user    
	 * @return void    ��������
	 * @param user
	 *
	 */
	public void deleteUser(User user);
	
	/**
	 * 
	 * @MethodName: getUserById
	 * @Description: ���ݱ�Ų�ѯ�û�
	 * @author jed
	 * @date 2017��5��1������11:02:24
	 * @param @param id
	 * @param @return    
	 * @return User    ��������
	 * @param id
	 * @return
	 *
	 */
	public User getUserById(int id);
	
	/**
	 * 
	 * @MethodName: updateUser
	 * @Description: �����û���Ϣ
	 * @author jed
	 * @date 2017��5��1������3:08:21
	 * @param @param user
	 * @param @return    
	 * @return void    ��������
	 * @param user
	 * @return
	 *
	 */
	public void updateUser(User user);

}
