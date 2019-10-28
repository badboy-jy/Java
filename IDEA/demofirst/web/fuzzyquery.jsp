<%@ page import="com.badboyjy.bean.EBookEntry" %>
<%@ page import="java.util.List" %>
<%@ page import="com.badboyjy.bean.EBookCategory" %><%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/10/22
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
    function goto(){
           var query = document.getElementById("text");
           var text = query.value;
            location.href="/fuzzyquery?query="+text;//获得类型列表的请求
        }
    </script>
</head>
<body>
<%
    List<EBookEntry> list = (List<EBookEntry>) request.getAttribute("booklist");
%>
<p>图书分类：<input type="text" id="text">
<input type="button"value="查询" onclick="goto()"> </p>
<table border="1" cellspacing="0" width="70%">
    <tr>
        <td>编号</td>
        <td>名称</td>
        <td>类型</td>
        <td>摘要</td>
        <td>日期</td>
        <td>上传人</td>
        <td>操作</td>
    </tr>
    <% for (EBookEntry entry : list) {%>
    <tr>
        <td><%=entry.getId()%>
        </td>
        <td><%=entry.getTitle()%>
        </td>
        <td><%=entry.getType().getName()%></td>
        <td><%=entry.getSummary()%>
        </td>
        <td><%=entry.getCreatDate()%>
        </td>
        <td><%=entry.getUploadUser()%>
        </td>
        <td><a href="/deletebyid?tid=<%=entry.getId()%>">删除</a> <a href="/findbyid?tid=<%=entry.getId()%>">修改</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>
