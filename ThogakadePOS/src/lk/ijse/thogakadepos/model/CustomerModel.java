package lk.ijse.thogakadepos.model;

import lk.ijse.thogakadepos.entity.Customer;
import lk.ijse.thogakadepos.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;

public class CustomerModel {

    public static boolean add(Customer customer) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public static boolean update(Customer customer) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public static boolean delete(Customer customer) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    public static ArrayList<Customer> getAll() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        Query query = session.createNativeQuery("SELECT * FROM Customer ORDER BY id;");
        query.setResultTransformer(Transformers.aliasToBean(Customer.class));

        ArrayList<Customer> customersData = (ArrayList<Customer>) query.getResultList();
        return customersData;
    }


    public static ArrayList<Customer> loadCustomerIds() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        Query query = session.createNativeQuery("SELECT id FROM Customer ORDER BY id;");
        query.setResultTransformer(Transformers.aliasToBean(Customer.class));

        ArrayList<Customer> customersData = (ArrayList<Customer>) query.getResultList();
        return customersData;
    }
}
