<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="iii.emp.model.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<title>本地訂單派車</title>
<!-- CSS主要套件 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="<%=request.getContextPath()%>/backend/css/main.css" rel="stylesheet">
<!-- CSS主要套件 -->
<!-- =================================================== -->
<!-- CSS個人套件 -->
  <link href="<%=request.getContextPath()%>/backend/css/local_order.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/gh/atatanasov/gijgo@1.7.3/dist/combined/css/gijgo.min.css" rel="stylesheet" type="text/css" />
<!-- CSS個人套件 -->	
<!-- =================================================== -->
</head>
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





<div class="content-wrapper">
    <div class="container-fluid">
     
<!-- 主要功能 -->
      <div style="background-color: #ccffcc">
      	<div class="container-fluid" id="orderDiv">
      		<div class="row row-custom">
	  			<div class="card col-xs-12 col-lg-5">
	  				<div class="card-body">
						<div class="form-group">
	                      <label for="db_id">貨運中心：</label>
	                      <jsp:useBean id="dbSvc" scope="page" class="iii.db.model.DBDAOService" />
							  <% 
							  	EmpVO empVO = (EmpVO)session.getAttribute("account");
							  	String db_id = empVO.getDb_id();
							  %>
							  <%=dbSvc.getOneDB(db_id).getDb_name() %>
		                      <input class="form-control-plaintext form-control-custom" type="hidden" value="${account.db_id}" name="db_id" id="db_id">
	                    </div>
	                    <div class="form-group">
	                    	<jsp:useBean id="traSvc" scope="page" class="iii.fee_transition.model.TraService"/>
	                        <label for="item_type">訂單類型：</label>
	                        <select class="form-control form-control-custom" name="item_type" id="item_type">
	                          <c:forEach var="traVO" items="${traSvc.all}">
	                            <option value="${traVO.transition_type}" ${(orderVO.item_type==traVO.transition_type)? 'selected':''}>${traVO.transition_type}
	                          </c:forEach>
	                        </select>
	                    </div>
	                    <br>
	  					<br>
		  				<button type="button" id="orderQuery" class="btn btn-outline-primary btn-sm pull-right">訂單查詢</button>
	  					<br>
	  					<div>
							以下列出所有未出貨訂單：
							<div class="card text-center">
								<select class="form-control" id ="unShipOrders" name="unShipOrders[]" multiple="multiple" style="height:200px;">
								</select>							
							</div>
						</div>
	  				</div>
	  			</div>
	  			<div class="col-xs-12 col-lg-2" id="middleDiv">
				      <div class="input-group" id="addDiv">
					  	<button type="button" value="" class="btn btn-outline-secondary btn-sm" id="add"> >> </button>
				      </div>
				      <div class="input-group" style="padding-top:10px;" id="removeDiv">
				        <button type="button" value="" class="btn btn-sm btn-outline-secondary" id="remove"> << </button> 
				      </div>
	  			</div>
	  			<div class="card col-xs-12 col-lg-5">
	  				<div class="card-body">
		  				<div class="form-group form-control-custom">
	                      <%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
	                      <c:set scope="page" var="localOrderDate" value="<%=date_SQL%>"></c:set>
	                      <label>出車日期：</label>
	                      <input id="localOrderDate" width="100%" name="loDate" value="${ localOrderDate }"/>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label for="car_type">車種類型：</label>
	                        <select class="form-control form-control-custom" name="car_type" id="car_type">
	                          <option value="">請選擇</option>
	                          <c:forEach var="traVO" items="${traSvc.all}">
	                            <option value="${traVO.transition_type}">${traVO.transition_type}車</option>
	                          </c:forEach>
	                        </select>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label for="local_schedule">車班：</label>
	                        <select class="form-control form-control-custom" name="local_schedule" id="local_schedule">
	                          <option value="">請選擇</option>
	                        </select>
	                    </div>
	                    <button type="button" id="localOrderQuery" class="btn btn-outline-primary btn-sm pull-right">派單查詢</button>
	                    <br>
	                    	以下列出已分派之訂單：
	                    <div class="card text-center">
					      <select class="form-control" name="localOrders[]" size="9" id="localOrders" multiple="multiple" style="height:200px;">
					      </select>						
						</div>
					</div>	
	  			</div>
      		</div>
      		<button id="submit" class="btn btn-primary btn-lg btn-block">送出</button>
      	</div>
		
      </div>
<!-- 主要功能 -->


  </div>
</div>
    
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
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
            	<input type="hidden" name="action" value="logout">
		 		<button type="submit" class="btn btn-info btn-block" >登出</button>
            </form>
          </div>
        </div>
      </div>
    </div>


<!-- js主要套件 -->
  <script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
  <script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
<!-- js主要套件 -->
<!-- js個人套件 -->
  <script src="<%=request.getContextPath()%>/backend/js/gijgo-combined-1.7.3/gijgo.min.js"></script>
<script>
	$( document ).ready(function() {
		var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()+1);
		$('#localOrderDate').datepicker({
	        uiLibrary: 'bootstrap4',
	        iconsLibrary: 'fontawesome',
	        minDate: today,
	        format:"yyyy-mm-dd"
	    });
		$("#orderQuery").trigger( "click" );
		//$('#localOrderDate').addClass("form-control-custom");
		var helfHeight = Math.floor($(".row-custom").height() / 2);
		var divLeft = $("#middleDiv").position().left;
		var helfWidth = Math.floor($("#middleDiv").width() / 2);
		$("#addDiv").css({'top': helfHeight + "px"});
		$("#removeDiv").css({'top':$("#addDiv").position().top + "px"});
		//$("#exchangeBtns").css('top', rowHeight + "px");
	});
	
	$("#car_type").change(function(){
		$.ajax({
			type : "post",
			url  : "<%=request.getContextPath()%>/local_schdule/LS_Servlet.do",
			data : {action:"getLSbyCarType", car_type:$("#car_type").val()},
			datatype: "json",
			success : function(Jdata){
				$("#local_schedule").empty();
				$.each(Jdata, function(index, element){
					$("#local_schedule").append("<option value='"+ element.local_schedule_id +"'>"+element.ls_time+" "+element.local_schedule_id +"</option>");
				});
			}
		
		});
	});
	$("#orderQuery").click(function(){
		$.ajax({
			type : "post",
			url  : "<%=request.getContextPath()%>/local_order/LO_Servlet.do",
			//data : {action:"getUnShipOrders",db_id:"${account.db_id}",item_type:$("#item_type").val()},
			data : {action:"getUnShipOrders",db_id:"${account.db_id}",item_type:$("#item_type").val()},
			datatype: "json",
			success : function(Jdata){
				$("#unShipOrders").empty();
				$.each(Jdata, function(index, element){
					$("#unShipOrders").append("<option value='"+ element.order_id +"'>"+element.order_id+" "
																+element.expected_time 
											+"</option>");
				});
			}
		});
	});
	$('#localOrderQuery').click(function(){
		$.ajax({
			type : "post",
			url  : "<%=request.getContextPath()%>/local_order/LO_Servlet.do",
			//data : {action:"getLocalOrders",loDate:$('localOrderDate').val(),item_type:$("#local_schedule").val()},
			data : {action:"getLocalOrders",db_id:"${account.db_id}",loDate:$('#localOrderDate').val(),item_type:$("#local_schedule").val()},
			datatype: "json",
			success : function(Jdata){
				$("#localOrders").empty();
				$.each(Jdata, function(index, element){
					$("#localOrders").append("<option value='"+ element.order_id +"'>訂單:"+element.order_id
												+" 車次:"+element.expected_time
											+"</option>");
				});
			}
		});
	});
	$('#add').click(function() {  
	    return !$('#unShipOrders option:selected')
				.remove().appendTo('#localOrders');  
	   });
	
	$('#remove').click(function() {  
	    return !$('#localOrders option:selected')
				.remove().appendTo('#unShipOrders');  
	   }); 
	$('#submit').click(function() {  
		var localOrderDate = $('#localOrderDate').val();
		var local_schedule_id = $('#local_schedule').val();
		$('#localOrders option').prop('selected', true);
		var localOrders = [];
		$("#localOrders :selected").map(function(i, item) {
			localOrders.push($(item).val());
		});
		console.log(localOrders);
		//var localOrders = JSON.stringify(orderArray);
		$.ajax({
			type : "post",
			url  : "<%=request.getContextPath()%>/local_order/LO_Servlet.do",
			//data : {action:"getLocalOrders",loDate:$('localOrderDate').val(),item_type:$("#local_schedule").val()},
			data : {action:"update",localOrderDate:localOrderDate,local_schedule_id:local_schedule_id,localOrders:localOrders,emp_id:"${account.emp_id}"},
			success : function(data){
				if(data>0)
					alert("資料更新成功!")
				else
					alert("資料更新有誤...")
			}
		});
	}); 
	
	
	
	
</script>
</body>

</html>