<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>資策貨運登入系統</title>
  
    
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
  <link href="<%=request.getContextPath()%>/backend/css/sup.css" rel="stylesheet"> 
</head>

<body class="loginCUS">

  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">資策貨運員工登入系統</div>
      <div class="card-body">
        <form method="post" action="<%=request.getContextPath()%>/employee/EmpServlet.do">
          <div class="form-group">
            <label for="empid">員工ID</label>
            <input class="form-control" name="empid" id="empid" type="text" aria-describedby="empid" placeholder="ID">
          </div>
          <div class="form-group">
            <label for="password">密碼</label>
            <input class="form-control" name="password" id="password" type="password" placeholder="Password">
          </div>
<!--           <a class="btn btn-info btn-block" href="index.html">登入</a> -->
		 <input type="hidden" name="action" value="login">
		 <button type="submit" id="btnGet" class="btn btn-info btn-block" >登入</button>
        </form>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript-->
  
    <script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
</body>

</html>