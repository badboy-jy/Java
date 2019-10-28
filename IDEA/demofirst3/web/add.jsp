<%@ page import="com.badboyjy.bean.EBookCategory" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/10/22
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <%
            List<EBookCategory> categoryList=(List<EBookCategory>)   request.getAttribute("types");
            //增强for循环
            //格式
            for(EBookCategory category:categoryList){
        %>
        <option value="<%=category.getId()%>" ><%=category.getName()%></option>
        <%}%>

    </select>
    </p>
    <p>图书摘要:<textarea  rows="5" cols="50" name="content"></textarea> </p>
    <p>上传人:<input type="text" name="username"> </p>
    <p>上传时间:<input type="date" name="time"> </p>
    <input type="submit" value="提交">
</form>
</body>
</html>
