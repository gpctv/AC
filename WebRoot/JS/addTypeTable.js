 $(document).ready(function () {
    	
        $('#PersonTableContainer').jtable({
            title: '費用種類',
            actions: {
                listAction: '/AC/SpendTypeDataAction',
                updateAction:'/AC/UpdateSpendTypeAction',
                deleteAction:'/AC/DeleteSpendTypeAction'
            },
            fields: {
            	IdNumber: {
                    key: true,
                    list: false
                },
                Name: {
                    title: '費用種類',
                    width: '40%'
                }
            },
      
            
        });
        $('#PersonTableContainer').jtable('load');
    });