<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="iii.foreign_schedule.model.*"%>

<%
	FsVO fsVO = (FsVO) request.getAttribute("fsVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>總部派車系統</title>
<!-- 主要套件 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />
<link href="<%=request.getContextPath()%>/backend/css/main.css"
	rel="stylesheet">

<!-- 主要套件 -->




</head>
<body class="fixed-nav sticky-footer" id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">

		<a class="navbar-brand" href="select_page.jsp"><strong>資策貨運後台系統</strong></a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<!-- _______________________________________________________________________ -->

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Example Pages"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseExamplePages" data-parent="#exampleAccordion"> <i
						class="fa fa-paper-plane"></i> <span class="nav-link-text">訂單管理</span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapseExamplePages">
						<li><a href="Order_Show.html">訂單查詢</a></li>
						<li><a href="Order_CR.html">建立新訂單</a></li>
					</ul></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Charts"><a class="nav-link" href="select_page.jsp">
						<i class="fa fa-list-ol"></i> <span class="nav-link-text">清單派車系統</span>
				</a></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Tables"><a class="nav-link" href="<%=request.getContextPath()%>/backend/local_schedule/listAllLs.jsp"> <i
						class="fa fa-motorcycle"></i> <span class="nav-link-text">車輛排班系統</span>
				</a></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Tables"><a class="nav-link" href="<%=request.getContextPath()%>/backend/foreign_schedule/listAllFs.jsp"> <i
						class="fa fa-plane"></i> <span class="nav-link-text">總部車輛排班系統</span>
				</a></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Example Pages"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseExamplePages1" data-parent="#exampleAccordion">
						<i class="fa fa-male"></i> <span class="nav-link-text">員工系統</span>
				</a>
					<ul class="sidenav-second-level collapse"
						id="collapseExamplePages1">
						<li><a href="emp_query.html">員工資料管理</a></li>
						<li><a href="emp_add.html">員工新增&權限管理</a></li>
					</ul></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Tables"><a class="nav-link" href="vehicle.html"> <i
						class="fa fa-car"></i> <span class="nav-link-text">車輛管理</span>
				</a></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Tables"><a class="nav-link" href="#"> <i
						class="fa fa-user"></i> <span class="nav-link-text">會員資料管理</span>
				</a></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Tables"><a class="nav-link" href="#"> <i
						class="fa fa-mobile"></i> <span class="nav-link-text">客服系統</span>
				</a></li>




				<!-- ____________________________________________________ -->
			</ul>
			<ul class="navbar-nav sidenav-toggler">
				<li class="nav-item"><a class="nav-link text-center"
					id="sidenavToggler"> <i class="fa fa-chevron-left"></i>
				</a></li>
			</ul>

			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<form>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="站內搜尋">
							<div class="input-group-append">
								<button type="submit" class="btn btn-secondary">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
				</li>
				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#logout"> <i class="fa fa-sign-out"></i>員工登出
				</a></li>
			</ul>

		</div>
	</nav>





	<div class="content-wrapper">
		<div class="container-fluid">

			<!-- 主要功能 -->




			<div class="col-xs-12 col-sm-5">
				<FORM METHOD="post" ACTION="fs.do" name="form1">
					<div>

						<h2>修改車班</h2>
						<h5>
							<a
								href="<%=request.getContextPath()%>/backend/foreign_schedule/listAllFs.jsp">回主畫面</a>
						</h5>

						<div>
							<hr>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>


							<div>
								<jsp:useBean id="traSvc" scope="page" class="iii.fee_transition.model.TraService"/>
								<label for="inputState">車種</label> <select id="inputState"
									name="car_TYPE" class="form-control" value="value="<%=(fsVO == null) ? "" : fsVO.getCar_TYPE()%>">
									<c:forEach var="traVO" items="${traSvc.all}">
						           		<option value="${traVO.transition_type}" ${(fsVO.car_TYPE==traVO.transition_type)? 'selected':''}>${traVO.transition_type}</option>
						        	</c:forEach>
								</select>

							</div>

							<jsp:useBean id="carSvc" scope="page"
								class="iii.car.model.CarService" />
							<div class="form-group">
								<label for="car">車號</label> <select class="form-control"
									id="carSvc" name="car_ID">
									<c:forEach var="carVO" items="${carSvc.all}">
										<option value="${carVO.car_id}">${carVO.car_id}
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label for="exampleFormControlSelect1">出車時間</label>
								<div class="input-group date" id="datetimepicker3"
									data-target-input="nearest">
									<input type="text" class="form-control datetimepicker-input"
										data-target="#datetimepicker3"
										value="<%=(fsVO == null) ? "" : fsVO.getFs_TIME()%>"
										name="fs_time" />
									<div class="input-group-append" data-target="#datetimepicker3"
										data-toggle="datetimepicker">
										<div class="input-group-text">
											<i class="fa fa-clock-o"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<jsp:useBean id="dbSvc" scope="page"
							class="iii.db.model.DBDAOService" />
						<div class="row">
							<div class="col-xs-12 col-sm-6">
								<label for="inputState">起點</label> <select class="form-control"
									id="dbSvc" name="star_DB">
									<c:forEach var="dbVO" items="${dbSvc.all}">
										<option value="${dbVO.db_id}"
											${(fsVO.star_DB==dbVO.db_id)? 'selected':'' }>${dbVO.db_name}
									</c:forEach>
								</select>

							</div>
							<div class="col-xs-12 col-sm-6">
								<label for="inputState">終點</label> <select class="form-control"
									id="dbSvc" name="end_DB">
									<c:forEach var="dbVO" items="${dbSvc.all}">
										<option value="${dbVO.db_id}"
											${(fsVO.end_DB==dbVO.db_id)? 'selected':'' }>${dbVO.db_name}
									</c:forEach>
								</select>
							</div>
						</div>

					</div>

					<div class="modal-footer">

						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="foreign_schedule_ID"
							value="<%=fsVO.getForeign_schedule_ID()%>"> <input
							type="hidden" name="requestURL"
							value="<%=request.getParameter("requestURL")%>"> <input
							type="hidden" name="whichPage"
							value="<%=request.getParameter("whichPage")%>"> <input
							type="submit" value="<<確認並更新>>" class="btn btn-primary btn-lg"
							onclick="return update()">
					</div>
				</FORM>
			</div>


			<script>
				function update() {
					if (confirm("確定更新此車次資料?") == true) {
						alert('已更新');
						return true;
					} else {
						alert('已取消');
						return false;
					}
				}
			</script>



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
	<div class="modal fade" id="logout" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">

		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">確定要登出?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">＠Ｑ＠．．．．．．．．．．．．．</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>



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
	<script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/backend/js/moment.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/locale/zh-tw.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>


	<script type="text/javascript">
		$(function() {
			$('#datetimepicker3').datetimepicker({
				format : 'LT'
			});
		});
	</script>


</body>



</html>