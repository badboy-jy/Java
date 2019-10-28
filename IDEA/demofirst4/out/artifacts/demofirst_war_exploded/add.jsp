<%@ page import="com.badboyjy.bean.EBookCategory" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/10/22
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>增加电子书</h1>
<form action="/entry?method=insertbook" method="post">
    <p>图书名称：<input type="text" name="bookname"></p>
    <p>
        分类：<select name="booktype">
        <c:forEach items="${types}" var="type">
            <option value="${type.id}">${type.name}</option>
        </c:forEach>

    </select>
    </p>
    <p>图书摘要:<textarea  rows="5" cols="50" name="content"></textarea> </p>
    <p>上传人:<input type="text" name="username"> </p>
    <p>上传时间:<input type="date" name="time"> </p>
    <input type="submit" value="提交">
</form>
</body>
</html>
