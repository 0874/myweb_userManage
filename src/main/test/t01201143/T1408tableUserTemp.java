package t01201143;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import top.what_can_i_say.model.UserTemp;
//import top.what_can_i_say.util.MyHibernateTool;

public class T1408tableUserTemp {
    public static void main(String []arg){

        Session session =
        new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
        session.beginTransaction();
        session.save(new UserTemp("ea00s","dasf",true));
        session.getTransaction().commit();
        session.close();
    }
}
