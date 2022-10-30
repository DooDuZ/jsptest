package model.dao;

import model.dto.MemberDto;

public class MemberDao extends Dao {

	// singleton - 메모리 하나만 쓰기 위해서 [공유 메모리]
	private static MemberDao mdao = new MemberDao();

	public static MemberDao getInstance() {
		return mdao;
	}

	// 비아 - 1. 회원가입 메소드
	public boolean signup(MemberDto dto) {
		String sql = "insert into member (m_id,m_password,m_email,m_nick,m_img,m_profile) values(?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getM_id());
			ps.setString(2, dto.getM_password());
			ps.setString(3, dto.getM_email());
			ps.setString(4, dto.getM_nick());
			ps.setString(5, dto.getM_img());
			ps.setString(6, dto.getM_profile());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	// 비아 - 2. 아이디 중복체크
	public boolean idcheck(String m_id) {
		String sql = "select * from member where m_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			rs = ps.executeQuery();
			if (rs.next())
				return true;
		} catch (Exception e) {
			System.out.println("DB오류) " + e);
		}
		return false;
	}

	// 비아 - 3. 이메일 중복체크
	public boolean emailcheck(String m_email) {
		String sql = "select * from member where m_email=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_email);
			rs = ps.executeQuery();
			if (rs.next())
				return true;
		} catch (Exception e) {
			System.out.println("DB오류) " + e);
		}
		return false;
	}

	// 비아 - 4. 로그인
	public int login(String m_id, String m_password) {
		String sql = "select * from member where m_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			rs=ps.executeQuery();
			if(rs.next()) {
				sql = "select * from member where m_id=? and m_password=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, rs.getString(2));	//첫번째 select로 찾은 데이터를 대입
				ps.setString(2, m_password);
				rs=ps.executeQuery();
				if(rs.next()) return 1;		//로그인 성공
				return 2;					//패스워드가 틀렸다는 뜻
			}
		} catch (Exception e) {System.out.println(e); }	//DB 오류
		return 0;		//아이디가 없다는 뜻
	}
	

	// 비아 - 5. 회원아이디 -> 회원번호
	public int getNo(String m_id) {
		String sql = "select m_no from member where m_id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			rs = ps.executeQuery();
			if(rs.next()) { return rs.getInt(1); }
		}catch (Exception e) { System.out.println("DB오류) "+e); }
		return 0;
	}
	
	
	// 비아 - 6. 회원정보 불러오기
	public MemberDto getMember(String m_id) {
		//?대신에 변수를 넣을거면 문자표시('') 해줘야 함
		String sql = "select * from member where m_id=?";
		MemberDto dto = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			rs = ps.executeQuery();
			if(rs.next()) { 		//동일한 정보가 있으면 
				dto = new MemberDto();
				dto.setM_nick(rs.getString(6));
				dto.setM_img(rs.getString(7));
				dto.setM_profile(rs.getString(8));
			}
			return dto;
		}catch(Exception e) { System.out.println("DB오류) "+e);}
		return dto;
	}
	
	// 비아 - 7. 회원정보 수정
	//11. 회원정보 수정
	public boolean update(String m_id, String m_password, String m_nick, String m_profile, String m_img) {
		String sql = "update member set m_password=?,m_nick=?,m_profile=?,m_img=? where m_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_password);
			ps.setString(2, m_nick);
			ps.setString(3, m_profile);
			ps.setString(4, m_img);
			ps.setString(5, m_id);
			ps.executeUpdate();
			return true;
		}catch (Exception e) { System.out.println("DB오류) "+e); }
		return false;
	}
}
