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
 * @date 2017年3月21日下午10:02:13
 *
 */
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 序列号
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
	 * @Description: 注册用户
	 * @author jed
	 * @date 2017年3月21日下午10:05:56
	 * @param @return    
	 * @return String    返回类型
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
	 * @Description: 是否已经存在用户
	 * @author jed
	 * @date 2017年3月21日下午10:08:46
	 * @param @return    
	 * @return String    返回类型
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
	 * @Description: 用户登录
	 * @author jed
	 * @date 2017年3月27日下午10:56:40
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String login(){
		HttpSession session = request.getSession();
		User currentUser = userService.login(user);
		if(!imageCode.equals(session.getAttribute("sRand"))){
			error="验证码错误!";
			if(user.getStatus()==2){
				return "adminError";
			}else{
				return ERROR;
			}
		}else if(currentUser==null){
			error="用户名或密码错误！";
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
	 * @Description: 注销用户
	 * @author jed
	 * @date 2017年3月31日下午9:48:02
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String logout(){
		request.getSession().invalidate(); //session失效
		return "logout";
	}
	
	/**
	 * 
	 * @MethodName: logout2
	 * @Description: 退出系统
	 * @author jed
	 * @date 2017年6月25日下午10:33:58
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String logout2(){
		request.getSession().invalidate(); //session失效
		return "logout2";
	}
	
	/**
	 * 
	 * @MethodName: userCenter
	 * @Description: 个人中心
	 * @author jed
	 * @date 2017年4月26日下午10:17:08
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String userCenter(){
		navCode=NavUtil.genNavCode("个人中心");
		mainPage="userCenter/ucDefault.jsp";
		return "userCenter";
	}
	
	/**
	 * 
	 * @MethodName: getUserInfo
	 * @Description: 获取用户信息
	 * @author jed
	 * @date 2017年4月26日下午10:37:29
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String getUserInfo(){
		navCode=NavUtil.genNavCode("个人中心");
		mainPage="userCenter/userInfo.jsp";
		return "userCenter";
	}
	
	/**
	 * 
	 * @MethodName: preSave
	 * @Description: 预保存用户信息(查询显示的用户信息)
	 * @author jed
	 * @date 2017年4月26日下午10:42:03
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String preSave(){
		HttpSession session = request.getSession();
		user= (User) session.getAttribute("currentUser");
		navCode=NavUtil.genNavCode("个人中心");
		mainPage="userCenter/userSave.jsp";
		return "userCenter";
	}
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: 修改用户信息,修改之后返回到查询用户信息页面
	 * @author jed
	 * @date 2017年4月26日下午10:52:48
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String save() {
		HttpSession session = request.getSession();
		userService.saveUser(user);
		session.setAttribute("currentUser", user); //修改用户信息，同时要更新session中用户信息
		navCode=NavUtil.genNavCode("个人中心");
		mainPage="userCenter/userInfo.jsp";
		return "userCenter";
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: 获取用户
	 * @author jed
	 * @date 2017年5月1日上午12:22:06
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String list() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<User> userList = userService.findUserList(s_user, pageBean);
		long total = userService.getUserCount(s_user);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderList"}); //去除要排除的属性   前台不展示
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,  new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rows = JSONArray.fromObject(userList, jsonConfig); //把list转为json数据
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: deleteUser
	 * @Description: 删除用户
	 * @author jed
	 * @date 2017年5月1日下午2:28:43
	 * @param @return    
	 * @return String    返回类型
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
	 * @Description: 保存用户信息（添加或更新）
	 * @author jed
	 * @date 2017年5月2日下午8:54:13
	 * @param @return    
	 * @return String    返回类型
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
	 * @Description: 修改密码
	 * @author jed
	 * @date 2017年6月25日下午10:23:02
	 * @param @return    
	 * @return String    返回类型
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
