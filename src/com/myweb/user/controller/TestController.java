package com.myweb.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 1. 확장자 패턴으로 변경 *.xxx로 맵핑을 변경
@WebServlet("*.test")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public TestController() {
        super();
    }

    // 2. get or post로 들어온것을 한곳에 모아준다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response); // dispatchServlet으로 모아준다 
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response); // dispatchServlet으로 모아준다
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 3. 요청분기 
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		if(command.equals("/controller/login.test")) {
			// login 페이지 처리 공간
		} else if(command.equals("/controller/logout.test")) {
			// logout페이지 처리
		} else if(command.equals("/controller/delete.jsp")) {
			// delete처리
		}
		
		
	}

}
