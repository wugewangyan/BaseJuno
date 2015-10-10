<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>雇员列表</title>
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
			<td>雇员名称</td>
			<td>雇员职务</td>
			<td>雇员上司</td>
			<td>雇佣日期</td>
			<td>雇员薪水</td>
			<td>雇员奖金</td>
			<td>所属部门</td>
		</tr>
		
		<c:forEach items="${list }" var="e">
			<tr>
			<td>${e.ename }</td>
				<td>${e.job }</td>
				<td>${e.mgr }</td>
				<td>${e.hiredate }</td>
				<td>${e.sal }</td>
				<td>${e.comm }</td>
				<td>${e.deptno}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>