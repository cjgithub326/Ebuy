/**
 * 
 */
package com.hik.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hik.dao.BaseDAO;
import com.hik.entity.Comment;
import com.hik.entity.PageBean;
import com.hik.service.CommentService;

/**
 * @ClassName: CommentServiceImpl
 * @Description: TODO
 * @author jed
 * @date 2017年3月30日下午10:22:01
 *
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{
	
	@Resource
	private BaseDAO<Comment> baseDao;

	@Override
	public List<Comment> getCommentList(Comment comment, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer(" from Comment");
		sb.append(" order by createTime desc");
		if(pageBean!=null){
			return baseDao.find(sb.toString(), param, pageBean);
		}else{
			return null;
		}
	}

	@Override
	public Long getCommentCount(Comment comment) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer(" select count(*) from Comment");
		return baseDao.count(sb.toString(), param);
	}

	@Override
	public void save(Comment comment) {
		baseDao.merge(comment);
	}

}
