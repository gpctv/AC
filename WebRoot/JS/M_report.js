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
    	$(document).ready(
    			function(){
    				 window.history.forward(1);
    				 var dateToday=new Date(); 
    				 var valMinDate=dateToday.Format("yyyy/MM");
    				 
    				 report(valMinDate);
    				 table();
    					$("#search").click(function(e){
    						
    						report($("#monthSelect").val());
    						e.preventDefault();
    				       	$('#PersonTableContainer').jtable('load', {
    				       	  selectDate: $('#monthSelect').val()
    				       	            
    				       	});
    					}
    							
    					);
    	  
    			
    	});   
function table() {
        
            
           $('#PersonTableContainer').jtable({
               title: '費用明細',
               sorting: true, 
               defaultSorting: 'Name ASC', 
               
               actions: {
                   listAction: '/AC/ExpendsDataAction'
                  
                   
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
                       
                       
                   },
                   Price: {
                       title: '價格',
                      
                   },
                   Time:{
                   	title:'建立日期',
                       //width:'18%',
                       //type: 'date',
                       //displayFormat: 'yy-mm-dd',
                   
                  
                       }
                   
               },
         
               
           });
          
           $('#PersonTableContainer').jtable('load');
     
           
       };
    	
    	function report(yyyyMM){
    		$.post("/AC/MonthReportAction",{monthSelect:yyyyMM},function(data1){$('#container').highcharts({
    		        
    				chart: {
    		            type: 'line'
    		        },
    		        title: {
    		            text: '月費用報表'
    		        },
    		        subtitle: {
    		            text: yyyyMM
    		        },
    		        xAxis: {
    		        	title: {
    		                text: '日期'
    		            },
    		            categories: data1.Day
    		        },
    		        yAxis: {
    		            title: {
    		                text: '金額$'
    		            }
    		            
    		        },
    		        plotOptions: {
    		            line: {
    		                dataLabels: {
    		                    enabled: true
    		                },
    		                enableMouseTracking: true
    		            }
    		        },
    		        tooltip: {
    		           // valueSuffix: '${point.y}',
    		            pointFormat:'{series.name}:${point.y}'
    		        },
    		        legend: {
    		            layout: 'vertical',
    		            align: 'right',
    		            verticalAlign: 'middle',
    		            borderWidth: 0
    		        },
    		        series: [{
    		            name: '本日費用',
    		            data: data1.data,
    		            format:"$ {point.y}"
    		        }]
    		    });
    			},"json");
    			
    		}