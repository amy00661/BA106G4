<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="iii.car.model.*" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>資策貨運後台系統</title>
  
  <!-- CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  
 <%--  <link href="<%= request.getContextPath() %>/backend/css/font_awesome_min.css" rel="stylesheet">  --%>	
  <link href="<%= request.getContextPath() %>/backend/css/sup.css" rel="stylesheet"> 
<%--   <link href="<%= request.getContextPath() %>/backend/css/main.css" rel="stylesheet"> --%>
    
  <link href="<%= request.getContextPath() %>/backend/css/car.css" rel="stylesheet"> 
  

  <!-- CSS -->
  <!-- =================================================== -->
    
                      <!-- 請大家把自己的套件放在這邊 -->

  <!-- =================================================== -->
</head>

<body class="fixed-nav sticky-footer" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    
    <a class="navbar-brand" href="index.html"><strong>資策貨運後台系統</strong></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
<!-- _______________________________________________________________________ -->
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseExamplePages" data-parent="#exampleAccordion">
            <i class="fa fa-paper-plane"></i>
            <span class="nav-link-text">訂單管理</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseExamplePages">
            <li>
              <a href="Order_Show.html">訂單查詢</a>
            </li>
            <li>
              <a href="Order_CR.html">建立新訂單</a>
            </li>
          </ul>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="#">
            <i class="fa fa-list-ol"></i>
            <span class="nav-link-text">清單派車系統</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="#">
            <i class="fa fa-motorcycle"></i>
            <span class="nav-link-text">車輛排班系統</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="#">
            <i class="fa fa-plane"></i>
            <span class="nav-link-text">總部車輛排班系統</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseExamplePages1" data-parent="#exampleAccordion">
            <i class="fa fa-male"></i>
            <span class="nav-link-text">員工系統</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseExamplePages1">
            <li>
              <a href="emp_query.html">員工資料管理</a>
            </li>
            <li>
              <a href="emp_add.html">員工新增&權限管理</a>
            </li>
          </ul>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="vehicle.html">
            <i class="fa fa-car"></i>
            <span class="nav-link-text">車輛管理</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="#">
            <i class="fa fa-user"></i>
            <span class="nav-link-text">會員資料管理</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="#">
            <i class="fa fa-mobile"></i>
            <span class="nav-link-text">客服系統</span>
          </a>
        </li>

        


<!-- ____________________________________________________ -->
      </ul>
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
      <div style="background-color: #ccffcc">
              
        <!-- 車輛主頁面-->
         <div class="card-body">
          <div class="container" class="align-middle">
            <div class="row">
            <!-- 新增車輛大按鈕 -->
              <div class="col-xs-12 col-sm-6">
                <div class="jumbotron text-center white-block">
                  <h1 class="black-font">新增車輛</h1>
                  <h1 class="black-font">&nbsp</h1>
                  <div class="lead ">
                    <div class="box">
                  
                      <img src="<%= request.getContextPath() %>/backend/img/vehicle-full.png" class="vehicle-img">
                    </div>
                  </div>
                  <hr class="my-4 black-line">
                  <p class="lead car">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add-show" data-whatever="@mdo">點我拜託</button>
                  </p>
                </div>
              </div>
              <!-- 查刪改車輛大按鈕 -->
              <div class="col-xs-12 col-sm-6">
                <div class="jumbotron text-center black-block">     
                <h1 class="white-font">查詢車輛</h1> 
                <h1 class="black-font">&nbsp</h1>      
                  <div class="lead">
                    <div class="box">
                      <img src="<%= request.getContextPath() %>/backend/img/vehicle-empty.png" class="vehicle-img">
                    </div>
                  </div>
                  <hr class="my-4 white-line">
                  <div class="lead">
                    <form METHOD="post" ACTION="<%= request.getContextPath() %>/car/car.do" name="form2">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modify-show" data-whatever="@mdo">不要點我</button>  
                <input type="hidden" name="action" value="selectAll">
                </form>
                  </div>
                </div>  
              </div>
            </div>    
          </div>
        </div>
      </div>  
      <div class="container">
        <div class="row">
          <div class="col-xs-12 col-sm-10"></div>
        </div>
      </div>
      
      <%--  <% --%>
   <!--  //    Map m = (Map)request.getAttribute("addError");
    //    int si = 0;
    //    if(m!=null){
    //      si = m.size();
    //    }
    //    String clz = si!=0 ?  " collapse show" : ""; -->
    <%--  %> --%>


      
    <%--  <div class='modal fade${not empty addError? " collapse show":""}' id="add-show" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
       --%> 
      
      <!-- 彈出視窗--新增 -->
      <div class='modal fade' id="add-show" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header text-center">
              <h5 class="modal-title" id="exampleModalLabel">新增車輛</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
           
         <%-- 錯誤表列 --%> 
          <c:if test="${not empty addError}">
            <font style="color:red">Please correct the errors</font>
            <ul>
              <c:forEach var="message" items="${addError}">
                ${message.value}
              </c:forEach>
            </ul>
        </c:if>
          
           <div class="modal-body">
            <form METHOD="post" ACTION="<%= request.getContextPath() %>/car/car.do" name="form1">
              <div class="container">
                <div class="row">
                  <div class="col-xs-12 col-sm-4">
                    <div class="form-group">
	                    <label for="recipient-name" class="col-form-label">車牌</label>
	                    <input type="text" name="car_plate" value="${param.car_plate}" class="form-control" id="recipient-name" />
                        <p>${addError.car_plate}</p> 
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-4">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">貨運公司</label>
                        <input type="text" name="db_id" value="${param.db_id}" class="form-control" id="recipient-name" />
                    	<p>${addError.db_id}</p> 
                    </div>  
                  </div>
                  <div class="col-xs-12 col-sm-4">
                    <div class="form-group">
                      <label for="recipient-name" class="col-form-label">車種</label>
                      <input type="text" name="car_brand" value="${param.car_brand}" class="form-control" id="recipient-name" />
                  	  <p>${addError.car_brand}</p> 
                    </div>
                  </div>
                </div>
              </div>
              <div class="container">
                <div class="row">
                  <div class="col-xs-12 col-sm-4">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">所屬駕駛</label>
                        <input type="text" name="car_driver" value="${param.car_driver}" class="form-control" id="recipient-name" />
                    	<p>${addError.car_driver}</p>
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-4">
                    <div class="form-group">
                       <label for="recipient-name" class="col-form-label">車輛狀態</label>
                       <input type="text" name="car_status" value="${param.car_status}" class="form-control" id="recipient-name" />
                   	   <p>${addError.car_status}</p> 
                   	</div>  
                  </div>
                  <div class="col-xs-12 col-sm-4">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">車輛顏色</label>
                        <input type="text" name="car_color" value="${param.car_color}" class="form-control" id="recipient-name" />
                    	<p>${addError.car_color}</p> 
                    </div>
                  </div>
                </div>
              </div>
              <div class="container">
                <div class="row">
                  <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">排氣量</label>
                        <input type="text" name="car_pdv" value="${param.car_pdv}" class="form-control" id="recipient-name" />
                    	<p>${addError.car_pdv}</p> 
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
                       <label for="recipient-name" class="col-form-label">目前載重</label>
                       <input type="text" name="car_load" value="${param.car_load}" class="form-control" id="recipient-name" />
                   	   <p>${addError.car_load}</p> 
                    </div>  
                  </div>    
                </div>
              </div>
              <div class="container">
                <div class="row">
                  <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
                       <label for="recipient-name" class="col-form-label">備註</label>
                       <input type="text" name="car_note" value="${param.car_note}" class="form-control" id="recipient-name" />
                       <p>${addError.car_note}</p>          
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
	                    <label for="recipient-name" class="col-form-label">員工姓名</label>
	                    <input type="text" name="emp_id" value="${param.emp_id}" class="form-control" id="recipient-name" />
	                    <p>${addError.emp_id}</p>         
                    </div>
                  </div>
                 </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                   <input type="hidden" name="action" value="insert">
                   <input type="submit" value="送出新增" class="btn btn-primary">
               </div>
              </div>  
             </form>
            </div>
          </div>
        </div>
      </div>
      
      
      <!-- 彈出視窗--查詢 修改 刪除 -->
      
      <%-- <%
        Map m = (Map)request.getAttribute("addError");
        int si = 0;
        if(m!=null){
          si = m.size();
        }
        String clz = si!=0 ?  " collapse show" : "";
      %> --%>
      
      <% 
          CarService carSvc = new CarService();
          List<CarVO> list = carSvc.getAll();
          pageContext.setAttribute("list", list);
          int listSize = 0;
          if( list.size() != 0){
            listSize = list.size();
          }
          String shrink = list.size() == list.size()-1 ? "collapse show" : "" ; 
          int iii = 123;
        %>
      
      <div class="modal fade <%= shrink %>" id="modify-show" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content" id="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="modify">修改資訊</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <form>
	            <div class="container">
	              <div class="row">
	                <div class="col-xs-12 col-sm-2">
	                  <div class="row">
	                    <a href="#" class="badge badge-dark title-delete">請選擇查詢</a>
	                      </div>
	                </div>
	                <div class="col-xs-12 col-sm-2">
	                  <div class="form-group">
	                          <div class="btn-group">
	                      <button type="button" class="btn btn-default dropdown-toggle btn-delete" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        出/未出車
	                      </button>
	                      <div class="dropdown-menu">
	                        <a class="dropdown-item" href="#">已出車</a>
	                        <a class="dropdown-item" href="#">未出車</a>
	                      </div>
	                    </div>
	                      </div>
	                </div>
	                <div class="col-xs-12 col-sm-2">
	                  <div class="form-group">
	                    <div class="btn-group">
	                      <button type="button" class="btn btn-default dropdown-toggle btn-delete" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        所屬公司
	                      </button>
	                      <div class="dropdown-menu">
	                        <a class="dropdown-item" href="#">總公司</a>
	                        <a class="dropdown-item" href="#">基隆市集貨站</a>
	                        <a class="dropdown-item" href="#">台北市集貨站</a>
	                        <a class="dropdown-item" href="#">新北市集貨站</a>
	                        <a class="dropdown-item" href="#">桃園縣集貨站</a>
	                        <a class="dropdown-item" href="#">新竹市集貨站</a>
	                        <a class="dropdown-item" href="#">新竹縣集貨站</a>
	                        <a class="dropdown-item" href="#">苗栗縣集貨站</a>
	                        <a class="dropdown-item" href="#">台中市集貨站</a>
	                        <a class="dropdown-item" href="#">彰化縣集貨站</a>
	                        <a class="dropdown-item" href="#">南投縣集貨站</a>
	                        <a class="dropdown-item" href="#">雲林縣集貨站</a>
	                        <a class="dropdown-item" href="#">嘉義市集貨站</a>
	                        <a class="dropdown-item" href="#">嘉義縣集貨站</a>
	                        <a class="dropdown-item" href="#">台南市集貨站</a>
	                        <a class="dropdown-item" href="#">高雄市集貨站</a>
	                        <a class="dropdown-item" href="#">屏東縣集貨站</a>
	                        <a class="dropdown-item" href="#">台東縣集貨站</a>
	                        <a class="dropdown-item" href="#">花蓮縣集貨站</a>
	                        <a class="dropdown-item" href="#">宜蘭縣集貨站</a>
	                        <a class="dropdown-item" href="#">澎湖縣集貨站</a>
	                        <a class="dropdown-item" href="#">金門縣集貨站</a>
	                        <a class="dropdown-item" href="#">連江縣集貨站</a>        
	                      </div>
	                    </div>
	                   </div>
	                </div>
	                <div class="col-xs-12 col-sm-2">
	                  <div class="form-group">
	                    <div class="btn-group">
	                      <button type="button" class="btn btn-default dropdown-toggle btn-delete" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        目前載重
	                      </button>
	                      <div class="dropdown-menu">
	                        <a class="dropdown-item" href="#">四分之一滿</a>
	                        <a class="dropdown-item" href="#">二分之一滿</a>
	                        <a class="dropdown-item" href="#">四分之三滿</a>
	                        <a class="dropdown-item" href="#">已滿</a>
	                      </div>
	                    </div>
	                  </div>  
	                </div>
	                <div class="col-xs-12 col-sm-2">
	                  <div class="form-group">
	                    <div class="btn-group">
	                      <button type="button" class="btn btn-default dropdown-toggle btn-delete" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        車種
	                      </button>
	                      <div class="dropdown-menu">
	                        <a class="dropdown-item" href="#">常溫</a>
	                        <a class="dropdown-item" href="#">醫藥</a>
	                        <a class="dropdown-item" href="#">冷凍</a>
	                      </div>
	                    </div>
	                  </div>
	                </div>
	                <div class="col-xs-12 col-sm-2">
	               		<div class="input-group">
		                    <input type="text" class="form-control search-delete" placeholder="試著搜看看">
		                    <span class="input-group-btn"><a href="#" class="btn btn-info"><i class="fa fa-search"></i></a></span>  
	                    </div>
	                </div>
	              </div>
	            </div>
	            <table class="table">
	              <thead class="thead-dark">
	                <tr>
						<th scope="col">車輛<br>車牌</th>
						<th scope="col">狀態</th>
						<th scope="col">所屬<br>公司</th>
						<th scope="col">目前<br>載重</th>
						<th scope="col">車輛<br>車種</th>
						<th scope="col">顏色</th>
						<th scope="col">總排<br>氣量</th>
						<th scope="col">車輛<br>駕駛</th>
						<th scope="col">更新<br>人員</th>
						<th scope="col">更新<br>日期</th>
						<th scope="col">車輛<br>備註</th>
						<th scope="col">更新</th>
						<th scope="col">刪除</th>
	                </tr>
	              </thead>
	              
	              <tbody>
	                <c:forEach var="carVO" items="${list}" >
	                  <tr>
	                    <td class="${carVO.car_plate}">${carVO.car_plate}</td> 
	                    <td class="${carVO.car_status}">${carVO.car_status}</td>
	                    <td class="${carVO.db_id}">${carVO.db_id}</td>
	                    <td class="${carVO.car_load}">${carVO.car_load}</td>
	                    <td class="${carVO.car_brand}">${carVO.car_brand}</td>
	                    <td class="${carVO.car_color}">${carVO.car_color}</td>
	                    <td class="${carVO.car_pdv}">${carVO.car_pdv}</td>
	                    <td class="${carVO.car_driver}">${carVO.car_driver}</td>
	                    <td class="${carVO.emp_id}">${carVO.emp_id}</td>
	                    <td class="${carVO.car_updatetime}"><fmt:formatDate value="${carVO.car_updatetime}" pattern="yyyy-MM-dd"/></td>
	                    <td class="${carVO.car_note}" id="note">${carVO.car_note}</td>
	                    
	                    <!-- 更新按鈕點擊 -->
	                    <td>
		                    <form METHOD="post" ACTION="<%= request.getContextPath() %>/car/car.do">
			                    <button type="button" class='btn btn-warning update' value="${carVO.car_id}">更新</button>
			                    <input type="hidden" name="action" value="update"/>
			                    <input type="hidden" name="car_id" value="${carVO.car_id}">
			                    <input type="hidden" name="car_plate" value="${carVO.car_plate}"/> 
								<input type="hidden" name="car_status" value="${carVO.car_status}"/>
								<input type="hidden" name="db_id" value="${carVO.db_id}"/>
								<input type="hidden" name="car_load" value="${carVO.car_load}"/>
								<input type="hidden" name="car_brand" value="${carVO.car_brand}"/>
								<input type="hidden" name="car_color" value="${carVO.car_color}"/>
								<input type="hidden" name="car_pdv" value="${carVO.car_pdv}"/>
								<input type="hidden" name="car_driver" value="${carVO.car_driver}"/>
								<input type="hidden" name="emp_id" value="${carVO.emp_id}"/>
								<fmt:formatDate value="${carVO.car_updatetime}" var="car_updatetime" pattern="yyyy-MM-dd"/>
								<input type="hidden" name="car_updatetime" value="${car_updatetime}"/>
								<input type="hidden" name="car_note" id="note" value="${carVO.car_note}"/>
								<!-- <input type="submit" value="e04su3xl3g"/> -->
		                    </form>
	                    </td>     
	                     
	                    <!-- 刪除按鈕點擊 -->
	                    <td>
		                    <form METHOD="post" ACTION="<%= request.getContextPath() %>/car/car.do" name="form4">
								<button type="submit" class="btn btn-danger">刪除</button>
								<input type="hidden" name="car_id" value="${carVO.car_id}">
								<input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
								<input type="hidden" name="action" value="delete">
		                    </form>
	                    </td>
	                  </tr>
	                </c:forEach>        
	              </tbody>
	            </table>
              </form>
            </div>
          	<div class="modal-footer">
	            <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
	            <input type="hidden" name="action" value="listall">
	            <input type="submit" value="送出新增" class="btn btn-primary">
            </div>
          </div>
        </div>
      </div>          
    </div>
<!-- 主要功能 -->
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
        <a class="btn btn-primary" href="login.html">Logout</a>
      </div>
    </div>
  </div>
</div>

<!--JS-->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
<%-- <script src="<%= request.getContextPath() %>/backend/js/main.js"></script> --%>
<script src="<%= request.getContextPath() %>/backend/js/car.js"></script>

<!-- 更新車輛資料 -->


<%-- <%-- 	<script src="<%= request.getContextPath() %>/backend/js/carUpdate.js">
	</script> --%>
	<script type="text/javascript">
	$(".update").click(function(){
		$(this).unbind("click");
		var td = $(this).parents("td").prevAll();
	  /* $(this).parents("td").prevAll().eq(5).text(8777); */
	  console.log($(this));
// 	  console.log(td);
// 	  console.log("td.eq(0).attr('class')" + td.eq(0).attr('class'));
// 	  console.log("td.eq(1).attr('class')" + td.eq(1).attr('class'));
// 	  console.log("td.eq(2).attr('class')" + td.eq(2).attr('class'));
// 	  console.log("td.eq(3).attr('class')" + td.eq(3).attr('class'));
// 	  console.log("td.eq(4).attr('class')" + td.eq(4).attr('class'));
// 	  console.log("td.eq(5).attr('class')" + td.eq(5).attr('class'));
// 	  console.log("td.eq(6).attr('class')" + td.eq(6).attr('class'));
// 	  console.log("td.eq(7).attr('class')" + td.eq(7).attr('class'));
// 	  console.log("td.eq(8).attr('class')" + td.eq(8).attr('class'));
// 	  console.log("td.eq(9).attr('class')" + td.eq(9).attr('class'));
// 	  console.log("td.eq(10).attr('class')" + td.eq(10).attr('class'));

	  td.eq(10).wrapInner('<input type="text" name="car_plate" value="'+ td.eq(10).attr('class') + '" style="width:80px">'); 
	   td.eq(9).wrapInner('<input type="text" name="car_status" value="'+ td.eq(9).attr('class') + '" style="width:70px">');
	   td.eq(8).wrapInner('<input type="text" name="db_id" value="'+ td.eq(8).attr('class') + '" style="width:60px">');
	   td.eq(7).wrapInner('<input type="text" name="car_load" value="'+ td.eq(7).attr('class') + '" style="width:70px">');
	   td.eq(6).wrapInner('<input type="text" name="car_brand" value="'+ td.eq(6).attr('class') + '" style="width:70px">');
	   td.eq(5).wrapInner('<input type="text" name="car_color" value="'+ td.eq(5).attr('class') + '" style="width:70px">');
	   td.eq(4).wrapInner('<input type="text" name="car_pdv" value="'+ td.eq(4).attr('class') + '" style="width:70px">');
	   td.eq(3).wrapInner('<input type="text" name="car_driver" value="'+ td.eq(3).attr('class') + '" style="width:70px">');
	   td.eq(2).wrapInner('<input type="text" name="emp_id" value="'+ td.eq(2).attr('class') + '" style="width:70px">');
	   td.eq(1).wrapInner('<input type="text" name="car_updatetime" value="'+ td.eq(1).attr('class') + '" style="width:70px">');
	   td.eq(0).wrapInner('<input type="text" name="car_note" value="'+ td.eq(0).attr('class') + '" style="width:70px">');
	   $(this).prop('class', 'btn btn-primary updateAction').html('確認');
	   $(".updateAction").click(function(){
		     
		   
		   var inputs =	$(this).parents("td").find("input");
 		   var list = $(this).parents("tr").find("td");
			$.each(list,function(i,items){
				$.each(inputs,function(j,input){
					var td = $(items);
					var ins = $(input);
					console.log($(items).children().attr("name"));
					console.log(ins.attr("name"));
					if(td.children().attr("name")===ins.attr("name"))
					ins.val($(items).children().val());
				});
			});
			
<%-- 			$.post("<%= request.getContextPath() %>/car/car.do"); --%>
// 			$(this).parents("form").post("car.jsp");
  			$(this).prop('type', 'submit'); 
			
			
// 		   td.eq(10).wrapInner('<input type="text" name="car_plate" value="${carVO.car_plate}" style="width:80px">');
// 		   td.eq(9).wrapInner('<input type="text" name="car_status" value="${param.car_status}" style="width:80px">');
// 		   td.eq(8).wrapInner('<input type="text" name="db_id" value="${param.db_id}" style="width:60px">');
// 		   td.eq(7).wrapInner('<input type="text" name="car_load" value="${param.car_load}" style="width:70px">');
// 		   td.eq(6).wrapInner('<input type="text" name="car_brand" value="${param.car_brand}" style="width:70px">');
// 		   td.eq(5).wrapInner('<input type="text" name="car_color" value="${param.car_color}" style="width:70px">');
// 		   td.eq(4).wrapInner('<input type="text" name="car_pdv" value="${param.car_pdv}" style="width:70px">');
// 		   td.eq(3).wrapInner('<input type="text" name="car_driver" value="${param.car_driver}" style="width:70px">');
// 		   td.eq(2).wrapInner('<input type="text" name="emp_id" value="${param.emp_id}" style="width:70px">');
// 		   td.eq(1).wrapInner('<input type="text" name="car_updatetime" value="${param.car_updatetime}" style="width:70px">');
// 		   td.eq(0).wrapInner('<input type="text" ame="car_note" value="${param.car_note}" style="width:70px">');
	   });
	});
	</script> 



<script type="text/javascript">
window.onload=function(){
    ${(not empty addError)? "$('#add-show').modal('show')":""}
    console.log("here");
    setBstModalMaxHeight($('.modal'));   
};  
</script>


</body>
</html>
