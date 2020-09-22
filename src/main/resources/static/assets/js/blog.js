let uploadFlag = false;

layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function () {
    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , carousel = layui.carousel //轮播
        , upload = layui.upload //上传
        , element = layui.element //元素操作
        , slider = layui.slider //滑块
    //上传

    upload.render({
        elem: '#uploadDemo'
        , url: '/uploadBlog'
        , auto: false //选择文件后不自动上传
        , data: {
            title: function () {
                return $("#title").val();
            }
            , content: function () {
                return $("#content").val();
            }
            , usercode: function(){
                return "admin";
            }
        }
        , bindAction: '#testListAction' //指向一个按钮触发上传
        , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            layer.load(); //上传loading
        }
        , done: function (res) {
            if (res.code == 0) {
                uploadFlag = true;
                layer.closeAll('loading'); //关闭loading
                layer.msg(res.data.src);
                Closepage();
            }
        }
    });
});
function Closepage() {
    if (window.opener && !window.opener.closed) {
        window.parent.opener.location.reload();
    }
    window.close(); return false;
}
function getBlog(ele) {

    var data = $.ajax({
        type: "post",
        data: {"author": "admin"},
        dataType: "text",
        url: "/getBlog",
        success: function (data) {
            var jsonObj = JSON.parse(data);
            for (var i = 0; i < jsonObj.length; i++) {
                if (i > 2) {
                    return;
                }

                $("#blogEle").append("<div class=\"single-porfolio-item grid-size design\">\n" +
                    "\t\t\t\t <div class=\"single-blog-post\">\n" +
                    "<img src=" + jsonObj[i].imgUrl + "  alt=\" portfolio image \" style=\"height:150px\">" +
                    "     \t\t\t\t\t\t\t<div class=\"hover\">\n" +
                    "     \t\t\t\t\t\t\t\t<div class=\"icon\">\n" +
                    "     \t\t\t\t\t\t\t\t\t<a href=" + jsonObj[i].imgUrl + " class=\"image-popup\" title=\"Portfolio image\">\n" +
                    "     \t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-search-plus\" aria-hidden=\"true\"></i>\n" +
                    "     \t\t\t\t\t\t\t\t\t</a>\n" +
                    "     \t\t\t\t\t\t\t\t</div>\n" +
                    "     \t\t\t\t\t\t\t\t<div class=\"icon\">\n" +
                    "     \t\t\t\t\t\t\t\t\t<i class=\"fa fa-link\" aria-hidden=\"true\"></i>\n" +
                    "     \t\t\t\t\t\t\t\t</div>\n" +
                    "     \t\t\t\t\t\t\t</div>" +
                    "\t\t\t\t\t <h4>" + jsonObj[i].title + "</h4>\n" +
                    "\t\t\t\t\t <ul class=\"post-meta\">\n" +
                    "\t\t\t\t\t\t <li><i class=\"fa fa-clock-o\"></i> " + new Date(jsonObj[i].s_atime).toLocaleString() + "</li>\n" +
                    "\t\t\t\t\t\t <li><i class=\"fa fa-user\"></i> By <a href=\"#\">" + jsonObj[i].author + "</a></li>\n" +
                    "\t\t\t\t\t\t <li><i class=\"fa fa-comments\"></i> 27</li>\n" +
                    "\t\t\t\t\t </ul>\n" +
                    "\t\t\t\t\t <p>" + jsonObj[i].description.substring(0,20) + "...</p>\n" +
                    "\t\t\t\t\t <a  href='" + jsonObj[i].imgUrl + "' class=\"boxed-btn\">Read More</a>\n" +
                    "\t\t\t\t </div>\n" +
                    "\t\t\t </div>");
            }
        }
    });
}

function uploadBlog() {
    var formData = new FormData();
    formData.append("img", $("#file1")[0].files[0]);
    formData.append("name", "123");
    $.ajax({
        url: "/uploadBlog",
        data: formData,
        type: "post",
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data)
        },
        error: function () {
            alert("失败")
        }
    });
}