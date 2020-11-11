<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

/* 	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String pw = request.getParameter("pw");
 */	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<!-- param.태그이름으로 한번에 받아서 사용이 가능하다 = req.getPara("id") -->
<!-- MVC 2 모델에서는 사용하지 않는다 -->
이름 : ${param.name }
ID : ${param.id }
PW : ${param.pw }

</body>
</html>