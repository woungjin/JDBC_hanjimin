package com.myweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;
import com.myweb.util.PageVO;

public class GetListServiceImpl implements BoardService {

	// list�� �Խ��� ��ϵ��� �����ֱ����� DAO���� �޼ҵ�����ؼ� ���� �޾ƿ� �Ѱ��ִ� ��
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		BoardDAO dao = BoardDAO.getInstance();
		// ArrayList<BoardVO> list = dao.getList();

		// 1. ù��° ������ ������ �� ��
		int pageNum = 1;
		int amount = 10;

		if (request.getParameter("pageNum") != null || request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}

		// ȭ�鿡 ������ �����͵� ��ȯ
		ArrayList<BoardVO> list = dao.getList(pageNum, amount);
		int total = dao.getTotal();
		// PageVO����
		PageVO pageVO = new PageVO(pageNum, amount, total);

		// ȭ������ �������� ���� requset�� list�� ����
		request.setAttribute("list", list);

		// ���������̼��� ȭ�鿡 ����
		request.setAttribute("pageVO", pageVO);

	}

}
