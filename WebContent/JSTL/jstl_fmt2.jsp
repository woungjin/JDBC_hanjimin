<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="TIME_A" value="2020-05-03" />
	<c:set var="TIME_B" value="2020/05/03" />
	<c:set var="TIME_C" value="2020-05-03 21:30:22" />
	<c:set var="TIME_D" value="<%=new Date() %>" />

	<fmt:parseDate var="P_TIME_A"  value="${TIME_A }" pattern="yyyy-MM-dd"/>
	<fmt:formatDate var="result01" value="${P_TIME_A }" pattern="yyyy년MM월dd일"/>
	${result01 }
	<fmt:parseDate var="P_TIME_B" value="${TIME_B }" pattern="yyyy/MM/dd"/>
	<fmt:formatDate var="result02" value="${P_TIME_B }" pattern="yyyy년MM월dd일"/>
	${result02 }
	<fmt:parseDate var="P_TIME_C" value="${TIME_C }" pattern="yyyy-MM-dd HH:mm:ss"/>
	<fmt:formatDate var="result03" value="${P_TIME_C }" pattern="yyyy년MM월dd일 HH시mm분ss초"/>
	${result03 }
	<fmt:formatDate var="result04" value="${TIME_D }" pattern="yyyy년MM월dd일 HH시mm분ss초"/>
	${result04 }
	
	
</body>
</html>