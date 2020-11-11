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

// 1. �� ��Ʈ�ѷ�
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}
	
	// 2. get or post�� ���°��� �Ѱ��� ����ش�
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatchServlet(request, response); // dispatchServlet���� ����ش�

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatchServlet(request, response); // dispatchServlet���� ����ش�
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 3. ��û�б�
		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());

		// mvc2�� forward�� �⺻���� �̵��ϸ� , �ٽ� ��Ʈ�ѷ��� ���ľ� �Ҷ��� redirect�� ����Ѵ�! 
		// service�θ�Ÿ�� ������ ���־�� �Ѵ�
		BoardService service;
		
		
		
		// home -> board�� Ŭ���ϸ� ��ó�� �����ִ� �Խ��� ��� 
		if (command.equals("/board/list.board")) { // ��θ� �̹� ������ �ȴ�

			service = new GetListServiceImpl();
			service.execute(request, response);

			// response�� ����ϸ� ��ü�� ���� ������Ƿ� Request ���
			request.getRequestDispatcher("board_list.jsp").forward(request, response);
		}
		
		
		
		// �� �ۼ� ȭ�� ��û
		else if (command.equals("/board/write.board")) { 
			request.getRequestDispatcher("board_write.jsp").forward(request, response);
		} 
		
		
		// �� ��� �� �����ϸ� �Ϸ�� list.board�� �ٽ� �Ѿ�� ����
		else if (command.equals("/board/regist.board")) { 
			service = new RegistServiceImpl();
			service.execute(request, response);

			response.sendRedirect("list.board");
			// list.board�� �ѹ��� ��ģ���� list�Խ����� �������־�� �Ѵ�
			// ��Ʈ�ѷ��� �ٽ� ���ö� response�� ����Ѵ�

		} 
		
		// �Խ��� ������ Ŭ���� �Ϸ�� board_content.jsp�� ���� �����ش�
		else if (command.equals("/board/content.board")) {
			service =  new UpHitServiceImpl(); 
			service.execute(request, response);

			service =  new ContentServiceImpl(); 
			service.execute(request, response);
			
			request.getRequestDispatcher("board_content.jsp").forward(request, response);
		}
		
		// �Խ��� ���� Ȯ���� ���� ������ ���� 
		else if (command.equals("/board/modify.board")) { 
			service = new ContentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("board_modify.jsp").forward(request, response);
			
			// 1. UpdateServiceImpl()�� �����ϰ� execute()�޼ҵ� ����
			// 2. ���񽺿��� bno, title, content�� �޾Ƽ� DAO�� ������Ʈ �޼ҵ� ������ 
			// 3. content ȭ������ �̵�
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
