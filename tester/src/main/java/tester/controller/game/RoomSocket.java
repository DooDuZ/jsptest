package tester.controller.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
// import model.dao.RoomDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tester.model.dao.RoomDao;
import tester.model.dto.PlayerDto;

import javax.websocket.*;


@ServerEndpoint("/room/RoomSocket/{m_id}")
public class RoomSocket {
	//20221031 01:29 ... 갑자기 많은것이 안되기 시작함
		// 일단 오늘은 포기ㅠㅠ
	
	
	// 지웅 20221030 방[서버]에 접속한 회원들의 정보 저장용 Hashtable
	public static Map<Session, String> clients = new Hashtable<>();
	
	// 지웅 20221030 OnOpen 실행 시 입장한 회원의 정보 불러와서 js로 전송
	public void getPlayerInfo() throws IOException{
		
		ArrayList<PlayerDto> list = RoomDao.getInstance().getPlayerInfo();
		JSONArray array = new JSONArray();
		
		for(int i = 0 ; i<list.size() ; i++) {
			JSONObject object = new JSONObject();
			object.put("s_no", list.get(i).getS_no());
			object.put("m_nick", list.get(i).getM_nick());
			object.put("m_img", list.get(i).getM_img());
			object.put("wins", list.get(i).getWins());
			object.put("total", list.get(i).getTotal());
			array.add(object);
		}
		JSONObject obj = new JSONObject();
		obj.put("data", array);
		obj.put("function_name", "addplayer");
		
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(obj.toString());
		}
	}
	
	// 지웅 20221031 OnClose 실행 시 나간 유저의 슬롯에 대입할 깡통 object를 전송
	
	/*
	public void getPlayerInfo(int s_no) throws IOException{		
		JSONObject object = new JSONObject();
		System.out.println(s_no);
		object.put("s_no", s_no);
		object.put("m_nick", "");
		object.put("m_img", "");
		object.put("wins", "");
		object.put("total", "");
		object.put("function_name", "addPlayer");
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(object.toString());
		}
	}
	 */
	
	// 지웅 20221030 유저 입장
	@OnOpen
	public void OnOpen( Session session, @PathParam("m_id") String m_id ) throws IOException  {
		clients.put(session, m_id);
		getPlayerInfo();
	}
	// 지웅 20221030 유저 퇴장
	@OnClose
	public void OnClose( Session session ) throws IOException {
		System.out.println(clients.get(session)+"퇴장");
		boolean result = RoomDao.getInstance().exitRoom(clients.get(session));
		getPlayerInfo();
		if(result) {
			clients.remove(session);
		}
	}
	
	// 지웅 20221030 js에서 send()함수로 서버 접근 시 서버 접속 중인 인원들에게 줄 정보를 js의 OnMessage로 전송
	@OnMessage
	public void OnMessage( Session session, String object ) throws IOException{
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(object);
		}
	}
}