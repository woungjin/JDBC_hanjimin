<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>if절 확인하기</h2>

	<form action="jstl_if.jsp" method="get">
		이름 : <input type="text" name="name"/> <br/>
		나이 : <input type="text" name="age"/> <br/>
		<input type="submit" value="확인"/> <br/>
	</form>


	<h2>choose절 확인하기</h2>

	<form action="jstl_choose.jsp" method="get">
		이름 : <input type="text" name="name"/> <br/>
		나이 : <input type="text" name="age"/> <br/>
		<input type="submit" value="확인"/> <br/>
	</form>


	<h2>choose2절 확인하기</h2>

	<form action="jstl_choose.jsp" method="get">
		점수 : <input type="text" name="num"/> <br/>
		<input type="submit" value="확인"/> <br/>
	</form>
	
	
</body>
</html>