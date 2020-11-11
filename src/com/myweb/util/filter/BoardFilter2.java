package com.myweb.util.filter;

import java.io.IOException; 
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.valves.rewrite.RewriteCond;

import com.myweb.user.model.UserVO;

// 게시글 수정, 삭제에 대한 필터
@WebFilter({ "/board/modify.board", "/board/update.board", "/board/delete.board" })
public class BoardFilter2 implements Filter {
	// 1. 등록화면에서는 작성자를 id값으로 고정한다
	// 2. 각 요청으로 id가 parameter로 전달되는지 확인한다
	// 3. writer화면에서 작성자를 id값으로 고정
	// 4. modify.board요청과 , update.board, delete.board요청으로 넘어갈때 writer를 담아서 보내주도록
	// 처리한다

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		String writer = request.getParameter("writer");

		if (user == null) {
			res.sendRedirect("/ss/user/login.user");
			return;
		}
		String id = user.getId();
		// 권한이 없는경우
		if (writer == null || !id.equals(writer)) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다');");
			out.println("location.href='/ss/board/list.board';");
			out.println("</script>");
			return;
		}

		chain.doFilter(request, response);

	}

}
