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
		 maxDate: dateToday,
		 dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
		   dayNamesMin:["日","一","二","三","四","五","六"],
		   monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
		   monthNamesShort:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
		   prevText:"上月",
		   nextText:"次月",
		   weekHeader:"週",
		 onSelect : function(date) { }
	     ,dateFormat:"yy-mm-dd" });
     
 }	
    $(document).ready(function () {
    	 window.history.forward(1);
         
        $('#PersonTableContainer').jtable({
            title: '費用明細',
            sorting: true, 
            defaultSorting: 'Name ASC', 
            
            actions: {
                listAction: '/AC/ExpendsDataAction',
                createAction: '/AC/CreatExpendsDataAction',
                deleteAction: '/AC/DeleteExpendsDataAction',
                updateAction: '/AC/UpdateExpendsDataAction'
                
            },
            fields: {
                IdNumber: {
                    key: true,
                    list: false
                },
                Name: {
                    title: '費用品項',
                    width: '15%'
                },
                ItemType:{
                   title:'費用種類',
                   width:'18%',
                   options:'/AC/SpendTypeSelectAction'
                },
                Num: {
                    title: '數量',
                    
                    input:function(data1){
                    	if(data1.record){
                    		return '<input type="text" onkeypress="return isNumber(event)" name="Num" style="width:30%" value="'+data1.value+'"  />';
                    	}else{
                    		return '<input type="text" onkeypress="return isNumber(event)" name="Num" style="width:30%" value=""/>';
                    	}
                   	
                   }
                },
                Price: {
                    title: '價格',
                    
                    input:function(data2){
                    	if(data2.record){
                    	return '<input type="text" onkeypress="return isNumber(event)" name="Price" style="width:30%" value="'+data2.value+'" />';
                    	}else{
                    		
                    		return '<input type="text" onkeypress="return isNumber(event)" name="Price" style="width:30%" value=""/>';
                    	}
                    	}
                },
                Time:{
                	title:'建立日期',
                    //width:'18%',
                    //type: 'date',
                    //displayFormat: 'yy-mm-dd',
                	input:function(data3){
                	 var date1=new Date(data3.value).Format("yyyy-MM-dd");
                		 if(data3.value){
                			 return '<input type="text" id="datepicker" name="Time" onMouseOver="return datepickerShow(event)"   value="'+date1+'">';
                			 
                		 }else{
                			 return '<input type="text" id="datepicker" name="Time" onMouseOver="return datepickerShow(event)"   value="">';
                		 }
                		
                	}
               
                    }
                
            },
      
            
        });
       
         $('#PersonTableContainer').jtable('load');
        $('#search').click(function(e){
       	 e.preventDefault();
       	$('#PersonTableContainer').jtable('load', {
       		selectDate: $('#selectDate').val()
            
    });
       });
    });
