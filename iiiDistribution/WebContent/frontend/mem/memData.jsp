<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="iii.mem.model.*"%>

<%
	//MemVO memVO = (MemVO)request.getAttribute("memVO");
%>


<html>
<head>
    <meta charset="utf-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>會員資料</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <link href="<%=request.getContextPath()%>/frontend/css/main.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/mem.css" rel="stylesheet">
    <!-- ========================================================基本套件 -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <!-- =================================================== -->
    
                      <!-- 請大家把自己的套件放在這邊 -->

    <!-- =================================================== -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script language="javascript">
    $(document).ready(function(){
    	
    	var flag ="${flag}";
    	if(flag==""){
    		$("#myalert").hide();
    	}else{
    		$("#myalert").delay(3000).toggle(1500);
    	}
    	
    	
    })

    </script>
  </head>

  <body>
    <header>
      <div class="container logoCus">
        <div class="row" align="center">
          <div class="col-xs-12 col-sm-4">
            

          </div>
          <div class="col-xs-12 col-sm-4">
            <!-- LOGO -->
            <div>
              <a class="navbar-brand" href="<%=request.getContextPath()%>/frontend/index.jsp"><img src="<%=request.getContextPath()%>/frontend/images/IIICAR5.png" height="150"></a>
            </div>
            <div class="input-group">
              <input  class="form-control" type="text" placeholder="站內搜尋">
              <div class="input-group-append">
              <button type="submit" class="btn btn-secondary"><i class="fa fa-search"></i></button>
              </div>
            </div>

          </div>
          <div class="col-xs-12 col-sm-4">
            

          </div>
        </div>
      </div>

        <!-- navbar -->
      <nav class="navbar navbar-expand-lg navbar-light bg-cus rounded">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">服務項目</a>
              <div class="dropdown-menu" aria-labelledby="dropdown10">
                <a class="dropdown-item" href="#">關於我們</a>
                <a class="dropdown-item" href="#">配送服務</a>
                <a class="dropdown-item" href="#">契約客戶</a>
                <a class="dropdown-item" href="#">到府服務</a>
                <a class="dropdown-item" href="#">QR CODE認證收貨</a>
                <a class="dropdown-item" href="#">即時追蹤</a>
              </div>
            </li>

            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">寄件申請</a>
              <div class="dropdown-menu" aria-labelledby="dropdown10">
                <a class="dropdown-item" href="order_cr.html">線上申請寄件</a>
                <a class="dropdown-item" href="#">寄件指南</a>
                <a class="dropdown-item" href="cal.html">運費查詢</a>
              </div>
            </li>

            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">相關查詢</a>
              <div class="dropdown-menu" aria-labelledby="dropdown10">
                 <a class="dropdown-item" href="order_inq.html">訂單查詢</a>
                <a class="dropdown-item" href="#">據點查詢</a>
                <a class="dropdown-item" href="#">包裹地圖追蹤</a>
              </div>
            </li>

            <li class="nav-item dropdown" >
              <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">客服中心</a>
              <div class="dropdown-menu" aria-labelledby="dropdown10">
                <a class="dropdown-item" href="#">常見問題</a>
                <a class="dropdown-item" href="#">聯絡我們</a>
              </div>
            </li>

            <li class="nav-item">
              <a class="nav-link" href=<%=request.getContextPath()%>/frontend/mem/logOut.jsp data-toggle="" data-target="#myModal">登出</a>
            </li>
          </ul>
        </div>
      </nav>
    </header> 

    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-3">
          <!-- 母子選單-->
            <div class="card my-4">
            <div id="accordion" role="tablist">

              <div class="card">
                <div class="card-header" role="tab" id="headingOne">
                  <h5 class="mb-0">
                    <a data-toggle="collapse" href="#collapseOne" role="button" aria-expanded="true" aria-controls="collapseOne">
      	 會員資訊
                    </a>
                  </h5>
                </div>

                <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
                  <div class="card-body">
                   	<a href="memDataUpdate.jsp">修改資料</a>
                  </div>
                </div>
              </div>


              <div class="card">
                <div class="card-header" role="tab" id="headingTwo">
                  <h5 class="mb-0">
                    <a class="collapsed" data-toggle="collapse" href="#collapseTwo" role="button" aria-expanded="false" aria-controls="collapseTwo">
                      導覽列
                    </a>
                  </h5>
                </div>

                <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion">

                    <div class="card-body">
                        子導覽列
                    </div>
                    <div class="card-body">
                        子導覽列
                    </div>
                    <div class="card-body">
                        子導覽列
                    </div>
                    <div class="card-body">
                        子導覽列
                    </div>
                </div>
              </div>
            </div>
          </div>

        </div>


        <div class="col-xs-12 col-sm-9">

          <!-- 麵包削 -->
          <div class="my-4">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="<%=request.getContextPath()%>/frontend/index.jsp">首頁</a>
                </li>
                <li class="breadcrumb-item active">會員個人資訊</li>
            </ol>
          </div> 
          <!-- 麵包削 -->

<!-- ================================(主要網格9格)====================================== -->

		<div id="myalert" class="alert alert-dismissable alert-success">
			<button type="button" class="close" data-dismiss="alert">×</button>
				修改成功!
		</div>


         <div >
           <div class="card border-info">
		  <h5 class="card-header bg-info text-white">會員資料</h5>
		  <div class="card-body">
			
			
		  <div class="form-group row">
		    <label for="inputEmail" class="col-sm-3 col-form-label">會員信箱:</label>
		    <div class="col-sm-9">
		      <input type="email" name="member_mail" class="form-control" id="inputEmail" disabled value="${memVO.member_mail}">
		    </div>
		  </div>
		  
		  <hr>
		  
		  <div class="form-group row">
		    <label for="inputName" class="col-sm-3 col-form-label">會員名稱:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_name" class="form-control" id="inputName" disabled value="${memVO.member_name}">
		    </div>
		  </div>
		  
		  <hr>
		  
		  <div class="form-group row">
		    <label for="inputBirth" class="col-sm-3 col-form-label">生日:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_birth" class="form-control" id="inputBirth" disabled value="${memVO.member_birth}">
		    </div>
		  </div>
		  
		  <hr>
		  
		  <div class="form-group row">
		    <label for="inputGender" class="col-sm-3 col-form-label">性別:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_gender" class="form-control" id="inputGender" disabled value="${memVO.member_gender}">
		    </div>
		  </div>
		  
		  <hr>
		  
		  <div class="form-group row">
		    <label for="inputIden" class="col-sm-3 col-form-label">身分證字號/統編:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_identification" class="form-control" id="inputIden" disabled value="${memVO.member_identification}">
		    </div>
		  </div>
		  
		  <hr>
		  
		  <div class="form-group row">
		    <label for="inputCell" class="col-sm-3 col-form-label">手機:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_cell" class="form-control" id="inputCell" disabled value="${memVO.member_cell}">
		    </div>
		  </div>
		  
		  <hr>
		  
		  <div class="form-group row">
		    <label for="inputPhone" class="col-sm-3 col-form-label">連絡電話:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_phone" class="form-control" id="inputPhone" disabled value="${memVO.member_phone}">
		    </div>
		  </div>
		  
		  <hr>
		  
		  <div class="form-group row">
		    <label for="inputAddr" class="col-sm-3 col-form-label">地址:</label>
		    <div class="col-sm-9">
		      <input type="text" name="member_addr" class="form-control" id="inputAddr" disabled value="${memVO.member_addr}">
		    </div>
		  </div>
        </div>
      </div>
          </div>
          <br>
<!-- ================================(主要網格9格)====================================== -->
        </div>
      </div>
    </div>

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">2018/2/4版本</p>
      </div> 
    </footer>

  </body>
</html>