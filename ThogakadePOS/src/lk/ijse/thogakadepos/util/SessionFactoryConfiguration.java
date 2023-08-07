package lk.ijse.thogakadepos.util;

import lk.ijse.thogakadepos.entity.Customer;
import lk.ijse.thogakadepos.entity.Item;
import lk.ijse.thogakadepos.entity.Orders;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfiguration {
    private final SessionFactory sessionFactory;
    private static SessionFactoryConfiguration sessionFactoryConfiguration;

    private SessionFactoryConfiguration(){
        sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Orders.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfiguration getInstance(){
        return sessionFactoryConfiguration == null ?
                sessionFactoryConfiguration = new SessionFactoryConfiguration() : sessionFactoryConfiguration;
    }

    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        return session;
    }
}
