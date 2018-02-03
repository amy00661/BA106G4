<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="iii.emp.model.*"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>資策貨運後台系統</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
 	<link href="<%=request.getContextPath()%>/back-end/css/main.css" rel="stylesheet">
  	<link href="<%=request.getContextPath()%>/back-end/css/employee/emp_add.css" rel="stylesheet">
  	<!-- <link href="css/gijgo-combined-1.7.3/gijgo.min_old.css" rel="stylesheet" type="text/css"> -->
  	<link href="https://cdn.jsdelivr.net/gh/atatanasov/gijgo@1.7.3/dist/combined/css/gijgo.min.css" rel="stylesheet" type="text/css" />
	<!-- Bootstrap core JavaScript -->
  	<script src="https://use.fontawesome.com/4a61e60fc7.js"></script>
 	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
  	<script src="<%=request.getContextPath()%>/back-end/js/main.js"></script>
  	<script src="<%=request.getContextPath()%>/back-end/js/gijgo-combined-1.7.3/gijgo.min.js"></script>
</head>
<body>
	 <div class="content-wrapper">
      <div class="container-fluid">
        <!-- Bread-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="#">員工系統</a>
          </li>
          <li class="breadcrumb-item active">員工新增&權限管理</li>
        </ol>
        <!-- 主要工作區 Start-->
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
				<!-- 新增員工資料start -->
          <form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
            <div class="row row-custom">
              <!-- 左側欄：新增員工資料 Start -->
              <div class="col-xs-12 col-lg-7">
                <div class="card">
                  <div class="card-body">
                    <div class="form-group">
                      <label for="empname">員工姓名</label>
                      <input type="text" class="form-control form-control-custom" name="empname" value="${param.empname}" id="empname" placeholder="員工姓名">${errorMsgs.empname}
                    </div>
                    <div class="form-group">
                      <label for="emp_title">職稱</label>                  
                      <select class="form-control form-control-custom" id="emp_title" name="emp_title">
                        <option value="head_office_manager">總公司經理</option>
                        <option value="branch_manager">地區經理</option>
                        <option value="customer_service" >客服人員</option>
                        <option value="driver">司機</option>
                        <option value="IT">資訊人員</option>
                        <option value="warehouse_staff">倉管人員</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="empmail">信箱</label>
                      <input type="text" class="form-control form-control-custom" name="empmail" value="${param.empmail}" id="empmail" placeholder="○○○XXX@iiidistribution.com">${errorMsgs.empmail}
                    </div>
                    <div class="form-group">
                      <div class="form-check form-check-inline disabled">
                        <label class="form-check-label">狀態</label>
                      </div>
                    
                      <div class="form-check form-check-inline">  
                        <label class="form-check-label">
                          <input class="form-check-input" type="radio" name="empstatus" id="inlineRadio1" value="01"> 在職
                        </label>
                      </div>
                      <div class="form-check form-check-inline">
                        <label class="form-check-label">
                          <input class="form-check-input" type="radio" name="empstatus" id="inlineRadio2" value="00"> 離職
                        </label>
                      </div>
                    </div>
                    
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
                      <%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
                      <c:set scope="page" var="hiredate" value="<%=date_SQL%>"></c:set>
                      <label>到職日期</label>
                      <input id="hireDate" width="40%" name="hireDate" value="${(param.hireDate==null or param.hireDate=='')? hiredate : param.hireDate }"/>
                      ${errorMsgs.hireDate}
                    </div>
                    <div class="form-group">
                      <label>離職日期</label>
                      <input id="leaveDate" width="40%" name="leaveDate" value="${param.leaveDate}"/>
                      ${errorMsgs.leaveDate}
                    </div>
                   </div>
                  </div>
              </div>
              <!-- 左側欄：新增員工資料 End -->
              
              <!-- 右側欄：設定員工權限 Start -->
              <div class="card col-xs-12 col-lg-5">
                <div class="card-body">
                  <div class="container-fluid">
                    <div id="tree"></div>
                  </div>
                </div>
              </div>
              <!-- 右側欄：設定員工權限 End -->
            </div>
            <input type="hidden" name="action" value="insert">
            <button type="submit" class="btn btn-primary btn-lg btn-block" >送出</button>
          </form>
          <!-- 新增員工資料End -->
        </div>
        <!-- 主要工作區 End-->
        
      </div>
      <!-- /.container-fluid-->
    </div>
    <!-- /.content-wrapper-->
    
    <script>
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

      // 權限設定樹內容初始化
      $('#tree').tree({
        uiLibrary: 'bootstrap4',
        iconsLibrary: 'fontawesome',
        dataSource: [
        {
          "text": "後台權限",
          "checked": true,
          "children": [
          {
            "text": "車輛定位",
            "checked": true,
            "children": [
            {
              "text": "車輛列表",
              "checked": true
            }
            ]
          },
          {
            "text": "會員管理",
            "checked": true,
            "children": [
            {
              "text": "會員資料維護",
              "checked": false
            }
            ]
          },
          {
            "text": "權限管理",
            "checked": true,
            "children": [
            {
              "text": "員工權限設定",
              "checked": false
            }
            ]
          },
          {
            "text": "基礎資料管理",
            "checked": true,
            "children": [
            {
              "text": "運費管理",
              "checked": false,
              "children": [
              {
                "text": "運費設定",
                "checked": false
              },
              {
                "text": "估算出車成本",
                "checked": false
              }
              ]
            },
            {
              "text": "排班管理",
              "checked": false
            },
            {
              "text": "車輛管理",
              "checked": false
            }
            ]
          },
          {
            "text": "訂單管理",
            "checked": true,
            "children": [
            {
              "text": "新增訂單",
              "checked": false
            },{
              "text": "查詢訂單",
              "checked": false
            },{
              "text": "訂單修改",
              "checked": false
            },{
              "text": "訂單自動分類",
              "checked": false
            },{
              "text": "訂單分配",
              "checked": false
            }
            ]
          },
          {
            "text": "倉儲管理",
            "checked": true,
            "children": [
            {
              "text": "入庫作業",
              "checked": false
            },{
              "text": "出庫作業",
              "checked": false
            }
            ]
          },
          {
            "text": "最新資訊",
            "checked": true,
            "children": [
            {
              "text": "優惠活動維護",
              "checked": false
            },{
              "text": "即時公告維護",
              "checked": false
            },{
              "text": "內部公告",
              "checked": false
            },{
              "text": "內部公告維護",
              "checked": false
            }
            ]
          },
          {
            "text": "客服系統",
            "checked": true,
            "children": [
            {
              "text": "問與答維護",
              "checked": false
            },{
              "text": "回報單處理",
              "checked": false
            },{
              "text": "線上客服",
              "checked": false
            }
            ]
          }
          ]
        }
        ],
        primaryKey: 'id',
        checkboxes: true
      });

    });
  </script>
</body>
</html>