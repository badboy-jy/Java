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
	<title></title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div class="menu">
	<form method="post" action="/bill" id="form">
		<input type="hidden" name="method" value="selectallbill">
		商品名称：<input type="text" name="goodsName" class="input-text" value="${goodsName}"/>&nbsp;&nbsp;&nbsp;&nbsp;
		是否付款：<select name="payStatus">
		<option value="">请选择</option>
		<option value="1" ${payStatus eq "1"?"selected":""}>已付款</option>
		<option value="0" ${payStatus eq "0"?"selected":""}>未付款</option>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" name="submit" value="组合查询" class="button" />
	</form>
</div>
<div class="main">
	<div class="optitle clearfix">
		<em><input type="button" name="button" value="添加数据" class="input-button" onclick="location.href='billAdd.jsp'" /></em>
		<div class="title">账单管理&gt;&gt;</div>
	</div>
	<div class="content">
		<table class="list">
			<tbody><tr>
				<td width="70" height="29"><div class="STYLE1" align="center">编号</div></td>
				<td width="100"><div class="STYLE1" align="center">商品名称</div></td>
				<td width="100"><div class="STYLE1" align="center">商品数量</div></td>
				<td width="100"><div class="STYLE1" align="center">交易金额</div></td>
				<td width="100"><div class="STYLE1" align="center">供应商名称</div></td>
				<td width="100"><div class="STYLE1" align="center">商品描述</div></td>
				<td width="100"><div class="STYLE1" align="center">账单时间</div></td>
				<td width="100"><div class="STYLE1" align="center">操作</div></td>
			</tr>
			<c:if test="${p1.totalCount!=0}">
				<c:forEach items="${p1.dataList}" var="bill" varStatus="num">
					<tr>
						<td>${num.count}</td>
						<td>${bill.goodsName}</td>
						<td>${bill.goodsCount}</td>
						<td>${bill.money}</td>
						<td>${bill.supplier.supplierName}</td>
						<td>${bill.goodsDescription}</td>
						<td>${bill.time}</td>
						<td>
							<a href="/bill?method=delete&bid=${bill.billId}">删除</a>
							<a href="/bill?method=findbyid&bid=${bill.billId}">修改</a>
						</td>
					</tr>
				</c:forEach>
				<%-- 分页的各种按钮链接--%>
				<tr>

					<td colspan="9" align="right">
						<a href="/bill?method=selectallbill&index=1&&goodsName=${goodsName}&&payStatus=${payStatus}">首页</a>
						<a href="/bill?method=selectallbill&index=${p1.pageIndex==1?1:p1.pageIndex-1}&&goodsName=${goodsName}&&payStatus=${payStatus}"><%="<<"%>上一页</a>
						|
						<a href="/bill?method=selectallbill&index=${p1.pageIndex==p1.totalPages?p1.totalPages:p1.pageIndex+1}&&goodsName=${goodsName}&&payStatus=${payStatus}">下一页>></a>
						<a href="/bill?method=selectallbill&index=${p1.totalPages}&&goodsName=${goodsName}&&payStatus=${payStatus}">尾页</a>
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
</body>
</html>
