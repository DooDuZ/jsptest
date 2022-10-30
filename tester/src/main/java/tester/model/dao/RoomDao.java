package tester.model.dao;

import java.sql.ResultSet;

import tester.model.dto.PlayerDto;

public class RoomDao extends Dao{

	static RoomDao dao = new RoomDao();
	public static RoomDao getInstance() {
		return dao;
	}
	ResultSet rs2;
	
	
	// [지웅] 20221030 유저 방 입장 메서드 -> index.jsp에서 Start Game 눌렀을 때 작동
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
	// [지웅] 20221030 유저 방 입장시 데이터 출력 -> Room 입장 후 게임 방정보에 표시용
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
				ps = con.prepareStatement(sql);
				ps.setString(1, m_id);
				rs2 = ps.executeQuery();
				if(rs2.next()) {
					dto.setS_no(rs.getInt(1));
				}
				return dto;
			} catch (Exception e) {
				System.out.println("게임 입장 유저 정보DB 오류" + e);
			}		
			return null;
		}
	
	// [지웅] 20221030 방 나가기 -> 나갈 때 html화면 유저슬롯 변경처리 되도록 수정 필요함
		// 20221031 boolean -> slot no 반환되도록 수정
	public int exitRoom(String m_id) {
		String sql = "select * from room where m_id = ?";
		int s_no = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				s_no = rs.getInt(1);
			}		
			sql = "update room set slot = false, m_id = null where m_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			ps.executeUpdate();
			return s_no;
		} catch (Exception e) {
			System.out.println("방 나가기 DB오류"+e);
		}
		return s_no;
	}
	
	
}
