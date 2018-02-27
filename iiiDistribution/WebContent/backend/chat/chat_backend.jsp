<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- CSS個人套件 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/chat/chat.css">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"> -->
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js" integrity="sha256-0YPKAwZP7Mp3ALMRVB2i8GXeEndvCq3eSl/WsAl1Ryk=" crossorigin="anonymous"></script>
</head>
<!-- 第一版 -->
<body>
	<div class="container">
		<div class="row">
<!-- 				style="position: fixed; right:300px; top: 55px; width: 400px;" -->
			    <div id="chatroom">
			    </div>
				<div class="people-list" id="people-list">
					<div class="card card-inverse card-primary">
						<div class="card-header" id="plistAccordion">
							<span class="far fa-comments"></span> 聊天列表
							<div class="btn-group pull-right">
			                    <a type="button" class="btn btn-default btn-sm" data-toggle="collapse" data-parent="#plistAccordion" href="#collapsePlist">
			                        <span class="fas fa-angle-down"></span>
			                    </a>
			                </div>
						</div>
					</div>
					<div class="collapse" id="collapsePlist">
				      <div class="search">
				        <input type="text" placeholder="search" />
				        <i class="fa fa-search"></i>
				      </div>
				      <ul class="list">
				      
						<li class="clearfix">
				          <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_01.jpg" alt="avatar" />
				          <div class="about">
				            <div class="name">Vincent Porter</div>
				            <div class="status">
				              <i class="fa fa-circle online"></i> online
				            </div>
				          </div>
				        </li>
				        
				      </ul>
					</div>
				</div><!-- end people-list -->			
		           <!--  
		            <div class="card card-inverse card-primary">
		                <div class="card-header" id="chatAccordion">
		                    <span class="far fa-comments"></span> Chat
		                    <button type="button" class="close close-icon" aria-label="Close">
							  <span aria-hidden="true">&times;</span>
							</button>
		                </div>
		                <div class="card card-body">
		                    <ul class="chat">
		                        <li class="left clearfix">
		                        	<span class="chat-img pull-left"><img src="img/customer-icon.png" alt="User Avatar" class="img-circle" /></span>
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
		                        	<span class="chat-img pull-left"><img src="img/customer-icon.png" alt="User Avatar" class="img-circle" /></span>
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
		                <div class="card-footer">
		                    <div class="input-group">
		                        <input id="inputMessage" type="text" class="form-control input-sm" placeholder="Type your message here..." />
		                        <span class="input-group-btn">
		                            <input type="submit" id="sendMessage" class="btn btn-warning" value="送出" onclick="sendMessage();"/>
		                        </span>
		                    </div>
		                </div>
		        	</div> 
		        	-->
	    </div>
	</div>
<!-- 	<div id="draggable">Drag me</div> -->
 
	<script>
	var myName = "Customer_Service";
	var receiver = "";
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
			var msgTOchatroom = "";//訊息發送的聊天室對象
			if(jsonObj.sender == myName){//自己發送的訊息
				content = "<li class='right clearfix'>"
							+"<span class='chat-img pull-right'><img src='http://placehold.it/50/FA6F57/fff&text=ME' alt='User Avatar' class='img-circle' /></span>"
							+"<div class='chat-body clearfix'>"
								+"<div class='header'>"
									+"<small class=' text-muted'><span class='glyphicons glyphicons-time'></span>13 mins ago</small>"
									+"<strong class='pull-right primary-font'>員工:${account.emp_id}</strong>"
								+"</div>"
								+"<p>"+jsonObj.content+"</p>"
							+"</div>"
						+"</li>";
				msgTOchatroom = $('#ROOM_'+ jsonObj.receiver);
				msgTOchatroom.find('.chat').append(content);//將新訊息加入至指定的聊天室						
			}else{//客戶發送的訊息
				//將傳回的friends放入聊天列表(people-list)
				$.each(jsonObj.friends, function(index, element){
					var friendNameId = element.replace(/\s+/g, "");
					chatroomID = $('#ROOM_'+ friendNameId);//將客戶帳號去空白作為chatroomID
					//判斷這個friend的chatroom是否已存在，不存在則「加入聊天列表」、「建立chatroom」
					if(chatroomID.length == 0){	
						var friendInfo = "<li class='clearfix'>"
	          				+"<img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_01.jpg' alt='avatar' />"
	          				+"<div class='about'>"
	            				+"<div class='name'>"+ element +"</div>"
	            				+"<div class='status'>"
	              					+"<i class='fa fa-circle online'></i> online"
	            				+"</div>"
	          				+"</div>"
	        			+"</li>";
					    $('.list').append(friendInfo);
					    //$('.list li:last').dblclick(friend_dblclick);
					    //$( '.list li:last' ).bind( "dblclick", friend_dblclick );
					    create_chatroom( $('.list li:last') );						
					}
				});
				content = "<li class='left clearfix'>"
								+"<span class='chat-img pull-left'><img src='img/customer-icon.png' alt='User Avatar' class='img-circle' /></span>"
								+"<div class='chat-body clearfix'>"
									+"<div class='header'>"
										+"<strong class='primary-font'>會員:"+jsonObj.sender+"</strong>"
										+"<small class='pull-right text-muted'><span class='glyphicons glyphicons-time'></span>13 mins ago</small>"
									+"</div>"
									+"<p>"+jsonObj.content+"</p>"
								+"</div>"
							+"</li>";
				msgTOchatroom = $('#ROOM_'+ jsonObj.sender);
				msgTOchatroom.find('.chat').append(content);//將新訊息加入至指定的聊天室
			}
			//msgTOchatroom.find(".card-body").scrollTop(msgTOchatroom.find(".chat").prop("scrollHeight"));
			setScrollBar(msgTOchatroom);
		};
    }
    
	var sendMessage = function(){
		var inputMessage = $(event.target).parents('.card-footer').find('.inputMessage');
		var friendName = $(event.target).parents('.draggable').attr('id');
		receiver = friendName.substring(friendName.indexOf("_")+1);
		//receiver = friendName;//設定訊息發送對象
		var content = inputMessage.val().trim();
		if (content === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();
	    }else{
	    	var jsonObj = {"sender" : myName,"receiver" : receiver ,"content" : content,"comeFrom" : "backend", "friends" : []};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.val("");
	        inputMessage.focus();
	    }
    }
    
	window.onload = function(){
		console.log("網頁載入完畢!");
		connect();//建立WebSocket連線
		create_chatroom( $('.people-list ul li') );/* 範例資料:Vincent Porter  */
	};
	
	var right = 250;//指定新聊天視窗出現的位置
	function create_chatroom(li){
		right += 30;
		var friendName = li.find("div.name").text();
		var friendNameId = friendName.replace(/\s+/g, "");
		console.log(friendName);
		var content = "<div class='draggable' id='ROOM_"+ friendNameId + "' style='position: fixed;z-index:999; right:"+ right +"px; top: 55px; width: 400px;'>"
						+"<div class='card card-inverse card-primary'>"
			                +"<div class='card-header' id='chatAccordion'>"
			                    +"<span class='far fa-comments'>"+"</span> Chat with " + friendName
			                    +"<button type='button' class='close close-icon' aria-label='Close'>"
			                      +"<span aria-hidden='true'>&times;</span>"
			                    +"</button>"
			                +"</div>"
			                +"<div class='card card-body'>"
			                    +"<ul class='chat'>"
			                        +"<li class='left clearfix'>"
			                          +"<span class='chat-img pull-left'>"+"<img src='img/customer-icon.png' alt='User Avatar' class='img-circle' />"+"</span>"
			                            +"<div class='chat-body clearfix'>"
			                                +"<div class='header'>"
			                                    +"<strong class='primary-font'>Customer Service</strong>" +"<small class='pull-right text-muted'>"
			                                        +"<span class='glyphicons glyphicons-time'>"+"</span>12 mins ago</small>"
			                                +"</div>"
			                                +"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales.</p>"
			                            +"</div>"
			                        +"</li>"
			                        +"<li class='right clearfix'>"
			                            +"<span class='chat-img pull-right'>"+"<img src='http://placehold.it/50/FA6F57/fff&text=ME' alt='User Avatar' class='img-circle' />"+"</span>"
			                            +"<div class='chat-body clearfix'>"
			                                +"<div class='header'>"
			                                    +"<small class=' text-muted'>"+"<span class='glyphicons glyphicons-time'>"+"</span>13 mins ago</small>"
			                                    +"<strong class='pull-right primary-font'>Bhaumik Patel</strong>"
			                                +"</div>"
			                                +"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales."
			                                +"</p>"
			                            +"</div>"
			                        +"</li>"
			                        +"<li class='left clearfix'>"
			                          +"<span class='chat-img pull-left'>"+"<img src='img/customer-icon.png' alt='User Avatar' class='img-circle' />"+"</span>"
			                            +"<div class='chat-body clearfix'>"
			                                +"<div class='header'>"
			                                    +"<strong class='primary-font'>Customer Service</strong>" +"<small class='pull-right text-muted'>"
			                                        +"<span class='glyphicons glyphicons-time'>"+"</span>14 mins ago</small>"
			                                +"</div>"
			                                +"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales."
			                                +"</p>"
			                            +"</div>"
			                        +"</li>"
			                        +"<li class='right clearfix'>"
			                          +"<span class='chat-img pull-right'>"+"<img src='http://placehold.it/50/FA6F57/fff&text=ME' alt='User Avatar' class='img-circle' />"+"</span>"
			                            +"<div class='chat-body clearfix'>"
			                                +"<div class='header'>"
			                                    +"<small class=' text-muted'>"+"<span class='glyphicons glyphicons-time'>"+"</span>15 mins ago</small>"
			                                    +"<strong class='pull-right primary-font'>Bhaumik Patel</strong>"
			                                +"</div>"
			                                +"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales."
			                                +"</p>"
			                            +"</div>"
			                        +"</li>"
			                    +"</ul>"
			                +"</div>"
			                +"<div class='card-footer'>"
			                    +"<div class='input-group'>"
			                        +"<input type='text' class='form-control input-sm inputMessage' placeholder='Type your message here...' />"
			                        +"<span class='input-group-btn'>"
			                            +"<input type='submit' class='btn btn-warning sendMessage' value='送出' onclick='sendMessage();'/>"
			                        +"</span>"
			                    +"</div>"
			                +"</div>"
           				+"</div>"
           			+"</div>";
		$('#chatroom').append(content);
		$( ".draggable" ).draggable();
		$('#ROOM_'+ friendNameId).hide();
		
		li.bind( "dblclick", function(){
			show_chatroom($('#ROOM_'+ friendNameId));
			setScrollBar($('#ROOM_'+ friendNameId));
			
		});
		
		$('.close-icon').on('click',function() {
			  $(this).closest('.draggable').hide();
		});
		
		var inputMessage = $('#ROOM_'+ friendNameId).find('.inputMessage');
		inputMessage.keypress(function (e) {
			 var key = e.which;
			 if(key == 13){	// the enter key code
				 $('#ROOM_'+ friendNameId).find( ".sendMessage" ).click();
			    return false;  
			  }
		}); 
		
	}
	
	function show_chatroom(chatroomID){
		chatroomID.toggle();
	}
	//重設聊天室捲軸位置
	function setScrollBar(chatroomID){
		chatroomID.find(".card-body").scrollTop(chatroomID.find(".chat").prop("scrollHeight"));
	}
	</script>
    

</body>
</html>