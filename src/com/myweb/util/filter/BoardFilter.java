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

import com.myweb.user.model.UserVO;

// 1. Filter �������̽��� ��ӹ޴´�
// 2. doFilter �޼ҵ带 �������̵��Ѵ�
// 3. @webFilter ������̼� ��� or web.xml�� ���� ����
// 4. @WebFilter("/*") - ����û 
// 5. @WebFilter("*.board") - board�� ������ ����û
// 6. @WebFilter({"/board/write.board"}) - 1��  , @WebFilter({"/board/write.board", "/board/regist.board"}) - 2�� 
@WebFilter({ "/board/write.board", "/board/regist.board" })
public class BoardFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// ServletRequest�� HttpServletRequest�� �θ�Ÿ���̴�
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// ������ �� ���� Ȯ��
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("user");

		if (user == null) { // ������ �������� �ʴٴ� ���� ������ ����
			// res.sendRedirect("list.board");

			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('�α����� �ʿ��� ���� �Դϴ�');");
			out.println("location.href='/ss/user/login.user';");
			out.println("</script>");

			return; // ��Ʈ�ѷ��� ���������ʴ´�
		}

		chain.doFilter(request, response); // �����̳�, ����Ǿ� �ִ� �ٸ� ���͸� �����Ѵ�.

	}

}
