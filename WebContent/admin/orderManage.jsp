<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

 //查询订单 
 function searchOrder(){
	 $("#dg").datagrid('load',{
		 "order.orderNo":$("#orderNo").val().trim(),
		 "order.user.userName":$("#userName").val().trim()
	 });
 }
 
  function formatUserId(val,row){
	 return row.user.id;
 } 
 
  function formatUserName(val,row){
	 return row.user.userName;
 } 
 
  function formatStatus(val,row){
	 if(val==1){
		 return "待审核";
	 }else if(val==2){
		 return "审核通过";
	 }else if(val==3){
		 return "卖家已发货";
	 }else if(val==4){
		 return "交易已完成";
	 }
	 return "";
 } 
</script>
</head>
<body style="margin: 1px;">
	<table id="dg" class="easyui-datagrid" title="订单管理" fitColumns="true" url="order_list.action" pagination="true" rownumbers="true" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="id" width="50" align="center">编号</th>
				<th field="orderNo" width="100" align="center">订单号</th>
				<th field="user.id" width="50" align="center" formatter="formatUserId">订单人ID</th>
				<th field="user.userName" width="100" align="center" formatter="formatUserName">订单人用户名</th>
				<th field="createTime" width="100" align="center">创建时间</th>
				<th field="cost" width="50" align="center">总金额</th>
				<th field="status" width="100" align="center" formatter="formatStatus">订单状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javaScript:openOrderAddDialog()" class="easyui-linkbutton" iconCls="icon-detail" plain="true">查看订单详情</a>
			<a href="javaScript:openOrderModifyDialog()" class="easyui-linkbutton" iconCls="icon-shenhe" plain="true">审核通过</a>
			<a href="javaScript:deleteOrder()" class="easyui-linkbutton" iconCls="icon-fahuo" plain="true">卖家已发货</a>
		</div>
		<div>
			&nbsp;订单号：&nbsp;<input type="text" id="orderNo" size="20"/>
			&nbsp;订单人：&nbsp;<input type="text" id="userName" size="20"/>
			<a href="javaScript:searchOrder()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
</body>
</html>