package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UserDeleteServiceImpl implements UserService{
 
	
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		UserDAO dao = UserDAO.getInstance();
		
		String pw = request.getParameter("pw");
		
		//���̵�� ����
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");
		String id = vo.getId();
		
		// 1. �α��� �޼ҵ�� ��ȿ��Ȯ��
		UserVO result = dao.login(id, pw); // id pw Ȯ�� ���н� null��ȯ
		if(result != null) {
			dao.delete(id);
			session.invalidate();
			return 1;
		} else {
			return 0;
		}
		
		// delete ������ : 1 , ���н� : 0 
	}
}
