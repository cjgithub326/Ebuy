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
    var url;
    //document加载完后执行 
    $(function(){
    	$("#bName").combobox({ //二级联动 
    		onSelect :function(record){
    			$("#sName").combobox("reload","productSmallType_comboList.action?productSmallType.bigType.id="+record.id);
    			$("#sName").combobox("setValue",""); //值置空
    		}
    	});
    });
    

	function searchProduct(){
		$("#dg").datagrid('load',{
			"productDetail.name":$("#s_productName").val().trim()
		});
	}

	function formatProPic(val,row){
		return "<img width=100 height=100 src='${pageContext.request.contextPath}/"+val+"'>";
	}
	
	 function formatSmallTypeId(val,row){
		return row.smallType.id;
	}
	
	function formatSmallTypeName(val,row){
		return row.smallType.name;
	}
	
	function formatBigTypeId(val,row){
		return row.bigType.id;
	}
	
	function formatBigTypeName(val,row){
		return row.bigType.name;
	} 
	
	function formatHot(val,row){
		if(val==1){
			return "是";
		}else{
			return "否";
		}
	}
	
	function formatSpecialPrice(val,row){
		if(val==1){
			return "是";
		}else{
			return "否";
		}
	}
	
	//添加 
	function openProductAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加商品信息");
		url = "product_save.action";
	}
	
	//修改 
	function openProductModifyDialog(){
		var selectedRows = $("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0]; //选择一条数据 
		$("#dlg").dialog("open").dialog("setTitle","编辑商品信息"); //打开编辑对话框 
		$("#name").val(row.name);
		$("#price").val(row.price);
		$("#stock").val(row.stock);
		$("#proPic").val(row.proPic);
		$("#hot").val(row.hot);
		$("#hotTime").val(row.hotTime);
		$("#specialPrice").val(row.specialPrice);
		$("#specialPriceTime").val(row.specialPriceTime);
		$("#description").val(row.description);
		$("#bName").combobox("setValue",row.bigType.id);
		$("#sName").combobox("setValue",row.smallType.id);
		url="product_save.action?product.id="+row.id;
	}
	
	//保存 
	function saveProduct() {
		$("#fm").form("submit",{
			url:url,
		    onSubmit:function(){//表单提交前进行表单验证 
		    	if($("#bName").combobox("getValue")==""){
		    		$.messager.alert("系统提示","请选择商品大类");
		    		return false;
		    	}
		    	if($("#sName").combobox("getValue")==""){
		    		$.messager.alert("系统提示","请选择商品小类");
		    		return false;
		    	}
		    	return $(this).form("validate");
		    },
		    success:function(result){//提交请求后台返回结果 
		    	var result = eval('('+result+')');
		    	if(result.success){
		    		$.messager.alert("系统提示","保存成功");
		    		resetValue(); //清空表单数据
		    		$("#dlg").dialog("close");//关闭添加对话框 
		    		$("#dg").datagrid("reload");//刷新，重新加载数据 
		    	}else{
		    		$.messager.alert("系统提示","保存失败");
		    		return;
		    	}
		    }
		});
	}
	
	 function resetValue(){
		 $("#name").val("");
		 $("#price").val("");
		 $("#stock").val("");
		 $("#pP").val("");
		 $("#bName").combobox("setValue","");
		 $("#sName").combobox("setValue","");
		 $("#description").val("");
			
		 $("#id").val("");
		 $("#proPic").val("");
		 $("#hot").val("");
		 $("#hotTime").val("");
		 $("#specialPrice").val("");
		 $("#specialPriceTime").val("");
	 }
	 
	 //关闭 
	 function closeProductDialog(){
		 $("#dlg").dialog("close");
		 resetValue();
	 }
	 
	 //删除商品 
	 function deleteProduct(){
		 var selectedRows= $("#dg").datagrid("getSelections");
		 if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要删除的数据！");
			 return;
		 }
		 var strIds=[]; //定义数组 
		 for(var i=0;i<selectedRows.length;i++){ //把选中行数据的id存入数组 
			 strIds.push(selectedRows[i].id);
		 }
		 
		 var ids = strIds.join(",");
		 $.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？ ",function(r){
			 if(r){
				 $.post("product_delete.action",{ids:ids},function(result){
					 if(result.success){
						 $.messager.alert("系统提示","数据已成功删除！");
						 $("#dg").datagrid("reload"); //重新加载，刷新数据 
					 }else{
						 $.messager.alert("系统提示","数据删除失败！");
					 }
				 },"json");
			 }
		 });
	 }
	 
	 //设置热卖商品 
	 function setProductHot(){
		 var selectedRows = $("#dg").datagrid("getSelections");
		 if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要设置热卖的商品！");
			 return;
		 }
		 var strIds=[]; //定义数组
		 for(var i=0;i<selectedRows.length;i++){  //把选中行数据的id存入数组
			 strIds.push(selectedRows[i].id); 
		 }
		 var ids = strIds.join(",");
		 $.messager.confirm("系统提示","您确认要设置这<font color=red>"+selectedRows.length+"</font>个商品为热卖吗？",function(r){
			 if(r){
				 $.post("product_setProductWithHot.action",{ids:ids},function(result){
					 if(result.success){
						 $.messager.alert("系统提示","已设置成功！");
						 $("#dg").datagrid("reload"); //重新加载，刷新数据 
					 }else{
						 $.messager.alert("系统提示","设置失败！");
					 }
				 },"json");
			 }
		 });
	 }
	 
	 //设置特价商品 
	 function setProductSpecialPrice(){
		 var selectedRows = $("#dg").datagrid("getSelections");
		 if(selectedRows.length==0){
			 $.message.alert("系统提示","请选择要设置特价的商品！");
			 return;
		 }
		 var strIds = []; //定义数组
		 for(var i=0;i<selectedRows.length;i++){  //把选中行数据的id存入数组
			 strIds.push(selectedRows[i].id);
		 }
		 var ids = strIds.join(",");
		 $.messager.confirm("系统提示","您确认要设置这<font color=red>"+selectedRows.length+"</font>个商品为特价吗？",function(r){
			 if(r){
				 $.post("product_setProductWithSpecialPrice.action",{ids:ids},function(result){
					 if(result.success){
						 $.messager.alert("系统提示","已设置成功！");
						 $("#dg").datagrid("reload"); //重新加载，刷新数据 
					 }else{
						 $.messager.alert("系统提示","设置失败！");
					 }
				 },"json");
			 }
		 });
	 }
	
</script>
</head>
<body style="margin: 1px;">
	<table id="dg" class="easyui-datagrid" title="商品管理" fitColumns="true" url="product_list.action" pagination="true" rownumbers="true" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="id" width="50" align="center">编号</th>
				<th field="proPic" width="150" align="center" formatter="formatProPic">商品图片</th>
				<th field="name" width="100" align="center">商品名称</th>
				<th field="price" width="100" align="center">价格</th>
				<th field="stock" width="50" align="center">库存</th>
				<th field="smallType.id" width="100" align="center" formatter="formatSmallTypeId" hidden="true">所属商品小类id</th>
				<th field="smallType.name" width="100" align="center" formatter="formatSmallTypeName">所属商品小类</th>
				<th field="bigType.id" width="100" align="center" formatter="formatBigTypeId" hidden="true">所属商品大类id</th>
				<th field="bigType.name" width="100" align="center" formatter="formatBigTypeName">所属商品大类</th>
				<th field="hot" width="50" align="center" formatter="formatHot">是否热卖</th>
				<th field="specialPrice" width="50" align="center" formatter="formatSpecialPrice">是否特价</th>
				<th field="description" width="50" align="center" hidden="true">描述</th>
				<th field="hotTime" width="50" align="center" hidden="true">设置热卖时间</th>
				<th field="specialPriceTime" width="50" align="center" hidden="true">设置特价时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javaScript:openProductAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javaScript:openProductModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javaScript:deleteProduct()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			<a href="javaScript:setProductHot()" class="easyui-linkbutton" iconCls="icon-hot" plain="true">设置为热卖</a>
			<a href="javaScript:setProductSpecialPrice()" class="easyui-linkbutton" iconCls="icon-special" plain="true">设置为特价</a>
		</div>
		<div>
			&nbsp;商品名称：&nbsp;<input type="text" id="s_productName" size="20" onkeydown="if(event.keyCode==13) searchProduct()"/>
			<a href="javaScript:searchProduct()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 600px;height:450px;padding: 10px 20px " closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post" enctype="multipart/form-data">
			<table cellspacing="8px">
				<tr>
					<td>商品名称：</td>
					<td colspan="4"><input type="text" id="name" name="product.name" class="easyui-validatebox" required="true" style="width:300px"/></td>
				</tr>
				<tr>
					<td>商品价格：</td>
					<td colspan="4"><input type="text" id="price" name="product.price" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>库存：</td>
					<td colspan="4"><input type="text" id="stock" name="product.stock" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>商品图片：</td>
					<td colspan="4"><input type="file" id="pP" name="proPic"/></td>
				</tr>
				<tr>
					<td>所属大类：</td>
					<td colspan="4">
						<input class="easyui-combobox" id="bName" name="product.bigType.id" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productBigType_comboList.action'">
					</td>
				</tr>
				<tr>
					<td>所属小类：</td>
					<td colspan="4">
						<input class="easyui-combobox" id="sName" name="product.smallType.id" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productSmallType_comboList.action'">
					</td>
				</tr>
				<tr>
					<td valign="top">备注：</td>
					<td colspan="4">
						<textarea rows="5" cols="50" id="description" name="product.description"></textarea>
						<input type="hidden" id="proPic" name="product.proPic"/>
						<input type="hidden" id="hot" name="product.hot"/>
						<input type="hidden" id="hotTime" name="product.hotTime"/>
	 					<input type="hidden" id="specialPrice" name="product.specialPrice"/>
	 					<input type="hidden" id="specialPriceTime" name="product.specialPriceTime"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javaScript:saveProduct()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javaScript:closeProductDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>