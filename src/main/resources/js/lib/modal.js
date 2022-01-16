var Modal = function() {
};

/**
* jQuery.ajax 로 options.url에 해당하는 html 내용을 가져온후
* body에 append 해주면 modal 이 보여짐.
*/
Modal.prototype.open = function(options) {
    $.ajax({
        url: options.url,
        dataType: 'html',
        success: function(html){
            var $modal = $(html); //$(html) 처리해서 바로 jquery 로 사용가능
            $('body').append($modal);//body에 $modal 이 출력됨
            $modal.modal('show');
            //닫기 콜백
            $modal.on('hidden.bs.modal', function() { //modal 에 있는 hidden.bs.modal 이라는 이벤트 호출
                $modal.remove();//안하면 html 에 남아있게됨
            });
        }
    });
};