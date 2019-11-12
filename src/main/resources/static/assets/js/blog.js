function getBlog(){
/*    $.get("/getBlog",function(msg){
        console.log(msg);
    });*/
        $.ajax({
                    url:'/getBlog',
                    type:'POST',
                    dataType:'json',
                    data:{'id':'1'},
                    success:function(msg){debugger;
                    console.log(msg);
                    },
                    error:function(msg){
                        console.log(msg);
                    }
                });
    }