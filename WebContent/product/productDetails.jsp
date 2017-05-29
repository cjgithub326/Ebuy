<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function addShoppingCart(productId){
		if('${currentUser.userName}'==''){
			alert("请先登录，然后购物！");
		}else{
			$.post("shopping_addShoppingCartItem.action",{productId:productId},function(result){
				var result = eval('('+result+')'); //把json字符串对象转为json字符串 
				if(result.success){
					alert("已成功加入购物车！");
					location.reload(); //立即刷新页面 
				}
				
			});
		}
	}
	
	function goBuy(productId){
		if('${currentUser.userName}'==''){
			alert("请先登录，然后购物！");
		}else{
			window.location.href="shopping_buyShopping.action?productId="+productId;
		}
	}
	
</script>
</head>
<body>
<div id="product"  class="main">
	<h1>${productDetail.name}</h1>
	<div class="infos">
		<div class="thumb">
			<img class="img" src="${productDetail.proPic}" />
		</div>
		<div class="buy">
			<br/>
			<p>
				商城价：<span class="price">￥${productDetail.price}</span>
			</p>
			<p>库 存：${productDetail.stock}</p>
			<br/>
			<div class="button">
				<input type="button" name="button" value="" onclick="goBuy(${productDetail.id})" /><br/>
				<a href="javascript:addShoppingCart(${productDetail.id})">放入购物车</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="introduce">
		<h2>
			<strong>商品详情</strong>
		</h2>
		<div class="text">
			${productDetail.description}
		</div>
	</div>
</div>
</body>
</html>