package top.what_can_i_say.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import javax.imageio.spi.ServiceRegistry;

public class MyHibernateTool {
    public static final SessionFactory sessionFactory;
    //ThreadLocal可以隔离多个线程的数据共享，因此不需要对线程进行同步
    public static ThreadLocal<Session> session = new ThreadLocal<Session>();
    static
    {
        //使用默认配置文件创建Configuration实例,,版本不一样，创建方式也不同
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }
    public static Session currentSession() {

        Session s = session.get();
        //如果该线程还没有Session，则创建一个新的Session
        if (s == null)
        {
            s = sessionFactory.openSession();
            //将获得的Session变量存储在ThreadLocal变量session里
            session.set(s);
        }
        return s;
    }

    public static void clossSession() {
        Session s = session.get();
        if (s != null)
        {
            s.close();
        }
        session.set(null);
    }
}
