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
			  var rs1=false,rs2=false,rs3=false,rs4=false,rs5=false,rs6=false;
			  $("[name='supplierName']").blur(function(){
				  var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
				  var suname=$("[name='supplierName']").val();
				  if(suname==""){
					  $("[name='supplierName']").next().html("请输入供应商名称");
					  rs1=false;
				  }else{
					  if (reg.test(suname)) {
						  $("[name='supplierName']").next().html("");
						  rs1 = true;
					  } else {
						  $("[name='supplierName']").next().html("只能中英文，数字，下划线，减号(3-9)位");
						  rs1=false;
					  }
				  }
			  });


			  $("[name='supplierDesc']").blur(function(){
				  var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,39}/;
				  var supplierDesc=$("[name='supplierDesc']").val();
				  if(supplierDesc==""){
					  $("[name='supplierDesc']").next().html("请输入供应商描述");
					  rs3=false;
				  }else{
					  if (reg.test(supplierDesc)) {
						  $("[name='supplierDesc']").next().html("");
						  rs3 = true;
					  } else {
						  $("[name='supplierDesc']").next().html("只能中英文，数字，下划线，减号(3-39)位");
						  rs3=false;
					  }
				  }
			  });



			  $("[name='supplierPerName']").blur(function(){
				  var supplierPerName=$("[name='supplierPerName']").val();
				  var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
				  if(supplierPerName==""){
					  $("[name='supplierPerName']").next().html("请输入联系人");
					  rs2=false;
				  }else{
					  if (reg.test(supplierPerName)) {
						  $("[name='supplierPerName']").next().html("");
						  rs2 = true;
					  } else {
						  $("[name='supplierPerName']").next().html("只能中英文，数字，下划线(3-9)位");
						  rs2=false;
					  }
				  }
			  });
			  $("[name='supplierPhone']").blur(function(){
				  var supplierPhone=$("[name='supplierPhone']").val();
				  var reg=/[0-9]{6,11}/;
				  if(supplierPhone==""){
					  $("[name='supplierPhone']").next().html("请输入供应商电话");
					  rs4=false;
				  }else{
					  if (reg.test(supplierPhone)) {
						  $("[name='supplierPhone']").next().html("");
						  rs4 = true;
					  } else {
						  $("[name='supplierPhone']").next().html("数字(6-11)位");
						  rs4=false;
					  }
				  }
			  });
			  $("[name='supplierChuanZhen']").blur(function(){
				  var supplierChuanZhen=$("[name='supplierChuanZhen']").val();
				  var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
				  if(supplierChuanZhen==""){
					  $("[name='supplierChuanZhen']").next().html("请输入传真");
					  rs5=false;
				  }else{
					  if (reg.test(supplierChuanZhen)) {
						  $("[name='supplierChuanZhen']").next().html("");
						  rs5 = true;
					  } else {
						  $("[name='supplierChuanZhen']").next().html("电话格式不对");
						  rs5=false;
					  }
				  }
			  });
			  $("[name='supplierAddress']").blur(function(){
				  var supplierAddress=$("[name='supplierAddress']").val();
				  var reg=/\w{3,9}/;
				  if(supplierAddress==""){
					  $("[name='supplierAddress']").next().html("请输入地址");
					  rs6=false;
				  }else{
					  if (reg.test(supplierAddress)) {
						  $("[name='supplierAddress']").next().html("");
						  rs6 = true;
					  } else {
						  $("[name='supplierAddress']").next().html("只能中英文，数字，下划线(3-9)位");
						  rs6=false;
					  }
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
			  location.href="/supplier?method=selectallsupplier";
		  }
	  </script>
</head>
<body>
<div class="main">
	<div class="optitle clearfix">
		<div class="title">供应商管理&gt;&gt;</div>
	</div>
	<span  id="span"></span>
	<form id="form" name="form" method="post" action="/supplier">
		<input name="method" value="addsupplier" type="hidden">
		<div class="content">
			<table class="box">
			<tbody>
				<tr>
					<td class="field">供应商名称：</td>
					<td><input id="supplierName" name="supplierName" class="text" type="text"> <font color="red"></font></td>

				</tr>
			<tr>
					<td class="field">供应商描述：</td>
					<td><textarea id="supplierDesc" name="supplierDesc" cols="45" rows="5"></textarea><font color="red"></font></td>
				</tr>
				<tr>
					<td class="field">供应商联系：</td>

					<td><input name="supplierPerName" id="supplierPerName" class="text" type="text"><font color="red"></font></td>
				</tr>
				<tr>
					<td class="field">供应商电话：</td>
					<td><input name="supplierPhone" id="supplierPhone" class="text" type="text"><font color="red"></font></td>
				</tr>
				<tr>
					<td class="field">供应商传真：</td>

					<td><input name="supplierChuanZhen" id="supplierChuanZhen" class="text" type="text"><font color="red"></font></td>
				</tr>
				<tr>
					<td class="field">供应商地址：</td>
					<td><input name="supplierAddress" id="supplierAddress" class="text" type="text"><font color="red"></font></td>
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
