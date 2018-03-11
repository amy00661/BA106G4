<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="iii.car.model.*" %>
<%@ page import="iii.order_main.model.*" %>

<% OrderVO orderVO = new OrderVO(); %>
<% CarVO carVO = new CarVO(); %>

<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>訂單地圖功能</title>
    <style>
      #right-panel {
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }

      #right-panel select, #right-panel input {
        font-size: 15px;
      }

      #right-panel select {
        width: 100%;
      }

      #right-panel i {
        font-size: 12px;
      }
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
        float: left;
        width: 70%;
        height: 100%;
      }
      #right-panel {
        border-width: 2px;
        width: 20%;
        height: 400px;
        float: left;
        text-align: left;
        padding-top: 0;
      }
      #directions-panel {
       
        margin-right:-100px;
        margin-top: 0px;
        background-color: #FFEE77;
        padding: 10px;
      }
      .multiple{
        width: 120px;
      }

      #legend {
        font-family:微軟正黑體;
        font-weight: bold;
        font-size: 13px;
        background: #fff;
        padding: 5px;
        margin: 10px;
        border: 3px solid #000;
        text-align: center;
      }
      
      #legend img{
      	width:50px;
      }
      #legend h3 {
        margin-top: 0;
      }
      #legend img {
        vertical-align: middle;
      }
      #route {
     	position: absolute;
	    top: 110%;
	    left: 37%;
	    width: 300px;
	    height: 200px;
	  }
	  #introduction{
  	 	position: absolute;
	    top: 110%;
	    left: 3%;
	    width: 300px;
	    height: 200px;
	  }
	  #space{
	  position: absolute;
	    top: 20%;
	    left: 3%;
	    width: 300px;
	    height: 200px;
	  }
	  
    </style>
  </head>
  
  <body style="background-color:#e6ffe6;">
	
    <div id="map"></div>
    <div id="legend"><h3></h3></div>

    <div id="right-panel">
    
    <div id="directions-panel"></div>
     <div style="border-style: solid; border-color: orange; border-style: dotted; width: 340px; height: 100px; margin-top:30px; padding-left:10px">統計資訊:<br>
           總公里數: <span id="totalDistance"></span> 公里。
           <br>
           總計成本: <span id="totalPrice"></span> 元。
    </div>
    
    <div>
	<div id="introduction">
	<div style="border-style: solid; border-color: red; border-style: dotted; width: 300px; height: 120px; ">計費說明:
           <br>平均貨車尺寸:3.5噸貨卡。
           <br>平面道路時速平均40公里, 約2元1公里。
           <!-- <br>車子每增重1kg,油耗增0.044%。 -->
           <br>高速公路收費約1.3元1公里。
    </div>
    <br>
    <img src="<%= request.getContextPath() %>/backend/img/dbicon.png">
	</div>
    </div>
    
    <div id="route">    <b>Start:桃園貨運站</b>
    <!-- 設定起點選項 -->
	<jsp:useBean id="carSvc" scope="page" class="iii.car.model.CarService"/>
	<jsp:useBean id="orderSvc" scope="page" class="iii.order_main.model.OrderService"/>
	<jsp:useBean id="loSvc" scope="page" class="iii.local_order.model.LoService"/>
	<jsp:useBean id="lsSvc" scope="page" class="iii.local_schedule.model.LsService"/>
    <select class="form-control" name="car_id" id="start">
    	<option value='-1' selected='selected'>請選擇車輛</option>
    	<c:forEach var="carVO" items="${carSvc.all}">
	    	<c:if test="${carVO.db_id=='DB05'}">
	    		<option id="${carVO.car_id}" value="320台灣桃園市中壢區新興路114號" class="${carVO.car_id}">${carVO.car_id} 車牌：${carVO.car_plate}</option>
	    	</c:if>	
    	</c:forEach>
    </select>
    <br>
    <!-- 設定中途點選項 -->
    <select class="form-control" name="car_id" id="queryLo">
    </select>
    	<%-- <c:forEach var="lsVO" items="${lsSvc.all}">
    		<c:forEach var="loVO" items="${loSvc.all}">
    			<c:forEach var="carVO" items="${carSvc.all}">
    				<c:forEach var="orderVO" items="${orderSvc.all}">
				    	<c:if test="${carVO.car_id==lsVO.car_id}">
				    		<c:if test="${loVO.local_schedule_ID==lsVO.local_schedule_id}">
				    			<c:if test="${loVO.order_ID==orderVO.order_id}">
					    		<option id="${carVO.car_id}" value="320台灣桃園市中壢區新興路114號" class="" onchange="ggg(${carVO.car_id})")">${carVO.car_id} 車牌：${carVO.car_plate}</option>
					    			<option>${orderVO.order_id}</option>
				    			</c:if>	
				    		</c:if>	
				    	</c:if>	
			    	</c:forEach>
			    </c:forEach>
		    </c:forEach>
    	</c:forEach> --%>
    
    
    
    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行程訂單&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未處理訂單&nbsp;&nbsp;&nbsp;&nbsp;
    <select multiple="multiple" class="multiple sbOne" id="waypoints" style="width: 120px;" >
    </select>
    
    <select multiple class="multiple sbTwo" id="queryNull" style="width: 120px;" >
    </select>
    
    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" id="left" value="&#60;" />
    <input type="button" id="leftall" value="&#60;&#60;" />
    <input type="button" id="right" value="&#62;"/>
    <input type="button" id="rightall" value="&#62;&#62;" />
    <br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="預覽路線"id="submit">
    <br>
    <b>End::桃園貨運站</b>
    <!-- 設定終點選項 -->
    <select id="end" disabled="disabled">
     <!--  <option value="320台灣桃園市中壢區新興路114號">桃園貨運站</option> -->
    </select>
    <br>
      

    
    
    
    <script> 
      function initMap() {
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 6,
          center: {lat:24.8022040, lng:120.9723760}
        });
        directionsDisplay.setMap(map);

        // 設定點擊監聽器，當submit被點擊時呼叫calculateAndDisplayRoute
        document.getElementById('submit').addEventListener('click', function() {
          calculateAndDisplayRoute(directionsService, directionsDisplay);
        });

        icons = {
          DB: {
            name: '貨運公司',
            icon: '<%=request.getContextPath()%>/backend/img/dbicon.png'
          },
          library: {
            name: '路線',
            icon: '<%=request.getContextPath()%>/backend/img/routeicon.png'
          },
        };

        var myLatlng = (24.969, 121.192);        
        var KeelungDB = (25.1316010, 121.7383780);
        var newTaipeiDB = (25.0143880, 121.4635270);
        var TaipeiDB = (25.0488250, 121.5186370);
        var YilanDB = (24.7543120, 121.7583780);
        var TaoyuanDB = (24.8022040, 120.9723760);
        var HsinchuDB = (24.9539720, 121.2259710);
        var HsinchuCityDB = (24.9011360, 121.0435380);
        var MiaoliDB = (24.5700200, 120.8223430);
        var TaichungDB = (25.0488250,  121.5186370);
        var ChanghuaDB = (24.0816310, 120.5384440);
        (23.9179640, 120.6775050);(23.7117920, 120.5411700);(23.4545970, 120.4038880);(23.4791190, 120.4411380);(22.997234, 120.2119360); (22.6389750, 120.3020070); (22.6688550, 120.4859690); (22.7937110, 121.1231610);(23.9929460, 121.6011140);(23.5674240, 119.5643650);
         (24.4291400, 118.3598540);(26.1491610, 119.9384210);


        var features = [
          {
            position: new google.maps.LatLng(25.1316010, 121.7383780),
            name: '基隆貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(25.0143880, 121.4635270),
            name: '新北貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(25.0488250, 121.5186370),
            name: '台北貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(24.7543120, 121.7583780),
            name: '宜蘭貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(24.9539720, 121.2259710),
            name: '桃園貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(24.8022040, 120.9723760),
            name: '新竹貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(24.9011360, 121.0435380),
            name: '新竹市貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(24.5700200, 120.8223430),
            name: '苗栗貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(25.0488250,  121.5186370),
            name: '台中貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(24.0816310, 120.5384440),
            name: '彰化貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(23.9179640, 120.6775050),
            name: '南投貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(23.7117920, 120.5411700),
            name: '雲林貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(23.4545970, 120.4038880),
            name: '嘉義貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(23.4791190, 120.4411380),
            name: '嘉義市貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(22.997234, 120.2119360),
            name: '台南貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(22.6389750, 120.3020070),
            name: '高雄貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(22.6688550, 120.4859690),
            name: '屏東貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(23.9929460, 121.6011140),
            name: '花蓮貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(22.7937110, 121.1231610),
            name: '台東貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(23.5674240, 119.5643650),
            name: '澎湖貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(24.4249670, 118.3190040),
            name: '金門貨運站',
            type: 'DB'
          }, {
            position: new google.maps.LatLng(26.1491610, 119.9384210),
            name: '連江貨運站',
            type: 'DB'
          }, 
        ];

        // Create markers.
        // 將剛剛features內的所有點放入相對應的自訂圖例
        features.forEach(function(feature) {
          var marker = new google.maps.Marker({
            position: feature.position,
            icon: {
                    url: icons[feature.type].icon, 
                    scaledSize: new google.maps.Size(60, 60), // scaled size
                    origin: new google.maps.Point(0,0), // origin
                    anchor: new google.maps.Point(0, 0) // anchor
                  },
            label:{
                    text: feature.name,
                    color: 'black',
                    fontWeight: 'bold',
                    fontFamily: '',
                  },
            // 改圖片的重點在這裡
            // 如果你只有一個自訂圖例要放的話，直接將圖片網址貼在"icon: "後面就能達到效果了
            map: map
          });
          var infowindow = new google.maps.InfoWindow({
          content: "<h2>"+feature.name+"</h2><div><p><b>車號: </b>"+feature.name+"</p><p><b>路線: </b>"+""+"</p><p><b>位置: </b></p></div>"
          });
          marker.addListener('click', function() {
              infowindow.open(map, marker);
          });

        });

        // 這裡在處理MAP右邊的提示小視窗
        var legend = document.getElementById('legend');
        for (var key in icons) {
          var type = icons[key];
          var name = type.name;
          var icon = type.icon;
          var div = document.createElement('div');
          div.innerHTML = '<img src="' + icon + '"><br><br>'+ name +'<br><br>';
          legend.appendChild(div);
        }

        // 透過這個方法可以把剛剛產生的提示小視窗放在地圖的右下角
        map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(legend);
      }


      // 設定中途點
      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        var waypts = [];
        var checkboxArray = document.getElementById('waypoints');
        for (var i = 0; i < checkboxArray.length; i++) {
          if (checkboxArray.options[i].selected) {                  // 若waypoints內的選項有被選到的話
            waypts.push({                                           // waypts則更新一筆資料
              location: checkboxArray[i].value,                     // 輸入位置
              stopover: true                                        // 是否為停靠點(可更改為false觀察差異)
            });
          }
        }

        directionsService.route({
          origin: document.getElementById('start').value,
          destination: document.getElementById('end').value,
          waypoints: waypts,
          optimizeWaypoints: true,
          travelMode: 'DRIVING'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            var summaryPanel = document.getElementById('directions-panel');
            summaryPanel.innerHTML = '';                            // 更新資料前先淨空資料
            // For each route, display summary information.
            var total = 0;
            for (var i = 0; i < route.legs.length; i++) {
              var routeSegment = i + 1;
              summaryPanel.innerHTML += '<b>第' + routeSegment + '段送貨路線</b><br>';
              summaryPanel.innerHTML += 'Form：' + route.legs[i].start_address + '<br>';         // 擷取起點
              summaryPanel.innerHTML += 'To：' + route.legs[i].end_address  + '<br>';           // 擷取終點
              summaryPanel.innerHTML += 'Total：'+route.legs[i].distance.text + '<br>';     // 
              total += route.legs[i].distance.value;     // 擷取起點至終點距離
            }  
            var totalDistance = total/1000 ;     
            document.getElementById('totalDistance').innerHTML = totalDistance;
            var totalPrice = totalDistance*2;
            document.getElementById('totalPrice').innerHTML = totalPrice;
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript">
      $(function () { function moveItems(origin, dest) {
        $(origin).find(':selected').appendTo(dest);
            }
         
        function moveAllItems(origin, dest) {
            $(origin).children().appendTo(dest);
        }
         
        $('#left').click(function () {
        	var x = $("#queryNull").find(":selected");
        	console.log(x.text());
            moveItems('.sbTwo', '.sbOne');
        });
        
        $('#right').on('click', function () {
        	var x = $("#waypoints").find(":selected");
        	console.log(x.text());

//     		$.ajax({
//     			type : "post",
<%-- 				url  : "<%=request.getContextPath()%>/order_main/queryLo.do", --%>
// 				data : {queryLo:queryLo},
// 				datatype: "",
// 				success : function(data){
// 					var str = $.parseJSON(data);
// 					/* $("#queryLo").empty(); */
// 					clearSelect();
// 					for(var i=0;i<str.length;i++){
// 						$("#queryLo").append("<option value='"+ str[i] +"' >"+"車輛行程:"+str[i]+"</option>");
// 					}
// 				}
//     		})
             moveItems('.sbOne', '.sbTwo');
        });
         
        $('#leftall').on('click', function () {
        	var x = $("#queryNull").find(":selected");
        	for(var i = 0; i < x.length; i++){
        		x += x
        	}
        	console.log(x.text());
            moveAllItems('.sbTwo', '.sbOne');
        });
         
        $('#rightall').on('click', function () {
        	var x = $("#waypoints").find(":selected");
        	console.log(x.text());
            moveAllItems('.sbOne', '.sbTwo');
        });
      }); 
    </script>
    <script>
    $(document).ready(function(){ 
    	 $('#start').change(function(data){
    		$('#end').empty();
    		/* var id = $(this).find("option:selected").text(); */
    		var id = "桃園貨運站";
    		$('#end').append($('<option>', {
    		    value: '320台灣桃園市中壢區新興路114號',
    		    text: id
    		}));
    	}); 
    	
    	$('#start').change(function(){
    		var queryLo = $(this).find("option:selected").attr('class');
    		$.ajax({
    			type : "post",
				url  : "<%=request.getContextPath()%>/order_main/queryLo.do",
				data : {queryLo:queryLo},
				datatype: "json",
				success : function(data){
					var str = $.parseJSON(data);
					/* $("#queryLo").empty(); */
					clearSelect();
					for(var i=0;i<str.length;i++){
						$("#queryLo").append("<option value='"+ str[i] +"' >"+"車輛行程:"+str[i]+"</option>");
					}
				}
    		})
    	})   		
    	$('#queryLo').change(function(){
    		var queryOrder = $(this).val();
    		$.ajax({
    			type : "post",
				url  : "<%=request.getContextPath()%>/order_main/queryOrder.do",
				data : {queryOrder:queryOrder},
				datatype: "json",
				success : function(data){
					var str = $.parseJSON(data);
					$("#waypoints").empty();
					for(var i=0;i<str.length;i++){
						$("#waypoints").append("<option value='"+ str[i].RECEIVER_CITY+str[i].RECEIVER_COUNTY+str[i].RECEIVER_CITY+"'selected>"+str[i].ORDER_ID+"</option>");
					}
				}
    		})
    	})
    	$('#queryLo').change(function(){
    		var queryOrder = $(this).val();
    		$.ajax({
    			type : "post",
				url  : "<%=request.getContextPath()%>/order_main/queryNull.do",
				
				datatype: "json",
				success : function(data){
					var str = $.parseJSON(data);
					
					$("#queryNull").empty();
					for(var i=0;i<str.length;i++){
						$("#queryNull").append("<option value='"+ str[i].RECEIVER_CITY+str[i].RECEIVER_COUNTY+str[i].RECEIVER_CITY+"' selected='selected'>"+str[i].ORDER_ID+"</option>");
					}
				},
    		})
    	})
//     	$("#waypoints").change(function(){
//     		var tt = $("#waypoints")
// 	    		console.log(tt);
//     	}) 
    	
    	function getSelectValues(select) {
	   		  var result = [];
	   		  var options = select && select.options;
	   		  var opt;
	
	   		  for (var i=0, iLen=options.length; i<iLen; i++) {
	   		    opt = options[i];
	
	   		    if (opt.selected) {
	   		      result.push(opt.value || opt.text);
	   		    }
	   		  }
	   		  return result;
	   		}
    	 $("#right").click(function(){
    	    var selectedValues = [];    
    	    $("#waypoints:selected").push(function(){
    	        selectedValues.push($(this).text()); 
    	    });
    	   /*  console.log(selectedValues); */
    	    return false;
    	});	 
    	$("#right").click(function(){
    		/* console.log($("#waypoints")); */
			var tt = $("#waypoints:selected").select('text').text();	
   /*  		console.log(tt); */
		}) 
     }) 
    
    function clearSelect(){
		$("#queryLo").empty();
		$("#queryLo").append("<option value='-1' selected='selected'>請選擇行程</option>");
	}
    
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4&callback=initMap"
    async defer></script>
  </body>
</html>
