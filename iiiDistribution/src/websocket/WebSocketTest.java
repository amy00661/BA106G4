package websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.server.PathParam;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer")//監聽client連接的URL.
public class WebSocketTest {
	
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	//收到client訊息便執行此方法.
	//印出client所發送的訊息,傳送訊息給client.
		
	@OnOpen//client開啟連接.
	  public void onOpen(Session userSession) {
		allSessions.add(userSession);
	    System.out.println("Client connected");
	  }
	
	@OnMessage
	  public void onMessage(String message, Session userSession)
	    throws IOException, InterruptedException {
			for (Session session : allSessions) {
				if (session.isOpen())
						session.getAsyncRemote().sendText(message);
					}
				System.out.println("Message received: " + message);	
			}
	  @OnClose//client關閉連接.
	  public void onClose() {
	    System.out.println("Connection closed");
	  }
	  
	  @OnError
	  public void onError(Session session, Throwable thr) {}
}
