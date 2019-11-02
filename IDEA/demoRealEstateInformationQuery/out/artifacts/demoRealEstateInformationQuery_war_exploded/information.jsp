<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/11/1
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>房产信息查询系统</title>
    <script type="text/javascript">
        //查询按钮的绑定事件
        function query(){
            var type = document.getElementById("type");
            var query = document.getElementsByName("query")[0];
            if (type.value ==0) {
                location.href = "/query?method=findall&name=" + query.value;//获得类型列表的请求
            }else {
                location.href="/query?methiod=findall&cardId="+query.value;
            }

        }
        function add() {
            location.href="/add.jsp";
        }

    </script>
</head>
<body>
<h2>房产信息查询</h2>
<P> 查询类型<select name="type" id="type">

    <option value="0" ${type==1?"selected":""}>用户名</option>
    <option value="1" ${type==0?"selected":""}>身份证号</option>
</select><input type="text" name="query" value="${uname!=""?uname:cardId}"><input type="button" value="查找" onclick="query()"><input type="button" value="添加" onclick="add()">
</P>
<table border="1" cellspacing="0" width="70%">
    <tr>
        <td>序号</td>
        <td>项目名称</td>
        <td>产权人</td>
        <td>身份证号</td>
        <td>房屋类型</td>
        <td>使用面积</td>
        <td>建造时间</td>
        <td>操作</td>
    </tr>
    <c:if test="${p1.totalCount!=0}">
        <c:forEach items="${p1.dataList}" var="realestate" varStatus="num">
            <tr>
                <td>${(p1.pageIndex-1)*size+num.count}</td>
                <td>${realestate.projectName}</td>
                <td>${realestate.user.name}</td>
                <td>${realestate.cardId}</td>
                <td>${realestate.houseType}</td>
                <td>${realestate.area}</td>
                <td>${realestate.buildTime}</td>
                <td><a href="/query?method=delete&id=${realestate.id}" >删除</a> <a href="/query?method=findbyid&id=${realestate.id}" >修改</a></td>
            </tr>
        </c:forEach>
        <%-- 分页的各种按钮链接--%>
        <tr>

            <td colspan="7" align="right">
                <a href="/query?method=findall&index=1&&name=${uname}&&cardId=${cardId}">首页</a>
                <a href="/query?method=findall&index=${p1.pageIndex==1?1:p1.pageIndex-1}&&name=${uname}&&cardId=${cardId}"><%="<<"%>上一页</a>
                |
                <a href="/query?method=findall&index=${p1.pageIndex==p1.totalPages?p1.totalPages:p1.pageIndex+1}&&name=${uname}&&cardId=${cardId}">下一页>></a>
                <a href="/query?method=findall&index=${p1.totalPages}&&name=${uname}&&cardId=${cardId}">尾页</a>
                第${p1.pageIndex}页/共${p1.totalPages}页  (${p1.totalCount}条)
            </td>
        </tr>
    </c:if>
</table>
<c:if test="${p1.totalCount==0}">
    <h2>抱歉，没有数据！</h2>
</c:if>
</body>
</body>
</html>
