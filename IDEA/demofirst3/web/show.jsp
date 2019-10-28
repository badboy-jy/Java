<%@ page import="java.util.List" %>
<%@ page import="com.badboyjy.bean.EBookEntry" %>
<%@ page import="com.badboyjy.bean.EBookCategory" %>
<%@ page import="com.badboyjy.utils.PageUtils" %><%--
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
        //新增按钮的 绑定事件
        function goto(){
            location.href="/entry?method=gettypes";//获得类型列表的请求
        }
        //模糊查询按钮的绑定事件
        function query(){
            var query = document.getElementById("text");
            var text = query.value;
            location.href="/entry?method=selectall&query1="+text;//获得类型列表的请求
        }
        //分类查询的onchange事件
        function query2(){
            var query = document.getElementById("type");
            var index = query.selectedIndex; // 选中索引
            var text = query.options[index].text; // 选中文本
            location.href="/entry?method=selectall&query2="+text;//获得类型列表的请求
        }
        //回显
       window.onload = function(){
            //解析url地址  获取参数值 回显
            //1.获取元素
           /* var query = document.getElementById("type");
            var index = query.selectedIndex; // 选中索引
            var text = document.getElementById("text");
            function GetQueryString(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
                var context = "";
                if (r != null)
                    context = r[2];
                reg = null;
                r = null;
                return context == null || context == "" || context == "undefined" ? "" : context;
            }
            query.options[0].text = decodeURIComponent(GetQueryString("query2"));
            text.value = decodeURIComponent(GetQueryString("query1"));*/

           //放参数值 在request里  在页面拿出来
           var text = document.getElementById("text");
           text.value="<%=request.getAttribute("query1")%>";
           var query = document.getElementById("type");
           var index = query.selectedIndex; // 选中索引
           query.options[0].text = "<%=request.getAttribute("query2")%>";
        }
    </script>
</head>
<body>
<%--接收存放的数据--%>
   <%
//       List<EBookEntry> list=(List<EBookEntry>) request.getAttribute("booklist");
       PageUtils<EBookEntry> p1= (PageUtils<EBookEntry>) request.getAttribute("p1");
       List<EBookCategory> categoryList=(List<EBookCategory>)   request.getAttribute("types");
//       EBookEntry eBookEntry = (EBookEntry) request.getAttribute("entry");
   %>
   <p><input type="button" value="新增电子书" onclick="goto()"><input type="button" value="模糊查询" onclick="query()"><input type="text" id="text">

       分类查询：<select name="booktype" id="type" onchange="query2()">
           <option style="display: none" ></option>
           <option value="all" >全部</option>
       <%
           for(EBookCategory category:categoryList){
       %>
       <option value="<%=category.getId()%>" <%--<%if (category.getId()==eBookEntry.getCategoryId()){%>selected<%}%>--%>><%=category.getName()%></option>
       <%}%>

   </select>

   </p>
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
        <% for(EBookEntry entry :p1.getDataList()){%>
        <tr>
            <td><%=entry.getId()%></td>
            <td><%=entry.getTitle()%></td>
            <td><%=entry.getType().getName()%></td>
            <td><%=entry.getSummary()%></td>
            <td><%=entry.getCreatDate()==null?"":entry.getCreatDate()%></td>
            <td><%=entry.getUploadUser()%></td>
            <td><a href="/entry?method=delete&tid=<%=entry.getId()%>" >删除</a> <a href="/entry?method=findbyid&tid=<%=entry.getId()%>" >修改</a></td>
        </tr>
       <% } %>
       <%-- 分页的各种按钮链接--%>
        <tr>
             <%--   <%
            int pageindex=(int)request.getAttribute("pageindex");
            int pages=(int)request.getAttribute("pages");
           int totalcount=(int)request.getAttribute("count");
                    %>--%>
            <td colspan="7" align="right">
                <a href="/entry?method=selectall&index=1&&query1=<%=request.getAttribute("query1")%>&&query2=<%=request.getAttribute("query2")%>">首页</a>
                <a href="/entry?method=selectall&index=<%=p1.getPageIndex()==1?1:p1.getPageIndex()-1%>&&query1=<%=request.getAttribute("query1")%>&&query2=<%=request.getAttribute("query2")%>">上一页</a>
                <% for (int i=1;i<=p1.getTotalPages();i++) {%>
                <%if (i==p1.getPageIndex()){%>
                <a href="/entry?method=selectall&index=<%=i%>&&query1=<%=request.getAttribute("query1")%>&&query2=<%=request.getAttribute("query2")%>"><%=i%></a>
                <%}else{%>
                <a href="/entry?method=selectall&index=<%=i%>&&query1=<%=request.getAttribute("query1")%>&&query2=<%=request.getAttribute("query2")%>" style="text-decoration: none"><%=i%></a>
                <%}%>
                <%}%>

                <a href="/entry?method=selectall&index=<%=p1.getPageIndex()==p1.getTotalPages()?p1.getTotalPages():p1.getPageIndex()+1%>&&query1=<%=request.getAttribute("query1")%>&&query2=<%=request.getAttribute("query2")%>">下一页</a>
                <a href="/entry?method=selectall&index=<%=p1.getTotalPages()%>&&query1=<%=request.getAttribute("query1")%>&&query2=<%=request.getAttribute("query2")%>">尾页</a>
                第<%=p1.getPageIndex()%>页/共<%=p1.getTotalPages()%>页  共<%=p1.getTotalCount()%>条
            </td>
    </table>
</body>
</html>
