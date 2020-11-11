package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class ContentServiceImpl implements BoardService {

	// 글 내용을 확인하기 위한 DAO실행을 해서 값을 받아오는 곳
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");

		// DAO객체 생성, 상세 보기 메소드 실행
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = dao.getContent(bno);

		// request에 vo 저장
		request.setAttribute("vo", vo);
	}

}
