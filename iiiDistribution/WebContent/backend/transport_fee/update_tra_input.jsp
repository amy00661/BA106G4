<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>  
<%@ page import="iii.tra.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	TraVO traVO = (TraVO) request.getAttribute("traVO");
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script language="javascript">
	$(document).ready(function(){
	
	})
	</script>
<html>

<style>
  	tr, th, td {
    padding: 2px;
    text-align: center;
  }

  </style>


<head>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>運費資料修改</title>
  
  <!-- 主要套件 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="<%=request.getContextPath()%>/backend/css/main.css" rel="stylesheet"> 
  <link href="<%=request.getContextPath()%>/backend/css/mem.css" rel="stylesheet"> 
  			

  <!-- 主要套件 -->
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

   <div class="container-fluid">
     
<!-- 主要功能 -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs }">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<!-- 主要功能 -->
	<div>
	<form method="post" action="<%=request.getContextPath()%>/tra/tra.do" name="form">
	  <table id="tb" class="table table-sm">
       <thead class="thead-dark">
		<tr>
			<th width="175px"><input style="text-align:center;" type="text" name="transition_id" class="form-control" id="inputTra" disabled="disabled"  value="${traVO.transition_id }"></th>
			<th width="380px"><input style="text-align:center;" type="text" name="transition_type" class="form-control" id="inputTra"   value="${traVO.transition_type }"></th>
			<th width="190px"><input style="text-align:center;" type="text" name="transition_price" class="form-control" id="inputTra"   value="${traVO.transition_price }"></th>
			<th width="275px"><input style="text-align:center;" type="text" name="emp_id" class="form-control" id="inputTra" disabled="disabled"  value="${traVO.emp_id }"></th>
			<th width="470px"><input style="text-align:center;" type="text" name="transition_updateTime" class="form-control" id="inputTra" disabled="disabled"  value="${traVO.transition_updatetime }"></th>
			<th>
				<input type=hidden name="action" value="update">
				<input type=hidden name="transition_id" value="${traVO.transition_id }">
				<input type=hidden name="emp_id" value="${traVO.emp_id }">
				<button class="btn btn-info btn-block" type="submit" id="btnlogin">送出修改</button>
			</th>
			<th>
				 <a class="btn btn-info" href="<%=request.getContextPath()%>/backend/transport_fee/listAllFee.jsp" role="button">取消修改</a>
			</th>
		</tr>
	  </thead>
	  </form>
	</table>

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


  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
  <script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
  
 
</body>
</html>