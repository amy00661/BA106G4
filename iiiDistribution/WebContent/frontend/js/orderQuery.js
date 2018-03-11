$(function () {
    flip();
});
function flip() {
    var timer = null;
    var i = 0;
var j = 0;
    var aFlip = $(".card");
    function flipFn(arg1, arg2) {
  aFlip.eq(i).toggleClass('card-flipped'); 
  i++;
  if(i==5){
    i=0;
  }
 
    }
    timer = setInterval(flipFn, 2000);
}    