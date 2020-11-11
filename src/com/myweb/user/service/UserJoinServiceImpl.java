package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UserJoinServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		UserDAO dao = UserDAO.getInstance();
		int result = dao.checkId(id); // 중복시 1 리턴 , 없을시 0

		// id가 존재한다면
		if (result == 1) {
			return 1;
		} else {
			UserVO vo = new UserVO(id, pw, name, email, address, null);
			dao.join(vo); // 성공이라 가정
			return 0;
		}
	}

}
