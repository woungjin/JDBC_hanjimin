<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>


	<section>
		<div align="center">
			<h2>로그인성공!</h2>
			<h3>${sessionScope.user.id } (${sessionScope.user.name }) 님 환영합니다! </a></h3>
			<a href="update.user">[정보 수정] </a>
			<a href="delete.user">[회원 탈퇴] </a>
		</div>
	</section> <br>
<%@ include file="../include/footer.jsp" %>"  
