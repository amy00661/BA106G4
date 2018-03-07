<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="iii.car.model.*" %>
<%@ page import="iii.db.model.*" %>
<%@ page import="iii.emp.model.*" %>

<%
	CarVO carVO = (CarVO) request.getAttribute("carVO");
	DBVO dbVO = (DBVO) request.getAttribute("dbVO");
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

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

  <!-- CSS -->
  <!-- =================================================== -->
    
     <link href="<%= request.getContextPath() %>/backend/css/car.css" rel="stylesheet"> 

  <!-- =================================================== -->
</head>

<body>
	<!-- 彈出視窗--新增 -->
      <div class='modal fade' id="addShow" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header text-center">
              <h5 class="modal-title" id="exampleModalLabel">新增車輛</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
           
   
       
          
           <div class="modal-body">
            <form METHOD="post" ACTION="<%= request.getContextPath() %>/car/car.do" name="form1">
              <div class="container">
                <div class="row">
                  <div class="col-xs-12 col-sm-3">
                    <div class="form-group">
	                    <label for="recipient-name" class="col-form-label">車牌</label>
	                    <input type="text" name="car_plate" value="${param.car_plate}" class="form-control" id="recipient-name" placeholder="範例：1R-1234"/>
                        <p>${addError.car_plate}</p> 
                       
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-3">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">貨運公司</label>
                        <jsp:useBean id="dbSvc" scope="page" class="iii.db.model.DBDAOService"/>
                        <select class="form-control" name="db_id">
							<c:forEach var="dbVO" items="${dbSvc.all}">
								<option value="${dbVO.db_id}" ${(carVO.db_id==dbVO.db_id)? 'selected':''}>${dbVO.db_name}
							</c:forEach>
						</select>     
                    	<p>${addError.db_id}</p> 
                    </div>  
                  </div>
                  <div class="col-xs-12 col-sm-3">
                    <div class="form-group">
                       <label for="recipient-name" class="col-form-label">車輛狀態</label>
                       <select class="form-control" name="car_status">
                       		<option value="${param.car_status}">已出車
                       		<option value="${param.car_status}">未出車
                       </select>
                   	   <p>${addError.car_status}</p> 
                   	</div>  
                  </div>                
                  <div class="col-xs-12 col-sm-3">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">所屬駕駛</label>
                        <jsp:useBean id="empRevSvc" scope="page" class="iii.emp.model.EmpService"/>
                        <select class="form-control" name="emp_id">
                        	<c:forEach var="empVO" items="${empRevSvc.allEmps}">
                        		<option value="${empVO.emp_id}" ${(carVO.emp_id==empVO.emp_id)? 'selected':''}>${empVO.emp_name}
                        		<%-- <option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
                        	</c:forEach>
                        </select>
                    	<p>${addError.car_driver}</p>
                    </div>
                  </div>
                </div>
              </div>
              <div class="container">
                <div class="row">
                <div class="col-xs-12 col-sm-3">
                    <div class="form-group">
                      <label for="recipient-name" class="col-form-label">車種</label>
                      <input type="text" name="car_brand" value="${param.car_brand}" class="form-control" id="recipient-name" />
                  	  <p>${addError.car_brand}</p> 
                    </div>
                  </div>  
                  <div class="col-xs-12 col-sm-3">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">車輛顏色</label>
                        <input type="text" name="car_color" value="${param.car_color}" class="form-control" id="recipient-name" />
                    	<p>${addError.car_color}</p> 
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-3">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">排氣量</label>
                        <input type="text" name="car_pdv" value="${param.car_pdv}" class="form-control" id="recipient-name" />
                    	<p>${addError.car_pdv}</p> 
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-3">
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
                  
                    
                </div>
              </div>
              <div class="container">
                <div class="row">
                  <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
                       <label for="recipient-name" class="col-form-label">車輛圖片</label>
                       <input type="text" name="car_note" value="${param.car_note}" class="form-control" id="recipient-name" />
                       <p>${addError.car_note}</p>          
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
	                    <label for="recipient-name" class="col-form-label">更新員工</label>
	                    <jsp:useBean id="empSvc" scope="page" class="iii.emp.model.EmpService"/>
                        <select class="form-control" name="emp_id">
                        	<c:forEach var="empVO" items="${empSvc.allEmps}">
                        		<option value="${empVO.emp_id}" ${(carVO.emp_id==empVO.emp_id)? 'selected':''}>
                        		${empVO.emp_name}
                        		</option>
                        	</c:forEach>
                        </select>
	                    <p>${addError.emp_id}</p>         
                    </div>
                  </div>
                 </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-secondary close1" data-dismiss="modal">關閉</button>
                   <input type="hidden" name="action" value="insert">
                   <input type="submit" value="送出新增" id="addCar" class="btn btn-primary">
               </div>
              </div>  
             </form>
            </div>
          </div>
        </div>
      </div>


</body>
</html>