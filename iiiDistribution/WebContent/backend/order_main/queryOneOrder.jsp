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

<!-- CSS -->
  <!-- =================================================== -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/css/jquery.datetimepicker.css" />
  <!-- =================================================== -->
</head>

<% request.setAttribute("car_plate", "" ); %>


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
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-9">
      <form METHOD="post" ACTION="<%= request.getContextPath() %>/order_main/order.do">
        <div style="position:absolute; left:30%;">
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
    <div class="col-xs-12 col-sm-3" style="margin-top:10%">
      <div class="row">
      	  <form action="<%=request.getContextPath()%>/backend/order_main/queryOrder.jsp">
      	  	<img src="<%=request.getContextPath()%>/frontend/img/carBack.gif" height="103">
		     <button type="submit" class='btn btn-warning update Pink15' id="submitButton" style="margin-top:30px; margin-bottom:30px;"> 回到訂單查詢
		     </button>
		  </form>
		  
		  <form METHOD="post" ACTION="<%= request.getContextPath() %>/order_main/order.do">
		  	<img src="<%=request.getContextPath()%>/frontend/img/carNext.gif" height="103">
		  	<button type="submit" class='btn btn-default update Pink15' id="submitButton"  >  修改此訂單 
		    </button>
		    <input type="hidden" name="order_id" value="${orderVO.order_id}">
		    <input type="hidden" name="action" value="insert">
		  </form>
      </div>
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
  $(document).ready(function(){
    $.ajax({
      type : "post",
      url  : "<%=request.getContextPath()%>/order_main/place.do",
      data : {plasebig:'桃園市'},
      datatype: "json",
      success : function(data){
        var str = $.parseJSON(data);
        $("#receiver_county").empty();
        $("#receiver_county").append("<option value=''>大光榮桃園市</option>");
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