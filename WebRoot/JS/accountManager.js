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
 
     $('#PersonTableContainer').jtable({
       title: '帳號控管',
       sorting: true, 
       defaultSorting: 'Id_num ASC', 
    	actions: {
    	                listAction: '/AC/AccountList',
    	                createAction: '/AC/AccountCreate ',
    	                deleteAction: '/AC/AccountDelete',
    	                updateAction: '/AC/AccountUpdate'
    	                
    	            },
    	fields: {
    		
    	 Idnum: {
    		 		type:'hidden',
    		 		
    	           },
    	 Account: {
    		  		key:true,
    	            title: '戶名',
    	            width: '5%',
    	            list:true,
    	            create:true,
    	            edit:true,
    	          input: function (data) {
    	                if (data.record) {
    	                    return '<b name="Account">'+data.record.Account+'</b>';
    	                } else {
    	                    return '<input type="text" name="Account" value="" />';
    	                }
    	            }
    	          
    	    	},
    	 Password:{
    	            title:'密碼',
    	             
    	            list :false,
    	            type:'password'
    	 },
    	 Admin:{
    		    title:'管理者',
    		    width:'20%',
    		    options: {'2': '一般使用者'}
    	      },
    	  
    	                 
    	                
    	  }
    	      
    	            
    });
    	       
    	$('#PersonTableContainer').jtable('load');
    	 
});