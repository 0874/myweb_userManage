<%--
  Created by IntelliJ IDEA.
  User: zzf
  Date: 18-1-20
  Time: 下午5:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录到waht-can-i-say.top</title>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="/res/js/inputFormat.js" type="text/javascript"></script>
</head>
<body>
    <%--标题提示一下--%>
    <h2>登录，，</h2>
    <%--通过账号，密码--%>
    <form>
        <p id="tips-email">&nbsp;</p>
        <%--需要js正则判断格式，--%>
        <input type="text" value="" placeholder="email" id="email" />
        <p id="tips-password">&nbsp;</p>
        <%--邮箱和密码的提示区，留作格式判断，登录时账号是否存在，密码是否正确--%>
        <input type="password" value="" placeholder="password" id="password" />
        <br />
        <%--只有当格式正确后，才会执行ajax-login--%>
        <input type="button" value="login" id="login" />
    </form>
    <script type="text/javascript">
        $(document).ready(function () {
            console.log('load over')
            listenInputCheckFormat('email',checkEmailFormat,'tips-email')
            listenInputCheckFormat('password',checkPasswordFormat,'tips-password')
//            点击登录
            $('#login').click(function () {
                if (checkEmailFormat($('#email').val()) && checkPasswordFormat($('#password').val())){
                    //格式正确，发给server端,锁定输入
                    $('#email').attr('disable','');
                    $.post(
                        "/user/login_requestLogin",//url
                        {email:$('#email').val(),
                            password:$('#password').val(),
                            go_where:""
                        },//发给server的数据
                        function (data, status) {//应答函数
                            console.log(status+',,'+data)
                            var code = data.trim()
                            if (code == 'no-exist'){
                                $('#tips-email').html('邮箱不存在')
                            }else if (code == 'password-error'){
                                $('#tips-password').html('密码错误')
                            }else {
//                                先进行字符串分割
                                var strs2 = new Array();
                                strs2 = code.split('&');
                                window.location.href = "/user/login_loginSuccess?go_where=" + strs2[0]
                                +'&login_id='+strs2[1]+'&email='+$('#email').val();

                            }
                            $('#email').removeAttr('disabled');
                        }
                    );
                }else
                    alert('电子邮箱或者密码的格式有误，请修改');

            })
        });
    </script>

</body>
</html>
