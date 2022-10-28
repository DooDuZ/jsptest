package maminTest.socketTest;

import java.io.IOException;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.*;

@ServerEndpoint("/test/SocketTest")
public class SocketTest {
	private static final long serialVersionUID = 1L;

    public SocketTest() {
        super();
    }
    @OnOpen
	public void OnOpen( Session session, @PathParam("mid") String mid ) throws IOException { // @PathParam(경로상의 변수명) : 경로상의 변수 호출
		// Session -> js에서 생성된 웹소캣의 메모리 주소값
			// new 연산자를 통해 페이지가 열릴 때마다 생성되기 때문에, 들어올 때마다 주소값이 다르다.		
			// 그래서 @pathparam을 통해 주소에 변수로mid를 넘긴 후, @serverendpoint에 {}로 지정한 변수를 가져와서 식별값으로 hashtable에 저장한다 
    	System.out.println("test");
	}
	
	@OnClose	// 서버 소켓이 꺼지거나, 클라이언트 소켓이 닫기 요청 했을 때
	public void OnClose( Session session ) throws IOException {
		// 종료된 세션을 리스트에서 제거
	}
	@OnMessage
	public void OnMessage( Session session, String msg ) throws IOException{
		// 접속된 클라이언트 소켓을 하나씩 호출
	}
    
    public void sendmsg(JSONObject object) throws IOException {
		System.out.println(object);		
	}


}
