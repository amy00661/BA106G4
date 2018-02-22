<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>資策貨運後台系統</title>

	<!-- CSS主要套件 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="<%=request.getContextPath()%>/backend/css/main.css" rel="stylesheet">
  <!-- CSS主要套件 -->
</head>
<body>
	<c:forEach var="menuNodeVO" items="${sessionScope.menuBar}">
		<!-- 後台權限 -->
		<ul class="navbar-nav navbar-sidenav" id="${menuNodeVO.id}">
			<c:forEach var="firstLevel" items="${menuNodeVO.children}">
				<!-- 第一層選單 -->
	          	<c:if test="${fn:length(firstLevel.children) > 0}">
					<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
          				<a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#${firstLevel.id}" data-parent="#${menuNodeVO.id}">
            				<i class="fa fa-paper-plane"></i>
            				<span class="nav-link-text">${firstLevel.text}</span>
          				</a>
						<ul class="sidenav-second-level collapse" id="${firstLevel.id}">
						
							<c:forEach var="secondLevel" items="${firstLevel.children}">
								<!-- 第二層選單 -->
					          	<c:if test="${fn:length(secondLevel.children) > 0}">
									<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
				          				<a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#${secondLevel.id}" data-parent="#${firstLevel.id}">
				            				<i class="fa fa-paper-plane"></i>
				            				<span class="nav-link-text">${secondLevel.text}</span>
				          				</a>
										<ul class="sidenav-second-level collapse" id="${secondLevel.id}">
										
											<c:forEach var="thirdLevel" items="${secondLevel.children}">
												<!-- 第三層選單 -->
												<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
								          			<a class="nav-link" href="#">
								            			<span class="nav-link-text">${thirdLevel.text}</span>
								          			</a>
							        			</li>
											</c:forEach>
										
										</ul>
									</li>
								</c:if>
								<c:if test="${fn:length(secondLevel.children) == 0}">
	<!-- 								<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables"> -->
	<!-- 				          			<a class="nav-link" href="#"> -->
	<%-- 				            			<span class="nav-link-text">${secondLevel.text}</span> --%>
	<!-- 				          			</a> -->
	<!-- 			        			</li> -->
				        			<li>
	              						<a href="#">${secondLevel.text}</a>
	            					</li>
								</c:if>
							</c:forEach>
							
						</ul>
					</li>
				</c:if>
				<c:if test="${fn:length(firstLevel.children) == 0}">
					<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
	          			<a class="nav-link" href="#">
	            			<span class="nav-link-text">${firstLevel.text}</span>
	          			</a>
        			</li>
				</c:if>
			</c:forEach>	
		</ul>
		
		<!-- 後台權限 -->
		
	</c:forEach>

<!--   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script> -->
<!--   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script> -->
<%--   <script src="<%=request.getContextPath()%>/backend/js/main.js"></script> --%>

</body>
</html>