<%@ page import="java.util.List" %>
<%@ page import="com.badboyjy.bean.EBookEntry" %>
<%@ page import="com.badboyjy.bean.EBookCategory" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/21
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function goto(){
            location.href="/gettypelist";//获得类型列表的请求
        }
        function query(){
            location.href="/fuzzyquery";//获得类型列表的请求
        }
    </script>
</head>
<body>
   <%
       List<EBookEntry> list=(List<EBookEntry>) request.getAttribute("booklist");
   %>
   <p><input type="button" value="新增电子书" onclick="goto()"><input type="button" value="模糊查询" onclick="query()"> <%--图书分类：<select name="booktype">
       <%
           List<EBookCategory> categoryList=(List<EBookCategory>)   request.getAttribute("types");
           for(EBookCategory category:categoryList){
       %>
       <option value="<%=category.getId()%>" ><%=category.getName()%></option>
       <%}%>

   </select>--%></p>
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
        <% for(EBookEntry entry :list){%>
        <tr>
            <td><%=entry.getId()%></td>
            <td><%=entry.getTitle()%></td>
            <td><%=entry.getType().getName()%></td>
            <td><%=entry.getSummary()%></td>
            <td><%=entry.getCreatDate()%></td>
            <td><%=entry.getUploadUser()%></td>
            <td><a href="/deletebyid?tid=<%=entry.getId()%>" >删除</a> <a href="/findbyid?tid=<%=entry.getId()%>" >修改</a></td>
        </tr>
       <% } %>
    </table>
</body>
</html>
