<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri = "http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加雇员信息</title>
		<style>
			.error{
				color : #FC0303;
				font-weight : bold;
			}
		</style> 
	</head>
<body>
	<!-- method="post" 属性用于表示表单提交时执行HTTP POST 请求。 
		 modelAttribute="emp" 属性用于表示表单数据绑定到emp模式 -->
	<form:form method="post" modelAttribute="emp" action="saveEmp">
	
		<!-- form:errors 这个标记用于定义在表单不符合控制器设置的规则时，存放错误信息的位置。
			 path = "*" 属性用于表示所有错误的显示（通配符 * ）,cssClass="error" 属性用于表示显示错误信息的CSS格式化类
		 -->
		<table>
			<tr>
				<td>雇员名称</td>
				<td><form:input path="ename"/></td>
				<td><form:errors path="ename" cssClass="error"/></td>
			</tr>
			<tr>
				<td>雇员职务</td>
				<td><form:input path="job"/></td>
				<td><form:errors path="job" cssClass="error"/></td>
			</tr>
			<tr>
				<td>雇员上司</td>
				<td><form:input path="mgr"/></td>
				<td><form:errors path="mgr" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>雇佣日期</td>
				<td><form:input path="hiredate"/></td>
				<td><form:errors path="hiredate" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>雇员薪水</td>
				<td><form:input path="sal"/></td>
				<td><form:errors path="sal" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>雇员奖金</td>
				<td><form:input path="comm"/></td>
				<td><form:errors path="comm" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>所属部门</td>
				<td><form:input path = "deptno"/></td>
				<td><form:errors path="deptno" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td colspan="3"><input type="submit"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>