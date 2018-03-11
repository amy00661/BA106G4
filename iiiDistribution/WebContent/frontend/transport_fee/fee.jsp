<!DOCTYPE html>
<%@	page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="iii.mem.model.*"%>

<%
	// 	MemVO memVO=(MemVO) request.getAttribute("memVO");
%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>運費試算</title>
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
<script language="javascript">
  	$(document).ready(function(){
  		
  		$.ajax({				//網頁一載入帶入台北市各個區
			type : "post",
			url  : "<%=request.getContextPath()%>/mem/test.do",
			data : {plasebig:"台北市"},
			datatype: "json",
			success : function(data){
				var str = $.parseJSON(data);

				$("#sel2").empty();
				for(var i=0;i<str.length;i++){
					$("#sel2").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
				}	
				
				$("#sel4").empty();
				for(var i=0;i<str.length;i++){
					$("#sel4").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
				}
			}
			})
	
  		
	$("#sel").change(function(){
		
		$.ajax({
				type : "post",
				url  : "<%=request.getContextPath()%>/mem/test.do",
				data : {plasebig:$("#sel").val()},
				datatype: "json",
				success : function(data){
					var str = $.parseJSON(data);

  				$("#sel2").empty();
  				for(var i=0;i<str.length;i++){
  					$("#sel2").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
  				}			
				}
				})
		})
		
		$("#sel3").change(function(){
		
		$.ajax({
				type : "post",
				url  : "<%=request.getContextPath()%>/mem/test.do",
				data : {plasebig:$("#sel3").val()},
				datatype: "json",
				success : function(data){
					var str = $.parseJSON(data);

  				$("#sel4").empty();
  				for(var i=0;i<str.length;i++){
  					$("#sel4").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
  				}			
				}
				})
		})
		
		
		$("#TRA001").attr('checked',true);   
		$("#calBtn").click(function(){
			
			var place1=$("#sel").val()+$("#sel2").val();
   			var place2=$("#sel3").val()+$("#sel4").val();
   			var sizeVal=$("#sel_size option:selected").val();
   			var sizeText=$("#sel_size option:selected").text();
   			var weightVal=$("#sel_weight option:selected").val();
   			var weightText=$("#sel_weight option:selected").text();
   			var tempVal=$('input[name=tempRadio]:checked').val();
   			var tempText=$('input[name=tempRadio]:checked').next("label").text();
			
			
			$.ajax({
					type : "post",
					url  : "<%=request.getContextPath()%>/proDis/proDis.do",
					success: function(data){
						var str = $.parseJSON(data);
						
						if(str.length==2){
							var dis = Math.round(str[0]*100)/100;
							var discount = dis*10;
							var title =str[1];
							var total=parseInt(sizeVal*weightVal*tempVal);
							var total2=parseInt((sizeVal*weightVal*tempVal)*dis);
				   			$("#result1").html("從 "+place1+" 寄到 "+place2+"，尺寸為 "+sizeText+"，重量為 "+weightText+"，使用 "+tempText+
				   					"服務，介於<<"+title+">>折扣期間給予"+discount+"折優惠，原價為"+total+" 元折扣後");

				   			$("#result2").html("您的包裹運費為 ");
				   			$("#sp1").text(total2+"元");
						}else{
							
							var total=parseInt(sizeVal*weightVal*tempVal);
							$("#result1").html("從 "+place1+" 寄到 "+place2+"，尺寸為 "+sizeText+"，重量為 "+weightText+"，使用 "+tempText+
					   					"服務，");
					   		$("#result2").html("您的包裹運費為 ");
					   		$("#sp1").text(total+"元");
							
							}
					}
			})
   		});
  	})

   </script> 
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
						href=${ (memVO==null) ? "http://localhost:8081/BA106G4/frontend/logIn.jsp" : "http://localhost:8081/BA106G4/frontend/logOut.jsp" }
						data-toggle=""
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
						<li class="breadcrumb-item active">運費試算</li>
					</ol>
				</div>
				<!-- /麵包削 -->

				<!-- ================================(主要網格9格)====================================== -->
				<div>
         	<div class="card border-info mb-3">
       
                <div class="card-header card-bg">
                    <div class="row">
                        <div class="col-9"><b>運費試算</b></div>
                        <div class="col-3" style="text-align:right;"></div>
                    </div>   
                </div>
                <div class="container">
                    
                    
                    <table class="table">
					  <thead>
					    <tr>
					    
					      <th><div class="col-6">Q1.您的包裹要從哪裡寄到哪裡?<br></div>
					      
					      <div class="col-6"></div>
					      
					       <span class="col-sm-2 text-center"><p class="font-weight-bold align-left">寄件地點</p></span>
				                <span class="col-sm-4 "> 
				                        <span class="form-group">
				                             <select class="form-control" name="fee_addr1" id="sel">
				                                <option value="台北市">台北市</option><option value="基隆市">基隆市</option>
									 			<option value="新北市">新北市</option><option value="桃園市">桃園市</option>
									 			<option value="新竹市">新竹市</option><option value="苗栗縣">苗栗縣</option>
											    <option value="台中市">台中市</option><option value="彰化縣">彰化縣</option>
									  			<option value="南投縣">南投縣</option><option value="雲林縣">雲林縣</option>
									  			<option value="嘉義市">嘉義市</option><option value="嘉義縣">嘉義縣</option>
									  			<option value="台南市">台南市</option><option value="高雄市">高雄市</option>
									  			<option value="屏東縣">屏東縣</option><option value="宜蘭縣">宜蘭縣</option>
									  			<option value="花蓮縣">花蓮縣</option><option value="台東縣">台東縣</option>
									  
				                             </select>
				                         </span> 
				                         <span class="form-group">
				                             <select class="form-control" name="fee_addr2" id="sel2">
				                             	<option value="0">鄉　鎮</option>								
				                             </select>
				
				                        </span>
				                    
				                </span>
				        
				                <span class="col-sm-2 text-center"><p class="font-weight-bold align-left">收件地點</p></span>
				
				                  <span class="col-sm-4">
				                        <span class="form-group">
				
				                             <select class="form-control" name="fee_addr3" id="sel3">
				                                <option value="台北市">台北市</option><option value="基隆市">基隆市</option>
									 			<option value="新北市">新北市</option><option value="桃園市">桃園市</option>
									 			<option value="新竹市">新竹市</option><option value="苗栗縣">苗栗縣</option>
											    <option value="台中市">台中市</option><option value="彰化縣">彰化縣</option>
									  			<option value="南投縣">南投縣</option><option value="雲林縣">雲林縣</option>
									  			<option value="嘉義市">嘉義市</option><option value="嘉義縣">嘉義縣</option>
									  			<option value="台南市">台南市</option><option value="高雄市">高雄市</option>
									  			<option value="屏東縣">屏東縣</option><option value="宜蘭縣">宜蘭縣</option>
									  			<option value="花蓮縣">花蓮縣</option><option value="台東縣">台東縣</option>
				                             </select>
				                        </span>
				                        <span class="form-group">
				
				                              <select class="form-control" name="fee_addr4" id="sel4">
				                               	<option value="0">鄉　鎮</option>
				                             </select>
				                         </span>
				                  </span>
					      
					      </th>

					    </tr>
					  </thead>
					  <thead>
					    <tr>
					    <jsp:useBean id="sizeSvc" scope="page" class="iii.size.model.SizeService"/>
					      
					      <th>
					      <div>
					      <div class="col-sm-5" style="float:left">Q2.您要寄的包裹尺寸(長+寬+高)為?</div>
					      <div class="col-sm-7" style="float:left">
					      	<select  class="form-control" name="fee_size" id="sel_size">
								<c:forEach var="sizeVO" items="${sizeSvc.all}">
									<option value="${sizeVO.size_price}">${sizeVO.size_type}
								</c:forEach>
							</select>
						   </div>
						   </div>
					     </th>
					  	 
					    </tr>
					    
					    <tr>
					    <jsp:useBean id="weightSvc" scope="page" class="iii.weight.model.WeightService"/>
					      <th>
					      
					      	<div class="col-sm-5" style="float:left">Q3.您的包裹重量重量為?</div>
					      	<div class="col-sm-7" style="float:left">
						      	<select  class="form-control" name="fee_weight" id="sel_weight">
									<c:forEach var="weightVO" items="${weightSvc.all}">
										<option value="${weightVO.weight_price}">${weightVO.weight_type}
									</c:forEach>
								</select>
					      	
					      	</div>
					      </th>
					    </tr>
					    
					    <tr>
					    <jsp:useBean id="traSvc" scope="page" class="iii.tra.model.TraService"/>
					      <th><div class="col-6" style="float:left">Q4.您的包裹是需要常溫配送或是低溫配送?</div>
					      <div class="col-6" style="float:left">
					      
					      <c:forEach var="traVO" items="${traSvc.all}">
					      	<div class="custom-control custom-radio custom-control-inline">
								  <input type="radio" id="${traVO.transition_id}" name="tempRadio" class="custom-control-input" value='${traVO.transition_price}'>
								  <label class="custom-control-label" for="${traVO.transition_id}">${traVO.transition_type}</label>
							</div>
						  </c:forEach>
							
					      </div>
					      </th>
					    
					    </tr>
					  </thead>
					  
					</table>
	   					 <div class="card">
		                    <div class="card-header card-bg">
		                        <div class="row">
		                            <div class="mx-auto">
		                                <input type ="button" id="calBtn" class="btn btn-success" href="#" value="計算運費"></input>  
		                             </div>                     
		                        </div> 
		                   </div>
		                    <div class="container">
		                           <div class="text-center">
		                               <div id="result1"></div> 
		                               <span id="result2"></span><span class="font-weight-bold" id="sp1" style="color:red"></span> 
		                             
		                            </div>
		                    </div>
		
		                  	</div>
					  <br>
                </div>
              </div>
          	<br>
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