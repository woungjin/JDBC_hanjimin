package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UserUpdateServiceImpl implements UserService{
 
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		UserVO vo = new UserVO(id, pw, name, email, address, null);
		
		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.update(vo);
		
		System.out.println(vo.getId());
		System.out.println(vo.getName());
		System.out.println("result : " + result);
		request.setAttribute("user", vo);
		return result;
		
		
	}
}
