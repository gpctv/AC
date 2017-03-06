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
    				 var addOneMonth=new Date(new Date(dateToday.setMonth(dateToday.getMonth()-1)));
    				 var valMinDate=addOneMonth.Format("yyyy/MM");
    				  sum();
    				 report(valMinDate);
    				 $("#search").click(function(e){
    					   
 						report($("#monthSelect").val());
 						sum();
 					});
    			
    	});     	
    	

    	function report(yyyyMM){
    		var year=yyyyMM.split("/")[0];
    		$.post("/AC/ProfitReportAction",{monthSelect:yyyyMM},function(data1){$('#container').highcharts({
    		        
    				chart: {
    		            type: 'line'
    		        },
    		        title: {
    		            text: '剩餘款項'
    		        },
    		        subtitle: {
    		            text: year
    		        },
    		        xAxis: {
    		        	title: {
    		                text: '月份'
    		            },
    		            categories: data1.Month
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
    		            //valueSuffix: '${point.y}',
    		            pointFormat:'{series.name}:${point.y}'
    		        },
    		        legend: {
    		            layout: 'vertical',
    		            align: 'right',
    		            verticalAlign: 'middle',
    		            borderWidth: 0
    		        },
    		        series: [{
    		            name: '本月餘額',
    		            data: data1.data,
    		            format:"$ {point.y}",
    		            color: '#4169e1'
    		        },{
    		        	name: '本月收入',
    		            data: data1.data2,
    		            format:"$ {point.y}",
    		            color: '#ffa500'
    		        },{
    		        	name: '本月支出',
    		            data: data1.data3,
    		            format:"$ {point.y}",
    		            color: '#ff0000'
    		        }]
    		    });
    			},"json");
    			
    		}
    	function sum(){
    		$.post("/AC/ProfitReportSumAction",function(data){
    			$('#remain').text(data.remain);
    			$('#sumNum').text(data.sum);
    			$('#result').text(data.Result);
    			if($('#result').val<=0){
    				$('#result').css("color", "red");
    			}else{
    				$('#result').css("color", "blue");
    			}
    		});
    			
    		
    	}