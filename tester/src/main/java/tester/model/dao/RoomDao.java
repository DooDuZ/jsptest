package tester.model.dao;

import java.sql.ResultSet;

import tester.model.dto.MemberDto;

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
		//수정중
			// player 한명 -> player[room에 있는 id]전체
			// 지웅 20221031 room 접속자 db -> 엔드포인트의 vector로 변경
		public MemberDto getPlayerInfo(String m_id) {
			MemberDto dto = new MemberDto();
			String sql = "select * from member where m_id = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, m_id);
				rs = ps.executeQuery();
				if(rs.next()) {
					dto.setM_no(rs.getInt(1));
					dto.setM_id(rs.getString(2));
					dto.setM_email(rs.getString(4));
					dto.setM_nick(rs.getString(6));
					dto.setM_img(rs.getString(7));
					dto.setWins(rs.getInt(9));
					dto.setTotal(rs.getInt(10));
				}
				return dto;
			} catch (Exception e) {
				System.out.println("게임 입장 유저 정보DB 오류" + e);
			}
			return null;
		}
	
	// [지웅] 20221030 방 나가기 -> 나갈 때 html화면 유저슬롯 변경처리 되도록 수정 필요함
		// 20221031 boolean -> slot no 반환되도록 수정
		// 20221031 13:41 유저 출력방식 변경으로 다시 boolean return하도록 변경
		// 20221031 23:08 db room table -> vector로 옮기면서 필요 없어짐. 일단 삭제하지않고 보류 
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
}
