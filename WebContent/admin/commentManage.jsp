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

   
 //查询留言 
 function searchComment(){
	 $("#dg").datagrid('load',{
		 "comment.content":$("#commentName").val().trim()
	 });
 }
 
 function formatReplyContent(val,row){
	 if(val==""){
		 return "<a href='javaScript:openCommentReplyDialog("+row.id+")'>回复</a>";
	 }else{
		 return val;
	 }
 }
 
</script>
</head>
<body style="margin: 1px;">
	<table id="dg" class="easyui-datagrid" title="留言管理" fitColumns="true" url="comment_listComment.action" pagination="true" rownumbers="true" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="content" width="200" align="center">留言内容</th>
				<th field="nickName" width="50" align="center">留言人昵称</th>
				<th field="createTime" width="50" align="center">创建时间</th>
				<th field="replyContent" width="200" formatter="formatReplyContent" align="center">回复内容</th>
				<th field="replyTime" width="50" align="center">回复时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javaScript:deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除留言</a>
		</div>
		<div>
			&nbsp;留言名称：&nbsp;<input type="text" id="commentName" size="20" onkeydown="if(event.keyCode==13) searchComment()"/>
			<a href="javaScript:searchComment()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
</body>
</html>