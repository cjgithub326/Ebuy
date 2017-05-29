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
 * @date 2017��3��30������10:21:31
 *
 */
public interface CommentService {
	
	/**
	 * 
	 * @MethodName: getCommentList
	 * @Description: ��ҳ��ȡ��������
	 * @author jed
	 * @date 2017��3��30������10:28:54
	 * @param @param comment
	 * @param @param pageBean
	 * @param @return    
	 * @return List<Comment>    ��������
	 * @param comment
	 * @param pageBean
	 * @return
	 *
	 */
	public List<Comment> getCommentList(Comment comment,PageBean pageBean); 
	
	/**
	 * 
	 * @MethodName: getCommentCount
	 * @Description: ��ȡ��������
	 * @author jed
	 * @date 2017��3��30������10:29:25
	 * @param @param comment
	 * @param @return    
	 * @return long    ��������
	 * @param comment
	 * @return
	 *
	 */
	public Long getCommentCount(Comment comment);
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: ��������
	 * @author jed
	 * @date 2017��3��31������10:03:30
	 * @param @param comment
	 * @param @return    
	 * @return Long    ��������
	 * @param comment
	 * @return
	 *
	 */
	public void save(Comment comment);

}
