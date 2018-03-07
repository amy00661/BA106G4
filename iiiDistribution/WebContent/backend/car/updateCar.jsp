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

  <!-- CSS -->
  <!-- =================================================== -->
    
     <link href="<%= request.getContextPath() %>/backend/css/car.css" rel="stylesheet"> 

  <!-- =================================================== -->
</head>
<body>

   <!--  //    Map m = (Map)request.getAttribute("addError");
    //    int si = 0;
    //    if(m!=null){
    //      si = m.size();
    //    }
    //    String clz = si!=0 ?  " collapse show" : ""; -->
    <%--  %> --%>
    <%--  <div class='modal fade${not empty addError? " collapse show":""}' id="addShow" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
       --%> 
      
      <!-- 彈出視窗--查詢 修改 刪除 -->
      
      <%-- <%
        Map m = (Map)request.getAttribute("addError");
        int si = 0;
        if(m!=null){
          si = m.size();
        }
        String clz = si!=0 ?  " collapse show" : "";
      %> --%>
         
      <% 
          CarService carSvc = new CarService();
          List<CarVO> list = carSvc.getAll();
          pageContext.setAttribute("list", list);
          
          CarVO carVO = new CarVO();
          Map updateError = (LinkedHashMap) request.getAttribute("updateError");
      %>
       
      
      
      
      <div class="modal fade " id="updateShow" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content" id="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="modify">修改資訊</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
	            <table class="table">
	              <thead class="thead thead-dark">
	                <tr>
						<th scope="col">車輛<br>車牌</th>
						<th scope="col">狀態</th>
						<th scope="col">所屬<br>公司</th>
						<th scope="col">目前<br>載重</th>
						<th scope="col">車輛<br>車種</th>
						<th scope="col">顏色</th>
						<th scope="col">總排<br>氣量</th>
						<th scope="col">車輛<br>駕駛</th>
						<th scope="col">更新<br>人員</th>
						<th scope="col">更新<br>日期</th>
						<th scope="col">車輛<br>備註</th>
						<th scope="col">更新</th>
						<th scope="col">刪除</th>
	                </tr>
	              </thead>

	              <tbody class="tbody">
	              	<tr style="color:red">
	              		<td>${updateError.car_plate}</td>
	              		<td>${updateError.car_status}</td>
	              		<td>${updateError.db_id}</td>
	              		<td>${updateError.car_load}</td>
	              		<td>${updateError.car_brand}</td>
	              		<td>${updateError.car_color}</td>
	              		<td>${updateError.car_pdv}</td>
	              		<td>${updateError.car_driver}</td>
	              		<td>${updateError.emp_id}</td>
	              		<td>${updateError.car_updatetime}</td>
	              		<td>${updateError.car_note}</td>
	              	</tr>
	              	<%-- <c:choose>
					    <c:when test="${not empty updateError}">
					    	<c:forEach var="message" items="${updateError}">
			                	<td><font style="color:red">${message.value}</font></td>
			              	</c:forEach>
					    </c:when>    
					    <c:otherwise>
					        <c:forEach var="message" items="${updateError}">
			                	<td><font style="color:red">${message.value}</font></td>
			              	</c:forEach>
					    </c:otherwise>
					</c:choose> --%>
	              	
	              	
	              	
	              <%-- 	 <c:if test="">
		              <c:forEach var="message" items="${updateError}">
		                <td><font style="color:red">${message.value}</font></td>
		              </c:forEach>
			        </c:if>
			        </tr> --%>
	              
	              	
	              	<%-- <tr>
	              		<% if(updateError != null){ %>
	                 	<td><%= (updateError.get("car_plate")!=null) ? updateError.get("car_plate") : "" %></td>
	                    <td><%= (updateError.get("car_status")!=null) ? updateError.get("car_status") : "" %></td>
	                    <td><%= (updateError.get("db_id")!=null) ? updateError.get("db_id") : "" %></td>
	                    <td><%= (updateError.get("car_load")!=null) ? updateError.get("car_load") : "" %></td>
	                    <td><%= (updateError.get("car_brand")!=null) ? updateError.get("car_brand") : "" %></td>
	                    <td><%= (updateError.get("car_color")!=null) ? updateError.get("car_color") : "" %></td>
	                    <td><%= (updateError.get("car_pdv")!=null) ? updateError.get("car_pdv") : "" %></td>
	                    <td><%= (updateError.get("car_driver")!=null) ? updateError.get("car_driver") : "" %></td>
	                    <td><%= (updateError.get("emp_id")!=null) ? updateError.get("emp_id") : "" %></td>
	                    <td><%= (updateError.get("car_updatetime")!=null) ? updateError.get("car_updatetime") : "" %></td>
	                    <td><%= (updateError.get("car_note")!=null) ? updateError.get("car_note") : "" %></td>
	                </tr> --%>
	                <c:forEach var="carVO" items="${list}" >
	                  <tr>
	                    <td class="${carVO.car_plate}">${carVO.car_plate}</td> 
	                    <td class="${carVO.car_status}">${carVO.car_status}</td>
	                    <jsp:useBean id="dbSvc" scope="page" class="iii.db.model.DBDAOService"/>
	                    <c:forEach var="dbVO" items="${dbSvc.all}">
	                    	<c:if test="${carVO.db_id==dbVO.db_id}">
				            	<td class="${carVO.db_id}">${dbVO.db_name}</td>
			                </c:if>
                        </c:forEach>
	                    <td class="${carVO.car_load}">${carVO.car_load}</td>
	                    <td class="${carVO.car_brand}">${carVO.car_brand}</td>
	                    <td class="${carVO.car_color}">${carVO.car_color}</td>
	                    <td class="${carVO.car_pdv}">${carVO.car_pdv}</td>
	                    <td class="${carVO.car_driver}">${carVO.car_driver}</td>
	                    <jsp:useBean id="empRevSvc" scope="page" class="iii.emp.model.EmpService"/>
	                    <c:forEach var="empVO" items="${empRevSvc.allEmps}">
	                    	<c:if test="${carVO.emp_id==empVO.emp_id}">
				            	<td class="${carVO.emp_id}">${empVO.emp_name}</td>
			                </c:if>
                        </c:forEach>
	                    <td class="${carVO.car_updatetime}"><fmt:formatDate value="${carVO.car_updatetime}" pattern="yyyy-MM-dd"/></td>
	                    <td class="${carVO.car_note}">${carVO.car_note}</td>
	                    <!-- 更新按鈕點擊 -->
	                    <td>
		                    <form METHOD="post" ACTION="<%= request.getContextPath() %>/car/car.do">
			                    <button type="button" class='btn btn-warning update ${carVO.car_id}' value="${carVO.car_id}">更新</button>
			                    <input type="hidden" name="action" value="update"/>
			                    <input type="hidden" name="car_id" value="${carVO.car_id}">
			                    <input type="hidden" name="car_plate" value="${carVO.car_plate}"/> 
								<input type="hidden" name="car_status" value="${carVO.car_status}"/>
								<input type="hidden" name="db_id" value="${carVO.db_id}"/>
								<input type="hidden" name="car_load" value="${carVO.car_load}"/>
								<input type="hidden" name="car_brand" value="${carVO.car_brand}"/>
								<input type="hidden" name="car_color" value="${carVO.car_color}"/>
								<input type="hidden" name="car_pdv" value="${carVO.car_pdv}"/>
								<input type="hidden" name="car_driver" value="${carVO.car_driver}"/>
								<input type="hidden" name="emp_id" value="${carVO.emp_id}"/>
								<fmt:formatDate value="${carVO.car_updatetime}" var="car_updatetime" pattern="yyyy-MM-dd"/>
								<input type="hidden" name="car_updatetime" value="${car_updatetime}"/>
								<input type="hidden" name="car_note" id="note" value="${carVO.car_note}"/>
								<!-- <input type="submit" value="e04su3xl3g"/> -->
		                    </form>
	                    </td>     
	                    
	                    <!-- 刪除按鈕點擊 -->
	                    <td>
		                    <form METHOD="post" ACTION="<%= request.getContextPath() %>/car/car.do" name="form4">
								<button type="submit" class="btn btn-danger">刪除</button>
								<input type="hidden" name="car_id" value="${carVO.car_id}">
								<input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
								<input type="hidden" name="action" value="delete">
		                    </form>
	                    </td>
	                  </tr>
	                 
	                </c:forEach>        
	              </tbody>              
            	</table>
            	<div id="${param.car_id}" class="error"></div>
            </div>
          	<div class="modal-footer">
	            <button type="button" class="btn btn-secondary close1" data-dismiss="modal">關閉</button>
	            <input type="hidden" name="action" value="listall">
            </div>
          </div>
        </div>
      </div>          
    </div>
    
   
</body>
</html>