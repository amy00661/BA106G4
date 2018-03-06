<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="iii.emp.model.*,java.util.*,iii.util.*" %>
<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	pageContext.setAttribute("titleList",StatusCodeUtil.empTitle);
	
%>    
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>資策貨運後台系統</title>
  
  <!-- CSS主要套件 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="<%=request.getContextPath()%>/backend/css/main.css" rel="stylesheet"> 
  <!-- CSS主要套件 -->

  <!-- CSS個人套件 -->
  <link href="<%=request.getContextPath()%>/backend/css/employee/emp_add.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/gh/atatanasov/gijgo@1.7.3/dist/combined/css/gijgo.min.css" rel="stylesheet" type="text/css" />
  <!-- CSS個人套件 -->
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
     <!-- Bread-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="#">員工系統</a>
          </li>
          <li class="breadcrumb-item active">修改員工&權限管理</li>
        </ol>
<!-- 主要功能 -->
      <div style="background-color: #ccffcc">
		<div class="container-fluid" id="emp_add">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font color='red'>請修正以下錯誤:
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message.value}</li>
						</c:forEach>
					</ul>
				</font>
			</c:if>
		<!-- 修改員工資料start -->
          <form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
            <div class="row row-custom">
              <!-- 左側欄：修改員工資料 Start -->
              <div class="col-xs-12 col-lg-7">
                <div class="card">
                  <div class="card-body">
                    <div class="form-group">
                      <label for="empid">員工編號：</label>
                      <span>${ empVO.emp_id }</span>
                     <input type="hidden" name="empid" value="${empVO.emp_id}" id="empid" >
                    </div>
                    <div class="form-group">
                      <label for="empname">員工姓名</label>
                      <input type="text" class="form-control form-control-custom" name="empname" value="${empVO.emp_name}" id="empname" placeholder="員工姓名">${errorMsgs.empname}
                      
                    </div>
                    <div class="form-group">
                      <label for="emp_title">職稱</label>                  
                      <select class="form-control form-control-custom" id="emp_title" name="emp_title">
                        <c:forEach var="emp_title" items="${titleList}">
                        	<option value="${emp_title.key}" ${(empVO.emp_title==emp_title.key)? 'selected':'' }>${emp_title.value}</option>
                        </c:forEach>
                      </select>
                      
                    </div>
                    <div class="form-group">
                      <label for="empmail">信箱</label>
                      <input type="text" class="form-control form-control-custom" name="empmail" value="${empVO.emp_email}" id="empmail" placeholder="○○○XXX@iiidistribution.com">${errorMsgs.empmail}
                    </div>
                    <div class="form-group">
                      <div class="form-check form-check-inline disabled">
                        <label class="form-check-label">狀態</label>
                      </div>
                    
                      <div class="form-check form-check-inline">  
                        <label class="form-check-label">
                          <input class="form-check-input" type="radio" name="empstatus" id="inlineRadio1" value="01" ${(empVO.emp_status=="01")?'checked="true"':'' }> 在職
                        </label>
                      </div>
                      <div class="form-check form-check-inline">
                        <label class="form-check-label">
                          <input class="form-check-input" type="radio" name="empstatus" id="inlineRadio2" value="00" ${(empVO.emp_status=="00")?'checked="true"':'' }> 離職
                        </label>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <jsp:useBean id="dbSvc" scope="page" class="iii.db.model.DBDAOService" />
                      <label for="db_name">貨運中心</label>                  
                      <select class="form-control form-control-custom" id="db_id" name="db_id">
                        <c:forEach var="dbVO" items="${dbSvc.all}">
                        	<option value="${dbVO.db_id}" ${(empVO.db_id==dbVO.db_id)? 'selected':'' }>${dbVO.db_name}</option>
                        </c:forEach>
                      </select>
                    </div>
                    <div class="form-group">
                      <%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
                      <c:set scope="page" var="hiredate" value="<%=date_SQL%>"></c:set>
                      <label>到職日期</label>
                      <input id="hireDate" width="40%" name="hireDate" value="${(empVO.emp_hireDate==null or empVO.emp_hireDate=='')? hiredate : empVO.emp_hireDate }"/>
                      ${errorMsgs.hireDate}
                    </div>
                    <div class="form-group">
                      <label>離職日期</label>
                      <input id="leaveDate" width="40%" name="leaveDate" value="${empVO.emp_leaveDate}"/>
                      ${errorMsgs.leaveDate}
                    </div>
                   </div>
                  </div>
              </div>
              <!-- 左側欄：修改員工資料 End -->
              
              <!-- 右側欄：設定員工權限 Start -->
              <div class="card col-xs-12 col-lg-5">
                <div class="card-body">
                  <div class="container-fluid">
                    <span id="menuArray">${ errorMsgs.menuArray }</span>
                    <div id="tree"></div>
                  </div>
                </div>
              </div>
              <!-- 右側欄：設定員工權限 End -->
            </div>
            <input type="hidden" name="action" value="update">
            <button type="submit" id="btnGet" class="btn btn-primary btn-lg btn-block" >送出</button>
          </form>
          <!-- 修改員工資料End -->
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

  <!-- JS主要套件 -->
  <script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
  <script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
  <!-- JS主要套件 -->
  <!-- JS個人套件 -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src="<%=request.getContextPath()%>/backend/js/gijgo-combined-1.7.3/gijgo.min.js"></script>
 <script>
    var tree;
    $(document).ready(function(){
      var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
      $('#hireDate').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        minDate: today,
        format:"yyyy/mm/dd"
      });
      $('#leaveDate').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        format:"yyyy/mm/dd"
      });
      
      // 讀取DB權限設定樹內容
     var onSuccessFunc = function (response) {
         //you can modify the response here if needed
         console.log(typeof(response));
         console.log(response);
         tree.render(response);
         //tree.render('[{"children":[{"children":[{"checked":false,"id":"MENU003","text":"車輛列表"}],"checked":false,"id":"MENU002","text":"車輛定位"},{"children":[{"checked":false,"id":"MENU005","text":"會員資料維護"}],"checked":false,"id":"MENU004","text":"會員管理"},{"children":[{"checked":false,"id":"MENU007","text":"員工權限設定"}],"checked":false,"id":"MENU006","text":"權限管理"},{"children":[{"children":[{"checked":false,"id":"MENU010","text":"運費設定"},{"checked":false,"id":"MENU011","text":"估算出車成本"}],"checked":false,"id":"MENU009","text":"運費管理"},{"checked":false,"id":"MENU012","text":"排班管理"},{"checked":false,"id":"MENU013","text":"車輛管理"}],"checked":false,"id":"MENU008","text":"基礎資料管理"},{"children":[{"checked":false,"id":"MENU015","text":"新增訂單"},{"checked":true,"id":"MENU016","text":"查詢訂單"},{"checked":true,"id":"MENU017","text":"訂單修改"},{"checked":false,"id":"MENU018","text":"訂單自動分類"},{"checked":false,"id":"MENU019","text":"訂單分配"}],"checked":false,"id":"MENU014","text":"訂單管理"},{"children":[{"checked":false,"id":"MENU021","text":"入庫作業"},{"checked":false,"id":"MENU022","text":"出庫作業"}],"checked":false,"id":"MENU020","text":"倉儲管理"},{"children":[{"checked":false,"id":"MENU024","text":"優惠活動維護"},{"checked":false,"id":"MENU025","text":"即時公告維護"},{"checked":false,"id":"MENU026","text":"內部公告"},{"checked":false,"id":"MENU027","text":"內部公告維護"}],"checked":false,"id":"MENU023","text":"最新資訊"},{"children":[{"checked":false,"id":"MENU029","text":"問與答維護"},{"checked":false,"id":"MENU030","text":"回報單處理"},{"checked":false,"id":"MENU031","text":"線上客服"}],"checked":false,"id":"MENU028","text":"客服系統"}],"checked":false,"id":"MENU001","text":"後台權限"}]');
     };
      tree = $('#tree').tree({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        dataSource: {
        	url: '${pageContext.request.contextPath}/employee/AuthorityServlet.do?action=getMenuTree&empid=${ empVO.emp_id }',
        	//dataType: "json",        	
        	success: onSuccessFunc,
        	error: function(xhr, ajaxOptions, thrownError) {
      			console.log(xhr.responseText);
      		}
        },
        //dataSource: [{"children":[{"children":[{"checked":false,"id":"MENU003","text":"車輛列表"}],"checked":false,"id":"MENU002","text":"車輛定位"},{"children":[{"checked":false,"id":"MENU005","text":"會員資料維護"}],"checked":false,"id":"MENU004","text":"會員管理"},{"children":[{"checked":false,"id":"MENU007","text":"員工權限設定"}],"checked":false,"id":"MENU006","text":"權限管理"},{"children":[{"children":[{"checked":false,"id":"MENU010","text":"運費設定"},{"checked":false,"id":"MENU011","text":"估算出車成本"}],"checked":false,"id":"MENU009","text":"運費管理"},{"checked":false,"id":"MENU012","text":"排班管理"},{"checked":false,"id":"MENU013","text":"車輛管理"}],"checked":false,"id":"MENU008","text":"基礎資料管理"},{"children":[{"checked":false,"id":"MENU015","text":"新增訂單"},{"checked":true,"id":"MENU016","text":"查詢訂單"},{"checked":true,"id":"MENU017","text":"訂單修改"},{"checked":false,"id":"MENU018","text":"訂單自動分類"},{"checked":false,"id":"MENU019","text":"訂單分配"}],"checked":false,"id":"MENU014","text":"訂單管理"},{"children":[{"checked":false,"id":"MENU021","text":"入庫作業"},{"checked":false,"id":"MENU022","text":"出庫作業"}],"checked":false,"id":"MENU020","text":"倉儲管理"},{"children":[{"checked":false,"id":"MENU024","text":"優惠活動維護"},{"checked":false,"id":"MENU025","text":"即時公告維護"},{"checked":false,"id":"MENU026","text":"內部公告"},{"checked":false,"id":"MENU027","text":"內部公告維護"}],"checked":false,"id":"MENU023","text":"最新資訊"},{"children":[{"checked":false,"id":"MENU029","text":"問與答維護"},{"checked":false,"id":"MENU030","text":"回報單處理"},{"checked":false,"id":"MENU031","text":"線上客服"}],"checked":false,"id":"MENU028","text":"客服系統"}],"checked":false,"id":"MENU001","text":"後台權限"}],
        primaryKey: 'id',
        checkboxes: true
      });
    });
    
    $('#btnGet').on('click', function() {
        var result = tree.getCheckedNodes();
        $.each( result, function( index, value ){
            $("#menuArray").append("<input type=\"hidden\" name=\"menuArray\" value="+value+">")
        });
    });
  </script>
  <!-- JS個人套件 -->
</body>
</html>