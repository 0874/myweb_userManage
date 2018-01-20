package top.what_can_i_say.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import top.what_can_i_say.model.UserTemp;
import top.what_can_i_say.util.MyEmail;
import top.what_can_i_say.util.MyHibernateTool;

import javax.mail.MessagingException;
//import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

public class UserTempDaoImpl201801201418 implements UserTempDao {


    /**
     * 验证码为一串UUID码
     * */
    public String produceVerifyAndSendEmail(String email) {
        String vc = UUID.randomUUID().toString();//长度36
        //发送邮件，
        String link="localhost:8080/user/verifyUserRegister?email="+email+"&vc="+vc;
        try {
            MyEmail.sendRegisterLinkbyQQ1147057783(email,link);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return vc;
    }

    public UserTemp findUserTempByEmail(String email) {
        Session session = MyHibernateTool.currentSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserTemp as ut where ut.email=?");
        query.setParameter(0,email);
        List<UserTemp> userTempList = query.list();
        session.getTransaction().commit();
        if (userTempList.size() > 0)
            return userTempList.get(0);
        return null;
    }

    public boolean insertOne(String email, String vc) {

        Session session = MyHibernateTool.currentSession();
        session.beginTransaction();
        //返回的是主键值
        Integer a = (Integer) session.save(new UserTemp(email,vc));
        session.getTransaction().commit();
        if (a != -1)
            return true;
        return false;
    }

    public boolean updateVerifyCodeByEmail(String email, String vc) {
        Session session = MyHibernateTool.currentSession();
        session.beginTransaction();
        Query query = session.createQuery("update UserTemp ut set ut.verifyCode=? where ut.email=?");
        query.setParameter(0,vc);
        query.setParameter(1,email);
        int a = query.executeUpdate();
        session.getTransaction().commit();
        if (a == 1)
            return true;
        return false;
    }

    public void setInvalidByEmail(String email) {
        Session session = MyHibernateTool.currentSession();
        session.beginTransaction();
        Query query = session.createQuery("update UserTemp ut set ut.valid=0" +
                " where ut.email='"+email+"'");
        int a = query.executeUpdate();
        session.getTransaction().commit();

    }

}
