/**
 * 
 */
package com.hik.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.deploy.LoginConfig;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.hik.entity.Order;
import com.hik.entity.PageBean;
import com.hik.entity.User;
import com.hik.service.UserService;
import com.hik.util.NavUtil;
import com.hik.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import sun.rmi.log.LogOutputStream;

/**
 * @ClassName: UserAction
 * @Description: TODO
 * @author jed
 * @date 2017��3��21������10:02:13
 *
 */
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userService;
	
	private String userName;
	
	private User user;
	
	private String error;
	
	private String adminError;
	
	private HttpServletRequest request;
	
	private String imageCode;
	
	private String mainPage;
	
	private String navCode;
	
	private User s_user;
	
	private String page;
	
	private String rows;
	
	private String ids;
	
	/**
	 * 
	 * @MethodName: register
	 * @Description: ע���û�
	 * @author jed
	 * @date 2017��3��21������10:05:56
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String register(){
		userService.saveUser(user);
		return "register_success"; 
	}
	
	/**
	 * 
	 * @MethodName: existUserWithUserName
	 * @Description: �Ƿ��Ѿ������û�
	 * @author jed
	 * @date 2017��3��21������10:08:46
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String existUserWithUserName() throws Exception{
		boolean exist = userService.existUserwithByUserName(userName);
		JSONObject result = new JSONObject();
		if(exist){
			result.put("exist", true);
		}else{
			result.put("exist", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: Login
	 * @Description: �û���¼
	 * @author jed
	 * @date 2017��3��27������10:56:40
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String login(){
		HttpSession session = request.getSession();
		User currentUser = userService.login(user);
		if(!imageCode.equals(session.getAttribute("sRand"))){
			error="��֤�����!";
			if(user.getStatus()==2){
				return "adminError";
			}else{
				return ERROR;
			}
		}else if(currentUser==null){
			error="�û������������";
			if(user.getStatus()==2){
				return "adminError";
			}else{
				return ERROR;
			}
		}else{
			session.setAttribute("currentUser", currentUser);
		}
		if(user.getStatus()==2){
			return "adminLogin";
		}else{
			return "login";
		}
	}
	
	/**
	 * 
	 * @MethodName: Logout
	 * @Description: ע���û�
	 * @author jed
	 * @date 2017��3��31������9:48:02
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String logout(){
		request.getSession().invalidate(); //sessionʧЧ
		return "logout";
	}
	
	/**
	 * 
	 * @MethodName: logout2
	 * @Description: �˳�ϵͳ
	 * @author jed
	 * @date 2017��6��25������10:33:58
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String logout2(){
		request.getSession().invalidate(); //sessionʧЧ
		return "logout2";
	}
	
	/**
	 * 
	 * @MethodName: userCenter
	 * @Description: ��������
	 * @author jed
	 * @date 2017��4��26������10:17:08
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String userCenter(){
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/ucDefault.jsp";
		return "userCenter";
	}
	
	/**
	 * 
	 * @MethodName: getUserInfo
	 * @Description: ��ȡ�û���Ϣ
	 * @author jed
	 * @date 2017��4��26������10:37:29
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String getUserInfo(){
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/userInfo.jsp";
		return "userCenter";
	}
	
	/**
	 * 
	 * @MethodName: preSave
	 * @Description: Ԥ�����û���Ϣ(��ѯ��ʾ���û���Ϣ)
	 * @author jed
	 * @date 2017��4��26������10:42:03
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String preSave(){
		HttpSession session = request.getSession();
		user= (User) session.getAttribute("currentUser");
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/userSave.jsp";
		return "userCenter";
	}
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: �޸��û���Ϣ,�޸�֮�󷵻ص���ѯ�û���Ϣҳ��
	 * @author jed
	 * @date 2017��4��26������10:52:48
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String save() {
		HttpSession session = request.getSession();
		userService.saveUser(user);
		session.setAttribute("currentUser", user); //�޸��û���Ϣ��ͬʱҪ����session���û���Ϣ
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/userInfo.jsp";
		return "userCenter";
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: ��ȡ�û�
	 * @author jed
	 * @date 2017��5��1������12:22:06
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String list() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<User> userList = userService.findUserList(s_user, pageBean);
		long total = userService.getUserCount(s_user);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderList"}); //ȥ��Ҫ�ų�������   ǰ̨��չʾ
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,  new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rows = JSONArray.fromObject(userList, jsonConfig); //��listתΪjson����
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: deleteUser
	 * @Description: ɾ���û�
	 * @author jed
	 * @date 2017��5��1������2:28:43
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String deleteUser() throws Exception{
		String[] idsStr = ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			User u = userService.getUserById(Integer.parseInt(idsStr[i]));
			userService.deleteUser(u);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: saveUser
	 * @Description: �����û���Ϣ����ӻ���£�
	 * @author jed
	 * @date 2017��5��2������8:54:13
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String saveUser() throws Exception{
		userService.saveUser(user);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: modifyPassword
	 * @Description: �޸�����
	 * @author jed
	 * @date 2017��6��25������10:23:02
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String modifyPassword() throws Exception{
		User currentUser = userService.getUserById(user.getId());
		currentUser.setPassword(user.getPassword());
		userService.saveUser(currentUser);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request= request;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
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

	public String getAdminError() {
		return adminError;
	}

	public void setAdminError(String adminError) {
		this.adminError = adminError;
	}

	public User getS_user() {
		return s_user;
	}

	public void setS_user(User s_user) {
		this.s_user = s_user;
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
