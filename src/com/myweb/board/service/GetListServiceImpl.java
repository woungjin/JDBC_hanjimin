package com.myweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;
import com.myweb.util.PageVO;

public class GetListServiceImpl implements BoardService {

	// list의 게시판 목록들을 보여주기위해 DAO에서 메소드실행해서 값을 받아와 넘겨주는 곳
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		BoardDAO dao = BoardDAO.getInstance();
		// ArrayList<BoardVO> list = dao.getList();

		// 1. 첫번째 페이지 진입할 때 값
		int pageNum = 1;
		int amount = 10;

		if (request.getParameter("pageNum") != null || request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}

		// 화면에 보여질 데이터들 반환
		ArrayList<BoardVO> list = dao.getList(pageNum, amount);
		int total = dao.getTotal();
		// PageVO생성
		PageVO pageVO = new PageVO(pageNum, amount, total);

		// 화면으로 가져가기 위해 requset에 list를 저장
		request.setAttribute("list", list);

		// 페이지네이션을 화면에 전달
		request.setAttribute("pageVO", pageVO);

	}

}
