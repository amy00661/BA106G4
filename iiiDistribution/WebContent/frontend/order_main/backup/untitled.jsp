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
  height: 100%;
  width: 40%;
  float: left;
}
/* Optional: Makes the sample page fill the window. */
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>

</head>
<body>
    <div id="map"></div>
    <div id="tableOutput">
      <FORM METHOD="post" ACTION="emp.do" name="form1">
        <table border=1>
          <thead>
            <tr>
              <td>No.</td>
              <td>routeID</td>
              <td>Speed</td>
              <td>BusID</td>
              <td>Latitude</td>
              <td>Longtitude</td>
            </tr>
          </thead>
          <tbody id="place">
          </tbody>
        </table>
        <input type="hidden" name="action" value="insert">
      <input type="submit" value="送出新增">
    </FORM>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>
      var map;
      var markers = [];
      var searchResult;
      var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
      function initMap() {
      var DBPosition = [
      {"DBName" : "myLatlng", "latLng": "{lat:24.969, lng:121.192}"},
              {"DBName" : "KeelungDB", "latLng": "{lat:25.1316010, lng:121.7383780}"},
              {"DBName" : "newTaipeiDB", "latLng": "{lat:25.0143880, lng:121.4635270}"},
              {"DBName" : "TaipeiDB", "latLng": "{lat: 25.0488250, lng:121.5186370}"},
              {"DBName" : "YilanDB", "latLng": "{lat:24.7543120, lng:121.7583780}"},
              {"DBName" : "newTaipeiDB", "latLng": "{lat:24.8022040, lng:120.9723760}"},
              {"DBName" : "TaoyuanDB", "latLng": "{lat:24.9539720, lng:121.2259710}"},
              {"DBName" : "HsinchuDB", "latLng": "{lat:24.9011360, lng:121.0435380}"},
              {"DBName" : "HsinchuCityDB", "latLng": "{lat:24.5700200, lng:120.8223430}"},
              {"DBName" : "MiaoliDB", "latLng": "{lat:25.0488250, lng: 121.5186370}"},
              {"DBName" : "TaichungDB", "latLng": "{lat:24.0816310, lng:120.5384440}"},
              {"DBName" : "ChanghuaDB", "latLng": "{lat:23.9179640, lng:120.6775050}"},
              {"DBName" : "YunlinDB", "latLng": "{lat:23.7117920, lng:120.5411700}"},
              {"DBName" : "ChiayiDB", "latLng": "{lat:23.4545970, lng:120.4038880}"},
              {"DBName" : "ChiayiCityDB", "latLng": "{lat:22.997234, lng:120.2119360}"},
              {"DBName" : "TainanDB", "latLng": "{lat:23.4791190, lng:120.4411380}"},
              {"DBName" : "KaohsiungDB", "latLng": "{lat:22.6389750, lng:120.3020070}"},
              {"DBName" : "PingtungDB", "latLng": "{lat:22.6688550, lng:120.4859690}"},
              {"DBName" : "TaitungDB", "latLng": "{lat:22.7937110, lng:121.1231610}"},
              {"DBName" : "HualienDB", "latLng": "{lat:23.9929460, lng:121.6011140}"},
              {"DBName" : "PenghuDB", "latLng": "{lat:23.5674240, lng:119.5643650}"},
              {"DBName" : "KinmenDB", "latLng": "{lat:24.4291400, lng:118.3598540}"},
              {"DBName" : "LienchiangDB", "latLng": "{lat:26.1491610, lng:119.9384210}"}
          ];
    var latLng = '[';
    $.each(DBPosition, function(key, value) {
//          return(DBPosition[key].latLng);
    latLng = latLng + DBPosition[key].latLng + ",";
    
    });
    latLng = latLng.substr(0, latLng.length-1);
    latLng = latLng + "]";
    console.log(latLng);
    latLng1 = latLng.toString();
    
    
//     $.each(JSON.parse(latLng1), function(key, value) {
// //         return(DBPosition[key].latLng);
//    console.log(value);
   
//    });
    
    
//     $.each(DBPosition, function(key, value) {
// console.log(value.latLng);
   
//    });
    
    
        var myLatlng = {lat:24.969, lng:121.192};        
        var KeelungDB = {lat:25.1316010, lng:121.7383780};
        var newTaipeiDB = {lat:25.0143880, lng:121.4635270};
        var TaipeiDB = {lat: 25.0488250, lng:121.5186370};
        var YilanDB = {lat:24.7543120, lng:121.7583780};
        var TaoyuanDB = {lat:24.8022040, lng:120.9723760};
        var HsinchuDB = {lat:24.9539720, lng:121.2259710};
        var HsinchuCityDB = {lat:24.9011360, lng:121.0435380};
        var MiaoliDB = {lat:24.5700200, lng:120.8223430};
        var TaichungDB = {lat:25.0488250, lng: 121.5186370};
        var ChanghuaDB = {lat:24.0816310, lng:120.5384440};
        var NantouDB = {lat:23.9179640, lng:120.6775050};
        var YunlinDB = {lat:23.7117920, lng:120.5411700};
        var ChiayiDB = {lat:23.4545970, lng:120.4038880};
        var ChiayiCityDB = {lat:23.4791190, lng:120.4411380};
        var TainanDB = {lat:22.997234, lng:120.2119360};
        var KaohsiungDB = {lat:22.6389750, lng:120.3020070};
        var PingtungDB = {lat:22.6688550, lng:120.4859690};
        var TaitungDB = {lat:22.7937110, lng:121.1231610};
        var HualienDB = {lat:23.9929460, lng:121.6011140};
        var PenghuDB = {lat:23.5674240, lng:119.5643650};
        var KinmenDB = {lat:24.4291400, lng:118.3598540};
        var LienchiangDB = {lat:26.1491610, lng:119.9384210};

        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat:26.1491610, lng:119.9384210},
          zoom: 8
        });
        var marker = new google.maps.Marker({
          position: {lat:26.1491610, lng:119.9384210},
          map: map,
          title: 'Click to zoom'
        });

        // 擷取及時公車資訊
//         getJSON1('https://data.tycg.gov.tw/api/v1/rest/datastore/bf55b21a-2b7c-4ede-8048-f75420344aed?format=json', callback);
//         getJSON2('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Taoyuan?$format=JSON', callback2);
//         getJSON3('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Tainan?$format=JSON', callback3);
//         getJSON4('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Kaohsiung?$format=JSON', callback4);
//         getJSON5('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Taichung?$format=JSON', callback5);
      
        map.addListener('center_changed', function() {
          deleteMarkers();
//          haha(callback);
//          getJSON1('https://data.tycg.gov.tw/api/v1/rest/datastore/bf55b21a-2b7c-4ede-8048-f75420344aed?format=json', callback);
//          getJSON2('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Taoyuan?$format=JSON', callback2);
//          getJSON3('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Tainan?$format=JSON', callback3);
//          getJSON4('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Kaohsiung?$format=JSON', callback4);
//          getJSON5('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Taichung?$format=JSON', callback5);
        });
      }


      
      $(document).ready(function(){
        setInterval( haha , 50000);
      haha();
    function haha(){
     
        $.ajax({
          type:"post",
          url :"jsonSchedule.do",
          
          datatype: "json",
          success : function(data){
            deleteMarkers();
            var err = null;
            callback(err, JSON.parse(data));
            jQuery.each(JSON.parse(data), function(i, item){
              
//              console.log(item.ProviderID);
//              console.log(item.Speed);
//              console.log(item.BusID);
              
            });
            
            
          },
          error: function(){alert("AJAX-grade發生錯誤囉!")}
        });
       
           
        }
      });
      
//       console.log(JSON.parse(jsonStr));
      
      
      
      

//         var xhr = new XMLHttpRequest();
//         xhr.open('GET', url, true);
//         xhr.responseType = 'json';
//         xhr.onload = function() {
//           var status = xhr.status;
//           if (status === 200) {

//           } else {

//           }
//         };
//         xhr.send();
//       }

      function callback(err, data) {
//        alert(139)
        if (err !== null) {
          alert('Something went wrong: ' + err);
        } else {
      searchResult = data;
      var records = data;
//      console.log(records);
      
          $("#place").empty();
          var index = 1;
          for(var i = 0; i < data.length; i++){
            var speed = data[i].Speed;
            var routeID = data[i].RouteID;
              
            if(parseFloat(speed)>=20.0){
              console.log(data[i].Latitude);
              console.log(data[i].Longitude);
              console.log(data[i].Speed);
              
              var busID = data[i].BusID;
              var busPosition = data[i].ProviderID;
              var latLng = new google.maps.LatLng(data[i].Latitude, data[i].Longitude);
              if(map.getBounds().contains(latLng)){
                addMarker(latLng, data[i]);
                $("#place").append("<tr><td name='index' value='" + index + "'>"+index+"</td><td name='routeID' value='" + routeID + "'>"+routeID+"</td><td name='speed' value='" + speed + "'>"+speed+"</td><td name='busID' value='" + busID + "'>"+busID+"</td><td name='latitude' value='" + data[i].Latitude + "'>"+data[i].Latitude+"</td><td name='longitude' value='" + data[i].Longitude + "'>"+data[i].Longitude+"</td></tr>");
                index++;
              }
            }
          }
        }
      }
      
      
      
      // Adds a marker to the map and push to the array.
      function addMarker(latLng, result) {
      var index = 1;
        var marker = new google.maps.Marker({
          position: latLng,
          map: map,
          icon: iconBase + 'bus_maps.png',
          title: result.BusID
        });
        markers.push(marker);
        var infowindow = new google.maps.InfoWindow({
          
            content: "<h2>"+result.BusID+"</h2><div><p><b>車號: </b>"+result.BusID+"</p><p><b>路線: </b>"+result.RouteID+"</p><p><b>位置: </b>{"+result.Latitude+","+result.Longitude+"}</p></div>"
        });
        marker.addListener('click', function() {
            infowindow.open(map, marker);
        });
        index++;
        
      }
      
      // Deletes all markers in the array by removing references to them.
      function deleteMarkers() {
      setMapOnAll(null);
        markers = [];
      }
      
      // Sets the map on all markers in the array.
      function setMapOnAll(map) {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(map);
        }
      }
      
//       function loadlink(){
//        $('#map', '#tableOutput').load('googleMapDemo.jsp',function () {
//             $(this).unwrap();
//        });
//    }

//    loadlink(); // This will run on page load
//    setInterval(function(){
//        loadlink() // this will run after every 5 seconds
//    }, 5000);
      
//       setTimeout(function(){
//         window.location.reload(1);
//       }, 5000);
   
    

      // window.onload = initMap;  //測試用
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4&callback=initMap"
    async defer></script>
</body>
</html>
