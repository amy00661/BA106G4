<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>  
<%@ page import="iii.news.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>最新消息修改</title>
  
  <!-- 主要套件 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="<%=request.getContextPath()%>/backend/css/main.css" rel="stylesheet"> 
  <link href="<%=request.getContextPath()%>/backend/css/mem.css" rel="stylesheet"> 
	
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  	
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
  <script src="<%=request.getContextPath()%>/backend/js/main.js"></script>


	
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
     
<!-- 主要功能 -->
      <div >
    <div class="row">
	  <div class="col-md-6 col-md-offset-3">
		<div class="card border-info">
                <div class="card-header card-bg">
                    <div class="row">
                        <div class="col-9"><b>修改最新消息</b></div>
                        <div class="col-3" style="text-align:right;"></div>
                    </div>   
                </div>
                <br>
                <form method="post" action="<%=request.getContextPath()%>/news/news.do" name="form1" enctype="multipart/form-data">
                <div class="container">
					    <div class="form-group row">
	                       <label for="news_date" class="col-sm-2 col-form-label"><h6><b>發布日期</b></h6></label>
	                       <div class="col-sm-10">
	                           	<input type="text" class="form-control" id="news_date" name="news_date" disabled="disabled" value=${newsVO.news_date}>
	                       </div>
					    </div>
					    <div class="form-group row">
	                       <label for="news_title" class="col-sm-2 col-form-label"><h6><b>標題</b></h6></label>
	                       <div class="col-sm-10">
	                           	<input type="text" class="form-control" id="news_title" name="news_title" value=${(newsVO==null) ?"":newsVO.news_title}>
	                       </div>
					   </div>
					  
					  	<div class="form-group row">
	                       <label for="news_context" class="col-sm-2 col-form-label"><h6><b>內文</b></h6></label>
	                       <div class="col-sm-10">
	                           	<textarea  class="form-control" id="news_context" rows="4" cols="46" name="news_context" >${(newsVO==null) ?"":newsVO.news_context}</textarea>
	                       </div>
					   </div>
					  
					  	<div class="form-group row">
	                       <label for="news_note" class="col-sm-2 col-form-label"><h6><b>備註</b></h6></label>
	                       <div class="col-sm-10">
	                           	<input type="text" class="form-control" id="news_note" name="news_note" value=${(newsVO==null) ?"":newsVO.news_note}>
	                       </div>
					   </div>
					  <div class="form-group row">
	                       <label for="upFile" class="col-sm-2 col-form-label"><h6><b>圖片</b></h6></label>
	                       <div class="col-sm-10">
	                       		  <input type="file" class="custom-file-input" id="upPic" name="news_picture">
								  <label class="custom-file-label" for="validatedCustomFile">Choose file...</label>
	                       </div>
					   </div>
						
						<div class="form-group row">
						 	<img id="show" src="<%=request.getContextPath()%>/newsPic/newsPic.do?news_id=${newsVO.news_id}" width="350" height="350"></img>
						</div>
					  <br>
					  <div class="card">
		                    <div class="card-header card-bg">
		                        <div class="row">
		                            <div class="mx-auto">
		                            	<input type="hidden" name="action" value="update">
		                            	<input type="hidden" name="news_id" value="${newsVO.news_id}">
		                            	<input type="hidden" name="news_date" value="${newsVO.news_date}">
		                                <input type ="submit" id="calBtn" class="btn btn-success"  value="送出修改"></input>  
		                             </div>                     
		                        </div> 
		                   </div>
		                    <div class="container">
		                           <div class="text-center">
		                           			<c:if test="${not empty errorMsgs}">
													<c:forEach var="message" items="${errorMsgs}">
														<li style="color:red">${message}</li>
													</c:forEach>
											</c:if>
		                            </div>
		                    </div>
		              </div>
		              <br>
                </div>
                
                </form>
              </div>
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
			<button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
            <form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
            	<input type="hidden" name="action" value="logout">
            	<button type="submit" class="btn btn-primary" >登出</button>
            </form>
          </div>
        </div>
      </div>
    </div>
</body>
<script language="javascript">
	
	$("#upPic").change(function(){
		readURL(this);
	})
	
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$("#show").attr("src",e.target.result);

			}
			reader.readAsDataURL(input.files[0]);			
		}
	}
</script>

</html>