<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="iii.order_main.model.*" %>
<%@ page import="iii.weight.model.*" %>
<%@ page import="iii.tra.model.*" %>
<%@ page import="iii.size.model.*" %>
<% 
  OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
  WeightVO weightVO = (WeightVO) request.getAttribute("weightVO");
  TraVO traVO = (TraVO) request.getAttribute("traVO");
  SizeVO sizeVO = (SizeVO) request.getAttribute("sizeVO");
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
					<a class="navbar-brand" href="index.jsp"><img
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
						<div class="dropdown-menu" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="#">關於我們</a> <a
								class="dropdown-item" href="#">配送服務</a> <a class="dropdown-item"
								href="#">契約客戶</a> <a class="dropdown-item" href="#">到府服務</a> <a
								class="dropdown-item" href="#">QR CODE認證收貨</a> <a
								class="dropdown-item" href="#">即時追蹤</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">寄件申請</a>
						<div class="dropdown-menu" aria-labelledby="dropdown10">
							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/frontend/order_main/order_main.jsp">線上申請寄件</a>
							<a class="dropdown-item" href="#">寄件指南</a> <a
								class="dropdown-item" href="cal.html">運費查詢</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">相關查詢</a>
						<div class="dropdown-menu" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="order_inq.html">訂單查詢</a> <a
								class="dropdown-item" href="#">據點查詢</a> <a class="dropdown-item"
								href="#">包裹地圖追蹤</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">客服中心</a>
						<div class="dropdown-menu" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="#">常見問題</a> <a
								class="dropdown-item" href=<%=request.getContextPath()%>
								/protected/select_MemPage.jsp>聯絡我們</a>
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
				<div class="card my-4">
					<div id="accordion" role="tablist">

						<div class="card">
							<div class="card-header" role="tab" id="headingMem">
								<h5 class="mb-0">
									<a data-toggle="collapse" href="#collapseMem" role="button"
										aria-expanded="false" aria-controls="collapseMem"> 會員資訊 </a>
								</h5>
							</div>

							<div id="collapseMem" class="collapse" role="tabpanel"
								aria-labelledby="headingMem" data-parent="#accordion">
								<div class="card-body">修改資料</div>
							</div>
						</div>


						<div class="card">
							<div class="card-header" role="tab" id="heading1">
								<h5 class="mb-0">
									<a data-toggle="collapse" href="#collapse1" role="button"
										aria-expanded="false" aria-controls="collapse1">服務項目</a>
								</h5>
							</div>

							<div id="collapse1" class="collapse" role="tabpanel"
								aria-labelledby="heading1" data-parent="#accordion">

								<div class="card-body">關於我們</div>
								<div class="card-body">配送服務</div>
								<div class="card-body">契約客戶</div>
								<div class="card-body">到府服務</div>
								<div class="card-body">QRcode驗證收貨</div>
								<div class="card-body">即時追蹤</div>
							</div>
						</div>


						<div class="card">
							<div class="card-header" role="tab" id="heading2">
								<h5 class="mb-0">
									<a data-toggle="collapse" href="#collapse2" role="button"
										aria-expanded="false" aria-controls="collapse2"> 寄件申請 </a>
								</h5>
							</div>

							<div id="collapse2" class="collapse" role="tabpanel"
								aria-labelledby="heading2" data-parent="#accordion">
								<div class="card-body">線上申請寄件</div>
								<div class="card-body">寄件指南</div>
								<div class="card-body">運費查詢</div>
							</div>
						</div>


						<div class="card">
							<div class="card-header" role="tab" id="heading3">
								<h5 class="mb-0">
									<a data-toggle="collapse" href="#collapse3" role="button"
										aria-expanded="false" aria-controls="collapse3">相關查詢</a>
								</h5>
							</div>

							<div id="collapse3" class="collapse" role="tabpanel"
								aria-labelledby="heading3" data-parent="#accordion">
								<div class="card-body">訂單查詢</div>
								<div class="card-body">據點查詢</div>
							</div>
						</div>

						<div class="card">
							<div class="card-header" role="tab" id="heading4">
								<h5 class="mb-0">
									<a data-toggle="collapse" href="#collapse4" role="button"
										aria-expanded="false" aria-controls="collapse4">相關查詢</a>
								</h5>
							</div>

							<div id="collapse4" class="collapse" role="tabpanel"
								aria-labelledby="heading4" data-parent="#accordion">
								<div class="card-body">常見問題</div>
								<div class="card-body">聯絡我們˙</div>
							</div>
						</div>


					</div>
				</div>

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
				                              <td bgcolor="#FFFFFF" style="border-right: #f7107d 1px solid; border-bottom: #f7107d 2px solid; width:31px;">
				                                <span class="Pink15"> 
				                                  <img src="<%=request.getContextPath()%>/frontend/img/Pink_receiver.gif" width="29" height="103">
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
				                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_name.gif" width="20" height="35">          
				                                                   </td>
				                                                  <td class="Pink15">
				                                                    &nbsp;
				                                                    ${orderVO.receiver_name}
				                                                  </td>
				                                                  <td width="10%" align="center" valign="middle" class="Pink15">
				                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_mobil.gif" width="20" height="35">
				                                                  </td>
				                                                  <td width="40%" class="Pink15">
				                                                    ${orderVO.receiver_cell} 
				                                                  </td>
				                                                  <td width="2%">
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
				                                                  <td height="35" style="border-left: #FEB4C8 2px solid;" class="bl12 Pink15">
				                                                    <table border="0" cellpadding="0" cellspacing="0" ><tbody><tr><td>
				                                                    &nbsp;&nbsp;${orderVO.receiver_city} ${orderVO.receiver_county}
				                                                      
				                                                      </td><td>
				                                                      </td></tr></tbody>
				                                                    </table>
				                                                  </td>
				                                                </tr>
				                                                <tr> 
				                                                  <td height="35" colspan="3" class="bl12 Pink15" align="left" style="border-top: #FEB4C8 2px solid; border-left: #FEB4C8 2px solid;">
				                                                    &nbsp; ${orderVO.receiver_address}
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
				                                                  <td width="55%" class="bl12 Pink15 " align="left">
				                                                    &nbsp; ${orderVO.receiver_tel}
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
				                              <td bgcolor="#FFFFFF" style="border-right: #f7107d 1px solid; border-bottom: #f7107d 2px solid; width:31px;">
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
				                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_name.gif" width="20" height="35">          
				                                                   </td>
				                                                  <td class="Pink15">
				                                                    &nbsp;
				                                                    ${orderVO.sender_name}
				                                                  </td>
				                                                  <td width="10%" align="center" valign="middle" class="Pink15">
				                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_mobil.gif" width="20" height="35">
				                                                  </td>
				                                                  <td width="40%" class="Pink15">
				                                                    ${orderVO.sender_cell} 
				                                                  </td>
				                                                  <td width="2%">
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
				                                                  <td height="35" style="border-left: #FEB4C8 2px solid;" class="bl12 Pink15">
				                                                    <table border="0" cellpadding="0" cellspacing="0" ><tbody><tr><td>
				                                                    &nbsp;&nbsp;${orderVO.sender_city} ${orderVO.sender_county}
				                                                      
				                                                      </td><td>
				                                                      </td></tr></tbody>
				                                                    </table>
				                                                  </td>
				                                                </tr>
				                                                <tr> 
				                                                  <td height="35" colspan="3" class="bl12 Pink15" align="left" style="border-top: #FEB4C8 2px solid; border-left: #FEB4C8 2px solid;">
				                                                    &nbsp; ${orderVO.sender_address}
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
				                                                  <td width="55%" class="bl12 Pink15 " align="left">
				                                                    &nbsp; ${orderVO.sender_tel}
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
				                                      <td width="35%" align="center" valign="middle" class="Pink15  Pink bold"> 服務使用人E-Mail: ${orderVO.receiver_mail}
				                                      </td>
				                                    </tr>
				                                  </tbody>
				                                </table>
				                              </td>
				                            </tr>
				                            <tr>
				                              <td bgcolor="#FFFFFF" style="border-top: #f7107d 1px solid; border-bottom: #f7107d 1px solid; text-align: center;">
				                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
				                                  <tbody>
				                                    <tr>
				                                      <td>
				                                       <span class="Pink15">
				                                         <jsp:useBean id="weightSvc" scope="page" class="iii.weight.model.WeightService"/>
				                                         重量:
				                                         <c:forEach var="weightVO" items="${weightSvc.all}">
				                                           ${(orderVO.item_weight==weightVO.weight_price) ? weightVO.weight_type : ''}
				                                         </c:forEach>
				                                       </span>
				                                      </td>
				                                    </tr>
				                                  </tbody>
				                                </table>
				                              </td>
				                            </tr>
				                            <tr>
				                              <td bgcolor="#FFFFFF" style="border-top: #f7107d 1px solid; border-bottom: #f7107d 1px solid; text-align: center;">
				                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
				                                  <tbody>
				                                    <tr>
				                                      <td>
				                                        <span class="Pink15">
				                                          <jsp:useBean id="sizeSvc" scope="page" class="iii.size.model.SizeService"/>
				                                          尺寸:
				                                          <c:forEach var="sizeVO" items="${sizeSvc.all}">
				                                            ${(orderVO.item_size==sizeVO.size_price) ? sizeVO.size_type : ''}
				                                          </c:forEach>
				                                        </span>
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
				                                      <td width="35%" align="center" valign="middle" class="Pink15  Pink bold">類型: ${orderVO.item_type}
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
				                                      <td width="35%" align="center" valign="middle" class="Pink15  Pink bold">預期送達時間: ${orderVO.expected_time}
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
				                                      <td width="35%" align="center" valign="middle" class="Pink15  Pink bold">備註: ${orderVO.order_note}
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
				                                      <td width="20%" align="center" valign="middle" class="Pink15  Pink bold"> 金額： ${orderVO.fee}元
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
				    <div class="col-xs-12 col-sm-3" style="margin-left:-100px;">
				      <div class="row">
				      	  <form action="<%=request.getContextPath()%>/frontend/order_main/queryOrder.jsp">
				      	  	<img src="<%=request.getContextPath()%>/frontend/img/carBack.gif" height="103">
						     <button type="submit" class='btn btn-warning update Pink15' id="submitButton" style="margin-top:30px; margin-bottom:30px;"> 回到訂單查詢
						     </button>
						  </form>
						  
						  <form METHOD="post" ACTION="<%= request.getContextPath() %>/order_main/order.do">
						  	<img src="<%=request.getContextPath()%>/frontend/img/carNext.gif" height="103">
						  	<button type="submit" class='btn btn-default update Pink15' id="submitButton"  >  修改此訂單 
						    </button>
						    <input type="hidden" name="order_id" value="${orderVO.order_id}">			    
						    <input type="hidden" name="action" value="getOne_For_Update_Frontend">
						  </form>
				      </div>
				    </div>
				  </div>
				</div>
			

				<!-- ================================(主要網格9格)====================================== -->

			</div>
		</div>
	</div>



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

	<!-- 	<footer class="py-5"> -->
	<!-- 		<div class="container"> -->
	<!-- 			<p class="m-0 text-center text-white">2018/2/4版本</p> -->
	<!-- 		</div> -->
	<!-- 	</footer> -->

</body>
</html>