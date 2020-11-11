<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@page import="sun.security.jca.GetInstance"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	
	UserDAO dao = UserDAO.getInstance();
	UserVO vo = new UserVO();
	vo.setId(id);
	vo.setPw(pw);
	vo.setName(name);
	vo.setEmail(email);
	vo.setAddress(address);
	
	int result = dao.update(vo);
	
	if(result == 1 ) { // 성공시  %>
	<% session.setAttribute("user",vo); // 세션값도 update해준다 %>
	<script>
		alert("회원정보가 수정되었습니다");
		location.href="mypage.jsp";
	</script>
<% } else { %>
	<script>
		alert("회원정보 수정에 실패하였습니다.");
		location.href="mypage.jsp";
	</script>
<% } %>
	
