<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		1. id, pw를 받아준다
		2. UserVo를 반환하는 login 메소드에 id,pw를 매개변수로 넘겨준다 
			UserVO에 select 결과를 저장 없다면 null을 반환  
		- 한번에 id와 pw를 가지고 반환해오낟 
		
		3. login_ok에서는 UserVO가 null이 아니라면 = 로그인 성공 UserVO를 세션에 저장후 mypage.jsp로 리다이렉트
			
		4. null이라면 실패를 의미하므로, script로 "아이디 비밀번호를 확인하세요"를 출력한 후에
			다시 로그인 페이지로 
	*/
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	UserDAO dao = UserDAO.getInstance();
	
	UserVO vo = dao.login(id, pw);
	if(vo !=null) { 
		session.setAttribute("user", vo);
		response.sendRedirect("mypage.jsp");
	} else {
%>
	<script>
		alert("아이디 비밀번호를 확인해 주세요");
		location.href="login.jsp";
	</script>
<%		
	}
	
%>