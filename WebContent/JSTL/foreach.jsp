<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>1~100 까지의 홀수 합</h3>
	<hr/>
	<c:set var="total" value="0"/>
	<c:forEach var="i" begin="1" end="100" step="2">
		<c:set var="total" value="${total + i }"/>
	</c:forEach>
	결과 : ${total }
	<br>
	
	<hr>
	<c:forEach var="j" begin="1" end ="9" step="1">
		3 * ${j } = ${3	*j } <br>
	</c:forEach>
	
	<hr>
	<c:forEach var="i" begin="2" end="9" step="1">
		<c:forEach var="j" begin="1" end="9" step="1">
			${i} * ${j } = ${i*j} <br> 
		</c:forEach>
	</c:forEach>
	
	
	<h3> 향상된 포문 ★ 중요 </h3>
	
<%
	int[] arr = new int[] {1,2,3,4,5};
	for(int a : arr) {
	out.println(a);
	}	
%>	
	
	<hr>
	<!-- new 연산은 el 태그로 안된다 -->
	<c:set var="arr2" value="<%=new int[] {1,2,3,4,5} %>"/>
	
	<c:forEach var="i" items="${arr2 }" varStatus="s">	<!-- items = var변수에 arr2를 담겠다 -->
	${s.index }인덱스 값 :	${i }							<!-- varStatus = 인덱스의 번호를 알려줌 -->
		
	</c:forEach>

</body>
</html>