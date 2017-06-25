/**
 * 
 */
package com.hik.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.hik.entity.Tag;
import com.hik.entity.PageBean;
import com.hik.service.TagService;
import com.hik.util.NavUtil;
import com.hik.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @ClassName: TagAction
 * @Description: TODO
 * @author jed
 * @date 2017年3月20日下午10:23:27
 *
 */
@Controller
public class TagAction extends ActionSupport{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	private int tagId;
	
	private Tag tag;
	
	private String navCode;
	
	private String mainPage;
	
	private String page;
	
	private String rows;
	
	private String ids;
	
	@Resource
	private TagService tagService;
	

	/**
	 * 
	 * @MethodName: list
	 * @Description: 分页查询标签信息
	 * @author jed
	 * @date 2017年6月25日下午5:04:39
	 * @param @return
	 * @param @throws Exception    
	 * @return String    返回类型
	 * @return
	 * @throws Exception
	 *
	 */
    public String list() throws Exception{
    	PageBean pageBean  = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
    	List<Tag> tagList = tagService.findTagList(tag, pageBean);
    	long total = tagService.getTagCount(tag);
    	JSONArray rows = JSONArray.fromObject(tagList);
    	JSONObject result = new JSONObject();
    	result.put("rows", rows);
    	result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }
    
   /**
    * 
    * @MethodName: save
    * @Description: 保存标签信息
    * @author jed
    * @date 2017年6月25日下午5:06:11
    * @param @return
    * @param @throws Exception    
    * @return String    返回类型
    * @return
    * @throws Exception
    *
    */
    public String save() throws Exception{
    	tagService.saveTag(tag);
    	JSONObject result = new JSONObject();
    	result.put("success", true);
    	ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }
    
    /**
     * 
     * @MethodName: delete
     * @Description: 删除标签信息
     * @author jed
     * @date 2017年6月25日下午5:06:35
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
    		Tag tag = tagService.getTagById(Integer.parseInt(idsStr[i]));
    		if(tag!=null){
    			tagService.delete(tag);
    		}
    	}
    	result.put("success", true);
    	ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
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
