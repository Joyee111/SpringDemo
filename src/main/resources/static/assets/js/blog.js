

layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
  var laydate = layui.laydate //日期
  ,laypage = layui.laypage //分页
  ,layer = layui.layer //弹层
  ,table = layui.table //表格
  ,carousel = layui.carousel //轮播
  ,upload = layui.upload //上传
  ,element = layui.element //元素操作
  ,slider = layui.slider //滑块
  //上传

upload.render({
  elem: '#uploadDemo'
  ,url: '/uploadBlog'
  ,auto: false //选择文件后不自动上传
  ,data:{
        title: function(){debugger;
        title.value;
        }
        ,content: function(){debugger;
        content.value;
        }
  }
  ,bindAction: '#testListAction' //指向一个按钮触发上传
  ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
     layer.load(); //上传loading
   }
  ,done: function(res){
  if(res.code == 0){
    layer.closeAll('loading'); //关闭loading
    layer.msg(res.data.src);
  }
  }
});
  });

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
    formData.append("name","123");
        $.ajax({
            url: "/uploadBlog",
            data: formData,
            type: "post",
            contentType: false,
            processData: false,
            success: function(data) {
                alert(data)
            },
            error: function() {
                alert("失败")
            }
        });
}