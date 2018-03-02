<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
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
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
<!-- _______________________________________________________________________ -->
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseExamplePages" data-parent="#exampleAccordion">
            <i class="fa fa-paper-plane"></i>
            <span class="nav-link-text">訂單管理</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseExamplePages">
            <li>
              <a href="Order_Show.html">訂單查詢</a>
            </li>
            <li>
              <a href="Order_CR.html">建立新訂單</a>
            </li>
          </ul>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="#">
            <i class="fa fa-list-ol"></i>
            <span class="nav-link-text">清單派車系統</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="#">
            <i class="fa fa-motorcycle"></i>
            <span class="nav-link-text">車輛排班系統</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="#">
            <i class="fa fa-plane"></i>
            <span class="nav-link-text">總部車輛排班系統</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseExamplePages1" data-parent="#exampleAccordion">
            <i class="fa fa-male"></i>
            <span class="nav-link-text">員工系統</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseExamplePages1">
            <li>
              <a href="emp_query.html">員工資料管理</a>
            </li>
            <li>
              <a href="emp_add.html">員工新增&權限管理</a>
            </li>
          </ul>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="vehicle.html">
            <i class="fa fa-car"></i>
            <span class="nav-link-text">車輛管理</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="#">
            <i class="fa fa-user"></i>
            <span class="nav-link-text">會員資料管理</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="#">
            <i class="fa fa-mobile"></i>
            <span class="nav-link-text">客服系統</span>
          </a>
        </li>

        


<!-- ____________________________________________________ -->
      </ul>
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
      	<div class="container-fluid" id="searchDiv">
      		<div class="row row-custom">
	  			<div class="col-xs-12 col-lg-4">
					<div class="form-group">
                      <label for="db_id">貨運中心：</label>
                      <input class="form-control-plaintext form-control-custom" type="text" value="${account.db_id}" name="db_id" id="db_id" readonly>
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
	  				<button type="button" id="orderQuery" class="btn btn-outline-primary btn-sm pull-right">訂單查詢</button>
	  			</div>
	  			<div class="col-xs-12 col-lg-2">
	  			</div>
	  			<div class="col-xs-12 col-lg-4">
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
	  			</div>
      		</div>
      	</div>
		<div class="container-fluid" id="resultDiv">
			<div class="row row-custom">
				<!-- 左側欄：查詢未出貨訂單 Start -->
				<div class="col-xs-12 col-lg-4">
					<div>
						以下列出所有未出貨訂單：
						<div class="card text-center">
							<select class="form-control" id ="unShipOrders" name="unShipOrders[]" multiple="multiple" style="height:200px;">
						          <option value="2">Row 2</option>  
						          <option value="4">Row 4</option>  
						          <option value="5">Row 5</option>  
						          <option value="6">Row 6</option>  
						          <option value="7">Row 7</option>  
						          <option value="8">Row 8</option>  
						          <option value="9">Row 9</option> 
							</select>							
						</div>
					</div>
				</div>
				<!-- 左側欄：查詢未出貨訂單 End -->
				<!-- 中間：查詢未出貨訂單 End -->
				<div class="col-xs-12 col-lg-2">
					<div class="input-group">
					  <button type="button" value="" class="btn btn-xs btn-primary" id="add">Add >></button>
					</div>
			      <div class="input-group" style="padding-top:10px;">
			        <button type="button" value="" class="btn btn-xs btn-danger" id="remove"><< Remove</button> 
			      </div>					
				</div>
				<!-- 中間：查詢未出貨訂單 End -->
				
				<!-- 右側欄：查詢車次派單 Start -->
				<div class="col-xs-12 col-lg-4">
					<div class="card text-center">
				      <select class="form-control" name="FeatureCodes[]" size="9" id="selected_features" multiple="multiple" style="height:200px;">
				        <option value="1">Row 1</option>  
				        <option value="3">Row 3</option>
				      </select>						
					</div>
				</div>
				<!-- 右側欄：查詢車次派單 End -->
				
			</div>
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
		var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
		$('#localOrderDate').datepicker({
	        uiLibrary: 'bootstrap4',
	        iconsLibrary: 'fontawesome',
	        minDate: today,
	        format:"yyyy/mm/dd"
	    });
		$('#localOrderDate').addClass("form-control-custom");
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
			data : {action:"getUnShipOrders",db_id:"${account.db_id}",item_type:$("#item_type").val()},
			datatype: "json",
			success : function(Jdata){
				$("#unShipOrders").empty();
				$.each(Jdata, function(index, element){
					$("#unShipOrders").append("<option value='"+ element.order_id +"'>"+element.order_id+" "
																+element.create_time 
											+"</option>");
				});
			}
		
		});
	});
</script>
</body>

</html>