// 前提需要导入jquery
function checkEmailFormat(email) {
    var pattern = /\w+[@]{1}\w+[.]\w+/
    if (pattern.test(email))
        return true;
    return false;
}


function checkPasswordFormat(password) {
    //
    var p1 = /^(\w){6,20}$/
    if (p1.test(password))
        return true;
    return false;
}

// 对相应的输入框进行实时检测
function listenInputCheckFormat(inputID,formatCheckFunction,tipID) {
    $('#'+inputID).bind('input propertychange',function () {
        //格式正确
        if (formatCheckFunction($('#' + inputID).val())) {
            $('#' + tipID).html('format right')
        } else {
            $('#' + tipID).html('format error')
        }
    });
}
