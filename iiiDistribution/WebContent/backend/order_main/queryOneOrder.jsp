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
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>資策貨運後台系統</title>
  
  <!-- CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  
  <link href="<%= request.getContextPath() %>/backend/css/font_awesome_min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/backend/css/sup.css" rel="stylesheet"> 
  <link href="<%= request.getContextPath() %>/backend/css/main.css" rel="stylesheet">  
  
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
  
  <style type="text/css">
    *,
    *:before,
    *:after {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }
    /* .button */
    button {
    font-weight:bold;
    text-align: center;
      width:200px;
      height:45px;
        color: white;
        display: inline-block;
        position: relative;
        overflow: hidden;
        text-decoration: none;
        font-size: 16px;
        outline: none;
        color: #FFF;
        background: #25b7e8 ;
        font-family: 'raleway', sans-serif;
        border-radius: .25rem;
    }

    .button span {
        -webkit-transition: 0.6s;
        -moz-transition: 0.6s;
        -o-transition: 0.6s;
        transition: 0.6s;
        -webkit-transition-delay: 0.2s;
        -moz-transition-delay: 0.2s;
        -o-transition-delay: 0.2s;
        transition-delay: 0.2s;
    }

    .button:before,
    .button:after {
        content: '';
        position: absolute;
        left: 0;
        width: 100%;
        text-align: center;
        opacity: 0;
        -webkit-transition: .4s,opacity .6s;
        -moz-transition: .4s,opacity .6s;
        -o-transition: .4s,opacity .6s;
        transition: .4s,opacity .6s;
    }

    /* :before */

    .button:before {
        content: attr(data-hover);
        -webkit-transform: translate(-150%,0);
        -moz-transform: translate(-150%,0);
        -ms-transform: translate(-150%,0);
        -o-transform: translate(-150%,0);
        transform: translate(-150%,0);
    }

    /* :after */

    .button:after {
        content: attr(data-active);
        -webkit-transform: translate(150%,0);
        -moz-transform: translate(150%,0);
        -ms-transform: translate(150%,0);
        -o-transform: translate(150%,0);
        transform: translate(150%,0);
    }

    /* Span on :hover and :active */

    .button:hover span,
    .button:active span {
        opacity: 0;
        -webkit-transform: scale(0.3);
        -moz-transform: scale(0.3);
        -ms-transform: scale(0.3);
        -o-transform: scale(0.3);
        transform: scale(0.3);
    }

    /*  
        We show :before pseudo-element on :hover 
        and :after pseudo-element on :active 
    */

    .button:hover:before,
    .button:active:after {
        opacity: 1;
        -webkit-transform: translate(0,0);
        -moz-transform: translate(0,0);
        -ms-transform: translate(0,0);
        -o-transform: translate(0,0);
        transform: translate(0,0);
        -webkit-transition-delay: .4s;
        -moz-transition-delay: .4s;
        -o-transition-delay: .4s;
        transition-delay: .4s;
    }

    /* 
      We hide :before pseudo-element on :active
    */

    .button:active:before {
        -webkit-transform: translate(-150%,0);
        -moz-transform: translate(-150%,0);
        -ms-transform: translate(-150%,0);
        -o-transform: translate(-150%,0);
        transform: translate(-150%,0);
        -webkit-transition-delay: 0s;
        -moz-transition-delay: 0s;
        -o-transition-delay: 0s;
        transition-delay: 0s;
    }
  </style>

  <style> 
  	
    .frame{
        width: 100%;
        height: 60px;
        padding: 1em;
        margin: 12px 0;
        box-sizing: border-box;
        border: 2px solid #25b7e8;
        border-radius: 4px;
    }

    .btn-circle.btn-lg {
      width: 50px;
      height: 50px;
      padding: 10px 16px;
      font-size: 18px;
      line-height: 1.33;
      border-radius: 25px;
    }
    .btn-circle.btn-xl {
      width: 100px;
      height: 70px;
      padding: 10px 16px;
      font-size: 24px;
      line-height: 1.33;
      border-radius: 35px;
    }
    
    .form-control-lg{
	    font-size: 1rem;
	    font-weight: bold;
    }
    .btn{
  		font-weight:bold;
  	}
  </style>

<!-- CSS -->
  <!-- =================================================== -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/css/jquery.datetimepicker.css" />
  <!-- =================================================== -->
</head>

<% request.setAttribute("car_plate", "" ); %>


<body class="fixed-nav sticky-footer" id="page-top" style="font-weight:bold;">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    
    <a class="navbar-brand" href="index.html"><strong>資策貨運後台系統</strong></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <!-- 引入MENU -->
      <jsp:include page="/backend/menu/menu.jsp" flush="true" />
      
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-chevron-left"></i>
          </a>
        </li>
      </ul>

      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <form>
            <div class="input-group">
              <input type="text" class="form-control" placeholder="站內搜尋">
              <div class="input-group-append">
                <button type="submit" class="btn btn-secondary"><i class="fa fa-search"></i></button>
              </div>
            </div>
            </form>
        </li>
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-sign-out"></i>員工登出</a>
        </li>
      </ul>

      </div>
    </nav>




<!-- 主要功能 -->
<div class="content-wrapper">
  <div class="container-fluid">   
    <table>
      <div class="container">
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_main/order.do" name="form1">
          <div class="row">
            <div class="col-xs-12 col-sm-3">
              <div class="center">
                <select name="order_status" class="form-control form-control-lg frame" style="height:60px;">
                  <option value=''>訂單狀態</option>
                  <option value="處理中">處理中</option>
                  <option value="未處理">未處理</option>
                </select>
              </div>
            </div>
            <div class="col-xs-12 col-sm-3">
              <div class="center">
                <select name="receiver_county" id="receiver_county" class="form-control form-control-lg frame" style="height:60px;"s>
                </select>
              </div>
            </div>          
            <div class="col-xs-12 col-sm-3" style="text-align:center;">
              <input type="text" id="receiver_name" name="receiver_name" placeholder="輸入姓名" class="frame">
            </div>
            <div class="col-xs-12 col-sm-2" >
              <button class="button" type="submit" data-hover="拜託點我" data-active="I'M ACTIVE"><span>綜合查詢</span></button>
              <input type="hidden" name="action" value="listOrders_ByCompositeQuery">
              <button class="button outer" type="submit" data-hover="點我有驚喜 吧！" data-active="I'M ACTIVE" style="margin-top:5px; background:#f9c320; color:black;"><span>訂單行程查詢系統</span></button>
            </div>
          </div>
        </FORM>
      </div>
    </table> 
    <form METHOD="post" ACTION="<%= request.getContextPath() %>/order_main/order.do">
        <div style="position:absolute; left:30%; top:25%">
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
	  <div style="position:absolute; left:60%; top:30%;">
      	<form action="<%=request.getContextPath()%>/backend/order_main/queryOrder.jsp">
     		<img src="<%=request.getContextPath()%>/frontend/img/carBack.gif" height="200">
	     	<button type="submit" class='btn btn-warning update Pink15' id="submitButton" style="font-weight:bold;"> 
	     		回到訂單查詢
	    	</button>
	  	</form>
	  	<form METHOD="post" ACTION="<%= request.getContextPath() %>/order_main/order.do">
	  		<img src="<%=request.getContextPath()%>/frontend/img/carNext1.gif" height="200">
	  		<button type="submit" class='btn btn-default update' id="submitButton"  style="font-weight:bold; font">  
	  		修改此訂單 
	    	</button>
	    	<input type="hidden" name="order_id" value="${param.order_id}">
	    	<input type="hidden" name="action" value="getOne_For_Update">
	  	</form>
	  	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	<img id='qrcode1' src='#'/ style="width:40%;"/>
																
	 </div>
   </div>
</div>
<!-- 主要功能 -->

<!-- footer -->
<footer class="sticky-footer">
  <div class="container">
    <div class="text-center">
      <small>2018/2/4 後台網頁版本</small>
    </div>
  </div>
</footer>
<!-- footer -->

<!--員工登入-->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">確定要登出?</h5>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div> 
      <div class="modal-body">＠Ｑ＠．．．．．．．．．．．．．</div>
      <div class="modal-footer">
	  	<button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
    	<form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
    		<input type="hidden" name="action" value="logout">
    		<button type="submit" class="btn btn-primary" >登出</button>
    	</form>
 	  </div>
    </div>
  </div>
</div>

<!--JS-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/orderQuery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>

<script>
var text='${orderVO.order_id}';

$("#qrcode1").attr("src","http://chart.apis.google.com/chart?cht=qr&chl="+ text +"&chs=120x120");
</script>


<script>
  $(document).ready(function(){
    $.ajax({
      type : "post",
      url  : "<%=request.getContextPath()%>/order_main/place.do",
      data : {plasebig:'桃園市'},
      datatype: "json",
      success : function(data){
        var str = $.parseJSON(data);
        $("#receiver_county").empty();
        $("#receiver_county").append("<option value=''>選擇鄉鎮市區</option>");
        for(var i=0;i<str.length;i++){
          $("#receiver_county").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
        }     
      }
    })
  })

  $(".outer").click(function(){
    window.open("<%=request.getContextPath()%>/backend/order_main/orderGoogleMap.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1000,height=1000");
  });
</script>
</html>