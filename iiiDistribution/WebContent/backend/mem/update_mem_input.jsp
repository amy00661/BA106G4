<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>  
<%@ page import="iii.mem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script language="javascript">
	$(document).ready(function(){
		//勾選會員先前設定的值(性別)
		$('#selGender option[value=${memVO.member_gender}]').attr('selected', 'selected');
		//勾選會員先前設定的值(狀態)
		$('input[name=member_status][value=${memVO.member_status}]').attr('checked',true);
		var time = new Date();
		
		$("#inputUpdate").val(time.toLocaleString()); //取得日期時間
		
	})
	</script>
<html>

<head>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>會員資料修改</title>
  
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

<div class="content-wrapper">
   <div class="container-fluid">
     
<!-- 主要功能 -->
    <div class="container">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs }">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


<div class="card border-info" style="max-width: 60rem;">
  <h5 class="card-header bg-info text-white">會員資料修改</h5>
  <div class="card-body">
  	  <form method="post" action="<%=request.getContextPath()%>/mem/mem.do" name="form">
			
			
		  <div class="form-group row">
		    <label for="inputId" class="col-sm-2 col-form-label">會員編號:</label>
		    <div class="col-sm-10">
		      <input type="text" name="member_id" class="form-control" id="inputId" disabled value="<%=memVO.getMember_id() %>">
		    </div>
		  </div>
			
		  <div class="form-group row">
		    <label for="inputEmail" class="col-sm-2 col-form-label">會員信箱:</label>
		    <div class="col-sm-10">
		      <input type="email" name="member_mail" class="form-control" id="inputEmail" disabled value="<%=memVO.getMember_mail() %>">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputPsw" class="col-sm-2 col-form-label">會員密碼:</label>
		    <div class="col-sm-10">
		      <input type="password" name="member_psw" class="form-control" id="inputPsw" disabled value="<%=memVO.getMember_psw() %>">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputName" class="col-sm-2 col-form-label">會員名稱:</label>
		    <div class="col-sm-10">
		      <input type="text" name="member_name" class="form-control" id="inputName" value="<%=memVO.getMember_name() %>">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputStatus" class="col-sm-2 col-form-label">會員狀態:</label>
		    <div class="col-sm-10">
		    	<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="inputStatus1" name="member_status" class="custom-control-input" value='01'>
					<label class="custom-control-label" for="inputStatus1">啟用</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="inputStatus0" name="member_status" class="custom-control-input" value='00'>
					<label class="custom-control-label" for="inputStatus0">停用</label>
		   		 </div>
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputBirth" class="col-sm-2 col-form-label">生日:</label>
		    <div class="col-sm-10">
		      <input type="date" name="member_birth" class="form-control" id="inputBirth" value="<%=memVO.getMember_birth() %>">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputGender" class="col-sm-2 col-form-label">性別:</label>
		    <div class="col-sm-10">
						<select  class="form-control" name="member_gender" id="selGender">
							<option value="保密">保密</option>
							<option value="男">男</option>
					   	 	<option value="女">女</option>
					    </select>
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputIden" class="col-sm-2 col-form-label">身分證字號/統編:</label>
		    <div class="col-sm-10">
		      <input type="text" name="member_identification" class="form-control" id="inputIden" value="<%=memVO.getMember_identification() %>">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputCell" class="col-sm-2 col-form-label">手機:</label>
		    <div class="col-sm-10">
		      <input type="text" name="member_cell" class="form-control" id="inputCell" value="${memVO.member_cell}">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputPhone" class="col-sm-2 col-form-label">連絡電話:</label>
		    <div class="col-sm-10">
		      <input type="text" name="member_phone" class="form-control" id="inputPhone" value="<%=memVO.getMember_phone() %>">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputAddr" class="col-sm-2 col-form-label">地址:</label>
		    <div class="col-sm-10">
		      <input type="text" name="member_addr" class="form-control" id="inputAddr" value="<%=memVO.getMember_addr() %>">
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="inputPerson" class="col-sm-2 col-form-label">修改員工編號:</label>
		    <div class="col-sm-10">
		      <input type="text" name="emp_id" class="form-control" id="inputPerson" value="<%=memVO.getEmp_id() %>">
		    </div>
		  </div>
		  
<!-- 		   <div class="form-group row"> -->
<!-- 		    <label for="inputUpdate" class="col-sm-2 col-form-label">修改時間:</label> -->
<!-- 		    <div class="col-sm-10"> -->
<!-- 		      <input type="text" name="member_updatetime" class="form-control" id="inputUpdate"> -->
<!-- 		    </div> -->
<!-- 		  </div> -->

  		<input type=hidden name="action" value="updateBack">
		<input type=hidden name="member_id" value="<%=memVO.getMember_id() %>">
		<input type=hidden name="member_mail" value="<%=memVO.getMember_mail() %>">
		<input type=hidden name="member_psw" value="<%=memVO.getMember_psw() %>">
		<button class="btn btn-info btn-block" type="submit" id="btnlogin" onclick="start()">送出修改</button>
<!-- 		<input type=submit value="送出修改"> -->
  	  </form>
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


  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
  <script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
  
  <script type="text/javascript">
	    
	  var MyPoint = "/MyEchoServer";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	  
	  var webSocket =
		  new WebSocket(endPointURL);//ServerEndpoint監聽的URL.
	 
	    webSocket.onerror = function(event) {
	      onError(event)
	    };
	 
	    webSocket.onopen = function(event) {
	      onOpen(event)
	    };
	 
	    webSocket.onmessage = function(event) {
	      onMessage(event)
	    };
	//  接收到server訊息時觸發.
	    function onMessage(event) {
	    }
	//  建立與server的連接.
	    function onOpen(event) {
	    }
	//  連線錯誤時觸發
	    function onError(event) {
/* 	      alert(event.data); */
	    }
	//  按下按鈕後觸發,發送訊息給server
	    function start() {
		var member_id="${memVO.member_id}";
	    var member_status=$('input[name=member_status]:checked').val();
 	    var jsonObj = {"member_id" : member_id, "member_status" : member_status};
 	    webSocket.send(JSON.stringify(jsonObj));

	      return false;
	    }
	  </script>
 
</body>
</html>