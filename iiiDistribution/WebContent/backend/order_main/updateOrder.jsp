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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
	  
	  <link href="<%= request.getContextPath() %>/backend/css/font_awesome_min.css" rel="stylesheet">
	  <link href="<%= request.getContextPath() %>/backend/css/sup.css" rel="stylesheet"> 
	  <link href="<%= request.getContextPath() %>/backend/css/main.css" rel="stylesheet">  
	  
    <!-- ========================================================基本套件 -->
    <!-- =================================================== -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/css/jquery.datetimepicker.css" />
                      <!-- 請大家把自己的套件放在這邊 -->

    <!-- =================================================== -->
     <style type="text/css">
     body{
     	font-size:10px;
     }
      .Pink15 {
          font-family: Verdana, Arial, Helvetica, sans-serif;
          font-size: 13px;
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
    
    select{
    	font-size:30px;
    }
    </style>


<body class="fixed-nav sticky-footer" id="page-top">
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
<!-- 主要功能 -->
<div class="content-wrapper">
  <div class="container-fluid">   
    <div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-9">
      <form METHOD="post" ACTION="<%= request.getContextPath() %>/order_main/order.do">
        <div>
          <table class="table table-hover" >
            <thead>
              <tr>
                <table border="0" cellpadding="3" cellspacing="0" bgcolor="#f7107d" class="padding3" style="width:50%; font-size:90%">
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
                                          <input type="hidden" name="action" value="update">
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
  </div>
</div>
<!-- 主要功能 -->




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

<script type="text/javascript">

$.datetimepicker.setLocale('zh');
$('.expected_time').datetimepicker({
    theme: '',              //theme: 'dark',
    timepicker:true,       //timepicker:true,
    step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
    format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
   <%-- value: '<%=carVO.car_updatetime%>', --%> // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:             '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});



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