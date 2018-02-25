package iii.chat.model;

public class ChatMsgVO {
	private String sender;
	private String receiver;
	private String content;
	private String comeFrom;
	
	
	public ChatMsgVO(String sender, String receiver, String content, String comeFrom) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.comeFrom = comeFrom;
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
	
	
	
}
