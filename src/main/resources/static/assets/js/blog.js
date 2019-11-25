function getBlog( ele) {
    /*    var htmlobj=$.ajax({url:"/getBlog",async:false});
        $.post("/getBlog","1",function (data) {
            alert(data);

        })*/

    var data = $.ajax({
        type: "post",
        data: {"data": ele.id},
        dataType: "text",
        url: "/getBlog",
        success :function(data) {
            window.open(data);
        }
});
}
function uploadBlog(){debugger;
    var formData = new FormData();
    formData.append("img", $("#file1")[0].files[0]);
        $.ajax({
            url: "/uploadBlog",
            data: formData,
            type: "post",
            contentType: false,
            processData: false,
            success: function(data) {
                console.log(data)
            },
            error: function() {
                alert("失败")
            }
        });
}