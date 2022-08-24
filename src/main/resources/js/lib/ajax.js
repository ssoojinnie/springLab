function Ajax() {
}
Ajax.prototype.loading = function(show){
    if(show) {
        $("#loading-backdrop").show();
        $("#loading").show();
    } else {
        $("#loading-backdrop").hide();
        $("#loading").hide();
    }
};

Ajax.prototype.execute = function(options) {
    var url = options.url;
    //options에 값이 없는 경우 기본값 설정
    if(!options.dataType) {
        options.dataType = "json";
    }
    if(!options.contentType) {
        options.contentType = 'application/x-www-form-urlencoded: charset=UTF-8';
    }
    if(!options.type) {
        options.type = "post;"
    }
    if(!options.data) {
        options.data = {};
    }
    if(options.menu != undefined && options.menu != null) {
        url = common.getMenuUrl({
            code : options.menu.code,
            param : options.menu.params
        });
    }
    var self = this;
    $.ajax({ //jquery ajax 로 감싸면 모듈화할수 있고 성공/에러 코드 처리 가능
        url: url,
        dataType: options.dataType,
        data: options.data,
        contentType: options.contentType,
        async: false,
        type: options.type,
        success: function(response) {
            console.log(response);
            if(response.code == 'SUCCESS'){
                options.success(response);
            }else{
                if(response.code == 'LOGIN_REQUIRED'){
                    location.href = '/login';
                } else{
                    alert(response.message);
                }
            }
            self.loading(false);
        },
        error: function(xhr, status, err){

        }
    });
};