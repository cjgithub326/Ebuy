/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.Comment;
import com.hik.entity.PageBean;

/**
 * @ClassName: CommentService
 * @Description: TODO
 * @author jed
 * @date 2017年3月30日下午10:21:31
 *
 */
public interface CommentService {
	
	/**
	 * 
	 * @MethodName: getCommentList
	 * @Description: 分页获取所有留言
	 * @author jed
	 * @date 2017年3月30日下午10:28:54
	 * @param @param comment
	 * @param @param pageBean
	 * @param @return    
	 * @return List<Comment>    返回类型
	 * @param comment
	 * @param pageBean
	 * @return
	 *
	 */
	public List<Comment> getCommentList(Comment comment,PageBean pageBean); 
	
	/**
	 * 
	 * @MethodName: getCommentCount
	 * @Description: 获取留言数量
	 * @author jed
	 * @date 2017年3月30日下午10:29:25
	 * @param @param comment
	 * @param @return    
	 * @return long    返回类型
	 * @param comment
	 * @return
	 *
	 */
	public Long getCommentCount(Comment comment);
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: 保存留言
	 * @author jed
	 * @date 2017年3月31日下午10:03:30
	 * @param @param comment
	 * @param @return    
	 * @return Long    返回类型
	 * @param comment
	 * @return
	 *
	 */
	public void save(Comment comment);

}
