<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<section>
	<div align="center">
		<h2>기존 비밀번호를 입력해 주세요</h2>
		<form action="delete_ok.jsp" method="post"> 
			비번 : <input type="password" name="pw">
			<input type="submit" value="탈퇴" class="btn btn-danger">
		</form>
	</div>
</section>	
<%@ include file="../include/header.jsp"%>
