
	$(".update").click(function(){
		$(this).unbind("click");
		var td = $(this).parents("td").prevAll();
	  /* $(this).parents("td").prevAll().eq(5).text(8777); */
	  console.log($(this));
// 	  console.log(td);
// 	  console.log("td.eq(0).attr('class')" + td.eq(0).attr('class'));
// 	  console.log("td.eq(1).attr('class')" + td.eq(1).attr('class'));
// 	  console.log("td.eq(2).attr('class')" + td.eq(2).attr('class'));
// 	  console.log("td.eq(3).attr('class')" + td.eq(3).attr('class'));
// 	  console.log("td.eq(4).attr('class')" + td.eq(4).attr('class'));
// 	  console.log("td.eq(5).attr('class')" + td.eq(5).attr('class'));
// 	  console.log("td.eq(6).attr('class')" + td.eq(6).attr('class'));
// 	  console.log("td.eq(7).attr('class')" + td.eq(7).attr('class'));
// 	  console.log("td.eq(8).attr('class')" + td.eq(8).attr('class'));
// 	  console.log("td.eq(9).attr('class')" + td.eq(9).attr('class'));
// 	  console.log("td.eq(10).attr('class')" + td.eq(10).attr('class'));

	  td.eq(10).wrapInner('<input type="text" name="car_plate" value="'+ td.eq(10).attr('class') + '" style="width:80px">'); 
	   td.eq(9).wrapInner('<input type="text" name="car_status" value="'+ td.eq(9).attr('class') + '" style="width:70px">');
	   td.eq(8).wrapInner('<input type="text" name="db_id" value="'+ td.eq(8).attr('class') + '" style="width:60px">');
	   td.eq(7).wrapInner('<input type="text" name="car_load" value="'+ td.eq(7).attr('class') + '" style="width:70px">');
	   td.eq(6).wrapInner('<input type="text" name="car_brand" value="'+ td.eq(6).attr('class') + '" style="width:70px">');
	   td.eq(5).wrapInner('<input type="text" name="car_color" value="'+ td.eq(5).attr('class') + '" style="width:70px">');
	   td.eq(4).wrapInner('<input type="text" name="car_pdv" value="'+ td.eq(4).attr('class') + '" style="width:70px">');
	   td.eq(3).wrapInner('<input type="text" name="car_driver" value="'+ td.eq(3).attr('class') + '" style="width:70px">');
	   td.eq(2).wrapInner('<input type="text" name="emp_id" value="'+ td.eq(2).attr('class') + '" style="width:70px">');
	   td.eq(1).wrapInner('<input type="text" name="car_updatetime" value="'+ td.eq(1).attr('class') + '" style="width:70px">');
	   td.eq(0).wrapInner('<input type="text" name="car_note" value="'+ td.eq(0).attr('class') + '" style="width:70px">');
	   $(this).prop('class', 'btn btn-primary updateAction').html('確認');
	   $(".updateAction").click(function(){
		     
		   
		   var inputs =	$(this).parents("td").find("input");
 		   var list = $(this).parents("tr").find("td");
			$.each(list,function(i,items){
				$.each(inputs,function(j,input){
					var td = $(items);
					var ins = $(input);
					if(td.children().attr("name")===ins.attr("name"))
					ins.val($(items).children().val());
				});
			});
			

// 			$(this).parents("form").post("car.jsp");
  			$(this).prop('type', 'submit'); 
			
			
// 		   td.eq(10).wrapInner('<input type="text" name="car_plate" value="${carVO.car_plate}" style="width:80px">');
// 		   td.eq(9).wrapInner('<input type="text" name="car_status" value="${param.car_status}" style="width:80px">');
// 		   td.eq(8).wrapInner('<input type="text" name="db_id" value="${param.db_id}" style="width:60px">');
// 		   td.eq(7).wrapInner('<input type="text" name="car_load" value="${param.car_load}" style="width:70px">');
// 		   td.eq(6).wrapInner('<input type="text" name="car_brand" value="${param.car_brand}" style="width:70px">');
// 		   td.eq(5).wrapInner('<input type="text" name="car_color" value="${param.car_color}" style="width:70px">');
// 		   td.eq(4).wrapInner('<input type="text" name="car_pdv" value="${param.car_pdv}" style="width:70px">');
// 		   td.eq(3).wrapInner('<input type="text" name="car_driver" value="${param.car_driver}" style="width:70px">');
// 		   td.eq(2).wrapInner('<input type="text" name="emp_id" value="${param.emp_id}" style="width:70px">');
// 		   td.eq(1).wrapInner('<input type="text" name="car_updatetime" value="${param.car_updatetime}" style="width:70px">');
// 		   td.eq(0).wrapInner('<input type="text" ame="car_note" value="${param.car_note}" style="width:70px">');
	   });
	});