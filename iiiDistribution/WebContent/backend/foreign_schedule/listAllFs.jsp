<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="iii.foreign_schedule.model.*"%>

<%
	FsVO fsVO = (FsVO) request.getAttribute("fsVO");
	FsService fsSvc = new FsService();
	List<FsVO> list = fsSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="dbSvc" scope="page" class="iii.db.model.DBDAOService" />
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
<link href="<%=request.getContextPath()%>/backend/css/sup.css"
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
			<!-- 引入MENU -->
			<jsp:include page="/backend/menu/menu.jsp" flush="true" />

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

			<h2>總部派車系統</h2>
			<%-- 錯誤表列 --%>
			
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>



			<table class="table table-striped">
				<thead>
					<tr>
						<th>車次編號</th>
						<th>車種</th>
						<th>車號</th>
						<th>出發站</th>
						<th>終點站</th>
						<th>出發時間</th>
						<th>更新時間</th>
						<th><div>
								<button type="button" class="btn btn-secondary btn-block"
									data-toggle="modal" data-target="#addCar">新增車次</button>
							</div></th>
							
						<th></th>
					</tr>
				</thead>

				<tbody>
					<%@ include file="page1.file"%>

					<c:forEach var="fsVO" items="${list}" varStatus="number"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr
							${(fsVO.foreign_schedule_ID==param.foreign_schedule_ID) ? 'style="background-color:#99ffcc"':''}>
							<td>${fsVO.foreign_schedule_ID}</td>
							<td>${fsVO.car_TYPE}</td>
							<td>${fsVO.car_ID}</td>
							<td><c:forEach var="dbVO" items="${dbSvc.all}">
									<c:if test="${fsVO.star_DB==dbVO.db_id}">
					                    	【${dbVO.db_id}】${dbVO.db_name}
					                    </c:if>
								</c:forEach></td>

							<td><c:forEach var="dbVO" items="${dbSvc.all}">
									<c:if test="${fsVO.end_DB==dbVO.db_id}">
					                    	【${dbVO.db_id}】${dbVO.db_name}
					                    </c:if>
								</c:forEach></td>


							<td>${fsVO.fs_TIME}</td>
							<td><fmt:formatDate value="${fsVO.fs_updatetime}"
									pattern="yyyy-MM-dd HH:mm" /></td>
							<td>
								<div id="updatediv">
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/foreign_schedule/fs.do">
										<input type="submit" value="修改" class="btn btn-info btn-block">
										<input type="hidden" name="foreign_schedule_ID"
											value="${fsVO.foreign_schedule_ID}"> <input
											type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>"> <input
											type="hidden" name="whichPage" value="<%=whichPage%>">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</div>

								<div id="deldiv">
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/foreign_schedule/fs.do">
										<input type="submit" value="刪除"
											class="btn btn-outline-danger btn-block"
											onclick="return del(${number.index})"> <input
											type="hidden" class="num" name="foreign_schedule_ID"
											value="${fsVO.foreign_schedule_ID}"> <input
											type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>"> <input
											type="hidden" name="whichPage" value="<%=whichPage%>">
										<input type="hidden" name="action" value="delete">
									</FORM>
								</div> 
								
							<script>
							function del(data) {
								var id = document.getElementsByClassName("num")[data].value;
							    if (confirm("確定刪除嗎")==true) {
							    	alert('已成功刪除' + id +'訂單！');
							    	return true;
							    } else {
							    	alert('取消刪除');
		
							    	return false;
							    }
							}
							</script>

							</td>
						</tr>
					</c:forEach>
					<%@ include file="page2.file"%>
				</tbody>
			</table>




			<!-- 新增資料是窗 -->

			<br>

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

	<!-- 新增資料跳窗-->
	<FORM METHOD="post" ACTION="fs.do" name="addcarform">
		<div class="modal fade" id="addCar" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="addCar">新增車次表單</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">


						<div>
							<jsp:useBean id="traSvc" scope="page" class="iii.fee_transition.model.TraService"/>
							<label for="inputState">車種</label> 
							<select id="inputState"
								name="car_TYPE" class="form-control">
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
						<input type="hidden" name="action" value="insert">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">離開</button>
						<input type="hidden" name="action" value="insert"> <input
							type="submit" value="送出" class="btn btn-primary">

					</div>
				</div>
			</div>
		</div>
	</FORM>






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
		            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
		            <form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
		            	<input type="hidden" name="action" value="logout">
				 		<button type="submit" class="btn btn-info btn-block" >登出</button>
		            </form>					
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