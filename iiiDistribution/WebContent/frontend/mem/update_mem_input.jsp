<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="iii.mem.model.*" %>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
%>


<html>
<head>
<title>會員資料修改 - update_mem_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body>


<table id="table-1">
	<tr><td>
		<h3>員工資料修改 - update_emp_input.jsp</h3>
		<h4><a href="select_MemPage.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs }">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="<%=request.getContextPath()%>/mem/mem.do" name="form">
<table>
	<tr>
		<td>會員信箱:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMember_mail() %></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="password" name="member_psw" value=<%=memVO.getMember_psw() %>></td>
	</tr>
	<tr>
		<td>確認密碼:</td>
		<td><input type="password" name=member_psw2 value=<%=memVO.getMember_psw() %>></td>
	</tr>
	<tr>
		<td>會員名稱:</td>
		<td><input type="text" name=member_name value=<%=memVO.getMember_name() %>></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input type=text name=member_birth id="f_date1"></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td>
			<input type="radio" name="member_gender" value="男"/>男
			<input type="radio" name="member_gender" value="女"/>女
			<input type="radio" name="member_gender" value="保密"/>保密
		</td>
	</tr>
	<tr>
		<td>身分證號碼(統編):</td>
		<td><input type="text" name="member_identification" size="45" value="<%=memVO.getMember_identification()%>"/></td>
	</tr>
	<tr>
		<td>手機:</td>
		<td><input type="text" name="member_cell" size="45" value="<%=memVO.getMember_cell() %>"/></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="text" name="member_phone" size="45" value="<%=(memVO.getMember_phone()==null)?"":memVO.getMember_phone() %>"/></td>
	</tr>
		
	<tr>
		<td>住址:</td>
		<td><input type="text" name="member_addr" size="45" value="<%=memVO.getMember_addr() %>"/></td>
	</tr>

</table>
<br>
<input type=hidden name="action" value="update">
<input type=hidden name="member_id" value="<%=memVO.getMember_id() %>">
<input type=hidden name="member_mail" value="<%=memVO.getMember_mail() %>">
<input type=hidden name="member_updatetime" value="<%=memVO.getMember_updatetime() %>">
<input type=submit value="送出修改">
</form>

</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=memVO.getMember_birth()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>