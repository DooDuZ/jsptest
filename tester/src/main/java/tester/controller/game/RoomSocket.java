package tester.controller.game;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
// import model.dao.RoomDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tester.model.dao.RoomDao;
import tester.model.dto.MemberDto;

import javax.websocket.*;


@ServerEndpoint("/room/RoomSocket/{m_id}")
public class RoomSocket {
	//20221031 01:29 ... 갑자기 많은것이 안되기 시작함
		// 일단 오늘은 포기ㅠㅠ
	
	// 지웅 20221030 방[서버]에 접속한 회원들의 정보 저장용 Hashtable
	public static Map<Session, String> clients = new Hashtable<>();
	
	// DB에 있는 room table을 여기서 vector로 바로 끝낼 수 있지 않을까?
		// 대기방->게임화면으로 넘어가면 기존 session값이 유지되지 않을 가능성이 높으니 vector로 분리해준다.
	private static Vector<MemberDto> players = new Vector<>();
	/*
		1. 게임소켓class를 만들고 필드값으로 Vector<PlayerDto> players = new Vector<>();
			ㄴ 지금은 test용 playerDto를 쓰고있지만, vector가 index값을 지원하므로 memberdto를 그대로 긁어서 사용해도 좋을 것 같다.
	 	2. 해당 Vector를 컨트롤할 setter 매서드 생성
	 	3. RoomSocket에 첫번째 OnOpen이 발생하고 players vector가 만들어지면, setter를 통해 같은 vector 주소를 입력?
	 		or 모든 플레이어가 입장하고 모두 ready상태이면, 그때 setter를 실행한다? 매서드 실행한다는게 말이여 방구여...
	 		머가먼지 몰르겠음니다.
	 	4. 방을 1개만 사용할 거라면, 대기방->게임화면으로 들어가도 메인 화면에서 새로운 유저들이 게임방에 접속하지 못하도록
	 	   게임중 / 시작대기 상태를 표시해줄 변수가 필요할 것 같다.
	 	   변수 저장 위치는 session으로 해서 start game버튼을 막아주는게 가장 좋지 않을까?
	 	   이거 맞니...?
	 	   
	 	   고민은 의미가 없다. 일단 DB -> Vector 변경 작업 시작
	 	   
	 	23:30
	 	db->vector로 유저 데이터를 옮기면서 index.jsp -> room.jsp로 페이지 전환할 때 사용하던 playerCount 서블렛이 의미가 없어졌다
	 	vector는 크기가 가변적이므로 map의 size를 통해서 입장 유저 수를 컨트롤할 필요가 있음
	 	index에서 websocket을 만들어 serverEndpoint로 접근시 자동으로 OnOpen이 실행되므로 지금 작성한 코드대로면 여러가지가 복잡해질 수 있다...
	 	예를들면
	 	1. index->room으로 페이지전환 되면서 유저 추가가 한 번
	 	2. 그리고 room에서 js가 작동하며 생성되는 websocket에서 한 번
	 	
	 	session값이 같다는 보장이 있다면 hashtable의 특성상 중복 키가 저장되진 않겠으나 그럼에도 불필요한 동작이 발생한다는 점에서 추가적인 오류 발생 가능성이 있고,
	 	페이지가 달라졋으므로 @ServerEndpoint가 제공하는 session값이 달라져 같은 아이디가 중복저장되는 불상사가 발생할 수도 있다. 
	 	
	 	보다 안전한 처리를 위해
	 	index->room으로의 전환은 start game 버튼 클릭시 무조건 되게하고, 
	 	onOpen 실행 시 clients.size()를 검사하는 매서드를 만들어서
	 	값이 4 이상인 경우 자동으로 다른 페이지로 전환시켜줘야할 것 같다.
	 	
	 	23:53
	 	index.jsp 의 button onclick 함수 변경 checkroom() -> enterRoom();
	 	index.js의 checkroom() 함수 주석처리
	 	userOverflow() 작성 후 onopen에 코드 추가
	 	
	 	지웅 20221101 12:13 db->vector전환 완료...? 아마도.. 완료  
	 	브라우저 크롬/웨일/엣지 세개여서... useroverflow 못해봤음. 작동 정상적으로 하는지 확인 필요
	 	
	 	기능 구현 필요한 부분
	 	1. 대기방 입장 유저 ready 체크 
	 		-> ready btn으로 변경 후 누를 때마다 ...준비중... <-> ready 변경되도록 만들고, innerHTML이 모두 ready이면 10초 후 게임 시작
	 	2. 채팅 / 장군이가 적용 예정 / 아마 send 구현해놓은 게 있어서 그거 쓰면 금방 될듯
	 	
	 	
	*/
	
	// 지웅 20221031 23:50 추가
		// 유저 수 4명 이상일 시 room->index.jsp로 내보내기
	public void UserOverflow(Session session) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("function_name", "exit");
		session.getBasicRemote().sendText(obj.toString());
	}
	

	
	// 지웅 20221030 OnOpen 실행 시 입장한 회원의 정보 불러와서 js로 전송
		// 지웅 20221031 유저 1명 정보 -> 전체로 변경
			// 지웅 20221031 db 테이블 일부 정보 -> vector로 변경
	public void getPlayerInfo(String m_id) throws IOException{
		
		MemberDto dto = RoomDao.getInstance().getPlayerInfo(m_id);
		players.add(dto);
		JSONArray array = new JSONArray();
		for(int i = 0 ; i<players.size() ; i++) {
			JSONObject object = new JSONObject();
			object.put("s_no", i+1);
			object.put("m_no", players.get(i).getM_no());
			object.put("m_id", players.get(i).getM_id());
			object.put("m_nick", players.get(i).getM_nick());
			object.put("m_img", players.get(i).getM_img());
			object.put("wins", players.get(i).getWins());
			object.put("total", players.get(i).getTotal());
			array.add(object);
		}
		JSONObject obj = new JSONObject();
		obj.put("data", array);
		obj.put("function_name", "addplayer");	
		
		for(MemberDto tmp : players) {
			System.out.println(tmp.toString());
		}
		
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(obj.toString());
		}
	}
	
	
	// 지웅 20221031 23:21 onclose 작동 시 vector 정보 js로 전송. getPlayerInfo(String m_id) 오버로딩
	public void getPlayerInfo() throws IOException {
		JSONArray array = new JSONArray();
		for(int i = 0 ; i<players.size() ; i++) {
			JSONObject object = new JSONObject();
			object.put("s_no", i+1);
			object.put("m_nick", players.get(i).getM_nick());
			object.put("m_img", players.get(i).getM_img());
			object.put("wins", players.get(i).getWins());
			object.put("total", players.get(i).getTotal());
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
		if(clients.size()>=4) {
			UserOverflow(session);
		}		
		clients.put(session, m_id);
		getPlayerInfo(m_id);
	}
	// 지웅 20221030 유저 퇴장
	@OnClose
	public void OnClose( Session session ) throws IOException {
		System.out.println(clients.get(session));
		for(int i = 0; i<players.size(); i++) {
			System.out.println("지워볼까!");
			System.out.println("아이디는"+players.get(i).getM_id());
			if(players.get(i).getM_id().equals(clients.get(session))) {
				System.out.println("지웠다!");
				players.remove(i);
				break;
			}
		}
		clients.remove(session);
		System.out.println("못지웠다!");
		getPlayerInfo();
	}
	
	// 지웅 20221030 js에서 send()함수로 서버 접근 시 서버 접속 중인 인원들에게 줄 정보를 js의 OnMessage로 전송
	@OnMessage
	public void OnMessage( Session session, String object ) throws IOException{
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(object);
		}
	}
}