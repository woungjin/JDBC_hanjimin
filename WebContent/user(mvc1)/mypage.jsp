<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<% if(session.getAttribute("user") == null) { 
		response.sendRedirect("login.jsp");
	}
	UserVO u = (UserVO)session.getAttribute("user");

%>

	<section>
		<div align="center">
			<h2>로그인성공!</h2>
			<h3><%=u.getId() %>(<%=u.getName() %>)님 환영합니다! </h3>
			<a href="update.jsp">[정보 수정] </a>
			<a href="delete.jsp">[회원 탈퇴] </a>
		</div>
	</section> <br>
<%@ include file="../include/footer.jsp" %>"  
