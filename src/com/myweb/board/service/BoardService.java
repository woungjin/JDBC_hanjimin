package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
	
	// 추상메소드로 매개변수를 (request, response를 받는다) 
	public void execute(HttpServletRequest request , HttpServletResponse response);
}
