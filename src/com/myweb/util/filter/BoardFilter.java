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

// 1. Filter 인터페이스를 상속받는다
// 2. doFilter 메소드를 오버라이딩한다
// 3. @webFilter 어노테이션 방법 or web.xml에 필터 설정
// 4. @WebFilter("/*") - 모든요청 
// 5. @WebFilter("*.board") - board로 끝나는 모든요청
// 6. @WebFilter({"/board/write.board"}) - 1개  , @WebFilter({"/board/write.board", "/board/regist.board"}) - 2개 
@WebFilter({ "/board/write.board", "/board/regist.board" })
public class BoardFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// ServletRequest은 HttpServletRequest의 부모타입이다
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 세션은 얻어서 권한 확인
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("user");

		if (user == null) { // 세션이 존재하지 않다는 것은 권한이 없다
			// res.sendRedirect("list.board");

			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스 입니다');");
			out.println("location.href='/ss/user/login.user';");
			out.println("</script>");

			return; // 컨트롤러를 실행하지않는다
		}

		chain.doFilter(request, response); // 서블릿이나, 연결되어 있는 다른 필터를 실행한다.

	}

}
