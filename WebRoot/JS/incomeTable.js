function isNumber(evt) {
    	    evt = (evt) ? evt : window.event;
    	    var charCode = (evt.which) ? evt.which : evt.keyCode;
    	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    	    	 
    	    	return false;
    	    }
    	    return true;
    	};
    	
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
    	        "M+": this.getMonth() + 1, //月份 
    	        "d+": this.getDate(), //日 
    	        "h+": this.getHours(), //小时 
    	        "m+": this.getMinutes(), //分 
    	        "s+": this.getSeconds(), //秒 
    	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
    	        "S": this.getMilliseconds() //毫秒 
  };
    
   if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
     if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
       return fmt;
  } ;  
  
 function datepickerShow(e){
    var dateToday=new Date(); 
    var valMinDate=dateToday.Format("yyyy")+"-"+dateToday.Format("MM")+"-"+"01";
    	$( "#datepicker" ).datepicker({ 
    		minDate:valMinDate,
    		 maxDate: dateToday
    		 ,dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
    		   dayNamesMin:["日","一","二","三","四","五","六"],
    		   monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
    		   monthNamesShort:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
    		   prevText:"上月",
    		   nextText:"次月",
    		   weekHeader:"週"
    		 , onSelect : function(date) { }
    		,dateFormat:"yy-mm-dd" });
    	     
   }	
    	 
 $(document).ready(function () {
    		    
	 window.history.forward(1);
	 var dateToday=new Date();
	 var valMinDate=dateToday.Format("yyyy/MM");
	 getSum(valMinDate);
     $('#PersonTableContainer').jtable({
       title: '收入明細',
       sorting: true, 
       defaultSorting: 'Name ASC', 
    	actions: {
    	                listAction: '/AC/IncomeQueryAction',
    	                createAction: '/AC/IncomeCreateAction',
    	                deleteAction: '/AC/IncomeDeleteAction',
    	                updateAction: '/AC/IncomeUpdateAction'
    	                
    	            },
    	fields: {
    	 Id_num: {
    	            key: true,
    	            list: false
    	           },
    	 Account: {
    	            title: '戶名',
    	            width: '15%',
    	            options:'/AC/IncomeAccountSelectAction'
    	    	},
    	 Amount:{
    	            title:'收入金額',
    	            width:'10%',
    	            input:function(data1){
    	            	if(data1.record){
    	            		return '<input type="text" onkeypress="return isNumber(event)" name="Amount" style="width:30%" value="'+data1.value+'"  />';
    	            	}else{
    	            		return '<input type="text" onkeypress="return isNumber(event)" name="Amount" style="width:30%" value=""/>';
    	            	}
    	            }
    	 },
    	 Income_date:{
    		 	title:'收入日期',
    		 	width:'10%',
    		 	input:function(data3){
               	 var date1=new Date(data3.value).Format("yyyy-MM-dd");
               		 if(data3.value){
               			 return '<input type="text" id="datepicker" name="Income_date" onMouseOver="return datepickerShow(event)"   value="'+date1+'">';
               			 
               		 }else{
               			 return '<input type="text" id="datepicker" name="Income_date" onMouseOver="return datepickerShow(event)"   value="">';
               		 }
               		
               	}
    		 
    	 },
    	 /*valid:{
    		 input:function(d){
    			 return '<script src="https://www.google.com/recaptcha/api.js"></script>'+
    			  '<div class="g-recaptcha" data-sitekey="6LeTKggUAAAAAPJu4-aythecSlEAgqLQOThpTkdT"></div>';
    		 }
    	 }*/
    	                 
    	                
    	  },
    	      
    	            
    });
    	       
    	$('#PersonTableContainer').jtable('load');
    	$('#search').click(function(e){
    	e.preventDefault();
    	$('#PersonTableContainer').jtable('load', {
    	   selectDate: $('#selectDate').val()
    	      
    	});
    	getSum($('#selectDate').val());     
    });
});
 function getSum(date){
	  
	  str=$('#sumNum').text();
	 
	 $.post("/AC/IncomeSumAction",{date:date},function(sum){
		 
		 
		 $('#sumNum').text(sum.sum);
	 });
	 
	 
 }