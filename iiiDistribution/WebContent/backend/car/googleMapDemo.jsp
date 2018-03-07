<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Google Map Demo</title>
<style>
/* Always set the map height explicitly to define the size of the div
 * element that contains the map. */
#map {
  height: 100%;
  width: 60%;
  float: left;
}
#tableOutput{
  height: 105%;
  width: 40%;
  float: left;
}
/* Optional: Makes the sample page fill the window. */
html, body {
  font-family: Verdana,sans-serif;
  height: 100%;
  margin: 0;
  padding: 0;
 background-color:#e6ffe6;
}

table, th, td {
    border: 1px solid black;
}

table {  
    width: 640px; 
    border-collapse: collapse; 
    border-spacing: 0;
}
td, th { border: 1px solid #CCC; }

table {  
    color: red;
    font-family: Helvetica, Arial, sans-serif;
    width: 640px; 
    border-collapse: 
    collapse; border-spacing: 0; 
}

td, th {  
    border: 1px solid transparent; /* No more visible border */
    height: 30px; 
    transition: all 0.3s;  /* Simple transition for hover effect */
}

thead {
    font-family: cursive;
	background:#5f2c82;
	color:#dd5;
	font-size:20px;
}

th {
	width:90px;
	font-family:cursive ;
	background:#5f2c82;
	color:#dd5;
	font-size:20px;
}


/* Cells in even rows (2,4,6...) are one color */        
tbody tr:nth-child(even) td { background: #efefc9; font-family: cursive;}   

/* Cells in odd rows (1,3,5...) are another (excludes header cells)  */        
tbody tr:nth-child(odd) td { background: #d6efc9; font-family: cursive;}  

tr td:hover { background: #666; color: black; }  
/* Hover cell effect! */






</style>

</head>
<body>
    <div id="map"></div>
    <div id="tableOutput">
      <FORM METHOD="post" ACTION="emp.do" name="form1">
	      <div>
	        <table border=1>
	          <thead>
	            <tr>
	              <td>Query</td>
	              <td>No.</td>
	              <td>Plate</td>
	              <td>Latitude</td>
	              <td>Longitude</td>
	              <td>Speed</td>
	            </tr>
	          </thead>
	          <tbody id="place">
	          </tbody>
	        </table>
	    </div>      
    </FORM>   
    </div>
    <br><br>
    <div id="introduction" >
      <table>
      <tr>
        <th>No.</th>
        <td id="index1"></td>
      </tr>
      <tr>
        <th>Plate</th>
        <td id="plate1"></td>
      </tr>
      <tr>
        <th>Latitude</th>
        <td id="latitude1"></td>
      </tr>
      <tr>
        <th>Longitude</th>
        <td id="longitude1"></td>
      </tr>
      <tr>
        <th>Latitude</th>
        <td id="speed1"></td>
      </tr>
      <tr>
        <th>Image</th>
        <td id="image1"></td>
      </tr>
    </table>

	  
	</div>
    <div id = "aaa"></div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>
      var map;
      var markers = [];
      var searchResult;
      var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat:24.994779, lng:121.266959},
          zoom: 11
        });
        var marker = new google.maps.Marker({
          position: {lat:26.1491610, lng:119.7784210},
          map: map,
          title: 'Click to zoom'
        });
        
        icons = {
                DB: {
                  name: '貨運公司',
                  icon: '<%=request.getContextPath()%>/backend/img/dbicon.png'
                },
                library: {
                  name: '車輛',
                  icon: '<%=request.getContextPath()%>/backend/img/caricon.png'
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
		          position: new google.maps.LatLng(25.0143880, 121.4635270),
		          name: '新北貨運站',
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

        map.addListener('center_changed', function() {
          deleteMarkers();
        });
      }
      
     $(document).ready(function(){
    	setInterval( haha , 300);
      	haha();
    	function haha(){
	      $.ajax({
	        type:"post",
	        url :"<%=request.getContextPath()%>/JsonFromDbToUrl.do",
	          
	        datatype: "json",
	        success : function(data){
	        	deleteMarkers();
	            var err = null;
	            callback(err, JSON.parse(data));
	            jQuery.each(JSON.parse(data), function(i, item){
	            });
	         },
	      });
       	}
     	

     });
      
      
      
      function callback(err, data) {
        if (err !== null) {
          alert('Something went wrong: ' + err);
        } else {
      searchResult = data;
      var records = data;
          $("#place").empty();
          var index = 1;
          for(var i = 0; i < data.length; i++){
        	<%int i = 1;%>
            var speed = data[i].Speed;
            var routeID = data[i].RouteID;
             
            if(parseFloat(speed)>=10.0){
			  var updatetime = data[i].DataTime;
	          var timer = 24; 
	          var d1 = new Date(); 
	          var d2 = new Date();
	          d1.setHours(+d2.getHours()-(timer/24) ); 
	          d1.setMinutes(new Date().getMinutes());           
// 	          if (new Date(updatetime) > new Date(d1)) { 
	              var busID = data[i].BusID;
	              var busPosition = data[i].ProviderID;
	              var latLng = new google.maps.LatLng(data[i].Latitude, data[i].Longitude);
	              if(map.getBounds().contains(latLng)){
	                addMarker(latLng, data[i], index);
	                $("#place").append("<tr><td><input type='button' value='查詢該車輛'></td><td>"+index+"</td><td>"+busID+"</td><td>"+data[i].Latitude+"</td><td>"+data[i].Longitude+"</td><td>"+speed+"</td></tr>");
	                index++;
// 	          	}
	          }
            }
          }
          $('input').click(function(){
        	    var index = $(this).parent().next();
        		$('#index1').text(index.text());
        		var plate = index.next();
        		$('#plate1').text(plate.text());
        		var latitude = plate.next();
        		$('#latitude1').text(latitude.text());
        		var longitude = latitude.next();
        		$('#longitude1').text(longitude.text());
        		var speed = longitude.next();
        		$('#speed1').text(speed.text());
        		var image = "<%=request.getContextPath()%>/backend/img/car/"+index.text()+".jpg"
        		$("#image1").empty();
        		$('#image1').prepend('<img src='+image+' style="width:400px; ">')
        		console.log("<img src="+image+"/>");
 
        		
        	});
        }
      }
      function addMarker(latLng, result, index) {
   	  var icon = {
	    url: "<%=request.getContextPath()%>/backend/img/caricon.png", 
	    scaledSize: new google.maps.Size(60, 60), 
	    origin: new google.maps.Point(0,0), 
	    anchor: new google.maps.Point(60, 60) 
 	  };
        var marker = new google.maps.Marker({
          position: latLng,
          map: map,
//           label: result.BusID,
          label: {
        	    text: result.BusID,
        	    color: 'black',
        	    fontWeight: 'bold',
        	    fontFamily: '',
       	  },
		  icon: icon,
          title: result.BusID
        });
        markers.push(marker);
        var infowindow = new google.maps.InfoWindow({
          
            content: "<h2>"+result.BusID+"</h2><div><p><b>車號: </b>"+result.BusID+"</p><p><b>路線: </b>"+result.RouteID+"</p><p><b>位置: </b>{"+result.Latitude+","+result.Longitude+"}</p></div>"
        });
        marker.addListener('click', function() {
            infowindow.open(map, marker);
        });
      }
      
      function deleteMarkers() {
      setMapOnAll(null);
        markers = [];
      }
      
      function setMapOnAll(map) {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(map);
        }
      }
      
  	/* for(var index = 1;;index++){
  		console.log("#button"+index+"");
		$("'#button"+index+"'").click(function(){
			alert(123);
		});
		
 	} */

      
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4&callback=initMap"
    async defer></script>
    
    
</body>
</html>