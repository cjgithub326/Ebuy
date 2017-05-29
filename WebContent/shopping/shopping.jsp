<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script> 
	$(function(){
		 //添加按钮 增加购买数量 
		$(".add").click(function(){
			var t = $(this).parent().find('input[class=text_box]');//当前节点父节点下的类为text_box的input  
			t.val(parseInt(t.val())+1);//数量加1;
			var productId=$(this).parent().find('input[id=product_id]').val();//获取productId的值
			var price =$('#price_'+productId).html();  //获取商品单价 
			$('#productItem_total_'+productId).html(price*t.val()); //计算商品总价 
			setTotal();//计算商品金额总计  
			refreshSession(productId,t.val());//刷新session 
		});
		
		//减少按钮 减少购买数量
		$('.min').click(function(){
			var t = $(this).parent().find('input[class=text_box]');//当前节点父节点下的类为text_box的input  
			t.val(parseInt(t.val())-1);//数量减1;
			if(parseInt(t.val())<0){ //不能为负值  
				t.val(0);
			}
			var productId =$(this).parent().find('input[id=product_id]').val();//获取productId的值
			var price =$('#price_'+productId).html();  //获取商品单价 
			$('#productItem_total_'+productId).html(price*t.val()); //计算商品总价 
			setTotal();//计算商品金额总计  
			refreshSession(productId,t.val());//刷新session 
		});
		
		//元素失去焦点触发  输入购买数量 
		$('.text_box').blur(function(){
			var t = $(this).parent().find('input[class=text_box]');//当前节点父节点下的类为text_box的input  
			if(parseInt(t.val())<0){
				t.val(0);
			}
			var productId =$(this).parent().find('input[id=product_id]').val();//获取productId的值
			var price =$('#price_'+productId).html();  //获取商品单价 
			$('#productItem_total_'+productId).html(price*t.val()); //计算商品总价 
			setTotal();//计算商品金额总计  
			refreshSession(productId,t.val());//刷新session 
		});
		
		//刷新session 
		function refreshSession(productId,count){
			$.post("shopping_updateShoppingCartItem.action",{productId:productId,count:count},
					function(result){
						var result = eval('('+result+')');
						if(result.success){
							
						}else{
							alert("刷新Session失败"); //把json字符串对象转为json字符串
						}
			});
		} 
		
		function setTotal(){
			var s=0; //用于统计商品总额 
			//遍历每一行 
			$('.productTr').each(function(){
				var n = $(this).find('input[class=text_box]').val();
				var price = $(this).find('label[class=price_]').html();
				s+=n*price;
			});
			$('#product_total').html(s);	
		}
		
		//计算商品金额总数 
		setTotal(); 
		
	});
	
	function removeShopping(productId){
		if(confirm("您确定要删除这个商品吗？")){
			window.location.href="shopping_removeShoppingCartItem.action?productId="+productId;
		}
	}
	
	
</script> 
</head>
<body>
<div id="shopping">
		<form action="order_save.action" method="post">
			<table id="myTableProduct">
				<tr>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>金额</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${shoppingCart.shoppingCartItems}" var="shoppingCartItem">
				<tr class="productTr">
					<td class="thumb">
						<img class="imgs" src="${shoppingCartItem.product.proPic }" /><a href="product_showProduct.action?productId=${shoppingCartItem.product.id}">${shoppingCartItem.product.name }</a>
					</td>
					<td class="price" ><span>￥<label class="price_" id="price_${shoppingCartItem.product.id}" >${shoppingCartItem.product.price}</label></span> 
					</td>
					<td class="price" >
					        <!-- id="productItem_total_${shoppingCartItem.product.id }" 这样好处就是标识id唯一 -->
						<span>￥<label id="productItem_total_${shoppingCartItem.product.id }">${shoppingCartItem.product.price*shoppingCartItem.count}</label></span>
					</td>
					<td class="number">
					        <input type="hidden" id="product_id" value="${shoppingCartItem.product.id }"/>
							<input class="min" name="" type="button" value=" - "  /> 
							<input class="text_box"  style="width: 30px;text-align: center" name="" type="text" value="${shoppingCartItem.count}" /> 
							<input class="add" name="" type="button" value=" + " /> 
					</td>
					<td class="delete"><a
						href="javascript:removeShopping(${shoppingCartItem.product.id})">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>

			<div class="button">
				<input type="submit" value="" />
			</div>
		</form>
</div>

<div class="shopping_list_end">

	<ul>
		<li class="shopping_list_end_2">￥<label id="product_total"></label>
		</li>
		<li class="shopping_list_end_3">商品金额总计：</li>
	</ul>
</div>

<div style="background-color: #cddbb8;margin-top: 10px;font-size: 20px;height: 40px;text-align: center">
	<div style="padding: 5px;">
		<b>个人信息</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>收件人：</b>${currentUser.trueName }&nbsp;&nbsp;<b>收获地址：</b>${currentUser.address }&nbsp;&nbsp;<b>联系电话：</b>${currentUser.mobile }
	</div>
</div>
</body>
</html>