package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.test.dto.Dto;

public class Dao {

	DataSource dataSource;

	public Dao() {
		System.out.println("Connectiong database...");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/paging");
			System.out.println("Database connection success");
		} catch (NamingException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
	}

	// 사용자가 요청한 페이지번째(requestPage : offset)와 페이지당 표시할 게시글의 수(numOfTuplePerPage : limit)을 매개변수로 받는다.
	public ArrayList<Dto> list(int requestPage, int numOfTuplePerPage) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<Dto> dtos = new ArrayList<Dto>();
		// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력
		String query = "SELECT no, writer, title, updateDate FROM paging ORDER BY no DESC LIMIT ?, ?";
		// page는 1부터 시작하지만, offset은 0부터 시작.(0~9(10개), 10~19(10개)와같이 offset을 설정해야 하기 때문)
		int offset = requestPage - 1;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			// 0을 나누면 에러가 발생하므로 예외처
			if (offset == 0) {
				psmt.setInt(1, offset);
			} else {
				psmt.setInt(1, offset * numOfTuplePerPage);
			}
			psmt.setInt(2, numOfTuplePerPage);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				Timestamp date = rs.getTimestamp("updateDate");

				Dto dto = new Dto(no, title, writer, date);
				dtos.add(dto);
				System.out.println("list-data load success");
			}
		} catch (Exception e) {
			System.out.println("list-data load fail");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< rs, psmt, conn close success>");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
			}
		}

		return dtos;
	}

	// list에서 사용하는 릴레이션이 가진 튜플의 총 갯수를 리턴한다.
	public int countTuple() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		String query = "SELECT COUNT(*) FROM paging";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
				System.out.println("list-count success");
			}
		} catch (Exception e) {
			System.out.println("list-count fail");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< rs, psmt, conn close success>");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
			}
		}

		return count;
	}
	
	//detail view를 위해 튜플의 모든 정보를 가져온다.
	public Dto content(int contentNo) {
		Dto dto = null;
		String query = "SELECT * FROM paging where no = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, contentNo);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("updateDate");
				String filePath = rs.getString("filePath");

				dto = new Dto(no, title, writer, content, date, filePath);
				System.out.println("<content-data load success>");
			}

		} catch (Exception e) { 
			System.out.println("<content-data load Fail>");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
				e.printStackTrace();
			}
		}

		return dto;
	}
	
	//DB에 정보를 입력. 모든값이 null 허용입니다. 실제구현시엔 jsp에서 null값을 체크해서 꼭 필요한 값은 넣게끔 해야합니다.
	public void write(String title, String writer, String content, String filePath) {

		String query = "INSERT INTO paging (title, writer, content, updateDate, filePath) VALUES (?, ?, ?, now(), ?)";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);

			psmt.setString(1, title);
			psmt.setString(2, writer);
			psmt.setString(3, content);
			psmt.setString(4, filePath);

			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("<data insert Fail>");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
	}
	
	//기존의 튜플을 수정하는 부분입니다.
	//실 구현시에 기존 데이터를 기록해두어야 하는 테이블이 있다는 점은 꼭 명심해주세요.
	public void update(int no, String title, String writer, String content, String filePath) {
		String query1 = "UPDATE paging SET ";
		String query2 = "title = ?, writer = ?, content = ?, filePath = ? ";
		String query3 = "WHERE no = ?";
		String query = query1 + query2 + query3;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);

			psmt.setString(1, title);
			psmt.setString(2, writer);
			psmt.setString(3, content);
			psmt.setString(4, filePath);
			psmt.setInt(5, no);

			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("<data insert Fail>");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
	}
	//조건을 만족하는 튜플을 삭제합니다.
	//실 구현시에 기존 데이터를 기록해두어야 하는 테이블이 있다는 점은 꼭 명심해주세요.
	public void delete(int no) {
		String query = "DELETE FROM paging WHERE no = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, no);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("<data delete Fail>");
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null)
					psmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
	}
	
}
