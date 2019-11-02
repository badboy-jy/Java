<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/10/27
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新检测信息</title>
    <script type="text/javascript">
        function goto() {
            location.href="/entry?method=delete&id=${airQualityIndex.id}"
        }
        function retu() {
            location.href="/entry";
        }

       function fal(obj) {
            var flag=true;
            var time = document.getElementById("time");
            var timevalue=time.value;
           if (timevalue==""){
               alert("时间未选择");
           }else {
               obj.submit();
               flag=true;
           }

        }
    </script>
</head>
<body>
<h2>空气质量信息维护页面</h2>
<form action="/entry" method="post">
    <input type="hidden" name="method" value="update">
    <input type="hidden" name="id" value="${airQualityIndex.id}">

    <p>
        检测区域：<select name="district">
        <c:forEach items="${district}" var="district">
            <option value="${district.id}" >${district.name}</option>
        </c:forEach>

    </select>
    </p>
    <p>检测日期:<input type="Date" name="lastMondifyTime" value="${airQualityIndex.lastMondifyTime}" id="time"> </p>
    <p>PM10值:<input type="text" name="pm10" value="${airQualityIndex.pm10==0?"":airQualityIndex.pm10}"> </p>
    <p>PM2.5值:<input type="text" name="pm2.5" value="${airQualityIndex.pm2_5==0?"":airQualityIndex.pm2_5}"> </p>
    <p>监测站:<input type="text" name="monitoringStation" value="${airQualityIndex.monitoringStation}"> </p>
    <p>最后修改时间:${airQualityIndex.lastMondifyTime}</p>
    <input type="button" value="更新"  onclick="fal(this.form)"><input type="button" value="删除" onclick="goto()"><input type="button" value="返回" onclick="retu()">
</form>
</body>
</html>
