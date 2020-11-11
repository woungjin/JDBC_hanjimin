<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 서로다른 두개의 lib 형식  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	<h2>formatNumber, formatDate, parseDate , parseNumber</h2>
	<!-- 표현자리수 --> <!-- 날짜--> <!-- 문자-->  <!-- 숫자 -->
	
	<h3>parseNumber -> 날짜를 밀리초로 변경가능 </h3> 
	<c:set var="d05" value="<%=new Date() %>" />
	<fmt:parseNumber var="v08" value="${ d05.time }"/>
	<%= new Date().getTime() %>
--%>
	<fmt:parseNumber var="d06" value="1,100.00" pattern ="0,000.00"/>
	${d06}

	
</body>
</html>