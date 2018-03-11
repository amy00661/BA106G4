<%@	page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="iii.mem.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 

// 	MemVO memVO=(MemVO) request.getAttribute("memVO");
%>
<html>

<head>
    <meta charset="utf-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <link href="<%=request.getContextPath()%>/datetimepicker/main.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/datetimepicker/mem.css" rel="stylesheet">
    <!-- ========================================================基本套件 -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <!-- =================================================== -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
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
    				data : {action:"checkMail2",mail:$("#mail").val()},
    				success : function(msg){
    					if($("#mail").val()!=""){
    						if(msg=="no"){
    							$("#mailresult").html("無此帳號(信箱)");
    						}else if(msg=="stop"){
    							$("#mailresult").html("此帳號(信箱)被停權");
    						}else{
    							$("#mailresult").html("");
    						}
    					}else{
   						 $("#mailresult").html("");
						}
    				}
    			});
    		});
    		$("#customCheck1").change(function(){					//神奇小按鈕
    			$("#mail").val("aaa999@yahoo.com.tw");
    			$("#psw").val("abc123456");
    		})
    	})
    	
	</script>
    <!-- =================================================== -->
  </head>

<body>
  
 <header>
      <div class="container logoCus">
        <div class="row" align="center">
          <div class="col-xs-12 col-sm-4">
            

          </div>
          <div class="col-xs-12 col-sm-4">
            <!-- LOGO -->
            <div>
              <a class="navbar-brand" href="index.jsp"><img src="<%=request.getContextPath()%>/frontend/img/IIICAR5.png" height="150"></a>
            </div>
            <div class="input-group">
              <input  class="form-control" type="text" placeholder="站內搜尋">
              <div class="input-group-append">
              <button type="submit" class="btn btn-secondary"><i class="fa fa-search"></i></button>
              </div>
            </div>

          </div>
          <div class="col-xs-12 col-sm-4">
          
<!--           WebSocket Test -->
           	  <div>
			    <input type="submit" value="Start" onclick="start()" />
			  </div>
			  <div id="messages">
  			  </div>
<!--           WebSocket Test -->
          </div>
        </div>
      </div>
		<!--登入成功 or失敗 顯示登入訊息        -->
		<div id="myalert" class="alert alert-dismissable alert-success" style="display:none">
			<button type="button" class="close" data-dismiss="alert">×</button>
				Welcome ${memVO.member_name}
		</div>
		<div id="passalert" class="alert alert-dismissable alert-danger" style="display:none">
			<button type="button" class="close" data-dismiss="alert">×</button>
		 		密碼錯誤!
		</div>
		<div id="MailNoalert" class="alert alert-dismissable alert-danger" style="display:none">
			<button type="button" class="close" data-dismiss="alert">×</button>
		 		無此帳號!
		</div>
		<div id="MailStopalert" class="alert alert-dismissable alert-danger" style="display:none">
			<button type="button" class="close" data-dismiss="alert">×</button>
		 		此帳號(信箱)被停權!
		</div>
      
        <!-- navbar -->
      <nav class="navbar navbar-expand-lg navbar-light bg-cus rounded">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">服務項目</a>
              <div class="dropdown-menu" aria-labelledby="dropdown10">
                <a class="dropdown-item" href="#">關於我們</a>
                <a class="dropdown-item" href="#">配送服務</a>
                <a class="dropdown-item" href="#">契約客戶</a>
                <a class="dropdown-item" href="#">到府服務</a>
                <a class="dropdown-item" href="#">QR CODE認證收貨</a>
                <a class="dropdown-item" href="#">即時追蹤</a>
              </div>
            </li>

            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">寄件申請</a>
              <div class="dropdown-menu" aria-labelledby="dropdown10">
                <a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/order_main/order_main.jsp">線上申請寄件</a>
                <a class="dropdown-item" href="#">寄件指南</a>
                <a class="dropdown-item" href="cal.html">運費查詢</a>
              </div>
            </li>

            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">相關查詢</a>
              <div class="dropdown-menu" aria-labelledby="dropdown10">
                 <a class="dropdown-item" href="order_inq.html">訂單查詢</a>
                <a class="dropdown-item" href="#">據點查詢</a>
                <a class="dropdown-item" href="#">包裹地圖追蹤</a>
              </div>
            </li>

            <li class="nav-item dropdown" >
              <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">客服中心</a>
              <div class="dropdown-menu" aria-labelledby="dropdown10">
                <a class="dropdown-item" href="#">常見問題</a>  
                <a class="dropdown-item" href=<%=request.getContextPath()%>/protected/select_MemPage.jsp>聯絡我們</a>
              </div>
            </li>
 
           	  <li class="nav-item">
              <a class="nav-link" id="dropdown10" href=${ (memVO==null) ? "#" : "http://localhost:8081/BA106G4/frontend/logOut.jsp" }  data-toggle=${ (memVO==null) ? "modal" : "" } data-target="#myModal">${ (memVO==null) ? "會員登入" : "登出" } </a>

            </li>
         
            
          </ul>
        </div>
      </nav>
     
    </header> 
    
 	
 
<!-- 跑馬燈 -->
    <main role="main">
      <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img class="first-slide" src="<%=request.getContextPath()%>/frontend/img/p5.jpg" alt="First slide">
            <div class="container">
              <div class="carousel-caption text-left">
                <h1>做網頁豪豪玩</h1>
                <p>有沒有覺得報錯班了? 前端工程師養成班歡淫你</p>
                <p><a class="btn btn-lg btn-primary" href="#" role="button">點此馬上報名</a></p>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <img class="second-slide" src="<%=request.getContextPath()%>/frontend/img/p9.jpg" alt="Second slide">
            <div class="container">
              <div class="carosel-caption text-left">
                <h1>用此系統，生意做很大</h1>
                <p>日本市場也送貨</p>
                <!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">點此報名</a></p> -->
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <img class="third-slide" src="<%=request.getContextPath()%>/frontend/img/hd2.jpg" alt="Third slide">
            <div class="container">
              <div class="carousel-caption text-right">
                <h1>恩........</h1>
                <p>這一段不知道要打什麼</p>
                <!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p> -->
              </div>
            </div>
          </div>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>


      <!-- ================================================== -->
     

      <div class="container marketing">
        <!-- Three columns of text below the carousel -->
        <div class="row" align="center">
      <!-- 1===== -->
          <div class="col-lg-3">
            <i class="fa fa-users fa-5x ic"></i>
            <h3>${ (memVO==null) ? "成為會員" : memVO.member_name }</h3>
            <p>${ (memVO==null) ? "將為您提供更多更棒的服務" : "您好!歡迎使用本系統" }</p>						 														
          <!--  <p><a class="btn btn-warning" href="<%=request.getContextPath()%>/frontend/mem/addMember.jsp" role="button">點選進入申請 &raquo;</a></p>-->
            <p><a class="btn btn-warning" href=${ (memVO==null) ? "http://localhost:8081/BA106G4/frontend/mem/addMember.jsp" : "http://localhost:8081/BA106G4/frontend/mem/memData.jsp" } role="button">${ (memVO==null) ? "點選進入申請 &raquo" : "會員資訊" }</a></p>
          </div><!-- /.col-lg-4 -->
																
          
      <!-- 2===== -->
          <div class="col-lg-3">
            <i class="fa fa-truck fa-5x ic"></i>
            <h3>貨車快速查詢</h3>
            <p>迅速查詢包裹位置</p>
            <form>
            <div class="input-group">
              <input type="text" class="form-control" placeholder="請輸入訂單編號">
              <div class="input-group-append">
                <button type="submit" class="btn btn-secondary"><i class="fa fa-search"></i></button>
              </div>
            </div>
            </form>
          </div>
      <!-- 3===== -->
          <div class="col-lg-3">
            <i class="fa fa-search fa-5x ic"></i>
            <h3>訂單查詢快速</h3>
            <p>查詢您的貨物狀態</p>
            <form>
            <div class="input-group">
              <input type="text" class="form-control" placeholder="請輸入訂單編號">
              <div class="input-group-append">
                <button type="submit" class="btn btn-secondary"><i class="fa fa-search"></i></button>
              </div>
            </div>
            </form>
          </div>
      <!-- 4===== -->
          
          <div class="col-lg-3">
            <h3>其他服務</h3>
            
              <h5><a href="<%=request.getContextPath()%>/frontend/transport_fee/fee.jsp"" role="button">運費查詢 &raquo;</a></h5>
              <hr>
              <h5><a href="#" role="button">據點查詢 &raquo;</a></h5>
              <hr>
              <h5><a href="#" role="button">聯絡我們 &raquo;</a></h5>
              <hr>
          </div>

        <!-- /.row -->
        </div>
        <!-- START THE FEATURETTES -->

		<!-- 最新消息start-->
		<div class="card-deck">
		  <div class="card">
		    <img class="card-img-top" src="<%=request.getContextPath()%>/frontend/img/02.jpg" width="150" height="150" alt="Card image cap">
		    <div class="card-body">
		      <h5 class="card-title">Card title</h5>
		      <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
		    </div>
		    <div class="card-footer">
		      <small class="text-muted">Last updated 3 mins ago</small>
		    </div>
		  </div>
		  <div class="card">
		    <img class="card-img-top" src="<%=request.getContextPath()%>/frontend/img/05.jpg" width="150" height="150" alt="Card image cap" alt="Card image cap">
		    <div class="card-body">
		      <h5 class="card-title">Card title</h5>
		      <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
		    </div>
		    <div class="card-footer">
		      <small class="text-muted">Last updated 3 mins ago</small>
		    </div>
		  </div>
		  <div class="card">
		    <img class="card-img-top" src="..." alt="Card image cap">
		    <div class="card-body">
		      <h5 class="card-title">Card title</h5>
		      <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
		    </div>
		    <div class="card-footer">
		      <small class="text-muted">Last updated 3 mins ago</small>
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
            我們為不同行業的製造商、通路商及零售商，提供完善的端到端、點到點及戶到戶的供應鏈管理。
            <br>
            <br>
            在廣泛的客戶服務及行業需求基礎上，我們於各行各業的供應鏈管理、物流及資訊科技模式中，累積了豐富經驗和專業知識。
            我們提供無可比擬的解決方案，讓客戶盡享更快捷、更具經濟效益的物流服務。</p>
          
          
          </div>
          <div class="col-md-5">
            <img class="featurette-image img-fluid mx-auto" src="<%=request.getContextPath()%>/frontend/img/p3.jpg">
          </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
          <div class="col-md-7 order-md-2">
            <h2 class="featurette-heading">服務設備</h2>

            <p class="lead">

              <strong>行動派遣管理系統</strong><br>
              透過行動派遣管理系統，加強人員車輛派遣調度，提昇整體服務品質。<br>
              <br>
              <strong>自動分貨系統</strong><br>
              商品以自動分貨機處理，讓商品更安全完整，效率更高更精確。<br>
              光學字元辨識（OCR），提高託運單資料準確度。<br>
              <br>
              <strong>託運單影像掃描設備</strong><br>
              使用託運單影像掃描設備，讓客戶在網路除了查詢配送狀況外，還可查詢託運的簽收單影像。<br>
              <br>
              <strong>HT手持刷碼設備</strong><br>
              使用HT手持刷碼設備進行貨品管理作業，讓商品得到完整的歷程。
              <br>
              <strong>網路即時貨況查詢</strong><br>
              網站提供即時的配送資訊，讓客戶可輕易查詢到配送的最新狀況。<br>
            </p>
          </div>
          <div class="col-md-5 order-md-1">
            <img class="featurette-image img-fluid mx-auto" src="<%=request.getContextPath()%>/frontend/img/p8.jpg" >
          </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
          <div class="col-md-7">
            <h2 class="featurette-heading">長途運輸</h2>
            <p class="lead">
              提供端對端長途運輸服務，範圍涵蓋台灣全省。<br>
              <br>
              資策貨運貨運能在交通繁雜的台灣地區提供完善的長途運輸服務，端靠遍及全省的樞紐輻射式配送網絡，以及各區域緊密配合的合作夥伴（協力車隊）。<br>
              <br>
              我們擁有及管理一支現代化的貨櫃車車隊，以20呎和40呎長的貨櫃車，為您提供高效率、具成本效益及準時的運輸服務。絕大部分貨櫃車均裝設了全球定位系統及監控設備，確保可從出發點至目的地，24小時及全程追踪和監控您的貨件。
            </p>
          </div>
          <div class="col-md-5">
            <img class=" featurette-image img-fluid mx-auto" src="<%=request.getContextPath()%>/frontend/img/p2.jpg">
          </div>
        </div>

        <hr class="featurette-divider">

        <!-- /END -->

      </div><!-- /.container -->


      <!-- FOOTER -->
      <footer class="container">
        <p class="float-right"><a href="#">回到上面</a></p>
        <p> 2018/1/21 版本 <i class="fa fa-github-alt"></i></p>
      </footer>
    </main>
    



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
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

                  <div class="modal-body">
                     <form method="post" action="<%=request.getContextPath()%>/mem/checkMail.do" name="form1">
                        <div class="form-group">
                            <label for="mail"><h5><p class="font-weight-bold"><i class="fa fa-envelope"></i> 信箱:</p></h5></label>
                            <span id="mailresult" class="font-weight-bold" style="color:red"></span>
                            <input id="mail" class="form-control" type="email" name="member_mail" required>
                        </div>
                        <div class="form-group">
                            <label for="psw"><h5><p class="font-weight-bold"><i class="fa fa-lock"></i> 密碼:</p></h5></label>
                            <span id="pswresult" class="font-weight-bold" style="color:red"></span>
                            <input id="psw" class="form-control" type="password" name="member_psw" required>
                        </div>

                         <div class="modal-footer">
                         		<input type="hidden" name="action" value="login">
                                <button class="btn btn-primary btn-block" type="submit" id="btnlogin" ><h4><i class="fa fa-user"> </i>登入</h4></button>                               
                        </div>
                            <div class="container">
                              <div class="row">
                                <div class="col-9">
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                                    <label class="custom-control-label" for="customCheck1">記住我</label>
                                  </div>
                                </div>
                                <div class="col-3" style="text-align:right;">
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
		
	    var member = "${memVO.member_id}"
// 	    var jsonObj = JSON.parse(event.data);
// 	    var memberEcho = jsonObj.member_id;
// 	    var memberStatus = jsonObj.member_status;
	    
	    
// 			if(member==event.data){
		      document.getElementById('messages').innerHTML
		        += '<br />' + event.data;
// 			}
	    }
	//  建立與server的連接.
	    function onOpen(event) {
	      document.getElementById('messages').innerHTML
	        = 'Connection established';
	    }
	//  連線錯誤時觸發
	    function onError(event) {
	      alert(event.data);
	    }
	//  按下按鈕後觸發,發送訊息給server
	    function start() {
	      return false;
	    }
	  </script>
  </body>
</html>