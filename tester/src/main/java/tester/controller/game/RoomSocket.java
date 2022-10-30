package tester.controller.game;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
// import model.dao.RoomDao;

import org.json.simple.JSONObject;

import tester.model.dao.RoomDao;
import tester.model.dto.PlayerDto;

import javax.websocket.*;


@ServerEndpoint("/room/RoomSocket/{m_id}")
public class RoomSocket {

	public static Map<Session, String> clients = new Hashtable<>();
	
	public void getPlayerInfo(String m_id) throws IOException{
		PlayerDto dto = RoomDao.getInstance().getPlayerInfo(m_id);
		System.out.println(dto.toString());
		
		JSONObject object = new JSONObject();
		object.put("s_no", dto.getP_no());
		object.put("m_nick", dto.getM_nick());
		object.put("m_img", dto.getM_img());
		object.put("wins", dto.getWins());
		object.put("total", dto.getTotal());
		object.put("function_name", "addPlayer");
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(object.toString());
		}
	}
	
	@OnOpen
	public void OnOpen( Session session, @PathParam("m_id") String m_id ) throws IOException  {
		clients.put(session, m_id);
		System.out.println(m_id+"입장");
		getPlayerInfo(m_id);
	}
	@OnClose
	public void OnClose( Session session ) throws IOException {
		RoomDao.getInstance().exitRoom(clients.get(session));
		clients.remove(session);
	}
	@OnMessage
	public void OnMessage( Session session, String object ) throws IOException{
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(object);
		}
	}
}