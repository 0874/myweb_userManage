<%--
  Created by IntelliJ IDEA.
  User: zzf
  Date: 18-1-20
  Time: 下午12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户-注册</title>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="/res/js/inputFormat.js" type="text/javascript"></script>
</head>
<body>
<%--标题--%>
<h2>Register one user for my website[www.what-can-i-say.top]</h2>

<%--提示使用者，他填写的邮箱是否有效--%>
<p id="tips-for-email">&nbsp;</p>
<%--通过邮箱注册，电子邮箱--%>
<form>
    <input name="email" placeholder="email" title="email" type="text" id="email" />
    <input type="button" value="submit-to-register" id="submit" disabled />
</form>
<script type="text/javascript">
//    查询使用者输入的email是否已经存在
    function checkEmailIsExistOrNot() {

    }
//    页面加载完成后需要对页面进行事件响应绑定
    $(document).ready(function () {
        console.log("page load over.");
//        需要对邮箱进行正则验证，只有成功了才可以启动按钮
        $('#email').bind('input propertychange',function () {
            console.log('email-input- or this property is changed.::'+$('#email').val());
            $('#tips-for-email').html('&nbsp;')
            var emailPattern = /\w+[@]{1}\w+[.]\w+/
            if (checkEmailFormat($('#email').val())){
                console.log('email -- right')
                $('#submit').show()
                $('#submit').removeAttr('disabled');
            }else{
                console.log('email -- error - format')
                $('#submit').hide()
            }
        });
        $('#submit').click(function () {
            console.log('提交表单')
            $.post(
                "/user/addOneUser",
                {email:$('#email').val()},
                function (data, status) {
                    console.log('server-reply:'+status+','+data)
                    var code = data.trim();
                    if (code == 'exist'){
                        $('#tips-for-email').html("该邮箱已被注册")
                    }else
                        alert('请前往该邮箱完成注册')
                }
            );
        });
    });
</script>
</body>
</html>
