package com.myweb.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.catalina.User;

import com.myweb.user.model.UserVO;
import com.myweb.user.service.UserDeleteServiceImpl;
import com.myweb.user.service.UserJoinServiceImpl;
import com.myweb.user.service.UserLoginServiceImpl;
import com.myweb.user.service.UserService;
import com.myweb.user.service.UserUpdateServiceImpl;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatchServlet(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatchServlet(request, response);
	}

	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");    

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());

		System.out.println(command);
		UserService service;

		// header ���κκ��� joinŬ���� join.jspȭ���� �����ش�
		if (command.equals("/user/join.user")) {

			request.getRequestDispatcher("join.jsp").forward(request, response);


		} 

		// header ���κκ��� login Ŭ���� ���ͼ� login.jspȭ���� �����ش�
		else if (command.equals("/user/login.user")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);

		} 

		// join.jsp���� �ۼ��� form���¸� �޾��ִ°� (id�ߺ� üũ����)
		else if (command.equals("/user/joinForm.user")) {
			service = new UserJoinServiceImpl();
			int result = service.execute(request, response);

			if (result == 1) {
				request.setAttribute("msg", "�̹� �����ϴ� ȸ���Դϴ�");
				request.getRequestDispatcher("join.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.user");
			}

		}

		// login.jsp id�� pw�� Ȯ���� ��ġ�ϸ� ���ǿ� �����Ѵ�
		else if (command.equals("/user/loginForm.user")) {
			service = new UserLoginServiceImpl();
			int result = service.execute(request, response);

			// ���н� 0 , ������ 1 
			if(result == 0) {
				request.setAttribute("msg", "���̵� ��й�ȣ�� Ȯ���� �ּ���");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				response.sendRedirect("mypage.user");
			}
			// �� �ڹٿ��� ���� ��¹�
			// HttpSession session = request.getSession(); 
		} 

		
		// ����ȭ�� mypage Ŭ���� mypage.jsp�� �Ѱ��ش�
		else if (command.equals("/user/mypage.user")) {
			request.getRequestDispatcher("mypage.jsp").forward(request, response);
			
		} 

		// ����ȭ�� logout Ŭ���� 
		else if (command.equals("/user/logout.user")) {
			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect(request.getContextPath());// Ȩ ȭ������ 
		}

		// ����ȭ�� �̵�
		else if (command.equals("/user/update.user")) { 
			// ���ǿ� ���� ��� �ֱ� ������ ȭ������ �̵�
			request.getRequestDispatcher("update.jsp").forward(request, response);
		}

		// mypage.jsp ���� ���� �����Ϸ�� ���°�
		else if (command.equals("/user/updateForm.user")) {

			service = new UserUpdateServiceImpl();
			int result = service.execute(request, response);

			// ���� : 1 , ���� : 0
			if (result == 1) {
				// ���ڿ��� ���¸� ��ũ��Ʈ�� �ۼ��ؼ� out.println�� ������ ����
				response.setContentType("text/html; charset=UTF-8"); // html�� ����
				PrintWriter out = response.getWriter(); // ��½�Ʈ��
				out.println("<script>");
				out.println("alert('ȸ������ ������ ���� ó�� �Ǿ����ϴ�');");
				out.println("location.href='mypage.user';");
				out.println("</script>");

			} else {
				response.sendRedirect("mypage.user"); // ���н� ������������
			}

		} 
		
		
		// mypage.jsp���� delete��û�� �ý� pw Ȯ�� �������� �����ش�
		else if (command.equals("/user/delete.user")) {
			request.getRequestDispatcher("delete.jsp").forward(request, response);
		} 
		
		// delete.jsp���� pwȮ���� Ż��Ȯ���� ������ Ż�𼺰� ���θ� �˷��ش�
		else if (command.equals("/user/deleteForm.user")) {
			service = new UserDeleteServiceImpl();
			int result = service.execute(request, response);
			
			// ���� : 1 , ���� : 0
			if (result == 1) {
				response.sendRedirect(request.getContextPath());
				
			} else {
				request.setAttribute("msg", "��й�ȣ�� Ȯ�����ּ���");
				request.getRequestDispatcher("delete.jsp");
			}
			
			
		}
		
	}

}
