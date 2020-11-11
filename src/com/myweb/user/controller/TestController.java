package com.myweb.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 1. Ȯ���� �������� ���� *.xxx�� ������ ����
@WebServlet("*.test")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public TestController() {
        super();
    }

    // 2. get or post�� ���°��� �Ѱ��� ����ش�
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response); // dispatchServlet���� ����ش� 
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response); // dispatchServlet���� ����ش�
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 3. ��û�б� 
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		if(command.equals("/controller/login.test")) {
			// login ������ ó�� ����
		} else if(command.equals("/controller/logout.test")) {
			// logout������ ó��
		} else if(command.equals("/controller/delete.jsp")) {
			// deleteó��
		}
		
		
	}

}
