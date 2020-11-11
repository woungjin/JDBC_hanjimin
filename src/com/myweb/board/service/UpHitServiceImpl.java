package com.myweb.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class UpHitServiceImpl implements BoardService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		// 쿠키를 각 게시판 번호에 맞게 저장을 해주고 쿠키의 번호와 게시판의 번호가 동일하면 중복이기에 조회수 누적이 안된다 
		Cookie[] arr = request.getCookies();
		boolean flag = true;
		if(arr!=null) {
			for(Cookie c : arr ) {
				if(c.getName().equals(bno)) {	// 쿠키 이름이 게시글조회번호와 동일한지 검사 = 중복
					flag = false;
					break;
				}
			}
		}
		
		if(flag ) { // 중복이 없다면 true 
			BoardDAO dao = BoardDAO.getInstance();
			dao.upHip(bno);
		}
		// 쿠키를 이용해서 조회된 번호를 클라이언트 측으로 전달
		// 각 번호의 쿠키가 여러개 생성이 된다. 1:1 , 2:2  
		Cookie cookie = new Cookie(bno,bno);
		cookie.setMaxAge(30);
		response.addCookie(cookie);

	}

}
