<%@ page import="com.badboyjy.bean.EBookEntry" %>
<%@ page import="com.badboyjy.bean.EBookCategory" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/10/22
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>更新电子书</h1>



<form action="/entry?method=update" method="post">
    <input type="hidden" name="method" value="update">
    <input type="hidden" name="id" value="${entry.id}">
    <p>图书编号：${entry.id}</p>
    <p>图书名称：<input type="text" name="bookname" value="${entry.title}"></p>
    <p>
        分类：<select name="booktype">
        <c:forEach items="${types}" var="type">
            <option value="${type.id}" ${type.id==entry.categoryId?selected:""}>${type.name}</option>
        </c:forEach>
    </select>
    </p>
    <p>图书摘要:<textarea  rows="5" cols="50" name="content">${entry.summary}</textarea> </p>
    <p>上传人:<input type="text" name="username" value="${entry.uploadUser}"> </p>
    <p>上传时间:<input type="date" name="time" value="${entry.creatDate}"> </p>
    <input type="submit" value="更新">
</form>
</body>
</html>
