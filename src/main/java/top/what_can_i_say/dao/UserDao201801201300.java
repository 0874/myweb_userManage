package top.what_can_i_say.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import top.what_can_i_say.model.User;
import top.what_can_i_say.util.MyHibernateTool;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserDao201801201300 implements UserDao {




    public boolean addOneUserByEmail(String email) {
        Session session = MyHibernateTool.currentSession();
        session.beginTransaction();
        Integer a= (Integer) session.save(new User(email));
        session.getTransaction().commit();
        if (a != -1)
            return true;
        return false;
    }

    public User findUserByEmail(String email) {
        if (email!=null){
            Session session = MyHibernateTool.currentSession();
            session.beginTransaction();
            Query query = session.createQuery("from User as u where u.email=?");
            query.setParameter(0,email);
            List<User> userList = query.list();
            if (userList.size() >0)
                return userList.get(0);
        }
        return null;
    }

    public String findUserLoginIDByEmail(String email) {

        Session session = MyHibernateTool.currentSession();
        session.beginTransaction();
        Query query = session.createQuery("select u.loginID from User u where u.email=?");
        query.setParameter(0,email);
        List<String> stringList=query.list();
        session.getTransaction().commit();
        if (stringList.size() >0)
            return stringList.get(0);
        return null;
    }

    public String updateUserLoginID(String email) {
        Long time = new Date().getTime();
        String id = time.toString()+ UUID.randomUUID().toString();
        Session session = MyHibernateTool.currentSession();
        session.beginTransaction();
        Query query = session.createQuery("update User u set u.loginID=:login_id where u.email=:email");
        query.setParameter("login_id",id);
        query.setParameter("email",email);
        int  a= query.executeUpdate();
        session.getTransaction().commit();
        return id;
    }
}
