package com.josh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josh.dto.GuestBookDto;

import oracle.jdbc.pool.OracleDataSource;

@Repository
public class GuestBookDao {

	@Autowired
	private OracleDataSource oracleDataSource;

	public List<GuestBookDto> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookDto> list = new ArrayList<GuestBookDto>();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩, 2. Connection 얻어오기
			conn = oracleDataSource.getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select no, " + "     name, " + "     content, " + "		reg_Date " + "from GUESTBOOK ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				Date regDate = rs.getDate("reg_Date");

				GuestBookDto dto = new GuestBookDto(no, name, content, regDate);
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return list;
	}

	public int delete(GuestBookDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from guestbook " + "     where " + "     no = ? and password=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getPassword());
			count = pstmt.executeUpdate();
			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}

	public int insert(GuestBookDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into guestbook " + "     (no, name, password, content, reg_date) " + "     values"
					+ "     (seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getContent());
			count = pstmt.executeUpdate();
			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}

	public GuestBookDto findList(GuestBookDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GuestBookDto guestBookDto = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from guestbook " + "   where name=? AND password=? AND content=? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getContent());
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				Date regDate = rs.getDate("reg_Date");

				guestBookDto = new GuestBookDto(no, name, content, regDate);
				
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return guestBookDto;
	}

	public String getPwd(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String pwd = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT PASSWORD FROM GUESTBOOK " + "WHERE NAME = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				pwd = rs.getString("PASSWORD");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return pwd;
	}

	public int changePwd(GuestBookDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "UPDATE GuestBook SET " + "     password = ? " + "     WHERE " + "     NAME = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getName());
			count = pstmt.executeUpdate();
			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}
}
