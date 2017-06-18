/**
 * 
 */
package com.hik.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.hik.entity.Comment;
import com.hik.entity.PageBean;
import com.hik.service.CommentService;
import com.hik.util.NavUtil;
import com.hik.util.PageUtil;
import com.hik.util.ResponseUtil;
import com.hik.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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
	
	private String rows;
	
	private String pageCode;
	
	private Long total;
	
	private List<Comment> commentList;
	
	private Comment comment;
	
	private Comment commentSave;
	
	private int commentId;
	
	private String ids;
	
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
	
	/**
	 * 
	 * @MethodName: listComment
	 * @Description: 分页获取留言
	 * @author jed
	 * @date 2017年6月4日下午10:14:12
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String listComment() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<Comment> commentList = commentService.getCommentList(comment, pageBean);
		long total  = commentService.getCommentCount(comment);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rows = JSONArray.fromObject(commentList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: loadCommentById
	 * @Description: 根据id获取留言
	 * @author jed
	 * @date 2017年6月18日下午2:54:26
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String loadCommentById() throws Exception{
		Comment comment = commentService.getCommentById(commentId);
	    JsonConfig jsonConfig = new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
	    JSONObject result = JSONObject.fromObject(comment, jsonConfig);
	    ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: replay
	 * @Description: 回复留言
	 * @author jed
	 * @date 2017年6月18日下午3:03:53
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String replay() throws Exception{
		comment.setReplyTime(new Date()); //设置回复日期
		commentService.save(comment);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: 删除留言
	 * @author jed
	 * @date 2017年6月18日下午3:12:32
	 * @param @return
	 * @param @throws Exception    
	 * @return String    返回类型
	 * @return
	 * @throws Exception
	 *
	 */
	public String delete() throws Exception{
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Comment comment = commentService.getCommentById(Integer.parseInt(idsStr[i]));
			commentService.delete(comment);
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
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

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	
}
