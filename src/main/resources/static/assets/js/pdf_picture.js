
/*three.js start*/
var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );

var renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
renderer.domElement.setAttribute("id","canvas-three");
document.body.appendChild( renderer.domElement );

var geometry = new THREE.BoxGeometry();

var material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
var cube = new THREE.Mesh( geometry, material );
scene.add( cube );

camera.position.z = 5;
function animate() {
    requestAnimationFrame( animate );
    renderer.render( scene, camera );
    cube.rotation.x += 0.05;
    cube.rotation.y += 0.05;
}

var loader = new THREE.FontLoader();

loader.load( 'fonts/helvetiker_regular.typeface.json', function ( font ) {

    var geometry = new THREE.TextGeometry( 'Hello three.js!', {
        font: font,
        size: 80,
        height: 5,
        curveSegments: 12,
        bevelEnabled: true,
        bevelThickness: 10,
        bevelSize: 8,
        bevelOffset: 0,
        bevelSegments: 5
    } );
} );

animate();
function onDocumentKeyPress( event ) {

    var keyCode = event.which;

    // backspace

    if ( keyCode == 8 ) {

        event.preventDefault();

    } else {

        var ch = String.fromCharCode( keyCode );
        text += ch;

        refreshText();

    }

}
//document.addEventListener( 'keypress', onDocumentKeyPress, false );
//document.addEventListener( 'keydown', onDocumentKeyDown, false );
function onDocumentKeyDown( event ) {

    if ( firstLetter ) {

        firstLetter = false;
        text = "";

    }

    var keyCode = event.keyCode;

    // backspace

    if ( keyCode == 8 ) {

        event.preventDefault();

        text = text.substring( 0, text.length - 1 );
        refreshText();

        return false;

    }

}

/*three.js end*/
showPdf();

$("#download").click(function () {
    saveAsLocalImage();
});

function saveAsLocalImage() {
    var myCanvas = document.getElementById("canvas-three");
    var image = myCanvas.toDataURL();
    $.post({
        url: "downloadPic.do",
        data: {'image': image},
        async: true,
        success:function(){
            window.location.href="/123.png";
        }
    });
}
$("#bilibiliSearchBtn").click(function(){
        var keyword = $("#bilibili_text").val();

        $.post({
            url: "searchBilibili.do",
            data: {'keyword': keyword},
            async: true,
            success:function(data){
            $.each(data.data.result,function(index,element){
                var itemTitle = "<div id ='title"+index+"'>【标题】"+element.title+"</div>";
                var itemTag = "<div>【类型】："+element.tag+"</div>";
                var author = "<div>【作者】"+element.author+"</div>";
                var itemIframe = "<iframe src='//player.bilibili.com/player.html?aid="+element.aid+"&bvid="+element.bvid+"&page=1' scrolling='no' border='0' frameborder='no' framespacing='0' allowfullscreen='true'> </iframe>";

                $("#pop").append(itemTitle+itemTag+author+itemIframe);
                $("#title"+index).click(function(){
                    window.open(element.arcurl);
                })
            })
            }
        });
});
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
    img.style.left = (x1- xImg/2) + "px";//
    img.style.top = (y1- yImg/2) + "px";//
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
    $("#pop").append("<img class='real' src='" + $("#seal_name").children('option:selected').val() + "' style='left: " + (seal_x_to_pic - 4*pop_x_to_pic) + "px; top: " + (seal_y_to_pic - pop_y_to_pic) + "px;'>")
});
$("#seal_name").change(function () {
    var selected = $(this).children('option:selected').val();
    $("#img").attr("src", selected);
})
