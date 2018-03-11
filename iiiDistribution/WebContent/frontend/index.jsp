<!DOCTYPE html>
<%@	page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="iii.mem.model.*"%>
<%@ page import="iii.news.model.*"%>
<%@ page import="iii.pro.model.*"%>

<%
	// 	MemVO memVO=(MemVO) request.getAttribute("memVO");
		NewsService newsSvc=new NewsService();
		NewsVO newsVO = newsSvc.getOneNews("NEWS001");
		pageContext.setAttribute("newsVO", newsVO);
		ProService proSvc = new ProService();
		ProVO proVO = proSvc.getOnePro("PMT003");
		pageContext.setAttribute("proVO", proVO);
%>


<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>index</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">
<link href="<%=request.getContextPath()%>/frontend/css/main.css" rel="stylesheet">
<!-- ========================================================基本套件 -->
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"
	integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4"
	crossorigin="anonymous"></script>
<!-- =======================彈跳視窗============================ -->	
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css" rel="stylesheet">  
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css" rel="stylesheet">  
<script src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>  
<!-- ======================彈跳視窗======================= -->
	
<!-- =================================================== -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script language="javascript">
    $(document).ready(function(){
		
		//會員登入之後顯示會員登入成功訊息
    		var name="${memVO.member_name}";
    		var flagPass="${flagPass}";
    		var flagNoMail="${flagNoMail}";
    		var flagStopMail="${flagStopMail}";
    		
    		if(name!=""){
    			$("#myalert").display=""
    			$("#myalert").delay(100).toggle(500);
    			$("#myalert").delay(2500).toggle(500);
    		}
    		
    		if(flagPass=="no" && flagStopMail!="no"){
    			$("#passalert").display=""
    			$("#passalert").delay(100).toggle(500);
    			$("#passalert").delay(2500).toggle(500);
    		}
    		
    		if(flagNoMail=="no"){
    			$("#MailNoalert").display=""
    			$("#MailNoalert").delay(100).toggle(500);
    			$("#MailNoalert").delay(2500).toggle(500);
    		}
    		
    		if(flagStopMail=="no"){
    			$("#MailStopalert").display=""
    			$("#MailStopalert").delay(100).toggle(500);
    			$("#MailStopalert").delay(2500).toggle(500);
    		}
    		
    		$("#mail").blur(function(){
    			$.ajax({
    				type : "post",
    				url : "<%=request.getContextPath()%>/mem/checkMail.do",
				data : {
					action : "checkMail2",
					mail : $("#mail").val()
				},
				success : function(msg) {
					if ($("#mail").val() != "") {
						if (msg == "no") {
							$("#mailresult").html("無此帳號(信箱)");
						} else if (msg == "stop") {
							$("#mailresult").html("此帳號(信箱)被停權");
							$("#btnlogin").attr('disabled', true);
						} else {
							$("#mailresult").html("");
							$("#btnlogin").removeAttr("disabled");
						
					}
					}else{
						$("#btnlogin").removeAttr("disabled");
					}
				}
			});
		});
		$("#customCheck1").change(function() { //神奇小按鈕
			$("#mail").val("aaa999@yahoo.com.tw");
			$("#psw").val("abc123456");
		})
	})
</script>


<!-- =================================================== -->
</head>

<body>
	<!-- 聊天室Start -->
	<c:if test="${memVO != null}">
		<div class="chatDiv" style="position: fixed;z-index:1; right: 0px; top: 0px;">	
			<jsp:include page="/frontend/chat/chat_front.jsp" flush="true" />
		</div>
	</c:if>
	 <!-- 聊天室End -->


	<header class="container py-3 header-cus">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 text-center"></div>
			<div class="col-4 text-center">
				<!--  LOGO -->
				<div>
					<a class="navbar-brand" href="<%=request.getContextPath()%>/frontend/index.jsp"><img
						src="<%=request.getContextPath()%>/frontend/img/IIICAR5.png"
						height="150"></a>
				</div>
			</div>
			<div class="col-4 d-flex justify-content-end align-items-center">
			</div>
		</div>
	</header>
	<!--           WebSocket Test -->
				  <div id="messages">
	  			  </div>
	<!--           WebSocket Test -->
	<!--登入成功 or失敗 顯示登入訊息        -->
	<div id="myalert" class="alert alert-dismissable alert-success"
		style="display: none">
		<button type="button" class="close" data-dismiss="alert">×</button>
		Welcome ${memVO.member_name}
	</div>
	<div id="passalert" class="alert alert-dismissable alert-danger"
		style="display: none">
		<button type="button" class="close" data-dismiss="alert">×</button>
		密碼錯誤!
	</div>
	<div id="MailNoalert" class="alert alert-dismissable alert-danger"
		style="display: none">
		<button type="button" class="close" data-dismiss="alert">×</button>
		無此帳號!
	</div>
	<div id="MailStopalert" class="alert alert-dismissable alert-danger"
		style="display: none">
		<button type="button" class="close" data-dismiss="alert">×</button>
		此帳號(信箱)被停權!
	</div>



	<!-- navbar -->
	<div class=container>
		<nav class="navbar navbar-expand-lg navbar-light nav-cus">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarsExample10" aria-controls="navbarsExample10"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-md-center"
				id="navbarsExample10">
				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">服務項目</a>
						<div class="dropdown-menu my-3" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="#">關於我們</a> <a
								class="dropdown-item" href="#">配送服務</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">寄件申請</a>
						<div class="dropdown-menu my-3" aria-labelledby="dropdown10">
							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/frontend/order_main/order_main.jsp">線上申請寄件</a>
							<a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/video.jsp">寄件指南</a> <a
								class="dropdown-item" href="<%=request.getContextPath()%>/frontend/transport_fee/fee.jsp">運費查詢</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">相關查詢</a>
						<div class="dropdown-menu my-3" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/order_main/queryOrder.jsp">訂單查詢</a> <a
								class="dropdown-item" href="#">據點查詢</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">客服中心</a>
						<div class="dropdown-menu my-3" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="#">常見問題</a> <a
								class="dropdown-item" href="#">聯絡我們</a>
						</div></li>

			<%
				String str = request.getContextPath();
				pageContext.setAttribute("str", str+"/frontend/logOut.jsp");
			%>
					
					<li class="nav-item"><a class="nav-link" id="dropdown10"
						href=${ (memVO==null) ? "#" : str }
						data-toggle=${ (memVO==null) ? "modal" :""}
						data-target="#myModal">${ (memVO==null) ? "會員登入" : "登出" }</a></li>
				</ul>
			</div>
		</nav>
	</div>
<!-- navbar -->

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<!-- 跑馬燈 -->

				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="first-slide"
								src="<%=request.getContextPath()%>/frontend/img/p5.jpg"
								alt="First slide">
							<div class="container">
								<div class="carousel-caption text-left">
									<h4>無論颳風下雨</h4>
									
								</div>
							</div>
						</div>
						<div class="carousel-item">
							<img class="second-slide" src="<%=request.getContextPath()%>/frontend/img/p9.jpg" alt="Second slide">
							<div class="container">
								<div class="carousel-caption text-left">
									<h3>無論您在何處</h3>
									<!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">點此報名</a></p> -->
								</div>
							</div>
						</div>
						<div class="carousel-item">
							<img class="third-slide"
								src="<%=request.getContextPath()%>/frontend/img/06.jpg"
								alt="Third slide">
							<div class="container">
								<div class="carousel-caption text-top">
									<strong></strong><h1 style="color: #f2f2f2">我們時刻用心,為了將貨物送達您的手上</h1></strong>
									
									<!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p> -->
								</div>
							</div>
						</div>
					</div>
					<a class="carousel-control-prev" href="#myCarousel" role="button"
						data-slide="prev"> <span class="carousel-control-prev-icon"
						aria-hidden="true"></span> <span class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#myCarousel" role="button"
						data-slide="next"> <span class="carousel-control-next-icon"
						aria-hidden="true"></span> <span class="sr-only">Next</span>
					</a>
				</div>
			</div>

			<div class="col-xs-12 col-sm-3 member-cus" align="center">
				<hr>

				<i class="fa fa-users fa-3x ic" style=${ (memVO==null) ? "color:black": "color:#33cc33" }></i>

				<p>${ (memVO==null) ? "會員專區" : memVO.member_name }</p>
				<div>
					<a class="btn btn-warning btn-block"
<%-- 						href=${ (memVO==null) ? "http://localhost:8081/BA106G4/frontend/mem/addMember.jsp" : "http://localhost:8081/BA106G4/frontend/mem/memData.jsp" } --%>
						href=${ (memVO==null) ? "mem/addMember.jsp": "mem/memData.jsp" }
						role="button">${ (memVO==null) ? "註冊會員 &raquo" : "會員資訊" }</a>
				</div>
				<hr>

				<h3>訂單查詢</h3>
				<div>
					<form method="post" action="<%=request.getContextPath()%>/order_main/order.do" name="form1">
						<div class="input-group">
		     				<input type="hidden" name="action" value="getOne_For_Display_Frontend">
							<input type="text" class="form-control" placeholder="請輸入訂單編號" name="order_id">
							<div class="input-group-append">
								<button type="submit" class="btn btn-secondary">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
				<br>
				<h4>
					<a class="btn btn-success btn-block"
						href="<%=request.getContextPath()%>/frontend/transport_fee/fee.jsp"
						 role="button">運費查詢 &raquo;</a>
				</h4>
				<hr>
				<h4>
					<a class="btn btn-success btn-block DBLocation" href="#" role="button">據點查詢
						&raquo;</a>
				</h4>
			</div>
		</div>
	</div>




	<!-- ================================================== -->


	<div class="container">

<%-- 								<h3>${ (memVO==null) ? "成為會員" : memVO.member_name }</h3> --%>
		<%-- 						<p>${ (memVO==null) ? "將為您提供更多更棒的服務" : "您好!歡迎使用本系統" }</p> --%>
		<!--  <p><a class="btn btn-warning" href="<%=request.getContextPath()%>/frontend/mem/addMember.jsp" role="button">點選進入申請 &raquo;</a></p>-->

		<hr>
		<!-- 最新消息start-->
		<div class="card-deck">
			<div class="card card-cus">
				<img class="card-img-top"
					src="<%=request.getContextPath()%>/frontend/img/04.jpg" width="150"
					height="150" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">入境機場「行李宅配」8折優惠</h5>
					<p class="card-text">活動辦法：旅客憑國泰航空、國泰港龍航空當日之香港/東京/大阪/名古屋/首爾回台北班機登機證，於桃園機場宅配通服務櫃檯出示，即可享有行李宅配到府8折優惠。</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">2018-03-01</small>
				</div>
			</div>
			<div class="card card-cus">
				<img class="card-img-top"
					src="<%=request.getContextPath()%>/newsPic/newsPic.do?news_id=${newsVO.news_id}" width="150"
					height="150" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">${newsVO.news_title}</h5>
					<p class="card-text">${newsVO.news_context}</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">${newsVO.news_date}</small>
				</div>
			</div>
			<div class="card card-cus">
				<img class="card-img-top"
					src="<%=request.getContextPath()%>/proPic/proPic.do?promotion_id=${proVO.promotion_id}" width="150"
					height="150" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">${proVO.promotion_title}</h5>
					<p class="card-text">${proVO.promotion_context}</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">${proVO.promotion_date}</small>
				</div>
			</div>
		</div>

		<!-- 最新消息end -->
		<br>
		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">


				<h2 class="featurette-heading">超快速的專業物流服務</h2>
				<p class="lead">
					我們為不同行業的製造商、通路商及零售商，提供完善的端到端、點到點及戶到戶的供應鏈管理。 <br> <br>
					在廣泛的客戶服務及行業需求基礎上，我們於各行各業的供應鏈管理、物流及資訊科技模式中，累積了豐富經驗和專業知識。
					我們提供無可比擬的解決方案，讓客戶盡享更快捷、更具經濟效益的物流服務。
				</p>


			</div>
			<div class="col-md-5">
				<img class="featurette-image img-fluid mx-auto"
					src="<%=request.getContextPath()%>/frontend/img/p3.jpg">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7 order-md-2">
				<h2 class="featurette-heading">服務設備</h2>

				<p class="lead">

					<strong>行動派遣管理系統</strong><br> 透過行動派遣管理系統，加強人員車輛派遣調度，提昇整體服務品質。<br>
					<br> <strong>自動分貨系統</strong><br>
					商品以自動分貨機處理，讓商品更安全完整，效率更高更精確。<br> 光學字元辨識（OCR），提高託運單資料準確度。<br>
					<br> <strong>託運單影像掃描設備</strong><br>
					使用託運單影像掃描設備，讓客戶在網路除了查詢配送狀況外，還可查詢託運的簽收單影像。<br> <br> <strong>HT手持刷碼設備</strong><br>
					使用HT手持刷碼設備進行貨品管理作業，讓商品得到完整的歷程。 <br> <strong>網路即時貨況查詢</strong><br>
					網站提供即時的配送資訊，讓客戶可輕易查詢到配送的最新狀況。<br>
				</p>
			</div>
			<div class="col-md-5 order-md-1">
				<img class="featurette-image img-fluid mx-auto"
					src="<%=request.getContextPath()%>/frontend/img/p8.jpg">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">長途運輸</h2>
				<p class="lead">
					提供端對端長途運輸服務，範圍涵蓋台灣全省。<br> <br>
					資策貨運貨運能在交通繁雜的台灣地區提供完善的長途運輸服務，端靠遍及全省的樞紐輻射式配送網絡，以及各區域緊密配合的合作夥伴（協力車隊）。<br>
					<br>
					我們擁有及管理一支現代化的貨櫃車車隊，以20呎和40呎長的貨櫃車，為您提供高效率、具成本效益及準時的運輸服務。絕大部分貨櫃車均裝設了全球定位系統及監控設備，確保可從出發點至目的地，24小時及全程追踪和監控您的貨件。
				</p>
			</div>
			<div class="col-md-5">
				<img class=" featurette-image img-fluid mx-auto"
					src="<%=request.getContextPath()%>/frontend/img/p2.jpg">
			</div>
		</div>

		<hr class="featurette-divider">

		<!-- /END -->
	</div>
	<!-- /.container -->


	<!-- FOOTER -->
	<footer class="container">
		<p class="float-right">
			<a href="#">回到上面</a>
		</p>
		<p>
			2018/1/21 版本 <i class="fa fa-github-alt"></i>
		</p>
	</footer>

	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="modal-title font-weight-bold text-white">登入帳號</h4>
				</div>

				<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<div class="modal-body">
					<form method="post"
						action="<%=request.getContextPath()%>/mem/checkMail.do"
						name="form1">
						<div class="form-group">
							<label for="mail"><h5>
									<p class="font-weight-bold">
										<i class="fa fa-envelope"></i> 信箱:
									</p>
								</h5></label> <span id="mailresult" class="font-weight-bold"
								style="color: red"></span> <input id="mail" class="form-control"
								type="email" name="member_mail" required>
						</div>
						<div class="form-group">
							<label for="psw"><h5>
									<p class="font-weight-bold">
										<i class="fa fa-lock"></i> 密碼:
									</p>
								</h5></label> <span id="pswresult" class="font-weight-bold"
								style="color: red"></span> <input id="psw" class="form-control"
								type="password" name="member_psw" required>
						</div>

						<div class="modal-footer">
							<input type="hidden" name="action" value="login">
							<button class="btn btn-primary btn-block" type="submit"
								id="btnlogin">
								<h4>
									<i class="fa fa-user"> </i>登入
								</h4>
							</button>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-9">
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input"
											id="customCheck1"> <label
											class="custom-control-label" for="customCheck1">記住我</label>
									</div>
								</div>
								<div class="col-3" style="text-align: right;">
									<a href="#">忘記密碼</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript -->

		<!-- 廣告視窗  Test -->
		<div id="abgne_float_ad" >
			<span class="abgne_close_ad">關閉廣告 [X]</span>
			<a href="#">
				<img src="<%=request.getContextPath()%>/frontend/img/IIICAR5.png" title="資策會運"
				width="150" height="150" />
			</a>
		</div>
		
		
<style type="text/css">
	#abgne_float_ad {
	display: none;
	position: absolute;
}
#abgne_float_ad .abgne_close_ad {
	display: block;
	text-align: right;
	cursor: pointer;
	font-size: 12px;
}
#abgne_float_ad a img {
	border: none;
}
  		
	</style> 
	<script type="text/javascript">
$(window).on('load',function(){
	var $win = $(window),
		$ad = $('#abgne_float_ad').css('opacity', 0).show(),	// 讓廣告區塊變透明且顯示出來
		_width = $ad.width(),
		_height = $ad.height(),
		_diffY = 20, _diffX = 20,	// 距離右及下方邊距
		_moveSpeed = 800;	// 移動的速度
 
	// 先把 #abgne_float_ad 移動到定點
	$ad.css({
		top: $(document).height(),
		left: $win.width() - _width - _diffX,
		opacity: 1
	});
 
	// 幫網頁加上 scroll 及 resize 事件
	$win.bind('scroll resize', function(){
		var $this = $(this);
 
		// 控制 #abgne_float_ad 的移動
		$ad.stop().animate({
			top: $this.scrollTop() + $this.height() - _height - _diffY,
			left: $this.scrollLeft() + $this.width() - _width - _diffX
		}, _moveSpeed);
	}).scroll();	// 觸發一次 scroll()
	$('#abgne_float_ad .abgne_close_ad').click(function(){
		$ad.hide();
	});
});
	</script>	
	
		
		<!-- 廣告視窗  Test -->



	<!--   建立websocket連線 -->
	 <script type="text/javascript">
	    
	  var MyPoint = "/MyEchoServer";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	  
	  var webSocket =
		  new WebSocket(endPointURL);//ServerEndpoint監聽的URL.
	 
	    webSocket.onerror = function(event) {
	      onError(event)
	    };
	 
	    webSocket.onopen = function(event) {
	      onOpen(event)
	    };
	 
	    webSocket.onmessage = function(event) {
	      onMessage(event)
	    };
	//  接收到server訊息時觸發.
	    function onMessage(event) {
		
	    var member = "${memVO.member_id}";
	    var jsonObj = JSON.parse(event.data);
	    var memberEcho = jsonObj.member_id;
	    var memberStatus = jsonObj.member_status;
	    
	    
			if(member==memberEcho && memberStatus==00){
				alertify.alert('你已經被停權了~滾!');
			}
	    }
	//  建立與server的連接.
	    function onOpen(event) {
	    }
	//  連線錯誤時觸發
	    function onError(event) {
	      alert(event.data);
	    }
	//  按下按鈕後觸發,發送訊息給server
	    function start() {
	      return false;
	    }
	
	    $(".DBLocation").click(function(){
	        window.open("<%=request.getContextPath()%>/frontend/DBLocation.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1200,height=950");
	      });
	  </script>
</body>
</html>