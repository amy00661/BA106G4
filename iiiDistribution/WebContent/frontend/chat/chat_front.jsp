<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- CSS個人套件 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/chat.css">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div id="chatroom">
		         <div class="card card-inverse card-primary">
		             <div class="card-header" id="chatAccordion">
		                 <span class="far fa-comments"></span> Chat
		                 <div class="btn-group pull-right">
		                     <a type="button" class="btn btn-default btn-sm" data-toggle="collapse" data-parent="#chatAccordion" href="#collapseChat">
		                         <span class="fas fa-angle-down"></span>
		                     </a>
		                 </div>
		             </div>
			         <div class="collapse" id="collapseChat">
			             <div class="card card-body">
			                 <ul class="chat">
			                     <li class="left clearfix">
			                     	<span class="chat-img pull-left"><img src="img/customer-service.png" alt="User Avatar" class="img-circle" /></span>
			                         <div class="chat-body clearfix">
			                             <div class="header">
			                                 <strong class="primary-font">Customer Service</strong> <small class="pull-right text-muted">
			                                     <span class="glyphicons glyphicons-time"></span>12 mins ago</small>
			                             </div>
			                             <p>
			                                 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
			                                 dolor, quis ullamcorper ligula sodales.
			                             </p>
			                         </div>
			                     </li>
			                     <li class="right clearfix">
			                     <span class="chat-img pull-right"><img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" /></span>
			                         <div class="chat-body clearfix">
			                             <div class="header">
			                                 <small class=" text-muted"><span class="glyphicons glyphicons-time"></span>13 mins ago</small>
			                                 <strong class="pull-right primary-font">Bhaumik Patel</strong>
			                             </div>
			                             <p>
			                                 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
			                                 dolor, quis ullamcorper ligula sodales.
			                             </p>
			                         </div>
			                     </li>
			                     <li class="left clearfix">
			                     	<span class="chat-img pull-left"><img src="img/customer-service.png" alt="User Avatar" class="img-circle" /></span>
			                         <div class="chat-body clearfix">
			                             <div class="header">
			                                 <strong class="primary-font">Customer Service</strong> <small class="pull-right text-muted">
			                                     <span class="glyphicons glyphicons-time"></span>14 mins ago</small>
			                             </div>
			                             <p>
			                                 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
			                                 dolor, quis ullamcorper ligula sodales.
			                             </p>
			                         </div>
			                     </li>
			                     <li class="right clearfix">
			                     	<span class="chat-img pull-right"><img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" /></span>
			                         <div class="chat-body clearfix">
			                             <div class="header">
			                                 <small class=" text-muted"><span class="glyphicons glyphicons-time"></span>15 mins ago</small>
			                                 <strong class="pull-right primary-font">Bhaumik Patel</strong>
			                             </div>
			                             <p>
			                                 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
			                                 dolor, quis ullamcorper ligula sodales.
			                             </p>
			                         </div>
			                     </li>
			                 </ul>
			             </div>
			             <div class="panel-footer">
			                 <div class="input-group">
			                     <input id="inputMessage" type="text" class="form-control input-sm" placeholder="Type your message here..." />
			                     <span class="input-group-btn">
			                         <input type="submit" id="sendMessage" class="btn btn-warning" value="送出" onclick="sendMessage();"/>
			                     </span>
			                 </div>
			             </div>
			         </div>
		         </div>
			</div>
		</div>
	</div>	
	<script>
	var myName = "${memVO.member_id}";
	var recevier = "Customer_Service";
    var MyPoint = "/MyChatServer/"+myName;
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
    function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			console.log("WebSocket 成功連線");
		};
		
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			var content = "";
			if(jsonObj.sender == myName){
				content = "<li class='right clearfix'>"
									+"<span class='chat-img pull-right'><img src='http://placehold.it/50/FA6F57/fff&text=ME' alt='User Avatar' class='img-circle' /></span>"
									+"<div class='chat-body clearfix'>"
										+"<div class='header'>"
											+"<small class=' text-muted'><span class='glyphicons glyphicons-time'></span>13 mins ago</small>"
											+"<strong class='pull-right primary-font'>會員:"+myName+"</strong>"
										+"</div>"
										+"<p>"+jsonObj.content+"</p>"
									+"</div>"
								+"</li>";
			}else{
				content = "<li class='left clearfix'>"
									+"<span class='chat-img pull-left'><img src='img/customer-service.png' alt='User Avatar' class='img-circle' /></span>"
									+"<div class='chat-body clearfix'>"
										+"<div class='header'>"
											+"<strong class='primary-font'>客服人員</strong>"
											+"<small class='pull-right text-muted'><span class='glyphicons glyphicons-time'></span>13 mins ago</small>"
										+"</div>"
										+"<p>"+jsonObj.content+"</p>"
									+"</div>"
								+"</li>";
			}
			$('.chat').append(content);
		};
    }
    
	function sendMessage() {
		var inputMessage = $('#inputMessage');
		var content = inputMessage.val().trim();
		if (content === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();
	    }else{
	    	var jsonObj = {"sender" : myName,"receiver" : recevier ,"content" : content,"comeFrom" : "frontend","friends" : [] };
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.val("");
	        inputMessage.focus();
	    }
    }
    
	window.onload = function(){
		connect();//建立WebSocket連線
		$('#inputMessage').keypress(function (e) {
			 var key = e.which;
			 if(key == 13){	// the enter key code
				 $( "#sendMessage" ).click();
			    return false;  
			  }
		}); 
		
		$( ".collapse#collapseChat" ).on( 'shown.bs.collapse','.card', function() {
			/* var messagesArea = document.getElementById("collapseChat");
			messagesArea.scrollTop = messagesArea.scrollHeight; */
			//$('#collapseChat').scrollTop($('#collapseChat').prop("scrollHeight"));
			setScrollBar($('#collapseChat'));
		});
     }
	function setScrollBar(chatroomID){
		chatroomID.find(".card-body").scrollTop(chatroomID.find(".chat").prop("scrollHeight"));
	}
    </script>
</body>
</html>