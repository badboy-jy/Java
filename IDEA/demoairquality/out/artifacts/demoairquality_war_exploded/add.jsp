<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/10/27
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>增加检测信息</title>
    <script type="text/javascript">
        function goto() {
            location.href="/entry";
        }
    </script>
</head>
<body>
<form action="/entry" method="post">
    <input type="hidden" name="method" value="insertairquality">
    <p>
        检测区域：<select name="district">
        <c:forEach items="${district}" var="district">
            <option value="${district.id}">${district.name}</option>
        </c:forEach>

    </select>
    </p>
    <p>检测日期:<input type="Date" name="monitorTime"> </p>
    <p>PM10值:<input type="text" name="pm10"> </p>
    <p>PM2.5值:<input type="text" name="pm2.5"> </p>
    <p>监测站:<input type="text" name="monitoringStatioin"> </p>
    <input type="submit" value="保存"><input type="reset" value="重置"><input type="button" value="返回" onclick="goto()">
</form>
</body>
</html>
