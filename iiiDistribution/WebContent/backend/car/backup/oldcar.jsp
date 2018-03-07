<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>中大貨運後台系統</title>
  
    
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="css/sb-admin.css" rel="stylesheet"> 
  <link rel="stylesheet" type="text/css" href="css/vehicle.css">
  
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.html">中大貨運後台系統</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="index.html">
            <i class="fa fa-apple"></i>
            <span class="nav-link-text">功能頁</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="charts.html">
            <i class="fa fa-reddit-alien" aria-hidden="true"></i>
            <span class="nav-link-text">功能頁</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="tables.html">
            <i class="fa fa-reddit-alien"></i>
            <span class="nav-link-text">功能頁</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
            <i class="fa fa-reddit-alien"></i>
            <span class="nav-link-text">母功能頁</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="#">子功能頁</a>
            </li>
            <li>
              <a href="#">子功能頁</a>
            </li>
          </ul>
        </li>


        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseExamplePages" data-parent="#exampleAccordion">
            <i class="fa fa-reddit-alien"></i>
            <span class="nav-link-text">母功能頁</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseExamplePages">
            <li>
              <a href="#">子功能頁</a>
            </li>
            <li>
              <a href="#">子功能頁</a>
            </li>
            <li>
              <a href="#">子功能頁</a>
            </li>
            <li>
              <a href="#">子功能頁</a>
            </li>
          </ul>
        </li>
      </ul>

      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-reddit-alien"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <form class="form-inline my-2 my-lg-0 mr-lg-2">
            <div class="input-group">
              <input class="form-control" type="text" placeholder="站內搜尋">
              <span class="input-group-btn">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-reddit-alien"></i>
                </button>
              </span>
            </div>
          </form>
        </li>
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-address-book-o"></i>員工登入</a>
        </li>
      </ul>
    </div>
  </nav>





<div class="content-wrapper">
    <div class="container-fluid">
      <!-- Bread-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">麵包</a>
        </li>
        <li class="breadcrumb-item active">麵包</li>
      </ol>


     
      <!-- 車輛主頁面-->
      
        
		 <div class="card-body">
	    <div class="container" class="align-middle">
	      <div class="row">
	      <!-- 新增車輛 -->
	        <div class="col-xs-12 col-sm-6">
	          <div class="jumbotron text-center white-block">
	            <h1 class="black-font">新增車輛</h1>
	            <h1 class="black-font">&nbsp</h1>
	            <p class="lead ">
	              <div class="box">
	                <img src="img/vehicle-full.png" class="vehicle-img">
	              </div>
	            </p>
	            <hr class="my-4 black-line">
	            <p class="lead car">
	              <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#add-show" data-whatever="@mdo">點我拜託</button>
	            </p>
	          </div>
	        </div>
	        <!-- 修改資訊 -->
	        <div class="col-xs-12 col-sm-6">
	          <div class="jumbotron text-center black-block">     
	          <h1 class="white-font">查詢、修改、刪除</h1>
	          <h1 class="white-font">車輛資訊</h1>        
	            <p class="lead">
	              <div class="box">
	                <img src="img/vehicle-empty.png" class="vehicle-img">
	              </div>
	            </p>
	            <hr class="my-4 white-line">
	            <p class="lead">
	              <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modify-show" data-whatever="@mdo">不要點我</button>
	            </p>
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
	<!-- 彈出視窗--新增 -->
	<%
		Map m = (Map)request.getAttribute("errorMsgs");
		int si = 0;
		if(m!=null){
			si = m.size();
		}
		String clz = si!=0 ?  " collapse show" : "";
	%>
	
	<div class='modal fade${not empty errorMsgs? " collapse show":""}' id="add-show" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="">
		<div class="modal-dialog" role="document">
		  <div class="modal-content">
		  	<!-- 新增車輛JSP -->
	    <div class="modal-header text-center">
	      <h5 class="modal-title" id="exampleModalLabel">新增車輛</h5>
	      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        <span aria-hidden="true">&times;</span>
	      </button>
	    </div>
	    
	    <%-- 錯誤表列 --%>
	    <c:if test="${not empty errorMsgs}">
	    	<font style="color:red">Please correct the errors</font>
	    	<ul>
	    		<c:forEach var="message" items="${errorMsgs}">
	    			<li style="color:red">${message.value}</li>
	    		</c:forEach>
	    	</ul>
	    </c:if>
	    
	    	<div class="modal-body">
				<form METHOD="post" ACTION="/car.do" name="form1">
					<div class="container">
						<div class="row">
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
			            			<label for="recipient-name" class="col-form-label">車牌</label>
			            			<input type="text" name="car_plate" value="${param.car_plate}" class="form-control" id="recipient-name" />
						    		<p>${errorMsgs.car_plate}</p>
						    	</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
				            		<label for="recipient-name" class="col-form-label">貨運公司</label>
				            		<input type="text" name="db_id" value="${param.db_id}" class="form-control" id="recipient-name" />
						    		<p>${errorMsgs.db_id}</p>
				        		</div>	
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
				            		<label for="recipient-name" class="col-form-label">車種</label>
				            		<input type="text" name="car_brand" value="${param.car_brand}" class="form-control" id="recipient-name" />
						    		<p>${errorMsgs.car_brand}</p>
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
						    		<p>${errorMsgs.car_driver}</p>
				        		</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
					            	<label for="recipient-name" class="col-form-label">車輛狀態</label>
				            		<input type="text" name="car_status" value="${param.car_status}" class="form-control" id="recipient-name" />
						    		<p>${errorMsgs.car_status}</p>
				        		</div>	
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
					            	<label for="recipient-name" class="col-form-label">車輛顏色</label>
				            		<input type="text" name="car_color" value="${param.car_color}" class="form-control" id="recipient-name" />
						    		<p>${errorMsgs.car_color}</p>
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
						    		<p>${errorMsgs.car_pdv}</p>
						        </div>
							</div>
							<div class="col-xs-12 col-sm-6">
								<div class="form-group">
					            	<label for="recipient-name" class="col-form-label">目前載重</label>
				            		<input type="text" name="car_load" value="${param.car_load}" class="form-control" id="recipient-name" />
						    		<p>${errorMsgs.car_load}</p>
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
						    		<p>${errorMsgs.car_note}</p>					
								 </div>
							</div>
							<div class="col-xs-12 col-sm-6">
								<div class="form-group">
							        <label for="recipient-name" class="col-form-label">員工姓名</label>
				            		<input type="text" name="emp_id" value="${param.emp_id}" class="form-control" id="recipient-name" />
						    		<p>${errorMsgs.emp_id}</p>					
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




<!-- <div class="container" id="main" role="main">
            <ul class="menu">
                <li><a href="#">Home</a></li>
                <li><a href="#s1">Menu 1</a>
                    <ul class="submenu">
                        <li><a href="#">Submenu a</a></li>
                        <li><a href="#">Submenu b</a></li>
                        <li><a href="#">Submenu c</a></li>
                        <li><a href="#">Submenu d</a></li>
                        <li><a href="#">Submenu e</a></li>
                        <li><a href="#">Submenu f</a></li>
                        <li><a href="#">Submenu g</a></li>
                        <li><a href="#">Submenu h</a></li>
                    </ul>
                </li>
                <li class="active"><a href="#s2">Menu 2</a>
                    <ul class="submenu">
                        <li><a href="#">Submenu a</a></li>
                        <li><a href="#">Submenu b</a></li>
                        <li><a href="#">Submenu c</a></li>
                        <li><a href="#">Submenu d</a></li>
                        <li><a href="#">Submenu e</a></li>
                        <li><a href="#">Submenu f</a></li>
                        <li><a href="#">Submenu g</a></li>
                        <li><a href="#">Submenu h</a></li>
                    </ul>
                </li>
                <li><a href="#">Menu 3</a>
                    <ul class="submenu">
                        <li><a href="#">Submenu a</a></li>
                        <li><a href="#">Submenu b</a></li>
                        <li><a href="#">Submenu c</a></li>
                        <li><a href="#">Submenu d</a></li>
                        <li><a href="#">Submenu e</a></li>
                        <li><a href="#">Submenu f</a></li>
                        <li><a href="#">Submenu g</a></li>
                        <li><a href="#">Submenu h</a></li>
                    </ul>
                </li>
                <li><a href="#">Menu 4</a>
										<ul class="submenu">
                        <li><a href="#">Submenu a</a></li>
                        <li><a href="#">Submenu b</a></li>
                        <li><a href="#">Submenu c</a></li>
                        <li><a href="#">Submenu d</a></li>
                        <li><a href="#">Submenu e</a></li>
                        <li><a href="#">Submenu f</a></li>
                        <li><a href="#">Submenu g</a></li>
                        <li><a href="#">Submenu h</a></li>
                    </ul>
                </li>
            </ul>
        </div> -->

	<!-- 彈出視窗--修改 -->
	<div class="modal fade" id="modify-show" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    <form>
	      <div class="modal-header">
	        <h5 class="modal-title" id="modify">修改資訊</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
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
	</div>
						<table class="table">
						  <thead class="thead-dark">
						    <tr>
						      <th scope="col">車輛車牌</th>
						      <th scope="col">車輛狀態</th>
						      <th scope="col">所屬公司</th>
						      <th scope="col">目前載重</th>
						      <th scope="col">車輛車種</th>
						      <th scope="col">車輛顏色</th>
						      <th scope="col">總排氣量</th>
						      <th scope="col">車輛駕駛</th>
						      <th scope="col">更新人員</th>
									<th scope="col">更新日期</th>
									<th scope="col">車輛備註</th>
						    </tr>
						  </thead>
						  <tbody>
						    
						      
						</table>
						<div class="modal-footer">
						    <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
						    <input type="hidden" name="action" value="insert">
						    <input type="submit" value="送出新增" class="btn btn-primary">
					  	</div>
	        </form>
	      </div>
	      </div>		
	    </div>
	  </div>
	</div>	
	<!-- <div class="container" id="main" role="main">
            <ul class="menu">
                <li><a href="#">Home</a></li>
                <li><a href="#s1">Menu 1</a>
                    <ul class="submenu">
                        <li><a href="#">Submenu a</a></li>
                        <li><a href="#">Submenu b</a></li>
                        <li><a href="#">Submenu c</a></li>
                        <li><a href="#">Submenu d</a></li>
                        <li><a href="#">Submenu e</a></li>
                        <li><a href="#">Submenu f</a></li>
                        <li><a href="#">Submenu g</a></li>
                        <li><a href="#">Submenu h</a></li>
                    </ul>
                </li>
                <li class="active"><a href="#s2">Menu 2</a>
                    <ul class="submenu">
                        <li><a href="#">Submenu a</a></li>
                        <li><a href="#">Submenu b</a></li>
                        <li><a href="#">Submenu c</a></li>
                        <li><a href="#">Submenu d</a></li>
                        <li><a href="#">Submenu e</a></li>
                        <li><a href="#">Submenu f</a></li>
                        <li><a href="#">Submenu g</a></li>
                        <li><a href="#">Submenu h</a></li>
                    </ul>
                </li>
                <li><a href="#">Menu 3</a>
                    <ul class="submenu">
                        <li><a href="#">Submenu a</a></li>
                        <li><a href="#">Submenu b</a></li>
                        <li><a href="#">Submenu c</a></li>
                        <li><a href="#">Submenu d</a></li>
                        <li><a href="#">Submenu e</a></li>
                        <li><a href="#">Submenu f</a></li>
                        <li><a href="#">Submenu g</a></li>
                        <li><a href="#">Submenu h</a></li>
                    </ul>
                </li>
                <li><a href="#">Menu 4</a>
										<ul class="submenu">
                        <li><a href="#">Submenu a</a></li>
                        <li><a href="#">Submenu b</a></li>
                        <li><a href="#">Submenu c</a></li>
                        <li><a href="#">Submenu d</a></li>
                        <li><a href="#">Submenu e</a></li>
                        <li><a href="#">Submenu f</a></li>
                        <li><a href="#">Submenu g</a></li>
                        <li><a href="#">Submenu h</a></li>
                    </ul>
                </li>
            </ul>
        </div> -->
	


    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>2018/1/19 後台網頁版本</small>
        </div>
      </div>
    </footer>
    

    <!--員工登入-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">


      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">想要登入還是登出嗎?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div> 
          <div class="modal-body">嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="login.html">Logout</a>
          </div>
        </div>
      </div>
    </div>
  </div>

  
  
    <!-- Bootstrap core JavaScript -->
    <script src="https://use.fontawesome.com/4a61e60fc7.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
    <script src="js/sb-admin-charts.min.js"></script>
    <script type="text/javascript">
    	function setBstModalMaxHeight(element) {
	    this.$element          = $(element);
	    this.$modalContent     = this.$element.find('.modal-content');
	    var $window            = $(window);
	    var $modalContentOH    = this.$modalContent.outerHeight();
	    var $modalContentIH    = this.$modalContent.innerHeight();
	    var _modalBorderWidth   = $modalContentOH - $modalContentIH;
	    var _modalDialogMargin  = $window.width() < 768 ? 20 : 60;
	    var _modalContentHeight = $window.height() - (_modalDialogMargin + _modalBorderWidth);
	    var _modalHeaderHeight  = this.$element.find('.modal-header').outerHeight() || 0;
	    var _modalFooterHeight  = this.$element.find('.modal-footer').outerHeight() || 0;
	    var _modalMaxHeight     = _modalContentHeight - (_modalHeaderHeight + _modalFooterHeight);
	  
	    this.$modalContent.css({
	        'overflow': 'hidden'
	    });
	    
	    this.$element
	      .find('.modal-body').css({
	        'max-height': _modalMaxHeight,
	        'overflow-y': 'auto'
	    });
	  }
	  
	  $('.modal').on('show.bs.modal', function() {
	    $(this).show();
	    setBstModalMaxHeight(this);
	  });
	  
	  $(window).resize(function() {
	    if ($('.modal.in').length != 0) {
	      setBstModalMaxHeight($('.modal.in'));
	    }
	  });
    window.onload=function(){
    	console.log("here");
    	setBstModalMaxHeight($('.modal'));
    };
	</script>
  </div>
</body>

</html>
