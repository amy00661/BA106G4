<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="iii.order_main.model.*" %>
<%@ page import="iii.weight.model.*" %>
<%@ page import="iii.tra.model.*" %>
<%@ page import="iii.mem.model.*" %>
<%@ page import="iii.emp.model.*" %>
<% 
  OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
  WeightVO weightVO = (WeightVO) request.getAttribute("weightVO");
  TraVO traVO = (TraVO) request.getAttribute("traVO");
  EmpVO account = (EmpVO) session.getAttribute("account");
  OrderService orderSvc=new OrderService();
  List<OrderVO> list = orderSvc.getAll();
  /* Set<OrderVO> set = OrderSvc.getOrderByDBAndEmp(empVO.getDb_id(), empVO.getEmp_id()); */
  Set<OrderVO> set = orderSvc.getDBAndEmpOrderByTime(account.getDb_id(), account.getEmp_id());
  pageContext.setAttribute("list", list); 
  pageContext.setAttribute("set", set); 
%>


${account.db_id}
${account.emp_id} 
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
    <style>
      /* entire container, keeps perspective */
      .flip-container {
        perspective: 1000;
        transform-style: preserve-3d;
      }
      /*  UPDATED! flip the pane when hovered */
      .flip-container:hover .back {
        transform: rotateY(0deg);
      }
      .flip-container:hover .front {
          transform: rotateY(180deg);
      }
      .flip-container, .front, .back {
        width: 200px;
        height: 200px;
      }
      /* flip speed goes here */
      .flipper {
        transition: 0.6s;
        transform-style: preserve-3d;

        position: relative;
      }
      /* hide back of pane during swap */
      .front, .back {
        backface-visibility: hidden;
        transition: 0.6s;
        transform-style: preserve-3d;

        position: absolute;
        top: 0;
        left: 0;
      }
      /*  UPDATED! front pane, placed above back */
      .front {
        z-index: 2;
        transform: rotateY(0deg);
      }

      /* back, initially hidden pane */
      .back {
        transform: rotateY(-180deg);
      }
      /* 
        Some vertical flip updates 
      */
      .vertical.flip-container {
        position: relative;
      }
      .vertical .back {
        transform: rotateX(180deg);
      }
      .vertical.flip-container:hover .back {
          transform: rotateX(0deg);
      }

      .vertical.flip-container:hover .front {
          transform: rotateX(180deg);
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
    .button {
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
      <div class="container">
	    <div class="row">
	      <div class="col-xs-12 col-sm-2">
	      	<c:forEach var="orderVO" items="${set}" varStatus="nextline">
              <c:if test="${nextline.count%7 == 1}"><tr></c:if>
              <td>
                <div class="flip-container" ontouchstart="this.classList.toggle('hover');">
                  <div class="flipper">
                    <div class="front">
                      <img src="<%=request.getContextPath()%>/frontend/img/orderBlur.jpg" class="orderBlur" width="110%" style="padding:30px">
                      <span style="font-weight:bold; position: absolute; top: 45%; left: 22%; font-size:20px">
                       	&nbsp; 客戶訂單編號
                        <br>
                        ==${orderVO.order_id}==
                      </span>
                    </div>
                    <div class="back">
                      <img src="<%=request.getContextPath()%>/frontend/img/orderBlur.jpg" class="orderBlur" width="100%" style="">
                      <form METHOD="post" ACTION="<%= request.getContextPath() %>/order_main/order.do">
                        <span style="font-weight:bold; position: absolute; top: 4%; left: 12%; text_align:center; font-size:19px;" >
                          <br>訂單編號:${orderVO.order_id}
                          <br>收件者姓名:${orderVO.receiver_name}
                          <br>預計送達時間:
                          <br><fmt:formatDate value="${orderVO.expected_time}" pattern="yyyy/MM/dd hh點"/>
                          <br>&nbsp;&nbsp;&nbsp;&nbsp;
                          <button type="submit" class='btn btn-warning update Pink15' id="submitButton" style="font-weight:bold; text_align:center; font-size:19px;"> 
                            	點擊查詢
                            	
                          </button>
                          <input type="hidden" name="order_id" value="${orderVO.order_id}">
                          <input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
                          <input type="hidden" name="action" value="getOne_For_Display">
                        </span>
                      </form>
                    </div>
                  </div>
                </div>
              </td>
            </c:forEach>
	      </div>
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
        $("#receiver_county").append("<option value=''>選擇鄉鎮市區</option>");
        for(var i=0;i<str.length;i++){
          $("#receiver_county").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
        }     
      }
    })
  })

  $(".outer").click(function(){
    window.open("<%=request.getContextPath()%>/backend/order_main/orderGoogleMap.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1600,height=950");
  });
</script>
</html>