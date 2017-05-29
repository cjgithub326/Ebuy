/**
 * 
 */
package com.hik.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.hik.entity.Comment;
import com.hik.entity.PageBean;
import com.hik.service.CommentService;
import com.hik.util.NavUtil;
import com.hik.util.PageUtil;
import com.hik.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: CommentAction
 * @Description: TODO
 * @author jed
 * @date 2017年3月30日下午10:59:18
 *
 */
@Controller
public class CommentAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private String page;
	
	private String pageCode;
	
	private Long total;
	
	private List<Comment> commentList;
	
	private Comment comment;
	
	private Comment commentSave;
	
	@Resource
	private CommentService commentService;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 
	 * @MethodName: list
	 * @Description: 分页获取留言数据
	 * @author jed
	 * @date 2017年3月30日下午11:07:11
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String list(){
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 3);
		commentList = commentService.getCommentList(comment, pageBean);
		total = commentService.getCommentCount(comment);
		pageCode = PageUtil.genPaginationNoParam(request.getContextPath()+"/comment_list.action", total, Integer.parseInt(page), 3);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: 保存留言
	 * @author jed
	 * @date 2017年3月31日下午10:10:02
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String save(){
		if(commentSave.getCreateTime()==null){
			commentSave.setCreateTime(new Date());
		}
		commentService.save(commentSave);
		return "save";
	}
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}


	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Comment getCommentSave() {
		return commentSave;
	}

	public void setCommentSave(Comment commentSave) {
		this.commentSave = commentSave;
	}
	
	
}
