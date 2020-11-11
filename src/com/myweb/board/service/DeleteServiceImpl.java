package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class DeleteServiceImpl implements BoardService {

	// 입력한 게시판 내용을 등록하는 DAO를 실행만 해주는곳 반환 필요 x
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bno = request.getParameter("bno");

		BoardDAO dao = BoardDAO.getInstance();
		dao.delete(bno);
	}
}
