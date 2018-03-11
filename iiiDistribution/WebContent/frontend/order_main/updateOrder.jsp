<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="iii.order_main.model.*" %>
<%@ page import="iii.weight.model.*" %>
<%@ page import="iii.tra.model.*" %>
<%
Object memVO = session.getAttribute("memVO");

if(memVO==null){
  System.out.println("test1");
  session.setAttribute("location", request.getRequestURI());
  response.sendRedirect(request.getContextPath()+"/frontend/logIn.jsp");
  return;
}
%>

<% 
  OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
  WeightVO weightVO = (WeightVO) request.getAttribute("weightVO");
  TraVO traVO = (TraVO) request.getAttribute("traVO");
%>
<!DOCTYPE html>
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
<!-- =================================================== -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script language="javascript">
      var name="${memVO.member_name}";
      var cell="${memVO.member_cell}";
      var phone="${memVO.member_phone}";
    var phone1=phone.substring(0,2);
    var phone2=phone.substr(3,7);
    
    
    $(document).ready(function(){ 
      $("#godBtn").click(function(){          //神奇小按鈕
        $("#receiver_name").val("曾菸雲");
        $("#receiver_cell").val("0934-567789");
        $("#receiver_address").val("中大路300號");
        $("#receiver_tel_front").val("03");
        $("#receiver_tel_back").val("4257387");
        $("#sender_name").val(name);
        $("#sender_cell").val(cell);
        $("#sender_address").val("五興路347號");
        $("#sender_tel_front").val(phone1);
        $("#sender_tel_back").val(phone2);
        $("#receiver_mail").val("HiPeter@gmail.com");
        $("#expected_time").val("2018-03-15 10:16:28");
        $("#order_note").val("拜託快點");
      })    
    })
  </script> 
      
      
                      <!-- 請大家把自己的套件放在這邊 -->
    <style type="text/css">
      .Pink15 {
          font-family: Verdana, Arial, Helvetica, sans-serif;
          font-size: 15px;
          color: #F7107D;
          line-height: 21px;
          font-weight: bold;
          }
          
         
    
    input, textarea, select, button {
        text-rendering: auto;
        color: initial;
        letter-spacing: normal;
        word-spacing: normal;
        text-transform: none;
        text-indent: 0px;
        text-shadow: none;
        display: inline-block;
        text-align: start;
        margin: 0em;
        font: 400 11px system-ui;
    }
    </style>

</head>

<body>

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

          <li class="nav-item"><a class="nav-link" id="dropdown10"
            href=${ (memVO==null) ? "#" : "http://localhost:8081/BA106G4/frontend/logOut.jsp" }
            data-toggle=${ (memVO==null) ? "modal" : "" }
            data-target="#myModal">${ (memVO==null) ? "會員登入" : "登出" } </a></li>
        </ul>
      </div>
    </nav>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-xs-12 col-sm-3">
        <!-- 母子選單3格-->
        <!-- ---menu---	 -->
<div id="accordion">
  <div class="card my-4">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
         	<h5>會員資訊</h5>
        </button>
      </h5>
    </div>

    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
        	<a href="<%=request.getContextPath()%>/frontend/mem/memDataUpdate.jsp">修改資料</a> 
        	
      </div>
    </div>
  </div>
  
  <div class="card my-4">
    <div class="card-header" id="headingTwo">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
        	 <h5>服務項目</h5>
        </button>
      </h5>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
      <div class="card-body">
     	  關於我們
      </div>
      <div class="card-body">
       	配送服務
      </div>
    </div>
  </div>
  <div class="card my-4">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
         	<h5>寄件申請</h5>
        </button>
      </h5>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
      <div class="card-body" href="#">
      	<a href="<%=request.getContextPath()%>/frontend/order_main/order_main.jsp">線上寄件申請</a> 
      </div>
      <div class="card-body" href="<%=request.getContextPath()%>/frontend/video.jsp">
      <a href=""></a>
       	寄件指南
      </div>
      <div class="card-body">
      <a href="<%=request.getContextPath()%>/frontend/transport_fee/fee.jsp">運費查詢</a> 
      </div>
    </div>
  </div>
  
  <div class="card my-4">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse4" aria-expanded="false" aria-controls="collapse4">
         	<h5>相關查詢</h5>
         	
        </button>
      </h5>
    </div>
    <div id="collapse4" class="collapse" aria-labelledby="heading4" data-parent="#accordion">
      <div class="card-body">
      <a href="<%=request.getContextPath()%>/frontend/order_main/queryOrder.jsp">訂單查詢</a> 
       	 
      </div>
      <div class="card-body">
       <a href="<%=request.getContextPath()%>/frontend/">據點查詢</a> 
      </div>
    </div>
  </div>
  
  <div class="card my-4">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse5" aria-expanded="false" aria-controls="collapse5">
         	 <h5>客服中心</h5>
        </button>
      </h5>
    </div>
    <div id="collapse5" class="collapse" aria-labelledby="heading5" data-parent="#accordion">
      <div class="card-body">
       <a href="<%=request.getContextPath()%>/frontend/">常見問題</a> 
       
      </div>
      <div class="card-body">
       <a href="<%=request.getContextPath()%>/frontend/">聯絡我們</a> 
       
      </div>
    </div>
  </div>
</div>
<!-- ---menu---	 -->

      </div>


      <div class="col-xs-12 col-sm-9">

        <!-- 麵包削 -->
        <div class="my-4">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a
              href="<%=request.getContextPath()%>/frontend/index.jsp">首頁</a></li>
            <li class="breadcrumb-item active">會員註冊</li>
          </ol>
        </div>
        <!-- /麵包削 -->


<!-- ================================(主要網格9格)====================================== -->
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-9">
      <form METHOD="post" ACTION="<%= request.getContextPath() %>/order_main/order.do">
        <div>
          <table class="table table-hover">
            <thead>
              <tr>
                <table border="0" cellpadding="3" cellspacing="0" bgcolor="#f7107d" class="padding3">
                  <tbody>
                    <tr>
                      <td>
                        <table width="410" border="0" cellspacing="0" cellpadding="0">
                          <tbody>
                            <tr>
                              <td bgcolor="#FFFFFF" style="border-right: #f7107d 1px solid; border-bottom: #f7107d 2px solid;">
                                <span class="Pink15"> 
                                  <img src="<%=request.getContextPath()%>/frontend/img/Pink_receiver.gif" width="29" height="103">
                                </span>
                              </td>
                              <td><!--收件人-->
                                <div>                                   
                                  <div>      
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tbody>
                                        <tr bordercolor="#FEB4C8" class="bl132">
                                          <td bgcolor="#FFFFFF">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tbody>
                                                <tr>
                                                  <td width="5%" height="35" align="center" valign="middle" class="Pink15">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_name.gif" width="20" height="35">
                                                  </td>
                                                  <td align="left">
                                                    <input type="text" maxlength="20" name="receiver_name" value=${(orderVO==null) ?"":orderVO.receiver_name}>
                                                  </td>
                                                  <td width="5%" align="center" valign="middle" class="Pink15">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_mobil.gif" width="20" height="35">
                                                  </td>
                                                  <td width="30%">
                                                    <input type="text" maxlength="11" style="width:120px;" name="receiver_cell" value=${(orderVO==null) ?"":orderVO.receiver_cell}> 
                                                  </td>
                                                  <td width="2%">
                                                  </td>
                                                  <td width="4%" align="center" valign="middle" bgcolor="#f7107d" class="TWhite">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_must.gif" width="20" height="35">
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                        <tr bordercolor="#FEB4C8" class="bl132">
                                          <td bgcolor="#FFFFFF" style="border-top: #FEB4C8 2px solid; border-bottom: #FEB4C8 2px solid;">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tbody>
                                                <tr>
                                                  <td width="5%" rowspan="2" align="center" valign="middle" class="Pink15">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_add.gif" width="20" height="35">
                                                  </td>
                                                  <td height="35" style="border-left: #FEB4C8 2px solid;">
                                                    <table border="0" cellpadding="0" cellspacing="0"><tbody><tr><td>
                                                      <select name="receiver_city" id="receiver_city">
                                                        <option value="台北市" selected="selected">台北市</option>
                                                        <option value="基隆市">基隆市</option>
                                                        <option value="新北市">新北市</option>
                                                        <option value="桃園市">桃園市</option>
                                                        <option value="新竹市">新竹市</option>
                                                        <option value="新竹縣">新竹縣</option>
                                                        <option value="苗栗縣">苗栗縣</option>
                                                        <option value="台中市">台中市</option>
                                                        <option value="彰化縣">彰化縣</option>
                                                        <option value="南投縣">南投縣</option>
                                                        <option value="雲林縣">雲林縣</option>
                                                        <option value="嘉義市">嘉義市</option>
                                                        <option value="嘉義縣">嘉義縣</option>
                                                        <option value="台南市">台南市</option>
                                                        <option value="高雄市">高雄市</option>
                                                        <option value="屏東縣">屏東縣</option>
                                                        <option value="宜蘭縣">宜蘭縣</option>
                                                        <option value="台東縣">台東縣</option>
                                                        <option value="花蓮縣">花蓮縣</option>
                                                        <option value="金門縣">金門縣</option>
                                                        <option value="連江縣">連江縣</option>
                                                        <option value="澎湖縣">澎湖縣</option>
                                                      </select>&nbsp;                                      
                                                      <select name="receiver_county" id="receiver_county">
                                                      </select>&nbsp;
                                                      </td><td>&nbsp;
                                                      </td></tr></tbody>
                                                    </table>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td height="35" colspan="3" align="left" style="border-top: #FEB4C8 2px solid; border-left: #FEB4C8 2px solid;">&nbsp;
                                                    <input type="text" maxlength="25" style="width:320px;" name="receiver_address" value= ${(orderVO==null) ?"":orderVO.receiver_address}>
                                                  </td>
                                                  <td width="4%" align="center" valign="middle" bgcolor="#f7107d" class="TWhite">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_must.gif" width="20" height="35">
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                        <tr class="bl132">
                                          <td bgcolor="#FFFFFF" style="border-bottom: #f7107d 2px solid;">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tbody>
                                                <tr>
                                                  <td width="1%" align="center" valign="middle" bgcolor="#f7107d" class="TWhite">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_tel.gif" width="20" height="35">
                                                  </td>
                                                  <td width="55%" class="bl12" align="left">
                                                    &nbsp;
                                                    <input type="text" maxlength="3" style="width:40px; " name="receiver_tel_front" id="receiver_tel_front" value=>
                                                      -
                                                    <input type="text" maxlength="8" style="width:120px;" name="receiver_tel_back" id="receiver_tel_back" value=>
                                                  </td>
                                                        
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </div>                                                                                
                                </div>
                <!-- /收件人--></td>
                            </tr>            
                          </tbody>
                        </table>
                        <table width="410" border="0" cellspacing="0" cellpadding="0">
                          <tbody>
                            <tr>
                              <td bgcolor="#FFFFFF" style="border-right: #f7107d 1px solid; border-bottom: #f7107d 2px solid;">
                                <span class="Pink15"> 
                                  <img src="<%=request.getContextPath()%>/frontend/img/Pink_sender.gif" width="29" height="103">
                                </span>
                              </td>
                              <td><!--寄件人-->
                                <div>                                   
                                  <div>      
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tbody>
                                        <tr bordercolor="#FEB4C8" class="bl132">
                                          <td bgcolor="#FFFFFF">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tbody>
                                                <tr>
                                                  <td width="5%" height="35" align="center" valign="middle" class="Pink15">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_name.gif" width="20" height="35">          </td>
                                                  <td align="left">
                                                    &nbsp;
                                                    <input type="text" maxlength="20" name="sender_name" value=${(orderVO==null) ?"":orderVO.sender_name}>
                                                  </td>
                                                  <td width="5%" align="center" valign="middle" class="Pink15">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_mobil.gif" width="20" height="35">
                                                  </td>
                                                  <td width="30%">
                                                    <input type="text" maxlength="12" style="width:120px;" name="sender_cell" value= ${(orderVO==null) ?"":orderVO.sender_cell}> 
                                                  </td>
                                                  <td width="2%">
                                                  </td>
                                                  <td width="4%" align="center" valign="middle" bgcolor="#f7107d" class="TWhite">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_must.gif" width="20" height="35">
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                        <tr bordercolor="#FEB4C8" class="bl132">
                                          <td bgcolor="#FFFFFF" style="border-top: #FEB4C8 2px solid; border-bottom: #FEB4C8 2px solid;">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tbody>
                                                <tr>
                                                  <td width="5%" rowspan="2" align="center" valign="middle" class="Pink15">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_add.gif" width="20" height="35">
                                                  </td>
                                                  <td height="35" style="border-left: #FEB4C8 2px solid;">
                                                    <table border="0" cellpadding="0" cellspacing="0"><tbody><tr><td>
                                                      <select name="sender_city" id="sender_city">
                                                        <option value="台北市" selected="selected">台北市</option>
                                                        <option value="基隆市">基隆市</option>
                                                        <option value="新北市">新北市</option>
                                                        <option value="桃園市">桃園市</option>
                                                        <option value="新竹市">新竹市</option>
                                                        <option value="新竹縣">新竹縣</option>
                                                        <option value="苗栗縣">苗栗縣</option>
                                                        <option value="台中市">台中市</option>
                                                        <option value="彰化縣">彰化縣</option>
                                                        <option value="南投縣">南投縣</option>
                                                        <option value="雲林縣">雲林縣</option>
                                                        <option value="嘉義市">嘉義市</option>
                                                        <option value="嘉義縣">嘉義縣</option>
                                                        <option value="台南市">台南市</option>
                                                        <option value="高雄市">高雄市</option>
                                                        <option value="屏東縣">屏東縣</option>
                                                        <option value="宜蘭縣">宜蘭縣</option>
                                                        <option value="台東縣">台東縣</option>
                                                        <option value="花蓮縣">花蓮縣</option>
                                                        <option value="金門縣">金門縣</option>
                                                        <option value="連江縣">連江縣</option>
                                                        <option value="澎湖縣">澎湖縣</option>
                                                      </select>&nbsp;                                      
                                                      <select name="sender_county" id="sender_county">
                                                        
                                                      </select>&nbsp;
                                                      </td><td>&nbsp;
                                                      </td></tr></tbody>
                                                    </table>
                                                  </td>
                                                </tr>
                                                <tr> 
                                                  <td height="35" colspan="3" align="left" style="border-top: #FEB4C8 2px solid; border-left: #FEB4C8 2px solid;">&nbsp;
                                                    <input type="text" maxlength="25" style="width:320px;" name="sender_address" value= ${(orderVO==null) ?"":orderVO.sender_address}>
                                                  </td>      
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                        <tr class="bl132">
                                          <td bgcolor="#FFFFFF" style="border-bottom: #f7107d 2px solid;">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tbody>
                                                <tr>
                                                  <td width="1%" align="center" valign="middle" bgcolor="#f7107d" class="TWhite">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_tel.gif" width="20" height="35">
                                                  </td>
                                                  <td width="55%" class="bl12" align="left">
                                                    &nbsp;
                                                    <input type="text" maxlength="3" style="width:40px; " name="sender_tel_front" id="sender_tel_front" value="">
                                                    -
                                                    <input type="text" maxlength="8" style="width:120px;" name="sender_tel_back" id="sender_tel_back" value="">
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </div>                                                                                
                                </div>
                <!-- /收件人--></td>
                            </tr>            
                          </tbody>
                        </table>
                                
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#FEB4C8" class="bl132">
                          <tbody>
                             <tr>
                              <td bgcolor="#FFFFFF" style="border-top: #f7107d 1px solid; border-bottom: #f7107d 1px solid;">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td width="35%" align="center" valign="middle" class="Pink15  Pink bold"> 服務使用人E-Mail</td>
                                      <td height="35">
                                        <div>
                                          <input type="text" maxlength="138" name="receiver_mail" value= ${(orderVO==null) ?"":orderVO.receiver_mail}>
                                          <span class="bl12 Pink15">(寄確認信用)</span>                                                 
                                        </div>
                                      </td>
                                      <td width="4%" align="center" valign="middle" bgcolor="#f7107d" class="TWhite">
                                        <img src="<%=request.getContextPath()%>/frontend/img/Pink_must.gif" width="20" height="35">
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td bgcolor="#FFFFFF" style="border-top: #f7107d 1px solid; border-bottom: #f7107d 1px solid;">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody> 
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td bgcolor="#FFFFFF">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td>
                                        <td width="35%" align="center" valign="middle" class="Pink15  Pink bold">重量</td>
                                        </span>
                                      </td>
                                      <td>
                                        <span class="Pink15">
                                          <jsp:useBean id="weightSvc" scope="page" class="iii.weight.model.WeightService"/>
                                          <select name="item_weight" id="item_weight">
                                            <c:if test="${not empty orderVO}">
                                              <c:forEach var="weightVO" items="${weightSvc.all}">
                                                <option value="${weightVO.weight_price}" ${(orderVO.item_weight==weightVO.weight_price)? 'selected':''}>${weightVO.weight_type}
                                              </c:forEach>
                                          </c:if>
                                          </select>
                                        </span>
                                      </td>
                                      <td>
                                        <td width="35%" align="center" valign="middle" class="Pink15  Pink bold">尺寸
                                        </td>
                                        </span>
                                      </td>
                                      <td>
                                        <span class="Pink15">
                                          <jsp:useBean id="sizeSvc" scope="page" class="iii.size.model.SizeService"/>
                                          <select name="item_size" id="item_size">
                                            <c:if test="${not empty orderVO}">
                                              <c:forEach var="sizeVO" items="${sizeSvc.all}">
                                                <option value="${sizeVO.size_price}" ${(orderVO.item_size==sizeVO.size_price)? 'selected':''}>${sizeVO.size_type}
                                              </c:forEach>
                                            </c:if>
                                          </select>
                                        </span>
                                      </td>
                                      <td>
                                        <td width="35%" align="center" valign="middle" class="Pink15  Pink bold">類型
                                        </td>
                                        </span>
                                      </td>
                                      <td>
                                        <span class="Pink15">
                                          <jsp:useBean id="traSvc" scope="page" class="iii.tra.model.TraService"/>
                                          <select name="item_type" id="item_type">
                                            <c:if test="${not empty orderVO}">
                                              <c:forEach var="traVO" items="${traSvc.all}">
                                                <option value="${traVO.transition_type}" ${(orderVO.item_type==traVO.transition_type)? 'selected':''}>${traVO.transition_type}
                                              </c:forEach>
                                            </c:if>
                                          </select>
                                        </span>
                                      </td>
                                      <td width="4%" align="center" valign="middle" bgcolor="#f7107d" class="TWhite">
                                        <img src="<%=request.getContextPath()%>/frontend/img/Pink_must.gif" width="20" height="35">
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td bgcolor="#FFFFFF" style="border-top: #f7107d 1px solid; border-bottom: #f7107d 1px solid;">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td width="35%" align="center" valign="middle" class="Pink15  Pink bold">預期送達時間
                                      </td>
                                      <td height="35">
                                        <div>
                                          <input type="text" maxlength="138" class="expected_time" name="expected_time" value=${(orderVO==null) ?"":orderVO.expected_time}>
                                        </div>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td bgcolor="#FFFFFF" style="border-top: #f7107d 1px solid; border-bottom: #f7107d 1px solid;">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td width="35%" align="center" valign="middle" class="Pink15  Pink bold">備註
                                      </td>
                                      <td height="35">
                                        <div>
                                          <input type="text" maxlength="138" name="order_note" value = ${(orderVO==null) ?"":orderVO.order_note}>
                                        </div>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td bgcolor="#FFFFFF" style="border-top: #f7107d 1px solid; border-bottom: #f7107d 1px solid;">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td width="20%" align="center" valign="middle" class="Pink15  Pink bold"> 金額：
                                      </td>
                                      <td width="20%" align="center" valign="middle" class="Pink15  Pink bold" id="total" name="fee" value="${orderVO.fee}">
                                      </td>
                                      <input type="hidden" id="totalGo" name="fee" >
                                      <td align="center" valign="middle" class="Pink15  Pink bold"> 元 
                                      </td>
                                      <td width="20%" align="center" valign="middle" class="Pink15  Pink bold" >
                                        <input type="button" value="試算金額才能送出" id="totalButton" onClick="document.getElementById('submitButton').disabled=false">
                                      </td>
                                      <td height="35">
                                        <div>
                                          <span class="bl12">
                                          </span> 
                                          <button type="submit" class='btn btn-warning update Pink15' id="submitButton" value="" style="float: right;"  disabled> 確認送出 
                                          </button>
                                          <input type="hidden" name="order_id" value="${orderVO.order_id}">
                                          <input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
                                          <input type="hidden" name="action" value="update_Frontend">
                                          <input type="hidden" name="mem_id" value="${memVO.member_id}">
                                        </div>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </tr>
            </thead>
          </table>    
        </div>     
      </form>
    </div>
    <div class="col-xs-12 col-sm-3" style="padding-left:;">
      <div class="row">
        <c:if test="${not empty addError}">
      <font style="color:red; font-size:15px">煩請重新檢視資料:</font>
      
        <c:forEach var="message" items="${addError}">
          <li style="color:red; font-size:13px">${message.value}</li>
        </c:forEach>
      
    </c:if>
      </div>
    </div>
  </div>
</div>


<!-- ================================(主要網格9格)====================================== -->

  <!-- Footer -->
  <!-- FOOTER -->
  <footer class="container">
    <p class="float-right">
      <a href="#">回到上面</a>
    </p>
    <p>
      2018/3/5 版本 <i class="fa fa-github-alt"></i>
    </p>
  </footer>

</body>

<!--JS-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/orderQuery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>

<script type="text/javascript">

// $.datetimepicker.setLocale('zh');
// $('.expected_time').datetimepicker({
//     theme: '',              //theme: 'dark',
//     timepicker:true,       //timepicker:true,
//     step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
//     format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<%--    <%-- value: '<%=carVO.car_updatetime%>', --%> // value:   new Date(), --%>
//    //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//    //startDate:             '2017/07/10',  // 起始日
//    //minDate:               '-1970-01-01', // 去除今日(不含)之前
//    //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
// });



$('#totalButton').click(function(){
    var item_size = $('#item_size').val();
    var item_weight = $('#item_weight').val();
    var item_type;
      if($('#item_type').val() == "常溫")
        item_type=1;
      else
        item_type=1.2
    var errMsg="";
    $("[id*='item_']").each(function(index){
      if($(this).val().trim()==""){
        if(index==0){
          errMsg+= "請選擇重量"+"\n";
        } else if(index==1){
          errMsg+= "請選擇尺寸"+"\n";
        } else if(index==2){
          errMsg+= "請選擇類型"+"\n";
        }
      } else {
        $('#total').text(item_size * item_weight * item_type);
        $('#totalGo').val(item_size * item_weight * item_type);
      }
    }); 
   if(errMsg!=""){
     alert(errMsg);
   } 
});


$(document).ready(function(){
  if('${orderVO}' != ""){
    $('#sender_city option[value = ${orderVO.sender_city}]').attr('selected', 'selected');
    $.ajax({
      type : "post",
      url  : "<%=request.getContextPath()%>/order_main/place.do",
      data : {plasebig:$("#sender_city").val()},
      datatype: "json",
      success : function(data){
        var str = $.parseJSON(data);
        $("#sender_county").empty();
        for(var i=0;i<str.length;i++){
          $("#sender_county").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
        }
        $('#sender_county option[value = ${orderVO.sender_county}]').attr('selected', 'selected');
      }
    })
  }else{
     $.ajax({
       type : "post",
       url  : "<%=request.getContextPath()%>/order_main/place.do",
       data : {plasebig:"台北市"},
       datatype: "json",
       success : function(data){
         var str = $.parseJSON(data);
         $("#sender_county").empty();
         for(var i=0;i<str.length;i++){
           $("#sender_county").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
         }     
       }
     })
   }
   $("#sender_city").change(function(){
     $.ajax({
       type : "post",
       url  : "<%=request.getContextPath()%>/order_main/place.do",
       data : {plasebig:$("#sender_city").val()},
       datatype: "json",
       success : function(data){
         var str = $.parseJSON(data);
         $("#sender_county").empty();
         for(var i=0;i<str.length;i++){
           $("#sender_county").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
         }     
       }
     })
   })
  if('${orderVO}' != ""){
    $('#receiver_city option[value = ${orderVO.receiver_city}]').attr('selected', 'selected');
    $.ajax({
      type : "post",
      url  : "<%=request.getContextPath()%>/order_main/place.do",
      data : {plasebig:$("#receiver_city").val()},
      datatype: "json",
      success : function(data){
        var str = $.parseJSON(data);
        $("#receiver_county").empty();
        for(var i=0;i<str.length;i++){
          $("#receiver_county").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
        }
        $('#receiver_county option[value = ${orderVO.receiver_county}]').attr('selected', 'selected');
      }
    })
  }else{
    $.ajax({
      type : "post",
      url  : "<%=request.getContextPath()%>/order_main/place.do",
      data : {plasebig:"台北市"},
      datatype: "json",
      success : function(data){
      var str = $.parseJSON(data);
      $("#receiver_county").empty();
        for(var i=0;i<str.length;i++){
        $("#receiver_county").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
        }           
      }
    })
  }
  $("#receiver_city").change(function(){
    $.ajax({
     type : "post",
     url  : "<%=request.getContextPath()%>/order_main/place.do",
     data : {plasebig:$("#receiver_city").val()},
     datatype: "json",
     success : function(data){
        var str = $.parseJSON(data);
        $("#receiver_county").empty();
        for(var i=0;i<str.length;i++){
           $("#receiver_county").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
        }           
      }
    })
  })
  if('${orderVO}'!=null){
  var x = '${orderVO.receiver_tel}' ;
  $('#receiver_tel_front').val(x.substring(0, 2).replace('-', ''));
  $('#receiver_tel_back').val(x.substring(x.length, 3).replace('-', ''));
  var y = '${orderVO.sender_tel}' ;
  $('#sender_tel_front').val(y.substring(0, 2).replace('-', ''));
  $('#sender_tel_back').val(y.substring(x.length, 3).replace('-', ''));
  }
})

</script>
</html>