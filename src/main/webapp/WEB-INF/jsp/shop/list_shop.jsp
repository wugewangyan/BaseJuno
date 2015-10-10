<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商店列表</title>
		<style>
			.error{
				color : #ff000;
				font-weight : bold;
			}
		</style> 
	</head>
<body>
	<table cellpadding="1" border="1">
		<tr>
			<td>商店名称</td>
			<td>商店地址</td>
			<td>商店电话</td>
			<td>商店描述</td>
			<td>操作</td>
		</tr>
		
		<c:forEach items="${list }" var="e">
			<tr>
			<td>${e.shopName }</td>
				<td>${e.shopAddress }</td>
				<td>${e.shopTelphone }</td>
				<td>${e.shopDesc }</td>
				<td>
					<a href = "update?shopId=${e.shopId}">更新</a> | 
					<a href = "delete?shopId=${e.shopId}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>