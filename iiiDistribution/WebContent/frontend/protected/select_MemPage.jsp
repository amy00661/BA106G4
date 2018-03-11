<%@	page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>會員頁面</title>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td><h3>會員首頁</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>資料查詢:</h3>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href="listAllMem.jsp">List All Member</a><br><br></li>
	<li>
		<form method="post" action="mem.do">
			<b>輸入會員編號(如MEM001)</b>
			<input type="text" name="member_id" >
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</form>
	</li>
	
	<jsp:useBean id="memSvc" scope="page" class="iii.mem.model.MemService"/>
	
	<li>
		<form method="post" action="mem.do">
			<b>選擇會員編號</b>
			<select size="1" name="member_id">
				<c:forEach var="memVO" items="${memSvc.all}">
				  <option value="${memVO.member_id}">${memVO.member_id}
				</c:forEach>
			</select>
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</form>
	</li>
	<li>
		<form method="post" action="mem.do">
			<b>選擇會員名稱</b>
			<select size="1" name="member_id">
			  <c:forEach var="memVO" items="${memSvc.all}">
			  	<option value="${memVO.member_id}">${memVO.member_name}
			  </c:forEach>
			</select>
		
		<input type="hidden" name="action" value="getOne_For_Display">
		<input type="submit" value="送出">
		</form>
	</li>
	

</ul>

<h3>會員新增:</h3>
<ul>
	<li><a href='addMember.jsp'>Add a new Member</a></li>
</ul>
	
</body>
</html>