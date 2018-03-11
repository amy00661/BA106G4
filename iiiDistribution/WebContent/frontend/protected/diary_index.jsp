<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/chatroom.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"><!-- �e���D�ncss-->
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

		 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">  		
	  	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
%>  
<%
%>		



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>TravelGO</title>
<style>
.body{
	font-family: Microsoft JhengHei;
}
</style>


</head>

<body bgcolor='white'>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid cus-center">
				<div class="navbar-brand">
					<a href="<%=request.getContextPath()%>/front_end/index/front_index.jsp" class="brand">
			        	<img alt="Brand" src="<%=request.getContextPath()%>/img/logo.png">
			      	</a>
				</div>

			
				<div class="sidemenu">
					<ul class="nav navbar-nav">
					<c:if test="${ memVO.mem_id!=null}">
							<li><a href="#">�z�n ${memVO.mem_name}</a></li>
							<li><a href="<%=request.getContextPath()%>/SignOut">�n�X</a></li>
					</c:if>
					<c:if test="${memVO.mem_id==null}">
						<li><a href='<%=request.getContextPath()%>/front_end//login.jsp'>�n�J</a></li>
					</c:if>
							<li><a href="#">�ӤH�]�w</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">�c�餤�� <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">�c�餤��</a></li>
								<li><a href="#">English</a></li>
								<li><a href="#">�饻�y</a></li>
							</ul>
						</li>
						<li><a href="#">�ӤH�]�w</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">�c�餤�� <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">�c�餤��</a></li>
								<li><a href="#">English</a></li>
								<li><a href="#">�饻�y</a></li>
							</ul>
						</li>
					</ul>	 
				</div>

				<button type="button" class="navbar-toggle ">
					<span class="sr-only">������</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
		</nav>
		
		<!-- ����menu-->
		<div id="menu"> 
			<ul class="links">
				<li><a href="<%=request.getContextPath()%>/front_end/act/listAllAct.jsp">�s������</a></li>
				<li><a href="../shop/shop.html">�s���ӫ�</a></li>
				<c:if test="${ memVO.mem_class_num==20}">
				<li><a href="elements.html">���ʺ޲z</a></li>
				<li><a href="elements.html">�ӫ~�޲z</a></li>
				<li><a href="<%=request.getContextPath()%>/front_end/act/act_edit.jsp">�إ߬���</a></li>
				<li><a href="<%=request.getContextPath()%>/front_end/att/addAtt.jsp">�إߴ��I</a></li>
				</c:if>
				<li><a href="elements.html">�s����x</a></li>
				<li><a href="elements.html">�n�ͺ޲z</a></li>
				<li><a href="elements.html">�b���޲z</a></li>
				<li><a href="elements.html">����ڭ�</a></li>
			</ul>
		</div>
		
			
			<!-- Body -->
			
		<!-- ����-->
		<div class="container-fluid wrapper"> 
			<div class="row content">
				<div class="col-sm-2 col-sm-offset-1 sidebar ">
	
					<!-- ??????-->
					
				</div>  

				<!-- content-->
				<div class="col-sm-7 ">

					<!-- top content -->
					<div id="top_pic" >
					</div>
					
					
					<!-- content ��x�d��-->
					<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="home">
						<h2 id="h2">�ڪ��峹</h2>
							<a href="diary_modify.jsp" style="position: relative;left: 200px;top: -40px;"><div class="glyphicon glyphicon-plus">�s�W�峹</div></a>
							<br>
					<c:forEach var="diaVO" items="${list}" begin="0" end="4">
						<div class="card preview">
							<a
								href="<%=request.getContextPath()%>/front_end/member/Retrieve?dia_name=${diaVO.dia_num}&mem_name=${memVO.mem_name}">
								<h1>
									<div>${diaVO.dia_name}</div>
								</h1>
								<div class="row">
									<div class="col-xs-12 col-sm-9">
										<h4>
											���:<div>${diaVO.dia_date}</div>
										</h4>
									</div>
									<div class="col-xs-12 col-sm-3">
										�@��:<div name="mem_name">${memVO.mem_name}</div>
									</div>
								</div>

							</a>
						</div>
					</c:forEach>

				</div>
				<div class="tab-pane fade" id="ios">
					<div class="card">
						<div class="row">
							<div class="col-xs-12">
								<h1>
									<p>����</p>
								</h1>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="produc">
					<div class="card">
						<div class="row">
							<div class="col-xs-12">
								<h1>
									<p>�ӫ~</p>
								</h1>
							</div>
						</div>
					</div>
				</div>
			</div>
					

			</div>
			<!-- footer -->			
				<div class="row footer">	
					<div class="col-sm-12 footer">BA106G3 �d�C�� �d�~ ���� �i�m�i ���ۿ�</div>
				</div>
	
		</div>
<!--===============CHAT ON BUTTON START===============-->			
<div class="container talk2">
	<div class="row">
	 <div id="Smallchat">
    <div class="Layout Layout-open Layout-expand Layout-right" style="background-color: #3F51B5;color: rgb(255, 255, 255);opacity: 5;border-radius: 10px;">
      <div class="Messenger_messenger">
        <div class="Messenger_header" style="background-color: rgb(22, 46, 98); color: rgb(255, 255, 255);">
          <h4 class="Messenger_prompt">��ѫ�</h4> <span class="chat_close_icon"><i class="fa fa-window-close" aria-hidden="true"></i></span> </div>
        <div class="Messenger_content">
		        <FORM METHOD="post" ACTION="" >
	    				<div class="input-group">
							  <input type="text" class="form-control" name="mem_num" value="�j�M�n��">
							  <span class="input-group-btn">
							    <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
							  </span>
						</div>
					    <input type="hidden" name="action" value="getOne_For_Display"><!--�e�X�����������|��Controller-->
				</FORM> 	
          <div class="Messages">
            <div class="Messages_list"></div>
          </div>
          <div class="Input Input-blank">
            <textarea class="Input_field" placeholder="Send a message..." style="height: 20px;"></textarea>
            <button class="Input_button Input_button-emoji">
              <div class="Icon" style="width: 18px; height: 18px;">
 
              </div>
            </button>
            <button class="Input_button Input_button-send">
              <div class="Icon" style="width: 18px; height: 18px;">

              </div>
            </button>
          </div>
        </div>
      </div>
    </div>
    <!--===============CHAT ON BUTTON STRART===============-->
    <div class="talk chat_on">
		<img src="<%=request.getContextPath()%>/img/icon-chat.png" class="img-responsive chat_on_icon">
	</div>	
    <!--===============CHAT ON BUTTON END===============-->
  </div>
</div>
</div> 
		
  <!--===============CHAT ON BUTTON END===============-->				
		
		<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<!-- <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script> -->
  		<script src="<%=request.getContextPath()%>/js/index.js"></script>
<script type="text/javascript">
			
$(document).ready(function(){
// 	$(".Layout").hide();
    $(".chat_on").click(function(){
        $(".Layout").toggle();
        $(".chat_on").hide(300);

    });
    
       $(".chat_close_icon").click(function(){
        $(".Layout").hide();
      	$(".chat_on").show();
    });
    
});
</script>
</body>
</html>