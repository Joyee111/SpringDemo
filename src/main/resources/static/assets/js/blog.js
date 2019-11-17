function getBlog(){

        $.ajax({
                    url:'/getBlog',
                    type:'POST',
                    dataType:'json',
                    data:'1',
                    success : function(msg){
                    var val = eval(msg)
                    console.log(val);
                    }
                });
    }