<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
session.removeAttribute("memVO");	
session.invalidate(); 
%>

<html>
<head>
	<meta charset="utf-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <link href="<%=request.getContextPath()%>/frontend/css/main.css" rel="stylesheet">
    <!-- ========================================================基本套件 -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- =================================================== -->

<title>登出</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script> 
$(document).ready(function(){
	   
	var left = $('#coolDiv').offset().left;


	$("#coolDiv").css({left:left}).animate({"left":"10px"}, "5000");
    
});
</script>

<style type="text/css">
	.card{
		background-color:rgba(255, 255, 255, 0.4);
	}

 	.card-login { 
  		max-width: 30rem; 
  		} 
  		
  	#bgvid {
     position: fixed; 
     right: 0; 
     bottom: 0; 
     min-width: 100%;
     min-height: 100%;
     width: auto; 
     height: auto; 
     z-index: -100;  
     background: url(/BA106G4/frontend/img/bg.jpg) no-repeat;
     background-size: cover;
    }	
  		
	</style> 
	
	

</head>
<body>
		<div class="container">
			
			
			<div id="coolDiv" style=" position: absolute; right: 10px; top:50px; ">
				<img  src="<%=request.getContextPath()%>/frontend/img/IIICAR5.png" height="150"></a>
			</div>
			<div>										
<%-- 			<%response.setHeader("refresh","2;URL=http://localhost:8081/BA106G4/frontend/index.jsp" ); %> --%>
			<%response.setHeader("refresh","2;URL=logIn.jsp" ); %>
				<h2 >登出中~兩秒後自動跳轉到首頁</h2>
				
			</div>
			 
		</div>
		
		<div class="progress">
				<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="100" aria-valuemax="0" style="width: 75%"></div>
		</div>
<video autoplay="autoplay" loop poster="<%=request.getContextPath()%>/frontend/img/bg.jpg" id="bgvid">
<%-- 	  <source src="<%=request.getContextPath()%>/frontend/img/bgvideo.webm" type="video/webm">  --%>
	  <source src="<%=request.getContextPath()%>/frontend/img/bgvideo.mp4" type="video/mp4"> 
  </video>

</body>
</html>