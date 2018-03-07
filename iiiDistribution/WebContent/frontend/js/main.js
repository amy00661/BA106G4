function onclick1(){ 
            var size = document.getElementById("exampleFormControlSelect1").value;
            radios1=document.getElementsByName("temperature");
            for(i=0 ; i<radios1.length;i++){
              if(radios1[i].checked){
                temp=radios1[i].value;
              }
            }
            radios2=document.getElementsByName("kg20");
            for(i=0 ; i<radios2.length;i++){
              if(radios2[i].checked){
                if(radios2[i].value!=0){
                  kg=radios2[i].value;
                }
                else{
                  document.getElementById("result").innerHTML="您的包裹超過20公斤，暫無提供此服務";
                  alert("您的包裹超過20公斤，暫無提供此服務");
                }
              }
            }
            total=size*temp*kg;
            if(total!="Nan")
              // alert("您的包裹運費為:"+total+"元");
              document.getElementById("result").innerHTML="您的包裹運費為:"+total+"元";
           }