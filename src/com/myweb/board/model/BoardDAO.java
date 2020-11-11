package com.myweb.board.model;

import com.myweb.user.model.*; 
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	private DataSource ds;

	private BoardDAO() {
		try {
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//			 	커넥션 풀 
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BoardDAO getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// write_작성완료 -> 컨트롤러(execute실행 )-> 서버(execute 및 DAO실행) -> DAO
	public void regist(String writer , String title ,String content) {
		String sql = "insert into board(bno,writer,title,content) values(board_seq.nextval,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content); 
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		} 
	}
	

	// GetListServiceImpl = 게시판 목록 보여주는 메소드
//	public ArrayList<BoardVO> getList() {
//		ArrayList<BoardVO> list = new ArrayList<>();  
//		// 	DB에서 번호를 거꾸로 뒤집어서 가져와야 함
//		String sql = "select * from board order by bno desc";
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//	
//			while(rs.next()) {
//				BoardVO vo = new BoardVO(rs.getInt("bno"),
//						rs.getString("writer"),
//						rs.getString("title"),
//						rs.getString("content"),
//						rs.getTimestamp("regdate"),
//						rs.getInt("hit"));
//				list.add(vo);
//			}
//					
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  finally {
//			JdbcUtil.close(conn, pstmt, rs);
//		}
//
//	return list;
//
//	}
	
	
	
	// 게시글 페이지 조회
	public	ArrayList<BoardVO> getList(int pageNum, int amount) {
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql = "select *\r\n" + 
				"from (select rownum rn, bno,writer,title,content,regdate,hit \r\n" + 
				"    from (select * from board order by bno desc)\r\n" + 
				") where rn > ? and rn <= ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum-1)*amount); // rn > (페이지번호 -1) * 데이터 개수 
			pstmt.setInt(2, pageNum * amount);	// rn <= 페이지번호 * 데이터 개수
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
			BoardVO vo = new BoardVO(rs.getInt("bno"),
					rs.getString("writer"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getTimestamp("regdate"),
					rs.getInt("hit"));
			list.add(vo);
		}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		        
		return list;
	}
	
	public int getTotal( ) {
		int total = 0;
		
		String sql = "select count(*) as total from board";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("total");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return total;
		
	}
	
	
	
	// ContentServiceImpl = 게시판 제목 선택시 보여주는 내용들
	public BoardVO getContent(String bno) {
		BoardVO vo = new BoardVO();
			String sql ="select * from board where bno = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setBno(rs.getInt("bno"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setHit(rs.getInt("hit"));
				vo.setRegdate(rs.getTimestamp("regdate"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return vo;
	}
	
	public void update(String bno, String title, String content) {
		String sql = "Update board set title=?, content=? where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
	}
	
	// 게시글 삭제
	// content.jsp -> 삭제.form -> delete_Controller -> server -> DAO 
	// -> delete_controller -> list.board_controller -> board_list.jsp 
	public void delete(String bno) {
		String sql = "delete from board where bno=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bno);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	// 조회수 업데이트 메소드
	public void upHip(String bno) {
		String sql = "update board set hit = hit+1 where bno = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bno);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
}
