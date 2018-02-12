<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="iii.emp.model.*,java.util.*,iii.util.*,org.json.JSONObject"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
	pageContext.setAttribute("titleList",StatusCodeUtil.empTitle);
	//Convert HashMap to json for javascript
	JSONObject titleListJson = new JSONObject(StatusCodeUtil.empTitle);
	pageContext.setAttribute("titleListJson",titleListJson);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="text/html; charset=utf-8; IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>資策貨運後台系統</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
	  
	<link href="<%=request.getContextPath()%>/backend/css/main.css" rel="stylesheet">  
	<link href="<%=request.getContextPath()%>/backend/css/gijgo-combined-1.7.3/gijgo.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/backend/css/employee/emp_query.css" rel="stylesheet">
	<!-- Bootstrap core JavaScript -->
	<script src="https://use.fontawesome.com/4a61e60fc7.js"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<!-- 	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script> -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
	<!-- this page JavaScript -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
	<script src="https://cdn.jsdelivr.net/gh/atatanasov/gijgo@1.7.3/dist/combined/js/gijgo.min.js"></script>
</head>
<body>
    <div class="content-wrapper">
      <div class="container-fluid">
        <!-- Bread-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="#">員工系統</a>
          </li>
          <li class="breadcrumb-item active">員工資料管理</li>
        </ol>
        
        <div id="emp_query">
          <div class="container-fluid container-fluid-custom">
            <div class="row">
              <div class="col-lg-10 offset-lg-1">
                <!-- 員工查詢欄位start -->
                <div>
                  <form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
                    <div class="row">
                      <!-- 員工查詢左側欄 -->
                      <div class="col-sm-5">
                        <div class="form-group">
                          <label for="empname">員工姓名</label>
                          <input type="text" class="form-control form-control-custom" id="empname" placeholder="員工姓名">
                        </div>
                        <div class="form-group">
                          <label for="emp_title">職稱</label>                  
                          <select class="form-control form-control-custom" id="emp_title" name="emp_title">
                        	<c:forEach var="emp_title" items="${titleList}">
                        		<option value="${emp_title.key}" >${emp_title.value}</option>
                        	</c:forEach>
                      	  </select>
                        </div>
                        
                        <div class="form-check form-check-inline disabled">
                          <label class="form-check-label">狀態</label>
                        </div>
                        
                        <div class="form-check form-check-inline">  
                          <label class="form-check-label">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 在職
                          </label>
                        </div>
                        <div class="form-check form-check-inline">
                          <label class="form-check-label">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 離職
                          </label>
                        </div>
                        
                        
                      </div>
                      <!-- 員工查詢右側欄 -->
                      <div class="col-md-7">
                        <div class="form-group">
                          <jsp:useBean id="dbSvc" scope="page" class="iii.db.model.DbService" />
                          <label for="db_name">貨運中心</label>                  
                          <select class="form-control form-control-custom" id="db_id" name="db_id">
	                        <c:forEach var="dbVO" items="${dbSvc.allDB}">
	                        	<option value="${dbVO.db_id}" ${(param.db_id==dbVO.db_id)? 'selected':'' }>${dbVO.db_name}</option>
	                        </c:forEach>
	                      </select>
                        </div>
                        <div class="form-group">
                          <label>到職日期</label>
                          <input id="hireDateFrom" width="40%" />~<input id="hireDateTo" width="40%" />
                        </div>
                        <div class="form-group">
                          <label>離職日期</label>
                          <input id="leaveDateFrom" width="40%" />~<input id="leaveDateTo" width="40%" />
                        </div>
                        <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
                      </div>
                    </div>
                    <button type="button" id="btnQuery" class="btn btn-info">查詢</button>&nbsp
                    <a href="#" class="btn btn-primary active" role="submit" aria-pressed="true">新增員工</a>
                    
                  </form>
                </div>
                <!-- 員工查詢欄位End -->
                <br><br>
                <!-- 員工查詢結果start -->
                <div id="output"></div>
                <!-- 員工查詢結果End -->
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- /.container-fluid-->
    </div>
    <!-- /.content-wrapper-->
    
    <script>
    $("#btnQuery").click(function() {
  	  //alert( "Handler for .click() called." );
  	  	var ctx = "${pageContext.request.contextPath}";
  	  	var conditions = { "action": "getAll", name: "John", location: "Boston" };
  	  	
  		$.ajax({
  		  url: ctx + "/employee/EmpServlet.do",
  		  type: "post",
  		  dataType: "json",
  		  data:  conditions,
  		  success: function(Jdata) {
  			  console.log(Jdata);
  		  
  			var table="<div class=\"table table-hover\">";
  			table = table+"<div class=\"thead\"><div class=\"thead-row\">"+
  							"<div>員工編號</div><div>員工姓名</div><div>狀態</div>"
  							+"<div>貨運中心</div><div>職稱</div><div>到職日</div><div>離職日</div>"+
  							"</div></div>";//<class=\"thead-row\"><class=\"thead\">
  			table = table+"<div class=\"tbody\">";
  			$.each(Jdata, function(index, element){
  				table = table+"<div class=\"tbody-row\">";
  				//每筆資料的第一個cell為form表單
  				var updateURL = ctx+"/employee/EmpServlet.do?empid="+element.emp_id;
  				table = table+"<div><FORM METHOD=\"post\" ACTION=\" "+ updateURL +" \" >"
  									//+"<input type=\"submit\" value="+element.emp_id+">";
  									+"<button type=\"submit\" class=\"btn btn-link\">"+element.emp_id+"</button>"
  									+"<input type=\"hidden\" name=\"empno\" value="+element.emp_id+">"
  									+"<input type=\"hidden\" name=\"action\" value=\"getOne_For_Update\">";
  				table = table+"</form></div>";//第一個cell的form結束 
  				//其餘欄位資訊
  				var emp_leaveDate = (typeof element.emp_leaveDate == "undefined")?"":element.emp_leaveDate;
  				var titleListJson = ${titleListJson};	
  				var emp_title = element.emp_title;
  				table = table+"<div>" + element.emp_name +"</div>"
                +"<div>" + element.emp_status +"</div><div>" + element.db_id +"</div>"
                + "<div>" + titleListJson[emp_title] +"</div><div>" + element.emp_hireDate + "</div>"
                + "<div>" + emp_leaveDate + "</div>";
  				
  				table = table+"</div>";// class=\"tbody-row\"
  			});
  			table = table+"</div>";//<class="class=\"tbody\">
  			table = table+"</div>";//<class=\"table table-hover\">
  			$("#output").html(table);
  		  },    		  
  		  error: function(xhr, ajaxOptions, thrownError) {
  			console.log(xhr.responseText);
  		  }
  		});
  });
    
    $(document).ready(function(){
      var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
      $('#hireDateFrom').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        minDate: today,
        maxDate: function () {
          return $('#hireDateTo').val();
        },
        format:"yyyy/mm/dd"
      });
      $('#hireDateTo').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        minDate: function () {
          return $('#hireDateFrom').val();
        },
        format:"yyyy/mm/dd"
      });
      $('#leaveDateFrom').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        minDate: today,
        maxDate: function () {
          return $('#leaveDateTo').val();
        },
        format:"yyyy/mm/dd"
      });
      $('#leaveDateTo').datepicker({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        minDate: function () {
          return $('#leaveDateFrom').val();
        },
        format:"yyyy/mm/dd"
      });
      
    })
  </script>
</body>
</html>