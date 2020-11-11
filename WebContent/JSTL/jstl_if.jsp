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
	<!-- 변수 선언 (el로 참조가 가능) -->
	<c:set var="num1" value="1"/>
	
	<!-- 변수 출력 -->
	<c:out value="${num1 }"/>
	
	<hr style="color:blue;">
	
	<c:if test="true">		<!-- test = 조건절 -->
		무조건 실행되는 문장
	</c:if>
	
	<hr/>
	
	<% if(request.getParameter("name").equals("홍길동")) { %>
		이름이 홍길동 입니다 <br>
	<% } %>
	<c:if test="${param.name eq '홍길동' }">
		이름이 홍길동입니다<br/>
	</c:if>
	
	<c:if test="${param.name == '이순신' }">
		이름이 이순신 입니다 <br/>
	</c:if>
	
	<hr/>
	
	<!-- age파라미터 값이 20이상이면 성인 , 미만이면 미성년자입니다 -->
	<c:if test="${param.age >= 20 }">
		성인입니다
	</c:if>
	<c:if test="${param.age < 20 }" >
		미성년자입니다
	</c:if>
	
</body>
</html>