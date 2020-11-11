package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class ContentServiceImpl implements BoardService {

	// �� ������ Ȯ���ϱ� ���� DAO������ �ؼ� ���� �޾ƿ��� ��
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");

		// DAO��ü ����, �� ���� �޼ҵ� ����
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = dao.getContent(bno);

		// request�� vo ����
		request.setAttribute("vo", vo);
	}

}
