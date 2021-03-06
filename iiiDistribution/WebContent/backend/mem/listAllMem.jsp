<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>  
<%@ page import="iii.mem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
	pageContext.setAttribute("list", list);

%>

<html>

<head>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>資策貨運後台系統</title>
  
  <!-- 主要套件 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="<%=request.getContextPath()%>/backend/css/main.css" rel="stylesheet"> 
  			

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
		    <!-- 聊天室Start -->
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<%--<c:if test="${account.emp_id!=''}"> --%>
			    <div class="chatDiv" style="position: fixed;z-index:1; right: 120px; top: 3px;">
			    		<jsp:include page="/backend/chat/chat_backend.jsp" flush="true" />
			    </div>
				<%--</c:if> --%>
    		<!-- 聊天室End -->
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
      <div >

     <table class="table table-hover">
       <thead class="thead-dark">
		<tr>
			<th>會員編號</th>
			<th>會員信箱</th>
			<th>會員名稱</th>
			<th>會員狀態</th>
			<th>生日</th>
			<th>性別</th>
			<th>身分證字號(統編)</th>
			<th>手機</th>
			<th>電話</th>
			<th>地址</th>
			<th>修改時間</th>
			<th>修改</th>
		</tr>
	  </thead>
	<%@ include file="page1.file" %>	
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${memVO.member_id}</td>
			<td>${memVO.member_mail }</td>
			<td>${memVO.member_name }</td>
			<td>${(memVO.member_status=='01')?'啟用':'停用' }</td>
			<td>${memVO.member_birth }</td>
			<td>${memVO.member_gender }</td>
			<td>${memVO.member_identification }</td>
			<td>${memVO.member_cell }</td>
			<td>${memVO.member_phone }</td>
			<td>${memVO.member_addr }</td>
			<td>${memVO.member_updatetime }</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
					<button class="btn btn-info btn-block" type="submit" id="btnUpdate">修改</button>
					<input type="hidden" name="member_id" value="${memVO.member_id}">
					<input type="hidden" name="flag" value="no">
					<input type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
		</tr>

	</c:forEach>
</table>

<%@ include file="page2.file" %>
          
           
        

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
            <button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
            <form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
            	<input type="hidden" name="action" value="logout">
            	<button type="submit" class="btn btn-primary" >登出</button>
            </form>
          </div>
        </div>
      </div>
    </div>


  <script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js" integrity="sha256-0YPKAwZP7Mp3ALMRVB2i8GXeEndvCq3eSl/WsAl1Ryk=" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
  <script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
  
 
</body>
</html>