package top.what_can_i_say.service;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import top.what_can_i_say.dao.UserDao;
import top.what_can_i_say.dao.UserDao201801201300;
import top.what_can_i_say.dao.UserTempDao;
import top.what_can_i_say.model.UserTemp;
import top.what_can_i_say.util.MySingletonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class UserAction201801201157 implements UserService ,ServletRequestAware,ServletResponseAware{
    HttpServletRequest request;
    private HttpServletResponse response;
    private String email;
    private UserDao userDao = MySingletonObject.getUserDao();
    private UserTempDao userTempDao = MySingletonObject.getUserTempDao();


    /**
     * 接收验证链接
     *
     * */

    public String verifyUserRegisterLink(){
        String email = request.getParameter("email");
        String vc = request.getParameter("vc");
        System.out.println(email+"\n"+vc);
        /**
         * 向user_temp查询对象
         * */
        UserTemp userTemp = userTempDao.findUserTempByEmail(email);
        if (userTemp == null){
            //提示，不存在
            return "no-exist";
        }else if (!userTemp.isValid()){
            //提示，无效，已经注册在库
            return "invalid";
        }else if (userTemp.getVerifyCode().equals(vc)){
            //向user添加条目，注册完成,,并提示用户初始密码为123456,请及时修改
            userDao.addOneUserByEmail(email);
            //设置对应项无效
            userTempDao.setInvalidByEmail(email);
            return "register-over";
        }else
            return "error-link";
    }
    /**
     * 接收来自网页的数据【email】
     * 调用dao写入db
     *
     * */
    public String addOneUser() {
        System.out.println(request.getParameter("email"));
        email = request.getParameter("email");
//        向数据库查看是否有存在的
        if (userDao.findUserByEmail(email) != null){
            //存在时，通知用户
            try {
                response.getWriter().print("exist");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{//不存在时，进入注册流程
            System.out.println("邮箱不存在，发送邮件");
            //1.生成验证链接,并发送邮件给用户
            String vcode = userTempDao.produceVerifyAndSendEmail(email);
            //2.从临时注册表中查询是否有存在的email
            if (userTempDao.findUserTempByEmail(email) == null){//如果不存在，就新插入一条
                userTempDao.insertOne(email,vcode);
            }else {//如果存在，就更新其中验证码
                userTempDao.updateVerifyCodeByEmail(email,vcode);
            }
            try {
                response.getWriter().print("send-over");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
        this.response.setContentType("text/html;charset=utf-8");
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserTempDao getUserTempDao() {
        return userTempDao;
    }

    public void setUserTempDao(UserTempDao userTempDao) {
        this.userTempDao = userTempDao;
    }
}
