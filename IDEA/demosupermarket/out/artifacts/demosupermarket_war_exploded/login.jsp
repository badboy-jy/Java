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
<title>系统登录 - 超市账单管理系统</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
	  <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	  <script type="text/javascript">
		  $(function(){
			  var rs1=false,rs2=false;
			  $("[name='userName']").blur(function(){
				  var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
				  var uname=$("[name='userName']").val();
				  if(uname==""){
					  $("[name='userName']").next().html("请输入用户名");
					  rs1=false;
				  }else{
					  if (reg.test(uname)) {
						  $.ajax({
							  url:"/In?method=checkname",
							  data:"userName="+uname,
							  type:"post",
							  dataType:"text",
							  success:function (rs) {

								  if (rs==""){
									  $("[name='userName']").next().html(rs);
									  rs1 = true;
								  } else {
									  $("[name='userName']").next().html(rs);
									  rs1 = false;
								  }
							  }
						  });


					  } else {
						  $("[name='userName']").next().html("只能中英文，数字，下划线，减号(3-9)位");
						  rs1=false;
					  }
				  }
			  });
			  $("[name='password']").blur(function(){
				  var num=$("[name='password']").val();
				  var reg=/\w{3,9}/;
				  if(num==""){
					  $("[name='password']").next().html("请输入密码");
					  rs2=false;
				  }else{
					  if (reg.test(num)) {
						  $("[name='password']").next().html("");
						  rs2 = true;
					  } else {
						  $("[name='password']").next().html("只能中英文，数字，下划线(3-9)位");
						  rs2=false;
					  }
				  }
			  });
			  $("#form").submit(function(){
				  //验证重复工作号
				  //验证备注
				  if(rs1==true&&rs2==true){
					  return true;
				  }else{
					  alert("表单有误,请检查数据");
					  return false;
				  }
			  });
		  })
	  </script>


</head>
<body class="blue-style">
<div id="login">
	<div class="icon"></div>
	<div class="login-box">
		<span  id="span"></span>
		<form method="post" action="/In" id="form">
			<input type="hidden" name="method" value="login">
			<dl>
				<dt>用户名：</dt>
				<dd><input type="text" name="userName" class="input-text" id="userName"/><font color="red"></font></dd>
				<dt>密　码：</dt>
				<dd><input type="password" name="password" class="input-text" id="password"/><font color="red"></font></dd>
			</dl>
			<div class="buttons">
				<input type="submit" name="submit"  value="登录" class="input-button" />
				<input type="reset" name="reset"  value="重　　填" class="input-button" />
			</div>
		</form>
	</div>
</div>
</body>
</html>
