package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
	
	// �߻�޼ҵ�� �Ű������� (request, response�� �޴´�) 
	public void execute(HttpServletRequest request , HttpServletResponse response);
}
