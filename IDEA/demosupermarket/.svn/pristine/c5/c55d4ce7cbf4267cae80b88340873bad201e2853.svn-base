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
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<script type="text/javascript">
  function goto() {
    alert("抱歉，您的权限不够，不可以删除数据");
    location.href="/user?method=selectalluser";
  }
  function goto1() {
    alert("抱歉，您的权限不够，不可以修改他人数据");
    location.href="/user?method=selectalluser";
  }
</script>
    <style>
      a{
        text-decoration: none;
      }

    </style>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head><body>




<div class="menu">

<table>
<tbody><tr><td><form method="post" action="/user">
<input type="hidden" name="method" value="selectalluser">
用户名称：<input name="userName" class="input-text" type="text" value="${username}">&nbsp;&nbsp;&nbsp;&nbsp; <input value="查 询" type="submit">
</form></td></tr>
</tbody></table>
</div>
<div class="main">

<div class="optitle clearfix">
    <em><input value="添加数据" class="input-button" onclick="window.location='userAdd.jsp'" type="button"></em>
		<div class="title">用户管理&gt;&gt;</div>
	</div>
	<div class="content">
<table class="list">
  <tbody><tr>
    <td width="70" height="29"><div class="STYLE1" align="center">编号</div></td>
    <td width="100"><div class="STYLE1" align="center">用户名称</div></td>
    <td width="100"><div class="STYLE1" align="center">性别</div></td>
    <td width="100"><div class="STYLE1" align="center">年龄</div></td>

    <td width="150"><div class="STYLE1" align="center">电话 </div></td>
    <td width="150"><div class="STYLE1" align="center">地址 </div></td>
    <td width="80"><div class="STYLE1" align="center">权限 </div></td>
   <td width="150"><div class="STYLE1" align="center">操作 </div></td>
  </tr>

  <c:if test="${p1.totalCount!=0}">
  <c:forEach items="${p1.dataList}" var="user" varStatus="num">

  <tr>
    <td height="23"><span class="STYLE1">${num.count}</span></td>
    <td><span class="STYLE1">${user.userName}</span></td>

    <td><span class="STYLE1">
    	${user.userSex}
    </span></td>
    <td><span class="STYLE1">${user.userAge}</span></td>
    <td><span class="STYLE1">${user.userPhone}</span></td>
    <td><span class="STYLE1">${user.userAddress}</span></td>
    <td><span class="STYLE1">&nbsp;${user.userQuanXian}
    </span></td>
      <td>
        <c:if test="${quanxian==1}">
        <a href="/user?method=delete&uid=${user.userId}" >删除</a> <a href="/user?method=findbyid&uid=${user.userId}" target="mainFrame">修改</a>
        </c:if>
        <c:if test="${quanxian==0}">
          <span onclick="goto()">删除</span>
          <c:if test="${user.userId==usid}">
          <a href="/user?method=findbyid&uid=${user.userId}" target="mainFrame">修改</a>
          </c:if>
          <c:if test="${user.userId!=usid}">
            <span onclick="goto1()">修改</span>
          </c:if>

        </c:if>
      </td>

  </tr>
  </c:forEach>
    <%-- 分页的各种按钮链接--%>
    <tr>

      <td colspan="9" align="right">
        <a href="/user?method=selectalluser&index=1&&userName=${username}">首页</a>
        <a href="/user?method=selectalluser&index=${p1.pageIndex==1?1:p1.pageIndex-1}&&userName=${username}"><%="<<"%>上一页</a>
        |
        <a href="/user?method=selectalluser&index=${p1.pageIndex==p1.totalPages?p1.totalPages:p1.pageIndex+1}&&userName=${username}">下一页>></a>
        <a href="/user?method=selectalluser&index=${p1.totalPages}&&userName=${username}">尾页</a>
        第${p1.pageIndex}页/共${p1.totalPages}页  (${p1.totalCount}条)
      </td>
    </tr>
  </c:if>

</tbody></table>
      <c:if test="${p1.totalCount==0}">
        <h2>抱歉，没有数据！</h2>
      </c:if>
</div>
</div>
</body>
</html>