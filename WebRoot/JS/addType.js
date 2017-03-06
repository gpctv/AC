
$(document).ready(function() {
	 window.history.forward(1);
addClick();
});

function addClick(){
	var i=1;
	
	$("#add").click(function (e) {
		if(i<5){
		 //Append a new row of code to the "#items" div
		 $("#table").append('<tr id="tableTr'+i+'"><td>物品種類:</td><td class="w3-center"><input type="text" class="w3-input w3-border w3-round-xlarge w3-xlarge" id="itemType" value="" name="itemType"></td></tr>'); 
		 i++;
		}
	});
	$("#dis").click(function(e){
		if(i>1){
			i--;
		$("#tableTr"+i).remove();
		
		}
	});
	

};