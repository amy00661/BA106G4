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
  
 <%--  <link href="<%= request.getContextPath() %>/backend/css/font_awesome_min.css" rel="stylesheet">  --%>	
  <link href="<%= request.getContextPath() %>/backend/css/sup.css" rel="stylesheet"> 
<%--   <link href="<%= request.getContextPath() %>/backend/css/main.css" rel="stylesheet"> --%>
    
  <link href="<%= request.getContextPath() %>/backend/css/car.css" rel="stylesheet"> 
  

  <!-- CSS -->
  <!-- =================================================== -->
    
                      <!-- 請大家把自己的套件放在這邊 -->

  <!-- =================================================== -->
</head>

<body>

<%
CarService carSvc = new CarService();
List<CarVO> list = carSvc.getAll();
pageContext.setAttribute("list", list);

%>
<table>
<tbody>
	                <c:forEach var="carVO" items="${list}" >
	                  <tr>
	                    <td class="${carVO.car_plate}">${carVO.car_plate}</td> 
	                    <td class="${carVO.car_status}">${carVO.car_status}</td>
	                    <td class="${carVO.db_id}">${carVO.db_id}</td>
	                    <td class="${carVO.car_load}">${carVO.car_load}</td>
	                    <td class="${carVO.car_brand}">${carVO.car_brand}</td>
	                    <td class="${carVO.car_color}">${carVO.car_color}</td>
	                    <td class="${carVO.car_pdv}">${carVO.car_pdv}</td>
	                    <td class="${carVO.car_driver}">${carVO.car_driver}</td>
	                    <td class="${carVO.emp_id}">${carVO.emp_id}</td>
	                    <td class="${carVO.car_updatetime}"><fmt:formatDate value="${carVO.car_updatetime}" pattern="yyyy-MM-dd"/></td>
	                    <td class="${carVO.car_note}" id="note">${carVO.car_note}</td>
	                    
	                    <!-- 更新按鈕點擊 -->
	                    <td>
		                    <form METHOD="post" ACTION="<%= request.getContextPath() %>/car/car.do">
			                    <button type="button" class='btn btn-warning update' value="${carVO.car_id}">更新</button>
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
</body>
