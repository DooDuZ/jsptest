package model.dao;

import java.util.ArrayList;

import model.dto.BoardDto;

public class BoardDao extends Dao {
	private static BoardDao bdao = new BoardDao();
	public static BoardDao getInstance() { return bdao; } 
	//1. 글등록 메소드 김장군
	public boolean write(String b_title,String b_content,String b_file ,int m_no) {
		String sql ="insert into board(b_title,b_content,b_file,m_no) values(?,?,?,?)";
		try {
		
			ps=con.prepareStatement(sql);
			ps.setString(1, b_title);
			ps.setString(2, b_content);
			ps.setString(3, b_file);
			ps.setInt(4, m_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}return false;		
	}
	//2.글 목록호출 메소드 김장군
	public ArrayList<BoardDto> getBoardList(){
		ArrayList<BoardDto> list = new ArrayList<>();
		
		String sql ="SELECT b_no, b_title, b_date, m_id from board b ,member m where b.m_no = m.m_no;";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(dto);	
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
		
	}
	// 3.개별 글 출력 김장군
	public BoardDto getBoard(int b_no) {
		String sql = "select b_no, b_title, b_content, m_id, b_file from board b , member m where   b.m_no=m.m_no and b.b_no="+b_no;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setB_no(b_no);
				dto.setB_title(rs.getString(2));
				dto.setB_content(rs.getString(3));
				dto.setM_id(rs.getString(4));
				dto.setB_file(rs.getString(5));
				return dto;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return null;
		
	}
	
	//4.글 삭제 김장군
	public boolean boardDelete(int b_no) {
		String sql = "delete from board where b_no="+b_no;
		try {
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
		
	}
	
	
	
	
}
