<%@page import="com.myweb.user.model.UserDAO"%>
<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	// 1. 사용자가 입력한 pw가 id를 기반으로 login() 메소드를 실행해 비번이 맞는지 확인한다
	// 2. login() = null을 반환햇다면 "현재 비밀번호를 확인하세요" 출력 후 뒤로가기 실행 ,  반환값이 있다면 delete()호출해서 삭제

	// 3. 삭제 성공시에는 세션을 지우고 index페이지로 리다이렉트 , 실패시 마이페이지로 리다이렉트 

	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	UserDAO dao = UserDAO.getInstance();
	UserVO u = dao.login(id, pw);
	
	if(u != null) { // pw가 일치하다면   
		
	 	int res = dao.delete(id);	// 회원정보를 삭제하라~
			
		if(res == 1) {				// 삭제가 성공햇다면
			session.invalidate();	// 세션도 삭제
			response.sendRedirect(request.getContextPath());
		} else {	
			response.sendRedirect("mypage.jsp");
		} 
		
	} else { %>
	<script>
		alert("비밀번호가 일치하지 않습니다. 다시 시도해 주세요");
		history.go(-1); ㅍ
	</script>
<% 	} %>
