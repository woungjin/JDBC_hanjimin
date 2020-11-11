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

		// header 메인부분의 join클릭시 join.jsp화면을 보여준다
		if (command.equals("/user/join.user")) {

			request.getRequestDispatcher("join.jsp").forward(request, response);


		} 

		// header 메인부분의 login 클릭시 들어와서 login.jsp화면을 보여준다
		else if (command.equals("/user/login.user")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);

		} 

		// join.jsp에서 작성한 form형태를 받아주는곳 (id중복 체크해줌)
		else if (command.equals("/user/joinForm.user")) {
			service = new UserJoinServiceImpl();
			int result = service.execute(request, response);

			if (result == 1) {
				request.setAttribute("msg", "이미 존재하는 회원입니다");
				request.getRequestDispatcher("join.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.user");
			}

		}

		// login.jsp id와 pw를 확인해 일치하면 세션에 저장한다
		else if (command.equals("/user/loginForm.user")) {
			service = new UserLoginServiceImpl();
			int result = service.execute(request, response);

			// 실패시 0 , 성공시 1 
			if(result == 0) {
				request.setAttribute("msg", "아이디 비밀번호를 확인해 주세요");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				response.sendRedirect("mypage.user");
			}
			// ＊ 자바에서 세션 얻는법
			// HttpSession session = request.getSession(); 
		} 

		
		// 메인화면 mypage 클릭시 mypage.jsp로 넘겨준다
		else if (command.equals("/user/mypage.user")) {
			request.getRequestDispatcher("mypage.jsp").forward(request, response);
			
		} 

		// 메인화면 logout 클릭시 
		else if (command.equals("/user/logout.user")) {
			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect(request.getContextPath());// 홈 화면으로 
		}

		// 수정화면 이동
		else if (command.equals("/user/update.user")) { 
			// 세션에 값이 들어 있기 떄문에 화면으로 이동
			request.getRequestDispatcher("update.jsp").forward(request, response);
		}

		// mypage.jsp 에서 정보 수정완료시 오는곳
		else if (command.equals("/user/updateForm.user")) {

			service = new UserUpdateServiceImpl();
			int result = service.execute(request, response);

			// 성공 : 1 , 실패 : 0
			if (result == 1) {
				// 문자열의 형태를 스크립트로 작성해서 out.println에 보내서 저장
				response.setContentType("text/html; charset=UTF-8"); // html로 저장
				PrintWriter out = response.getWriter(); // 출력스트림
				out.println("<script>");
				out.println("alert('회원정보 수정이 정상 처리 되었습니다');");
				out.println("location.href='mypage.user';");
				out.println("</script>");

			} else {
				response.sendRedirect("mypage.user"); // 실패시 마이페이지로
			}

		} 
		
		
		// mypage.jsp에서 delete요청이 올시 pw 확인 페이지로 보내준다
		else if (command.equals("/user/delete.user")) {
			request.getRequestDispatcher("delete.jsp").forward(request, response);
		} 
		
		// delete.jsp에서 pw확인후 탈퇴확인을 누르면 탈퇴성공 여부를 알려준다
		else if (command.equals("/user/deleteForm.user")) {
			service = new UserDeleteServiceImpl();
			int result = service.execute(request, response);
			
			// 성공 : 1 , 실패 : 0
			if (result == 1) {
				response.sendRedirect(request.getContextPath());
				
			} else {
				request.setAttribute("msg", "비밀번호를 확인해주세요");
				request.getRequestDispatcher("delete.jsp");
			}
			
			
		}
		
	}

}
