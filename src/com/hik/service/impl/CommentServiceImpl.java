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
import com.hik.util.StringUtil;

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
		if(comment!=null){
			if(StringUtil.isNotEmpty(comment.getContent())){
				sb.append(" and content like ?");
				param.add("%"+comment.getContent()+"%");
			}
		}
		sb.append(" order by createTime desc");
		if(pageBean!=null){
			return baseDao.find(sb.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDao.find(sb.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public Long getCommentCount(Comment comment) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer(" select count(*) from Comment");
		if(comment!=null){
			if(StringUtil.isNotEmpty(comment.getContent())){
				sb.append(" and content like ?");
				param.add("%"+comment.getContent()+"%");
			}
		}
		return baseDao.count(sb.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void save(Comment comment) {
		baseDao.merge(comment);
	}

	@Override
	public Comment getCommentById(int commentId) {
		return baseDao.get(Comment.class, commentId);
	}

	@Override
	public void delete(Comment comment) {
		baseDao.delete(comment);
	}

}
