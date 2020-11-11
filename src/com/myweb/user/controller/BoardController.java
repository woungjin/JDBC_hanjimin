package com.myweb.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.service.BoardService;
import com.myweb.board.service.ContentServiceImpl;
import com.myweb.board.service.DeleteServiceImpl;
import com.myweb.board.service.GetListServiceImpl;

import com.myweb.board.service.RegistServiceImpl;
import com.myweb.board.service.UpHitServiceImpl;
import com.myweb.board.service.UpdateServiceImpl;

// 1. 글 컨트롤러
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}
	
	// 2. get or post로 들어온것을 한곳에 모아준다
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatchServlet(request, response); // dispatchServlet으로 모아준다

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatchServlet(request, response); // dispatchServlet으로 모아준다
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 3. 요청분기
		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());

		// mvc2는 forward를 기본으로 이동하며 , 다시 컨트롤러를 거쳐야 할때는 redirect를 사용한다! 
		// service부모타입 선언을 해주어야 한다
		BoardService service;
		
		
		
		// home -> board를 클릭하면 맨처음 보여주는 게시판 목록 
		if (command.equals("/board/list.board")) { // 경로를 이미 찍고들어간게 된다

			service = new GetListServiceImpl();
			service.execute(request, response);

			// response를 사용하면 객체의 값이 사라지므로 Request 사용
			request.getRequestDispatcher("board_list.jsp").forward(request, response);
		}
		
		
		
		// 글 작성 화면 요청
		else if (command.equals("/board/write.board")) { 
			request.getRequestDispatcher("board_write.jsp").forward(request, response);
		} 
		
		
		// 글 등록 을 실행하며 완료시 list.board로 다시 넘어가게 해줌
		else if (command.equals("/board/regist.board")) { 
			service = new RegistServiceImpl();
			service.execute(request, response);

			response.sendRedirect("list.board");
			// list.board로 한번더 거친다음 list게시판을 실행해주어야 한다
			// 컨트롤러로 다시 들어올땐 response를 사용한다

		} 
		
		// 게시판 제목을 클릭후 완료시 board_content.jsp로 값을 보내준다
		else if (command.equals("/board/content.board")) {
			service =  new UpHitServiceImpl(); 
			service.execute(request, response);

			service =  new ContentServiceImpl(); 
			service.execute(request, response);
			
			request.getRequestDispatcher("board_content.jsp").forward(request, response);
		}
		
		// 게시판 내용 확인중 수정 누르면 들어옴 
		else if (command.equals("/board/modify.board")) { 
			service = new ContentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("board_modify.jsp").forward(request, response);
			
			// 1. UpdateServiceImpl()을 생성하고 execute()메소드 실행
			// 2. 서비스에서 bno, title, content를 받아서 DAO의 업데이트 메소드 실행후 
			// 3. content 화면으로 이동
		} 
	
		
		else if (command.equals("/board/update.board")) {	
			service = new UpdateServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("content.board?bno=" + request.getParameter("bno"));
			
		} 
		
		
		else if (command.equals("/board/delete.board")) {
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("list.board");
		}

	}

}
