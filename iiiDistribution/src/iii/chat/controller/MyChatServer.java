package iii.chat.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import iii.chat.model.ChatMsgVO;


@ServerEndpoint("/MyChatServer/{userName}")
public class MyChatServer {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	private static Map<String, Set<String>> friendsMap = new ConcurrentHashMap<>();//所有發送訊息的請求客戶
	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
		String text = String.format(
				"Session ID = %s, connected; userName = %s%nmaxTextMessageBufferSize = %s",
				userSession.getId(), userName, userSession.getMaxTextMessageBufferSize());
		System.out.println(text);
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("Message received: " + message);
		ChatMsgVO chatMessage = gson.fromJson(message, ChatMsgVO.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		//將receiver加進sender的好友列表
		if(friendsMap.containsKey(sender)){
			friendsMap.get(sender).add(receiver);
		}else{
			Set<String> friends = new HashSet<>();
			friends.add(receiver);
			friendsMap.put(sender,friends);
		}
		//將sender加進receiver的好友列表
		if(friendsMap.containsKey(receiver)){
			friendsMap.get(receiver).add(sender);
		}else{
			Set<String> friends = new HashSet<>();
			friends.add(sender);
			friendsMap.put(receiver,friends);
		}
		chatMessage.getFriends().addAll(friendsMap.get(receiver));//設定傳回receiver的好友列表
		String respMessage = gson.toJson(chatMessage);
		Session receiverSession = sessionsMap.get(receiver);
		Session senderSession = sessionsMap.get(sender);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(respMessage);
			senderSession.getAsyncRemote().sendText(respMessage);
		}
		System.out.println("Message response: " + respMessage);
		StringBuilder friends = new StringBuilder();
		//列出發送者的好友列表
		for(String friend : friendsMap.get(sender)){
			friends.append(friend);
			friends.append('、');
		}
		System.out.println(friends);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		System.out.println("Error: " + e.toString());
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		Set<String> userNames = sessionsMap.keySet();	
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				sessionsMap.remove(userName);
				friendsMap.remove(userName);//移除此user的好友列表
				removeFriendFromSet(userName);//將此user從其他人的好友列表中移除
			}
		}
		
		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
	
	public void removeFriendFromSet(String userName){
		for(Map.Entry<String, Set<String>> friend : friendsMap.entrySet()) {
			if(friend.getValue().contains(userName)){
				friend.getValue().remove(userName);
			}
		}
	}
}
