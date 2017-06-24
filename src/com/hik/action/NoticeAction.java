/**
 * 
 */
package com.hik.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.JoinColumn;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.hik.entity.Notice;
import com.hik.entity.PageBean;
import com.hik.service.NoticeService;
import com.hik.util.NavUtil;
import com.hik.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import sun.org.mozilla.javascript.internal.IdScriptableObject;

/**
 * @ClassName: NoticeAction
 * @Description: TODO
 * @author jed
 * @date 2017年3月20日下午10:16:08
 *
 */
@Controller
public class NoticeAction extends ActionSupport{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	private int noticeId;
	
	private Notice notice;
	
	private String mainPage;
	
	private String navCode;
	
	private String page;
	
	private String rows;
	
	private String ids;
	
	@Resource
	private NoticeService noticeService;

    public String showNotice(){
    	notice = noticeService.getNoticeById(noticeId);
    	navCode = NavUtil.genNavCode("公告信息 ");
    	mainPage ="notice/noticeDetails.jsp";
		return SUCCESS;
    }

    /**
     * 
     * @MethodName: list
     * @Description: 分页查询公告信息
     * @author jed
     * @date 2017年6月24日下午8:49:00
     * @param @return    
     * @return String    返回类型
     * @return
     * @throws Exception 
     *
     */
    public String list() throws Exception{
    	PageBean pageBean  = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
    	List<Notice> noticeList = noticeService.findNoticeList(notice, pageBean);
    	long total = noticeService.getNoticeCount(notice);
    	JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
    	JSONArray rows = JSONArray.fromObject(noticeList, jsonConfig);
    	JSONObject result = new JSONObject();
    	result.put("rows", rows);
    	result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }
    
    /**
     * 
     * @MethodName: save
     * @Description: 保存公告信息
     * @author jed
     * @date 2017年6月24日下午9:03:44
     * @param @return
     * @param @throws Exception    
     * @return String    返回类型
     * @return
     * @throws Exception
     *
     */
    public String save() throws Exception{
    	if(notice.getId()==0){//新增公告内容
    		notice.setCreateTime(new Date());
    	}
    	noticeService.saveNotice(notice);
    	JSONObject result = new JSONObject();
    	result.put("success", true);
    	ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }
    
    /**
     * 
     * @MethodName: delete
     * @Description: 删除公告信息 
     * @author jed
     * @date 2017年6月24日下午11:23:23
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
    		Notice notice = noticeService.getNoticeById(Integer.parseInt(idsStr[i]));
    		if(notice!=null){
    			noticeService.delete(notice);
    		}
    	}
    	result.put("success", true);
    	ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }
    
	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}


	public String getMainPage() {
		return mainPage;
	}


	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}


	public String getNavCode() {
		return navCode;
	}


	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
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

	
}
