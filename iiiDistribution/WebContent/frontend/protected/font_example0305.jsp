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
<title>index</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">
<link href="css/main.css" rel="stylesheet">

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

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
					
<!-- 有問題 -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">寄件申請</a>
						<div class="dropdown-menu" aria-labelledby="dropdown10">
							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/frontend/order_main/order_main.jsp">線上申請寄件</a>
							<a class="dropdown-item" href="#">寄件指南</a> <a
								class="dropdown-item" href="cal.html">運費查詢</a>
						</div></li>

					

					<li class="nav-item "><a
						class="nav-link " href="#" id="aa"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">客服中心</a>
						
						
						<div class="dropdown" aria-labelledby="aa">
							<a class="dropdown" href="#">常見問題</a> <a
								class="dropdown" href=<%=request.getContextPath()%>
								/protected/select_MemPage.jsp>聯絡我們</a>
						</div></li>


<!-- 有問題 -->


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


<!-- 											網頁內容 -->
				<p class="lead">
					提供端對端長途運輸服務，範圍涵蓋台灣全省。<br> <br>
					資策貨運貨運能在交通繁雜的台灣地區提供完善的長途運輸服務，端靠遍及全省的樞紐輻射式配送網絡，以及各區域緊密配合的合作夥伴（協力車隊）。<br>
					<br>
					我們擁有及管理一支現代化的貨櫃車車隊，以20呎和40呎長的貨櫃車，為您提供高效率、具成本效益及準時的運輸服務。絕大部分貨櫃車均裝設了全球定位系統及監控設備，確保可從出發點至目的地，24小時及全程追踪和監控您的貨件。
				</p>
				<p class="lead">
					提供端對端長途運輸服務，範圍涵蓋台灣全省。<br> <br>
					資策貨運貨運能在交通繁雜的台灣地區提供完善的長途運輸服務，端靠遍及全省的樞紐輻射式配送網絡，以及各區域緊密配合的合作夥伴（協力車隊）。<br>
					<br>
					我們擁有及管理一支現代化的貨櫃車車隊，以20呎和40呎長的貨櫃車，為您提供高效率、具成本效益及準時的運輸服務。絕大部分貨櫃車均裝設了全球定位系統及監控設備，確保可從出發點至目的地，24小時及全程追踪和監控您的貨件。
				</p>
				<p class="lead">
					提供端對端長途運輸服務，範圍涵蓋台灣全省。<br> <br>
					資策貨運貨運能在交通繁雜的台灣地區提供完善的長途運輸服務，端靠遍及全省的樞紐輻射式配送網絡，以及各區域緊密配合的合作夥伴（協力車隊）。<br>
					<br>
					我們擁有及管理一支現代化的貨櫃車車隊，以20呎和40呎長的貨櫃車，為您提供高效率、具成本效益及準時的運輸服務。絕大部分貨櫃車均裝設了全球定位系統及監控設備，確保可從出發點至目的地，24小時及全程追踪和監控您的貨件。
				</p>

				<!-- 											網頁內容 -->


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