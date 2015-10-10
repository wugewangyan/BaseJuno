<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri = "http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<h2>Welcome to Juno System</h2>
	Today is <fmt:formatDate value="${today }" pattern="yyyy-MM-dd"/>,
	SimpleName is ${simpleName}
	User Path is ${user}
	
	
	<h1>Process Time : ${processTime}毫秒</h1>
	<h3><spring:message code = "name" text="无"></spring:message></h3>
	<a href = "mailto:${email}">${email}</a>
</body>
</html>