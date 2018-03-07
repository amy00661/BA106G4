<%@	page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <link href="<%=request.getContextPath()%>/frontend/css/main.css" rel="stylesheet">
    <!-- ========================================================基本套件 -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <!-- =================================================== -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

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
     background: url(<%=request.getContextPath()%>/frontend/img/bg.jpg) no-repeat;
     background-size: cover;
    }	
  		
	</style> 
	
	
	

</head>
<body>

	  <div class="container">
	    <div class="card card-login mx-auto mt-5">
	      <div class="card-header  bg-primary"><h4 class="font-weight-bold text-white">登入帳號</h4></div>
	      <div class="card-body">
	        <form method="post" action="<%=request.getContextPath()%>/mem/checkMail.do" name="form1">
	          <div class="form-group">
	            <label for="exampleInputEmail1"><h5><p class="font-weight-bold"><i class="fa fa-envelope"></i> 信箱:</p></h5></label>
	            <input class="form-control" id="exampleInputEmail1" type="email" name="member_mail" aria-describedby="emailHelp" placeholder="Mail">
	          </div>
	          <div class="form-group">
	            <label for="exampleInputPassword1"><h5><p class="font-weight-bold"><i class="fa fa-lock"></i> 密碼:</p></h5></label>
	            <input class="form-control" id="exampleInputPassword1" type="password" name="member_psw" placeholder="Password">
	          </div>
	          <input type="hidden" name="action" value="login">
	          <button class="btn btn-primary btn-block" type="submit" id="btnlogin"> <i class="fa fa-user"> </i>登入</button>
	          <div>
                <div class="row">
                  <div class="col-9">
                      <div class="custom-control custom-checkbox">
                         <input type="checkbox" class="custom-control-input" id="customCheck1">
                         <label class="custom-control-label font-weight-bold" for="customCheck1">記住我</label>
                      </div>
                   </div>
                      <div class="col-3" style="text-align:right;">
                         <a href="#" class="font-weight-bold">忘記密碼</a>
                      </div>
                 </div>
               </div>
	        </form>
	      </div>
	    </div>
	  </div>

  <video autoplay="autoplay" loop poster="<%=request.getContextPath()%>/frontend/img/bg.jpg" id="bgvid">
<%-- 	  <source src="<%=request.getContextPath()%>/frontend/images/bgvideo.webm" type="video/webm">  --%>
	  <source src="<%=request.getContextPath()%>/frontend/img/bgvideo.mp4" type="video/mp4"> 
  </video>








</body>
</html>