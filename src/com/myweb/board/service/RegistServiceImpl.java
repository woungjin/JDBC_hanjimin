package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class RegistServiceImpl implements BoardService {

	// �Է��� �Խ��� ������ ����ϴ� DAO�� ���ุ ���ִ°� ��ȯ �ʿ� x
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// DAO��ü ����
		BoardDAO dao = BoardDAO.getInstance();
		dao.regist(writer, title, content);
	}
}
