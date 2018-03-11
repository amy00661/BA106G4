<!DOCTYPE html>
<%@	page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="iii.mem.model.*"%>

<%
		MemVO memVO=(MemVO) request.getAttribute("memVO");
%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>會員更新資料</title>
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
    	$('#selGender option[value=${memVO.member_gender}]').attr('selected', 'selected');
    
    	var member_address="${memVO.member_addr}";  //取得整串地址
		var city=member_address.substring(0,3);   	//取縣市
		var country=member_address.substring(3,6);  //取鄉鎮
    	var address=member_address.substr(6,15);	//取後半段地址
 
    	$("#sel").val(city);                        //設定縣市下拉是選單的選項
    	$('#address').val(address);					//賦予後半段地址的值
    	
    	$.ajax({
				type : "post",
				url  : "<%=request.getContextPath()%>/mem/test.do",   //將縣市值帶入 取得鄉鎮下拉是選單的值
				data : {plasebig:city},
				datatype: "json",
				success : function(data){
					var str = $.parseJSON(data);

  				$("#sel2").empty();
  				for(var i=0;i<str.length;i++){
  					$("#sel2").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
  				}	
  					$("#sel2").val(country); 
				}
			})
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
				 <div class="card border-info">
		  <h5 class="card-header bg-info text-white">會員資料修改</h5>
		  <form method="post" action="<%=request.getContextPath()%>/mem/mem.do" name="form">
		  <div class="card-body">
			
			
		  <div class="form-group row">
		    <label for="inputEmail" class="col-sm-3 col-form-label">會員信箱:</label>
		    <div class="col-sm-9">
		      <input type="email" name="member_mail" class="form-control" id="inputEmail" disabled value="${memVO.member_mail}">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputPsw" class="col-sm-3 col-form-label">密碼:</label>
		    <div class="col-sm-9">
		      <input type="password" name="member_psw" class="form-control" id="inputPsw" value="${memVO.member_psw}">
		    </div>
		  </div>
		  
		   <div class="form-group row">
		    <label for="inputPsw2" class="col-sm-3 col-form-label">確認密碼:</label>
		    <div class="col-sm-9">
		      <input type="password" name="member_psw2" class="form-control" id="inputPsw2" value="${memVO.member_psw}">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputName" class="col-sm-3 col-form-label">會員名稱:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_name" class="form-control" id="inputName" value="${memVO.member_name}">
		    </div>
		  </div>
		  
		  
		  <div class="form-group row">
		    <label for="inputBirth" class="col-sm-3 col-form-label">生日:</label>
		    <div class="col-sm-9">
		      <input type="date" name="member_birth" class="form-control" id="inputBirth" value="${memVO.member_birth}">
		    </div>
		  </div>
		  
		  
		  <div class="form-group row">
		    <label for="inputGender" class="col-sm-3 col-form-label">性別:</label>
		    <div class="col-sm-9">
		      <select  class="form-control" name="member_gender" id="selGender">
				<option value="保密">保密</option>
				<option value="男">男</option>
			    <option value="女">女</option>
			  </select>
		    </div>
		  </div>
		  
		  
		  <div class="form-group row">
		    <label for="inputIden" class="col-sm-3 col-form-label">身分證字號/統編:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_identification" class="form-control" id="inputIden" value="${memVO.member_identification}">
		    </div>
		  </div>
		  
		  
		  <div class="form-group row">
		    <label for="inputCell" class="col-sm-3 col-form-label">手機:</label>
		    <div class="col-sm-9">
		      <input type="tel" name="member_cell" class="form-control" id="inputCell" value="${memVO.member_cell}">
		    </div>
		  </div>
		  
		  
		  <div class="form-group row">
		    <label for="inputPhone" class="col-sm-3 col-form-label">連絡電話:</label>
		    <div class="col-sm-9">
		      <input type="tel" name="member_phone" class="form-control" id="inputPhone" value=${(memVO.member_phone==null)?"" : memVO.member_phone}>
		    </div>
		  </div>
		  
		  <div class="form-group row">
 						<label for="cellphone" class="col-sm-2 col-form-label"><i class="fa fa-star" style="font-size:10px" aria-hidden="true"></i>地址:</label>
							<div class="col-sm-2">
								 <select  class="form-control" name="member_addr1" id="sel">
								      
									  <option value="台北市">台北市</option>
									  <option value="基隆市">基隆市</option>
									  <option value="新北市">新北市</option>
									  <option value="桃園市">桃園市</option>
									  <option value="新竹市">新竹市</option>
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
									  <option value="花蓮縣">花蓮縣</option>
									  <option value="台東縣">台東縣</option>
								  </select>
								
							</div>
							<div class="col-sm-2">
								 <select  class="form-control" name="member_addr2" id="sel2">
								      <option value="0">鄉鎮</option>								
								  </select>
							
							</div>
							<div class="col-sm-6">
                          	<input type="text" class="form-control" id="address" name="member_addr3" required />
                 		  </div>
 					</div>
		  
<!-- 		  <div class="form-group row"> -->
<!-- 		    <label for="inputAddr" class="col-sm-3 col-form-label">地址:</label> -->
<!-- 		    <div class="col-sm-9"> -->
<%-- 		      <input type="text" name="member_addr" class="form-control" id="inputAddr" value="${memVO.member_addr}"> --%>
<!-- 		    </div> -->
<!-- 		  </div> -->
         
         
         </div>
         
         <div class="text-center"> <!--按鈕置中-->
           <input type=hidden name="action" value="update">
			<input type=hidden name="member_id" value="${memVO.member_id}">
			<input type=hidden name="member_mail" value="${memVO.member_mail}">
			<input type=hidden name="member_updatetime" value="${memVO.member_updatetime}">
<!-- 			<button class="btn btn-info" type="submit" id="btnlogin">送出修改</button> -->
			<input type ="submit" id="btnlogin" class="btn btn-info" value="送出修改"></input>
		
		</div>
		<br>
		</form>
          </div>
          <br>

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