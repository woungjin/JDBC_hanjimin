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

// �Խñ� ����, ������ ���� ����
@WebFilter({ "/board/modify.board", "/board/update.board", "/board/delete.board" })
public class BoardFilter2 implements Filter {
	// 1. ���ȭ�鿡���� �ۼ��ڸ� id������ �����Ѵ�
	// 2. �� ��û���� id�� parameter�� ���޵Ǵ��� Ȯ���Ѵ�
	// 3. writerȭ�鿡�� �ۼ��ڸ� id������ ����
	// 4. modify.board��û�� , update.board, delete.board��û���� �Ѿ�� writer�� ��Ƽ� �����ֵ���
	// ó���Ѵ�

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
		// ������ ���°��
		if (writer == null || !id.equals(writer)) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('������ �����ϴ�');");
			out.println("location.href='/ss/board/list.board';");
			out.println("</script>");
			return;
		}

		chain.doFilter(request, response);

	}

}
