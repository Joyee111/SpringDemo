showPdf();
$("#download").click(function () {
    saveAsLocalImage();
});

function saveAsLocalImage() {
    var myCanvas = document.getElementById("the-canvas");
    var image = myCanvas.toDataURL("image/png");
    $.post({
        url: "downloadPic.do",
        data: {'image': image},
        async: true
    });
}

function showPdf() {
    let url = 'zw.pdf'//char2buf(data);
    const loadingTask = pdfjsLib.getDocument(url);
    loadingTask.promise.then(function (pdf) {
        //
        // Fetch the first page
        //
        for (var i = 1; i <= pdf.numPages; i++) {
            renderPage(i, pdf);
        }
    });
}

function renderPage(num, pdf) {
    //
    // Prepare canvas using PDF page dimensions
    //
    let addCanvas = document.getElementById('pop');
    var canvasChild = document.createElement('canvas');
    canvasChild.id = "the-canvas" + num;
    canvasChild.className = "the-canvas";
    addCanvas.append(canvasChild);

    let canvas = document.getElementById('the-canvas' + num);
    pdf.getPage(num).then(function (page) {
        const scale = 1.5;
        let viewport = page.getViewport({scale: scale,});
        viewport.append
        let context = canvas.getContext('2d');
        canvas.height = viewport.height;
        canvas.width = viewport.width;
        //
        // Render PDF page into canvas context
        //
        let renderContext = {
            canvasContext: context,
            viewport: viewport,
        };
        page.render(renderContext);
    });
}

function mouseCoords(event) {
    return {x: event.clientX, y: event.clientY};
}

$("#pop").mousemove(function (event) {
    var img = document.getElementById("img");
    //解决兼容问题
    event = event || window.event;
    var data1 = mouseCoords(event);
    var x1 = data1.x;
    var y1 = data1.y;
    var xImg = img.clientWidth;
    var yImg = img.clientHeight;
    //设置图片坐标
    img.style.left = (x1) + "px";//- xImg/2
    img.style.top = (y1) + "px";// - yImg/2
})
$("#img").click(function (event) {
    var img = document.getElementById("img");
    var pop = document.getElementById("pop");
    var posX = 0, posY = 0;

    if (event.pageX || event.pageY) {
        posX = event.pageX;
        posY = event.pageY;
    } else if (event.clientX || event.clientY) {
        posX = event.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;
        posY = event.clientY + document.documentElement.scrollTop + document.body.scrollTop;
    }
    var seal_x_to_pic = posX;
    var seal_y_to_pic = posY;
    var pop_x_to_pic = pop.offsetLeft;
    var pop_y_to_pic = pop.offsetTop;
    console.log("印章相对pdf的位置x:" + (seal_x_to_pic - pop_x_to_pic) + "  y:" + (seal_y_to_pic - pop_y_to_pic));
    $("#pop").append("<img class='real' src='" + $("#seal_name").children('option:selected').val() + "' style='left: " + (seal_x_to_pic) + "px; top: " + (seal_y_to_pic) + "px;'>")
});
$("#seal_name").change(function () {
    var selected = $(this).children('option:selected').val();
    $("#img").attr("src", selected);
})
