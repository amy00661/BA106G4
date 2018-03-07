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
  
  <link href="<%= request.getContextPath() %>/backend/css/font_awesome_min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/backend/css/sup.css" rel="stylesheet"> 
  <link href="<%= request.getContextPath() %>/backend/css/main.css" rel="stylesheet">  
	
<style type="text/css">

.tbody {
    display:block;
    height:400px;
    overflow:auto;
}
.thead, .tbody tr {
    display:table;
    width:100%;
    table-layout:fixed;
}
.thead {
    width: calc( 100% - 1em )
}

</style>
<style type="text/css">
  * {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

.outer {
  position: absolute;
  width:300px;
  height:150px;
  cursor: pointer;
  top: 72%;
  left: 49%;
}

.inner {
  width: inherit;
  text-align: center;
}

.label { 
  font-size:30px; 
  line-height: 1.3em;
  font-weight: 600;
  letter-spacing: 4px;
  text-transform: uppercase;
  color: #007aff;
  transition: all .3s ease-in;
  opacity: 0;
  cursor: pointer;
}

.inner:before, .inner:after {
  position: absolute;
  content: '';
  height: 4px;
  width: inherit;
  background: #0b61bf;
  left: 0;
  transition: all .3s ease-in;
}

.inner:before {
  top: 45%; 
  transform: rotate(45deg);  
}

.inner:after {  
  bottom: 50%;
  transform: rotate(-45deg);  
}

.outer:hover label {
  opacity: 1;
}

.outer:hover .inner:before,
.outer:hover .inner:after {
  transform: rotate(0);
}

.outer:hover .inner:before {
  top: 0;
}

.outer:hover .inner:after {
  bottom: 0;
}
</style>



<!-- CSS -->
  <!-- =================================================== -->
    
    <link href="<%= request.getContextPath() %>/backend/css/car.css" rel="stylesheet"> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/css/jquery.datetimepicker.css" />
  <!-- =================================================== -->
</head>

<% request.setAttribute("car_plate", "" ); %>


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
                  	<form METHOD="post" ACTION="<%= request.getContextPath() %>/backend/car/car.jsp" name="form2">
                    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addShow" data-whatever="@mdo">點我拜託</button>
                  	</form>
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
                    <form METHOD="post" ACTION="<%= request.getContextPath() %>/backend/car/car.jsp" name="form2">
                    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateShow" data-whatever="@mdo">不要點我</button>  
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
      <!-- 彈出視窗--新增 -->
      <jsp:include page="addCar.jsp"/>
      <!-- 彈出視窗--查詢 修改 刪除 -->
      <jsp:include page="updateCar.jsp"/>
   	  <div class="outer">
		  <div class="inner">
		    <label class="label"><br>中大貨運<br>車輛定位系統</label>
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
	  	<button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
    	<form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
    		<input type="hidden" name="action" value="logout">
    		<button type="submit" class="btn btn-primary" >登出</button>
    	</form>
 	  </div>
    </div>
  </div>
</div>

<!--JS-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/js/jquery.datetimepicker.full.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
<%-- <script src="<%= request.getContextPath() %>/backend/js/main.js"></script> --%>
<script src="<%= request.getContextPath() %>/backend/js/car.js"></script>

<!--   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> -->



<!-- 更新車輛資料 -->


<%-- <script src="<%= request.getContextPath() %>/backend/js/carUpdate.js"></script>	 --%>

	<script type="text/javascript">
	$(".update").click(function(){
	   $(this).unbind("click");
	   var td = $(this).parents("td").prevAll();
	   td.eq(10).wrapInner('<input type="text" name="car_plate" value="'+ td.eq(10).attr('class') + '" style="width:80px">'); 
	   td.eq(9).wrapInner('<input type="text" name="car_status" value="'+ td.eq(9).attr('class') + '" style="width:70px">');
	   td.eq(8).wrapInner('<input type="text" name="db_id" value="'+ td.eq(8).attr('class') + '" style="width:60px">');
	   td.eq(7).wrapInner('<input type="text" name="car_load" value="'+ td.eq(7).attr('class') + '" style="width:70px">');
	   td.eq(6).wrapInner('<input type="text" name="car_brand" value="'+ td.eq(6).attr('class') + '" style="width:70px">');
	   td.eq(5).wrapInner('<input type="text" name="car_color" value="'+ td.eq(5).attr('class') + '" style="width:70px">');
	   td.eq(4).wrapInner('<input type="text" name="car_pdv" value="'+ td.eq(4).attr('class') + '" style="width:70px">');
	   td.eq(3).wrapInner('<input type="text" name="car_driver" value="'+ td.eq(3).attr('class') + '" style="width:70px">');
	   td.eq(2).wrapInner('<input type="text" name="emp_id" value="'+ td.eq(2).attr('class') + '" style="width:70px">');
	   td.eq(1).wrapInner('<input type="text" name="car_updatetime" value="'+ td.eq(1).attr('class') + '" class="car_updatetime" style="width:120px">');
	   
	   $.datetimepicker.setLocale('zh');
       $('.car_updatetime').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   <%-- value: '<%=carVO.car_updatetime%>', --%> // value:   new Date(),
          //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
          //startDate:	            '2017/07/10',  // 起始日
          //minDate:               '-1970-01-01', // 去除今日(不含)之前
          //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
       });
	   
	   
	   td.eq(0).wrapInner('<input type="text" name="car_note" value="'+ td.eq(0).attr('class') + '" style="width:70px">');
	   $(this).prop('class', 'btn btn-primary updateAction').html('確認');
	   $(".updateAction").click(function(){
		   var inputs =	$(this).parents("td").find("input");
 		   var list = $(this).parents("tr").find("td");
			$.each(list,function(i,items){
				$.each(inputs,function(j,input){
					var td = $(items);
					var ins = $(input);
					if(td.children().attr("name")===ins.attr("name"))
					ins.val($(items).children().val());
				});
			});			
  			$(this).prop('type', 'submit');	
	   });
	});
	
	/* <c:if test = "${updateError != null}">
		$('.'+$('.error').attr('id')).click();	
    </c:if>
    
    <c:if test = "${updateError == null}">
    	$(".updateAction").click();	
	</c:if> */
	
	${(not empty updateError) ? "$('.'+$('.error').attr('id')).click();" : ""};
	
	</script> 



<script type="text/javascript">

$(".close, .close1").click(function(){
	window.location = "<%= request.getContextPath() %>/backend/car/car.jsp";
});

$('#addShow, #updateShow').on('hidden.bs.modal', function(e){
	window.location = "<%= request.getContextPath() %>/backend/car/car.jsp";
});

window.onload=function(){
    ${(not empty addError) ? "$('#addShow').modal('show')":""};
    ${(not empty updateError) ? "$('#updateShow').modal('show')":""};
    setBstModalMaxHeight($('.modal'));   
};  

$(".outer").click(function(){
	window.open("<%=request.getContextPath()%>/backend/car/googleMapDemo.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1600,height=950");
});
</script>


<script>

<%--$.post("<%= request.getContextPath() %>/car/car.do"); --%>
//	$(this).parents("form").post("car.jsp");
	
	/* $(this).parents("td").prevAll().eq(5).text(8777); */
// console.log(td);
// console.log("td.eq(0).attr('class')" + td.eq(0).attr('class'));
// console.log("td.eq(1).attr('class')" + td.eq(1).attr('class'));
// console.log("td.eq(2).attr('class')" + td.eq(2).attr('class'));
// console.log("td.eq(3).attr('class')" + td.eq(3).attr('class'));
// console.log("td.eq(4).attr('class')" + td.eq(4).attr('class'));
// console.log("td.eq(5).attr('class')" + td.eq(5).attr('class'));
// console.log("td.eq(6).attr('class')" + td.eq(6).attr('class'));
// console.log("td.eq(7).attr('class')" + td.eq(7).attr('class'));
// console.log("td.eq(8).attr('class')" + td.eq(8).attr('class'));
// console.log("td.eq(9).attr('class')" + td.eq(9).attr('class'));
// console.log("td.eq(10).attr('class')" + td.eq(10).attr('class'));
	

//td.eq(10).wrapInner('<input type="text" name="car_plate" value="${carVO.car_plate}" style="width:80px">');
//td.eq(9).wrapInner('<input type="text" name="car_status" value="${param.car_status}" style="width:80px">');
//td.eq(8).wrapInner('<input type="text" name="db_id" value="${param.db_id}" style="width:60px">');
//td.eq(7).wrapInner('<input type="text" name="car_load" value="${param.car_load}" style="width:70px">');
//td.eq(6).wrapInner('<input type="text" name="car_brand" value="${param.car_brand}" style="width:70px">');
//td.eq(5).wrapInner('<input type="text" name="car_color" value="${param.car_color}" style="width:70px">');
//td.eq(4).wrapInner('<input type="text" name="car_pdv" value="${param.car_pdv}" style="width:70px">');
//td.eq(3).wrapInner('<input type="text" name="car_driver" value="${param.car_driver}" style="width:70px">');
//td.eq(2).wrapInner('<input type="text" name="emp_id" value="${param.emp_id}" style="width:70px">');
//td.eq(1).wrapInner('<input type="text" name="car_updatetime" value="${param.car_updatetime}" style="width:70px">');
//td.eq(0).wrapInner('<input type="text" ame="car_note" value="${param.car_note}" style="width:70px">');

</script>



</body>
</html>
