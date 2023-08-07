package lk.ijse.thogakadepos.model;

import lk.ijse.thogakadepos.entity.Item;
import lk.ijse.thogakadepos.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;

public class ItemModel {

    public static boolean add(Item item) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(item);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public static boolean update(Item item) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(item);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public static boolean delete(Item item) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(item);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    public static ArrayList<Item> getAll() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        Query query = session.createNativeQuery("SELECT * FROM item ORDER BY code;");
        query.setResultTransformer(Transformers.aliasToBean(Item.class));

        ArrayList<Item> itemData = (ArrayList<Item>) query.getResultList();
        return itemData;
    }


}
