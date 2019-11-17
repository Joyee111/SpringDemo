function getBlog( ele) {
    /*    var htmlobj=$.ajax({url:"/getBlog",async:false});
        $.post("/getBlog","1",function (data) {
            alert(data);

        })*/
    debugger;
    var data = $.ajax({type:"post",
        data:{"data":ele.id},
        dataType:"text",
        url:"/getBlog"
    })
    data.success(function(data){
        window.open(data);
    })
    debugger;

}