/**
 * 
 */
package com.hik.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.hik.entity.Notice;
import com.hik.service.NoticeService;
import com.hik.util.NavUtil;
import com.opensymphony.xwork2.ActionSupport;

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
	
	@Resource
	private NoticeService noticeService;

    public String showNotice(){
    	notice = noticeService.getNoticeById(noticeId);
    	navCode = NavUtil.genNavCode("公告信息 ");
    	mainPage ="notice/noticeDetails.jsp";
		return SUCCESS;
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

}
