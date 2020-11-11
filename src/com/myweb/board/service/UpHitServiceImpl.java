package com.myweb.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class UpHitServiceImpl implements BoardService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		// ��Ű�� �� �Խ��� ��ȣ�� �°� ������ ���ְ� ��Ű�� ��ȣ�� �Խ����� ��ȣ�� �����ϸ� �ߺ��̱⿡ ��ȸ�� ������ �ȵȴ� 
		Cookie[] arr = request.getCookies();
		boolean flag = true;
		if(arr!=null) {
			for(Cookie c : arr ) {
				if(c.getName().equals(bno)) {	// ��Ű �̸��� �Խñ���ȸ��ȣ�� �������� �˻� = �ߺ�
					flag = false;
					break;
				}
			}
		}
		
		if(flag ) { // �ߺ��� ���ٸ� true 
			BoardDAO dao = BoardDAO.getInstance();
			dao.upHip(bno);
		}
		// ��Ű�� �̿��ؼ� ��ȸ�� ��ȣ�� Ŭ���̾�Ʈ ������ ����
		// �� ��ȣ�� ��Ű�� ������ ������ �ȴ�. 1:1 , 2:2  
		Cookie cookie = new Cookie(bno,bno);
		cookie.setMaxAge(30);
		response.addCookie(cookie);

	}

}
