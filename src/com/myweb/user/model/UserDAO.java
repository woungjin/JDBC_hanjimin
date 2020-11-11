package com.myweb.user.model;

import com.myweb.user.model.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class UserDAO {

	private static UserDAO instance = new UserDAO();
	private DataSource ds;
	
	private UserDAO() {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		 	커넥션 풀 
			InitialContext ct = new InitialContext();
			ds =(DataSource)ct.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserDAO getInstance() {
		return instance;
	}

//	private String url = "JDBC:oracle:thin:@localhost:1521/XEPDB1";
//	private String uid = "JSP";
//	private String upw = "JSP";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// insert into
	public int join(UserVO vo) {

		int result = 0; // 결과값 반환
		String sql = "insert into users(id,pw,name,email,address) values(?,?,?,?,?)";

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return result;
	}

	// id 중복 검사 중복시 1 , 없을시 0
	public int checkId(String vo) {
		int result = 0;

		String sql = "select * from users where id = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return result;
	}

	UserVO u = new UserVO();

	// 로그인 id , pw 있는지 확인후 1명 객체 반환 
	public UserVO login(String id, String pw) {

		String sql = "select * from users where id = ? and pw = ?";
		UserVO u = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				u = new UserVO();
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setAddress(rs.getString("address"));
				u.setRegdate(rs.getTimestamp("regdate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return u;

	}

	
	// 회원정보 수정 = update
	public int update(UserVO vo) {
		String sql  = "update users set pw=? ,name=?,email=?,address=? where id=?";
		int result = 0 ;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw() );
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getId());
			
			 result = pstmt.executeUpdate(); 
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		return result;
	}
	
	
	public int delete(String id) {
		
		String sql = "delete users where id = ?";
		int res = 0 ;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return res;
	}

}
