<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/10/27
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AirQuality</title>
    <script type="text/javascript">
        //查询按钮的绑定事件
        function query(){
            var districtId = document.getElementById("district");
            location.href="/entry?method=selectall&districtId="+districtId.value;//获得类型列表的请求
        }

    </script>
</head>
<body>
<%--接收存放的数据--%>
<h2>空气质量监测信息库</h2>
<P> 按区域查询 <select name="district" id="district">
        <option value="0" >不限</option>
    <c:forEach items="${district}" var="district">
        <option value="${district.id}" ${district.id==districtId?"selected":""}>${district.name}</option>
    </c:forEach>

</select><input type="button" value="查找" onclick="query()">
 <a href="/entry?method=getdistrict">添加空气质量信息</a>
</P>
<table border="1" cellspacing="0" width="70%">
    <tr>
        <td>序号</td>
        <td>区域</td>
        <td>检测时间</td>
        <td>PM10数据</td>
        <td>PM2.5数据</td>
        <td>监测站</td>
    </tr>
    <c:if test="${p1.totalCount!=0}">
    <c:forEach items="${p1.dataList}" var="airQualityIndex">
        <tr>
            <td>${airQualityIndex.id}</td>
            <td><a href="/entry?method=findbyid&did=${airQualityIndex.id}" >${airQualityIndex.district.name}</a></td>
            <td>${airQualityIndex.monitorTime==null?"":airQualityIndex.monitorTime}</td>
            <td>${airQualityIndex.pm10==0?"":airQualityIndex.pm10}</td>
            <td>${airQualityIndex.pm2_5==0?"":airQualityIndex.pm2_5}</td>
            <td>${airQualityIndex.monitoringStation}</td>
        </tr>
    </c:forEach>
    <%-- 分页的各种按钮链接--%>
    <tr>

        <td colspan="7" align="right">
            <a href="/entry?method=selectall&index=1&&districtId=${districtId}">首页</a>
            <a href="/entry?method=selectall&index=${p1.pageIndex==1?1:p1.pageIndex-1}&&districtId=${districtId}"><%="<<"%>上一页</a>
             |
            <a href="/entry?method=selectall&index=${p1.pageIndex==p1.totalPages?p1.totalPages:p1.pageIndex+1}&&districtId=${districtId}">下一页>></a>
            <a href="/entry?method=selectall&index=${p1.totalPages}&&districtId=${districtId}">尾页</a>
            第${p1.pageIndex}页/共${p1.totalPages}页  (${p1.totalCount}条)
        </td>
    </tr>
    </c:if>
</table>
<c:if test="${p1.totalCount==0}">
<h2>抱歉，没有数据！</h2>
</c:if>
</body>
</html>
