package top.what_can_i_say.service;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import top.what_can_i_say.model.User;
import top.what_can_i_say.util.MySingletonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class UserAction01202022ForLogin implements ServletRequestAware,ServletResponseAware{
    private HttpServletRequest request;
    private HttpServletResponse response;
    /**
     *
     * 接收来自客户端的登录请求
     * */
    public String requestLogin() throws IOException {
//        1.接收数据
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String goWhere = request.getParameter("go_where");
        System.out.println("收到:"+email+","+password+"\n"+goWhere);
//        2.查询对象
        User user = MySingletonObject.getUserDao().findUserByEmail(email);
//        3.开始检验
        if (user == null){
//            3.1.说明没有这个email指向的条目
            response.getWriter().print("no-exist");
        }else if (password.equals(user.getPassword())){
//            3.2.账号密码都匹配，说明登录成功[登录成功需要给个标识,存入db]；成功后去哪呢？
            String loginID = MySingletonObject.getUserDao().updateUserLoginID(email);
            //返回消息中以&为分隔符，第一部分为去往地点，第二部分为登录标识
            if (goWhere == null || goWhere.equals(""))//如果没有指向地，就到默认页面
                response.getWriter().print(SUCCESS+"&"+loginID);
            //否则去往，指定页面,,,ajax好像不支持跳转，只能把网址留给前端了
            response.getWriter().print(goWhere+'&'+loginID);
        }else
             response.getWriter().print("password-error");
        return null;
    }

    //在登录成功后，由于ajax不太方便写出url,我的意思是不如写标识来的直观，所以我进行二次请求进行页面跳转
    public String loginSuccess(){
//        由于window.location.href会重置session,所以把登录标识放到session相关是错误的，那就把他放数据库里吧
        String goWhere = request.getParameter("go_where");
        String email = request.getParameter("email");
        String loginID = request.getParameter("login_id");
        if (email == null || loginID == null)
            return "illegal-login";
        //从数据库读取登录成功的标识，以防有人假冒
        if (!loginID.equals(MySingletonObject.getUserDao().findUserLoginIDByEmail(email)))
            return "illegal-login";
        if (goWhere!=null && goWhere.length() > 0)
            return goWhere;
        return SUCCESS;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
        this.response.setContentType("text/html;charset=UTF-8");
    }
}
