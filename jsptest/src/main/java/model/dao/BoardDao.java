package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.dto.BoardDto;

public class BoardDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public BoardDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jsptest",
					"root", 
					"1234");
			System.out.println("DB연동 성공");
		} catch (Exception e) {
			System.out.println("DB연결 오류" + e);
		}
	}
	
	public boolean write(String title, String content, String writer, String password) {
		String sql = "insert into board values (null, ?, ?, ?, ?, now(), 0);";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, writer);
			ps.setString(4, password);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("글등록 DB오류" + e);
		}		
		return false;
	}
	
	public ArrayList<BoardDto> getlist() {
		ArrayList<BoardDto> list = new ArrayList();
		String sql = "select * from board";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setcNo(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setCdate(rs.getString(6));
				dto.setView(rs.getInt(7));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println("글등록 DB오류" + e);
		}		
		return list;
	}
	
	public BoardDto getboard(int cNo) {
		BoardDto dto = new BoardDto();
		String sql = "select * from board where cNo = ?;";
		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, cNo);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setcNo(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setCdate(rs.getString(6));
				dto.setView(rs.getInt(7));
			}
			sql = "update board set cView = cView+1 where cNo = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cNo);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("글보기 DB오류"+e);
		}
		return dto;
	}
}
