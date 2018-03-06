<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>  
<%@ page import="iii.tra.model.*"%>
<%@ page import="iii.size.model.*"%>
<%@ page import="iii.weight.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	SizeService sizeSvc = new SizeService();
	List<SizeVO> list = sizeSvc.getAll();
	pageContext.setAttribute("list", list);
	
	TraService traSvc = new TraService();
	List<TraVO> listTra = traSvc.getAll();
	pageContext.setAttribute("listTra", listTra);
	
	WeightService weightSvc = new WeightService();
	List<WeightVO> listWeight = weightSvc.getAll();
	pageContext.setAttribute("listWeight",listWeight);
	
	SizeVO sizeVO = (SizeVO) request.getAttribute("sizeVO");
	TraVO traVO = (TraVO) request.getAttribute("traVO");
	WeightVO WeightVO = (WeightVO) request.getAttribute("weightVO");
	String flagSize = (String) request.getAttribute("flagSize");
	String flagSizeAdd = (String) request.getAttribute("flagSizeAdd");
	String flagTra = (String) request.getAttribute("flagTra");
	String flagTraAdd = (String) request.getAttribute("flagTraAdd");
	String flagWeight = (String) request.getAttribute("flagWeight");
	String flagWeightAdd = (String) request.getAttribute("flagWeightAdd");
%>



<html>

<head>

<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>資策貨運後台系統</title>
  
  <!-- 主要套件 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="<%=request.getContextPath()%>/backend/css/main.css" rel="stylesheet"> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  
  
  <style>
  	tr, th, td {
    padding: 2px;
    text-align: center;
  }
  	
  </style>

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
    
<%--     <c:if test="${not empty errorMsgsSize}"> --%>
<%-- 		<c:forEach var="message" items="${errorMsgsSize}"> --%>
<%-- 			<li style="colo:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<%-- 	</c:if> --%>
	
     
<!-- 主要功能 -->
     <div>
	  <table id="tb" class="table table-bordered table-sm" >
       <thead class="thead-dark">
		<tr>
			<th width="190px">尺寸編號</th>
			<th>尺寸選項</th>
			<th>計價費率</th>
			<th>修改員工編號</th>
			<th>修改時間</th>
			<th colspan="2">
				<form method="post" action="<%=request.getContextPath()%>/size/size.do" style="margin-bottom: 0px;">
					<button class="btn btn-success btn-block" type="submit" id="SizebtnAdd">新增</button>
					<input type="hidden" name="action" value="getOne_For_Insert">
				</form>
			</th>
		</tr>
	  </thead>
	<%@ include file="page1.file" %>	
	<c:forEach var="sizeVO" items="${list}" varStatus="num" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${sizeVO.size_id}</td>
			<td>${sizeVO.size_type }</td>
			<td>${sizeVO.size_price }</td>
			<td>${sizeVO.emp_id}</td>
			<td>${sizeVO.size_updatetime }</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/size/size.do" style="margin-bottom: 0px;">
					<button class="btn btn-info btn-block" type="submit" id="SizebtnUpdate">修改</button>
					<input type="hidden" name="size_id" value="${sizeVO.size_id}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
			<td>
<!-- 			onclick="return false" -->
				<button class="btn btn-danger btn-block" type="submit" id="SizebtnDelete" data-toggle="modal" data-parent="#accordion" href="#modal-id${num.count}">刪除</button>
			</td>
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/size/size.do">
			    <div class="modal fade" id="modal-id${num.count}">
			    	<div class="modal-dialog">
			        	<div class="modal-content">
			            	<div class="modal-header bg-info">
			                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                </div>
			            		<div class="modal-body" style="font-size: 20px;color: black">確定要刪除嗎?</div>
			            	<div class="modal-footer" id="modal-cus">
			            		<input type="hidden" name="size_id" value="${sizeVO.size_id}">
								<input type="hidden" name="action" value="delete">
			                	<button type="submit" class="btn btn-primary cus-btn" style="font-size: 15px;">確定</button>
			                	<button type="button" class="btn  cus-btn2" data-dismiss="modal">取消</button>
			            	</div>
			            </div>
			        </div>
			    </div>
		    </FORM>
        
		</tr>

	  </c:forEach>
     </table>
<%@ include file="page2.file" %>

<%if(!"ok".equals(flagSize) && sizeVO!=null){%>
       <jsp:include page="update_fee_input.jsp" /> 
<%}%>

<%if(!"ok".equals(flagSizeAdd) && sizeVO!=null){%>
       <jsp:include page="add_fee_input.jsp" /> 
<%}%>
     


<br>
<table id="tb3" class="table table-bordered table-sm">
       <thead class="thead-dark">
		<tr>
			<th>重量編號</th>
			<th width="345px">重量選項</th>
			<th>計價費率</th>
			<th>修改員工編號</th>
			<th>修改時間</th>
			<th colspan="2">
				<form method="post" action="<%=request.getContextPath()%>/weight/weight.do" style="margin-bottom: 0px;">
					<button class="btn btn-success btn-block" type="submit" id="WieghtbtnAdd">新增</button>
					<input type="hidden" name="action" value="getOne_For_Insert">
				</form>
			</th>
		</tr>
	  </thead>
	  <%@ include file="page5.file" %>
	<c:forEach var="weightVO" items="${listWeight}" varStatus="num" begin="<%=pageIndex2%>" end="<%=pageIndex2+rowsPerPage2-1%>">
		<tr>
			<td>${weightVO.weight_id}</td>
			<td>${weightVO.weight_type }</td>
			<td>${weightVO.weight_price }</td>
			<td>${weightVO.emp_id}</td>
			<td>${weightVO.weight_updateTime}</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/weight/weight.do" style="margin-bottom: 0px;">
					<button class="btn btn-info btn-block" type="submit" id="WeightbtnUpdate">修改</button>
					<input type="hidden" name="weight_id" value="${weightVO.weight_id}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
			<td>
				<button class="btn btn-danger btn-block" type="button" id="SizebtnDelete" data-toggle="modal" data-parent="#accordion" href="#modal-id2${num.count}">刪除</button>
			</td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/weight/weight.do">
			    <div class="modal fade" id="modal-id2${num.count}">
			    	<div class="modal-dialog">
			        	<div class="modal-content">
			            	<div class="modal-header bg-info">
			                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                </div>
			            		<div class="modal-body" style="font-size: 20px;color: black">確定要刪除嗎?</div>
			            	<div class="modal-footer" id="modal-cus">
			            	<input type="hidden" name="weight_id" value="${weightVO.weight_id}">
								<input type="hidden" name="action" value="delete">
			                	<button type="submit" class="btn btn-primary cus-btn" style="font-size: 15px;">確定</button>
			                	<button type="button" class="btn  cus-btn2" data-dismiss="modal">取消</button>
			            	</div>
			            </div>
			        </div>
			    </div>
		    </FORM>
			
			
		</tr>

	  </c:forEach>
     </table>
<%@ include file="page6.file" %>
<%if(!"ok".equals(flagWeight) && WeightVO!=null){%>
       <jsp:include page="update_weight_input.jsp" /> 
<%} %>
<%if(!"ok".equals(flagWeightAdd) && WeightVO!=null){%>
       <jsp:include page="add_weight_input.jsp" /> 
<%}%>
<br>

<table id="tb2" class="table table-bordered table-sm">
       <thead class="thead-dark">
		<tr>
			<th width="190px">配送方式編號</th>
			<th width="345px">配送方式選項</th>
			<th>計價費率</th>
			<th>修改員工編號</th>
			<th>修改時間</th>
			<th colspan="2">
				<form method="post" action="<%=request.getContextPath()%>/tra/tra.do" style="margin-bottom: 0px;">
					<button class="btn btn-success btn-block" type="submit" id="TrabtnAdd">新增</button>
					<input type="hidden" name="action" value="getOne_For_Insert">
				</form>
			</th>
		</tr>
	  </thead>
	  <%@ include file="page3.file" %>
	<c:forEach var="traVO" items="${listTra}" varStatus="num" begin="<%=pageIndex1%>" end="<%=pageIndex1+rowsPerPage1-1%>">
		<tr>
			<td>${traVO.transition_id}</td>
			<td>${traVO.transition_type }</td>
			<td>${traVO.transition_price }</td>
			<td>${traVO.emp_id}</td>
			<td>${traVO.transition_updatetime}</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/tra/tra.do" style="margin-bottom: 0px;">
					<button class="btn btn-info btn-block" type="submit" id="TrabtnUpdate">修改</button>
					<input type="hidden" name="transition_id" value="${traVO.transition_id}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
			<td>
				<button class="btn btn-danger btn-block" type="button" id="SizebtnDelete" data-toggle="modal" data-parent="#accordion" href="#modal-id3${num.count}">刪除</button>
			</td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tra/tra.do">
			    <div class="modal fade" id="modal-id3${num.count}">
			    	<div class="modal-dialog">
			        	<div class="modal-content">
			            	<div class="modal-header bg-info">
			                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                </div>
			            		<div class="modal-body" style="font-size: 20px;color: black">確定要刪除嗎?</div>
			            	<div class="modal-footer" id="modal-cus">
			            	<input type="hidden" name="transition_id" value="${traVO.transition_id}">
								<input type="hidden" name="action" value="delete">
			                	<button type="submit" class="btn btn-primary cus-btn" style="font-size: 15px;">確定</button>
			                	<button type="button" class="btn  cus-btn2" data-dismiss="modal">取消</button>
			            	</div>
			            </div>
			        </div>
			    </div>
		    </FORM>
		</tr>

	  </c:forEach>
     </table>
<%@ include file="page4.file" %>
<%if(!"ok".equals(flagTra) && traVO!=null){%>
       <jsp:include page="update_tra_input.jsp" /> 
<%} %>
<%if(!"ok".equals(flagTraAdd) && traVO!=null){%>
       <jsp:include page="add_tra_input.jsp" /> 
<%}%>

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
  <script language="javascript">
	$(document).ready(function(){
		$("SizebtnDelete").click(function(){
			
		})
	})
// 		document.form1.submit();
  </script>	
 
</body>
</html>