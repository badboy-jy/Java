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
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>更新电子书</h1>
<%--接收参数--%>
<% EBookEntry eBookEntry = (EBookEntry) request.getAttribute("entry");%>
<form action="/entry?method=update" method="post">
    <input type="hidden" name="method" value="update">
    <input type="hidden" name="id" value="<%=eBookEntry.getId()%>">
    <p>图书编号：<%=eBookEntry.getId()%></p>
    <p>图书名称：<input type="text" name="bookname" value="<%=eBookEntry.getTitle()%>"></p>
    <p>
        分类：<select name="booktype">
        <%
            List<EBookCategory> categoryList=(List<EBookCategory>)   request.getAttribute("types");
            for(EBookCategory category:categoryList){
        %>
        <option  value="<%=category.getId()%>" <%if (category.getId()==eBookEntry.getCategoryId()){%>selected<%}%>><%=category.getName()%></option>
        <%}%>

    </select>
    </p>
    <p>图书摘要:<textarea  rows="5" cols="50" name="content"><%=eBookEntry.getSummary()%></textarea> </p>
    <p>上传人:<input type="text" name="username" value="<%=eBookEntry.getUploadUser()%>"> </p>
    <p>上传时间:<input type="text" name="time" value="<%=eBookEntry.getCreatDate()%>"> </p>
    <input type="submit" value="更新">
</form>
</body>
</html>
