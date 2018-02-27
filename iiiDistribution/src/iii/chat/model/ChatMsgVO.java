package iii.chat.model;

import java.util.HashSet;

public class ChatMsgVO {
	private String sender;
	private String receiver;
	private String content;
	private String comeFrom;
	private HashSet<String> friends = new HashSet<>();
	
	public ChatMsgVO(String sender, String receiver, String content, String comeFrom,HashSet<String> friends) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.comeFrom = comeFrom;
		this.friends = friends;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public HashSet<String> getFriends() {
		return friends;
	}
	
}
