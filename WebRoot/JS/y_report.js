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
			 var valMinDate=dateToday.Format("yyyy");
			 
			 report(valMinDate);
			 
				$("#search").click(function(){
					
					report($("#yearSelect").val());
				}
						
				);
  
		
});
   

function report(years){
$.post("/AC/YearReportAction",{yearSelect:years},function(data1){$('#container').highcharts({
        
		chart: {
            type: 'line'
        },
        title: {
            text: '年費用報表'
        },
        subtitle: {
            text: years
        },
        xAxis: {
        	title: {
                text: '月份'
            },
            categories: data1.month
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
            name: '本月費用',
            data: data1.data,
            format:"$ {point.y}"
        }]
    });
	});
	
}