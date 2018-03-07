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
    <link href="<%=request.getContextPath()%>/frontend/css/main.css" rel="stylesheet">
    <!-- ========================================================基本套件 -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <!-- =================================================== -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/css/jquery.datetimepicker.css" />
                      <!-- 請大家把自己的套件放在這邊 -->

    <!-- =================================================== -->
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
    <header>
      <div class="container logoCus">
        <div class="row" align="center">
          <div class="col-xs-12 col-sm-4">
            

          </div>
          <div class="col-xs-12 col-sm-4">
            <!-- LOGO -->
            <div>
              <a class="navbar-brand" href="index.html"><img src="<%=request.getContextPath()%>/frontend/img/IIICAR5.png" height="150"></a>
            </div>
            <div class="input-group">
              <input  class="form-control" type="text" placeholder="站內搜尋">
              <div class="input-group-append">
              <button type="submit" class="btn btn-secondary"><i class="fa fa-search"></i></button>
              </div>
            </div>

          </div>
          <div class="col-xs-12 col-sm-4">
            

          </div>
        </div>
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
                <a class="dropdown-item" href="order_cr.html">線上申請寄件</a>
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
                <a class="dropdown-item" href="#">聯絡我們</a>
              </div>
            </li>

            <li class="nav-item">
              <a class="nav-link" href="#" data-toggle="modal" data-target="#myModal">會員登入 </a>
            </li>
          </ul>
        </div>
      </nav>
    </header> 

    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-3">
          <!-- 母子選單-->
            <div class="card my-4">
            <div id="accordion" role="tablist">

              <div class="card">
                <div class="card-header" role="tab" id="headingOne">
                  <h5 class="mb-0">
                    <a data-toggle="collapse" href="#collapseOne" role="button" aria-expanded="true" aria-controls="collapseOne">
                      導覽列
                    </a>
                  </h5>
                </div>

                <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
                  <div class="card-body">
                    子導覽列
                  </div>
                </div>
              </div>


              <div class="card">
                <div class="card-header" role="tab" id="headingTwo">
                  <h5 class="mb-0">
                    <a class="collapsed" data-toggle="collapse" href="#collapseTwo" role="button" aria-expanded="false" aria-controls="collapseTwo">
                      導覽列
                    </a>
                  </h5>
                </div>

                <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion">

                    <div class="card-body">
                        子導覽列
                    </div>
                    <div class="card-body">
                        子導覽列
                    </div>
                    <div class="card-body">
                        子導覽列
                    </div>
                    <div class="card-body">
                        子導覽列
                    </div>
                </div>
              </div>
            </div>
          </div>

        </div>


        <div class="col-xs-12 col-sm-9">

          <!-- 麵包削 -->
          <div class="my-4">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="index.html">首頁</a>
                </li>
                <li class="breadcrumb-item active">寄件申請</li>
            </ol>
          </div> 
          <!-- 麵包削 -->
      
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
                                                    <input type="text" maxlength="3" style="width:40px; " name="receiver_tel_front" value= ${(orderVO==null) ?"":orderVO.receiver_tel}>
                                                      -
                                                    <input type="text" maxlength="8" style="width:120px;" name="receiver_tel_back" value="${param.receiver_tel_back}">
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
                                                    <input type="text" maxlength="20" name="sender_name" value="${param.sender_name}">
                                                  </td>
                                                  <td width="5%" align="center" valign="middle" class="Pink15">
                                                    <img src="<%=request.getContextPath()%>/frontend/img/Pink_mobil.gif" width="20" height="35">
                                                  </td>
                                                  <td width="30%">
                                                    <input type="text" maxlength="12" style="width:120px;" name="sender_cell" value="${param.sender_cell}"> 
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
                                                    <input type="text" maxlength="25" style="width:320px;" name="sender_address" value="${param.sender_address}">
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
                                                    <input type="text" maxlength="3" style="width:40px; " name="sender_tel_front" value="${param.sender_tel_front}">
                                                    -
                                                    <input type="text" maxlength="8" style="width:120px;" name="sender_tel_back" value="${param.sender_tel_back}">
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
                                          <input type="text" maxlength="138" name="receiver_mail" value="${param.receiver_mail}">
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
                                            <c:forEach var="weightVO" items="${weightSvc.all}">
                                              <option value="${weightVO.weight_price}" ${(orderVO.item_weight==weightVO.weight_price)? 'selected':''}>${weightVO.weight_type}
                                            </c:forEach>
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
                                            <c:forEach var="sizeVO" items="${sizeSvc.all}">
                                              <option value="${sizeVO.size_price}" ${(orderVO.item_size==sizeVO.size_price)? 'selected':''}>${sizeVO.size_type}
                                            </c:forEach>
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
                                            <c:forEach var="traVO" items="${traSvc.all}">
                                              <option value="${traVO.transition_type}" ${(orderVO.item_type==traVO.transition_type)? 'selected':''}>${traVO.transition_type}
                                            </c:forEach>
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
                                          <input type="text" maxlength="138" class="expected_time" name="expected_time" value="${param.expected_time}">
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
                                          <input type="text" maxlength="138" name="expected_note" value="${param.expected_note}">
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
                                          <input type="hidden" name="action" value="insert">
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
          

        </div>
      </div>
    </div>



    
          
          

    

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">2018/2/4版本</p>
      </div> 
    </footer>
  
  </body>
  
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/frontend/js/orderQuery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
  
<script type="text/javascript">
$(document).ready(function(){
  if($("#sender_city").val() != ""){
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

console.log(${orderVO.receiver_tel});
  
})

</script>
 
</html>