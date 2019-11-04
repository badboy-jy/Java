<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/11/3
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
	<script type="text/javascript" src="js/jquery.js"></script>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<script type="text/javascript">

		$(function(){
			var rs1=true,rs2=true,rs3=true,rs4=true,rs5=true,rs6=true;
			$("[name='goodsName']").blur(function(){
				var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
				var goodsName=$("[name='goodsName']").val();
				if(goodsName==""){
					$("[name='goodsName']").next().html("请输入商品名称");
					rs1=false;
				}else{
					if (reg.test(goodsName)) {
						$("[name='goodsName']").next().html("");
						rs1 = true;
					} else {
						$("[name='goodsName']").next().html("只能中英文，数字，下划线，减号(3-9)位");
						rs1=false;
					}
				}
			});


			$("[name='goodsCount']").blur(function(){
				var reg=/[0-9]{1,5}/;
				var goodsCount=$("[name='goodsCount']").val();
				if(goodsCount==""){
					$("[name='goodsCount']").next().html("请输入商品数量");
					rs3=false;
				}else{
					if (reg.test(goodsCount)) {
						$("[name='goodsCount']").next().html("");
						rs3 = true;
					} else {
						$("[name='goodsCount']").next().html("数字(1-5)位");
						rs3=false;
					}
				}
			});



			$("[name='money']").blur(function(){
				var money=$("[name='money']").val();
				var reg=/[0-9]{1,9}/;
				if(money==""){
					$("[name='money']").next().html("请输入交易金额");
					rs2=false;
				}else{
					if (reg.test(money)) {
						$("[name='money']").next().html("");
						rs2 = true;
					} else {
						$("[name='money']").next().html("数字(1-9)位");
						rs2=false;
					}
				}
			});
			$("[name='supplier_id']").blur(function(){
				var supplier_id=$("[name='supplier_id']").val();
				var reg=/[0-9]{0,3}/;
				if(supplier_id==""){
					$("[name='supplier_id']").next().html("请输入供应商ID");
					rs4=false;
				}else{
					if (reg.test(supplier_id)) {
						$.ajax({
							url:"/bill?method=checksupplierId",
							data:"supplier_id="+supplier_id,
							type:"post",
							dataType:"text",
							success:function (rs) {

								if (rs==""){
									$("[name='supplier_id']").next().html(rs);
									rs4 = true;
								} else {
									$("[name='supplier_id']").next().html(rs);
									rs4 = false;
								}
							}
						});
					} else {
						$("[name='supplier_id']").next().html("数字(1-3)位");
						rs4=false;
					}
				}
			});
			$("[name='goodsDescription']").blur(function(){
				var goodsDescription=$("[name='supplierChuanZhen']").val();
				var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,39}/;
				if(goodsDescription==""){
					$("[name='goodsDescription']").next().html("请输入商品描述");
					rs5=false;
				}else{
					if (reg.test(goodsDescription)) {
						$("[name='goodsDescription']").next().html("");
						rs5 = true;
					} else {
						$("[name='goodsDescription']").next().html("只能中英文，数字，下划线，减号(3-39)位");
						rs5=false;
					}
				}
			});
			$("[name='time']").blur(function(){
				var time=$("[name='time']").val();
				if(time==""){
					$("[name='time']").next().html("请输入账单时间");
					rs6=false;
				}else{
					$("[name='time']").next().html("");
					rs6=true;
				}
			});


			$("#form").submit(function(){
				//验证重复工作号
				//验证备注
				if(rs1==true&&rs2==true&&rs3==true&&rs4==true&&rs5==true&&rs6==true){
					return true;
				}else{
					alert("表单有误,请检查数据");
					return false;
				}
			});
		})
		function goto() {
			location.href="/bill?method=selectallbill";
		}
	</script>
</head>
<body>
<div class="main">
	<div class="optitle clearfix">
		<div class="title">账单管理&gt;&gt;</div>
	</div>
	<span  id="span"></span>
	<form id="form" name="form" method="post" action="/bill">
		<input name="method" value="updatebill" type="hidden">
		<input type="hidden" name="bid" value="${bid}">
		<div class="content">
			<table class="box">
				<tbody>
				<tr>
					<td class="field">商品名称：</td>
					<td><input id="goodsName" name="goodsName" class="text" type="text" value="${bill.goodsName}"> <font color="red"></font></td>

				</tr>
				<tr>
					<td class="field">商品数量：</td>
					<td><input id="goodsCount" name="goodsCount" cols="45" rows="5" value="${bill.goodsCount}"></input><font color="red"></font></td>
				</tr>
				<tr>
					<td class="field">交易金额：</td>
					<td><input name="money" id="money" class="text" type="text" value="${bill.money}"><font color="red"></font></td>
				</tr>
				<tr>
					<td class="field">是否付款：</td>
					<td><select name="isPay">
						<option value="1" ${bill.pay eq "1"?"selected":""}>是</option>
						<option value="0" ${bill.pay eq "0"?"selected":""}>否</option>
					</select></td>
				</tr>
				<tr>
					<td class="field">供应商：</td>
					<td><input name="supplier_id" id="supplier_id" class="text" type="text" value="${bill.supplierId}"><font color="red"></font></td>
				</tr>
				<tr>
					<td class="field">商品描述：</td>
					<td><textarea name="goodsDescription" id="goodsDescription" class="text" type="text"> ${bill.goodsDescription}</textarea><font color="red"></font></td>
				</tr>
				<tr>
					<td class="field">账单时间：</td>
					<td><input name="time" id="time" class="text" type="Date" value="${bill.time}"><font color="red"></font></td>
				</tr>
				</tbody></table>
		</div>

		<div class="buttons">
			<input  value="提交" class="input-button" id="sub_btn" type="submit">
			<input type="button" class="input-button" onclick="goto()" value="返回">
		</div>
	</form>
</div>
</body>
</html>
