<%@	page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="iii.mem.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String flag = (String) request.getAttribute("flag");
	MemVO memVO = (MemVO)request.getAttribute("memVO");
%>

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

	<script language="javascript">
	$(document).ready(function(){
		$("#stopMail").hide();    //一開始3條訊息都隱藏
		$("#noMail").hide();
		$("#loginalert").hide();
		
		var flag="${flag}";
		var flagNoMail="${flagNoMail}";
	

		if(flag!=""){                   //顯示密碼錯誤訊息
			$("#loginalert").toggle(500);
			$("#loginalert").delay(1000).toggle(500);
		}
		if(flagNoMail=="no"){                   //顯示密碼錯誤訊息
			$("#noMail").toggle(500);
			$("#noMail").delay(1000).toggle(500);
		}
			
		
		$("#exampleInputEmail1").blur(function(){  //顯示無此帳號 或 停權訊息
			
			
			$.ajax({
				type : "post",
				url : "<%=request.getContextPath()%>/mem/checkMail.do",
				data : {action:"checkMail2",mail:$("#exampleInputEmail1").val()},
				success : function(msg){
					
					if(msg==="no" && $("#exampleInputEmail1").val()!=""){
						$("#noMail").toggle(500);
						$("#noMail").delay(1000).toggle(500);
					}else if(msg==="stop"){		
						$("#stopMail").toggle(500);
						$("#stopMail").delay(1000).toggle(500);
						$("#btnlogin").prop('disabled', true);
					}else{
						$("#btnlogin").prop('disabled', false);
					}
				}
			});
		
		});
		
		$("#customCheck1").change(function(){					//神奇小按鈕
			$("#exampleInputEmail1").val("aaa999@yahoo.com.tw");
			$("#validationDefaultUsername").val("abc123456");
		})
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
	    <div class="card card-login mx-auto mt-5">
	      <div class="card-header  bg-primary"><h4 class="font-weight-bold text-white">登入帳號</h4></div>
	      <div class="card-body">
	        <form method="post" action="<%=request.getContextPath()%>/mem/checkMail.do" name="form1">
	          
	          <div id="loginalert" class="alert alert-dismissable alert-danger">
	          
                <button type="button" class="close" data-dismiss="alert">×</button>
				   	密碼錯誤
			  </div>
			  <div id="stopMail" class="alert alert-dismissable alert-danger">
			  	<button type="button" class="close" data-dismiss="alert">×</button>
				   	此帳號被停權
			  </div>
			  <div id="noMail" class="alert alert-dismissable alert-danger">
			  	<button type="button" class="close" data-dismiss="alert">×</button>
				   	無此帳號
			  </div>
	          
	          
	          <div class="form-group">
	            <label for="exampleInputEmail1"><h5><p class="font-weight-bold"><i class="fa fa-envelope"></i> 信箱:</p></h5></label>
	            <input class="form-control" id="exampleInputEmail1" type="email" name="member_mail" aria-describedby="emailHelp" placeholder="Mail" required value="<%=(memVO==null)?"":memVO.getMember_mail() %>">
	          </div>
	          
	          <div class="form-group">
	            <label for="validationDefaultUsername"><h5><p class="font-weight-bold"><i class="fa fa-lock"></i> 密碼:</p></h5></label>
	            <input class="form-control" id="validationDefaultUsername" type="password" name="member_psw" placeholder="Password" aria-describedby="inputGroupPrepend2" required> 
		            <div class="invalid-tooltip">
			          Please choose a unique and valid username.
			        </div>
	          </div>
	          <input type="hidden" name="action" value="loginMain">
	          <button class="btn btn-success btn-block" type="submit" id="btnlogin"> <i class="fa fa-user"> </i>登入</button>
	          </form>
	     
	          	<div>
	          		<button style="margin-top:5px" onclick="location.href='<%=request.getContextPath()%>/frontend/index.jsp'"  class="btn btn-info btn-block" type="button" id="btnlogin"> <i class="fa fa-user"> </i>以訪客身分登入</button>
	          	</div>
	          
	          <div>
                <div class="row">
                  <div class="col-9">
                      <div class="custom-control custom-checkbox">
                         <input type="checkbox" class="custom-control-input" id="customCheck1">
                         <label class="custom-control-label font-weight-bold" for="customCheck1">記住我</label>
                      </div>
                   </div>
                      <div class="col-3" style="text-align:right;">
                      
                         <a href="#" class="font-weight-bold" data-toggle="modal" data-target="#myModal" style="color:red;" >忘記密碼</a>
                      </div>
                 </div>
               </div>
	        
	      </div>
	    </div>
	  </div>

  <video autoplay="autoplay" loop poster="<%=request.getContextPath()%>/frontend/img/bg.jpg" id="bgvid">
<%-- 	  <source src="<%=request.getContextPath()%>/frontend/img/bgvideo.webm" type="video/webm">  --%>
	  <source src="<%=request.getContextPath()%>/frontend/img/bgvideo.mp4" type="video/mp4"> 
  </video>



		<div id="myModal" class="modal fade" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header bg-primary">
                <h5 class="modal-title font-weight-bold text-white">忘記密碼</h5>
                </div>

                  <div class="modal-body">
                     <form method="post" action="<%=request.getContextPath()%>/mem/checkMail.do" name="form1">
                        <div class="form-group">
                            <label for="mail"><h5><p class="font-weight-bold"><i class="fa fa-envelope"></i> 信箱:</p></h5></label>
                            <span id="result1" class="font-weight-bold" style="color:red"></span>
                            <input id="mail2" class="form-control" type="email" name="member_mail" required>
                        </div>
                        <div class="form-group">
                            <label for="psw"><h5><p class="font-weight-bold"><i class="fa fa-lock"></i> 身分證字號/統編:</p></h5></label>
                            <span id="result2" class="font-weight-bold" style="color:red"></span>
                            <input id="psw2" class="form-control" type="text" name="member_identification" required>
                        </div>

                         <div class="modal-footer">
                         		<input type="hidden" name="action" value="foundPsw">
                                <button class="btn btn-primary btn-block" type="submit" id="btnFoundPsw" ><h5><i class="fa fa-user"> </i>送出</h5></button>                               
                        </div>
                      </form> 
                  </div>
            </div>
          </div>
        </div>




</body>
</html>