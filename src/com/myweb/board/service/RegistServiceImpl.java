package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class RegistServiceImpl implements BoardService {

	// 입력한 게시판 내용을 등록하는 DAO를 실행만 해주는곳 반환 필요 x
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// DAO객체 생성
		BoardDAO dao = BoardDAO.getInstance();
		dao.regist(writer, title, content);
	}
}
