package tester.model.dao;

import tester.model.dto.PlayerDto;

public class RoomDao extends Dao{

	static RoomDao dao = new RoomDao();
	public static RoomDao getInstance() {
		return dao;
	}
	
	// 지웅[] 유저 방 입장 메서드
		// return 0 : 빈자리 없음, return -1 : 방에 같은 아이디의 유저 있음(이미 다른 브라우저로 참여한 아이디)
		// return 1~4 : 리턴하는 번호의 슬롯으로 입장
	public int enterRoom(String m_id) {
		String sql = "select * from room where m_id = ?";
		int slotNum = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return -1;
			}
			sql = "select * from room where slot = false";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {				// 첫번째 레코드의 슬롯 번호 반환
				slotNum = rs.getInt(1);
			}
			sql = "update room set slot = true, m_id = ? where s_No = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			ps.setInt(2, slotNum);
			ps.executeUpdate();
			return slotNum;
		} catch (Exception e) {
			System.out.println("방 입장 DB오류" + e);
		}
		return 0;
	}
	
	public boolean exitRoom(String m_id) {
		String sql = "update room set slot = false, m_id = null where m_id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("방 나가기 DB오류"+e);
		}
		return false;
	}
	
	public PlayerDto getPlayerInfo(String m_id) {
		PlayerDto dto = new PlayerDto();
		String sql = "select * from member where m_id = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setM_nick(rs.getString(6));
				dto.setM_img(rs.getString(7));
				dto.setWins(rs.getInt(9));
				dto.setTotal(rs.getInt(10));
			}
			sql = "select * from room where m_id =?";
			ps.setString(1, m_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("여기" + rs.getInt(1));
				dto.setP_no(rs.getInt(1));
			}
			return dto;
		} catch (Exception e) {
			System.out.println("게임 입장 유저 정보DB 오류" + e);
		}		
		return null;
	}
}
