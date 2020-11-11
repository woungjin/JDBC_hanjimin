<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String pwcheck = request.getParameter("pwCheck");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	// DAO 받아내기
	UserDAO dao = UserDAO.getInstance();
	// 중복검사
	int result = dao.checkId(id);
	if(result == 1){
%>
	<script>
		alert("아이디 중복 입니다")
		history.go(-1); // 뒤로가기
	</script>
<%
	} else {
		 UserVO vo = new UserVO(id,pw,name,email,address,null);	// 시간은 줄게 없음
		int result2 = dao.join(vo);
		if(result2 == 1){ 
%>			
	<script>
		alert("가입을 축하합니다");
		location.href="login.jsp";
	</script>
<%
	} else {
%>
	<script>
		alert("가입에 실패 하였습니다. 다시 시도해 주세요");
		location.href="join.jsp";
	</script>
<%
		}
	} 	
%>
