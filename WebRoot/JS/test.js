$( document ).ready(function() {
         setSelect();
           
    });

function setSelect(){
	$.post('/AC/AccountWebServcie',function(key,a){
		var test=key.select;
		var test2=key.account;
		 $.each(test,function(index,value){
			$('#Stext').append($("<option></option>").attr("value", value.Idnum ).text(value.Account));
			
				$("#Stext option[value="+test2+"]").attr("selected","selected");
			
		
		});
	 	 
	});
}