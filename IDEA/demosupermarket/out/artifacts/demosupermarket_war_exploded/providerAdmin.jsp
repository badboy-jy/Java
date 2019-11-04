<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<script type="text/javascript">
</script>
</head>
<body>
<div class="menu">

<table>
<tbody><tr><td><form id="form" method="post" action="/supplier">
<input name="method" value="selectallsupplier" type="hidden">
<input type="hidden" name="page" id="page" value="1" />
供应商名称：<input name="supplierName" class="input-text" type="text" value="${supplierName}"> &nbsp;&nbsp;&nbsp;&nbsp;供应商描述：<input name="supplierDesc" class="input-text" type="text" value="${supplierDesc}">&nbsp;&nbsp;&nbsp;&nbsp;<input value="组 合 查 询" type="submit">
</form></td></tr>
</tbody></table>
</div>
<div class="main">
<div class="optitle clearfix">
<em><input value="添加数据" class="input-button" onclick="location.href='/providerAdd.jsp'" type="button"></em>
		<div class="title">供应商管理&gt;&gt;</div>
	</div>

	<div class="content">
        <table class="list">
          <tbody><tr>
              <td width="70" height="29"><div class="STYLE1" align="center">编号</div></td>
              <td width="100"><div class="STYLE1" align="center">供应商名称</div></td>
              <td width="100"><div class="STYLE1" align="center">供应商描述</div></td>
              <td width="100"><div class="STYLE1" align="center">联系人</div></td>
              <td width="100"><div class="STYLE1" align="center">电话</div></td>
              <td width="100"><div class="STYLE1" align="center">传真</div></td>
              <td width="100"><div class="STYLE1" align="center">地址</div></td>
              <td width="100"><div class="STYLE1" align="center">操作</div></td>

          </tr>
          <c:if test="${p1.totalCount!=0}">
          <c:forEach items="${p1.dataList}" var="supplier" varStatus="num">
              <tr>
                  <td>${num.count}</td>
                  <td>${supplier.supplierName}</td>
                  <td>${supplier.supplierDesc}</td>
                  <td>${supplier.supplierPerName}</td>
                  <td>${supplier.supplierPhone}</td>
                  <td>${supplier.supplierChuanZhen}</td>
                  <td>${supplier.supplierAddress}</td>
                  <td>
                      <a href="/supplier?method=delete&supplierId=${supplier.supplierId}">删除</a>
                      <a href="/supplier?method=findbyid&supplierId=${supplier.supplierId}">修改</a>
                  </td>
              </tr>
          </c:forEach>
              <%-- 分页的各种按钮链接--%>
          <tr>

              <td colspan="9" align="right">
                  <a href="/supplier?method=selectallsupplier&index=1&&supplierName=${supplierName}&&supplierDesc=${supplierDesc}">首页</a>
                  <a href="/supplier?method=selectallsupplier&index=${p1.pageIndex==1?1:p1.pageIndex-1}&&supplierName=${supplierName}&&supplierDesc=${supplierDesc}"><%="<<"%>上一页</a>
                  |
                  <a href="/supplier?method=selectallsupplier&index=${p1.pageIndex==p1.totalPages?p1.totalPages:p1.pageIndex+1}&&supplierName=${supplierName}&&supplierDesc=${supplierDesc}">下一页>></a>
                  <a href="/supplier?method=selectallsupplier&index=${p1.totalPages}&&supplierName=${supplierName}&&supplierDesc=${supplierDesc}">尾页</a>
                  第${p1.pageIndex}页/共${p1.totalPages}页  (${p1.totalCount}条)
              </td>
          </tr>
          </c:if>
        </tbody></table>
        <c:if test="${p1.totalCount==0}">
            <h2>抱歉，没有数据！</h2>
        </c:if>
	</div>
</div>

</body></html>