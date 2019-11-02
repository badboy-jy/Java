<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/11/1
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房产信息查询系统</title>
</head>

<body>
<title>房产信息查询系统</title>
<script type="text/javascript">
    function goto() {
        location.href="/user?method=tuichu"
    }
</script>
<style type="text/css">
    .div1{float: left}
    .div2{float: left}
</style>
</head>
<h1>房产信息查询系统</h1>
<p>用户名： ${usernow.name}    <input type="button" value="退出" onclick="goto() " ></p>
<div class="div1"><a href="/query" target="right">房产信息查询</a></div>
<div class="div2">
<iframe src="/right.jsp" name="right" width="1000" height="400" frameborder="no" ></iframe></div>
</body>
</html>
