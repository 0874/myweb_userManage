package t01201143;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import top.what_can_i_say.model.User;

public class SeeHibernateIs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello hibernate 3.10");
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		Session session
		=factory.openSession();
		
		//保存一条信息
		session.beginTransaction();
		User user = new User("email501525");
		session.save(user);
		session.getTransaction().commit();
		session.close();
		System.out.println("end");
	}

}
