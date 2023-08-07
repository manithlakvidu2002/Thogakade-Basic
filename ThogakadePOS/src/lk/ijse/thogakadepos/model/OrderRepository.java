package lk.ijse.thogakadepos.model;

import lk.ijse.thogakadepos.entity.Orders;
import lk.ijse.thogakadepos.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderRepository {
    private final Session session;

    public OrderRepository(){
        session = SessionFactoryConfiguration.getInstance().getSession();
    }
    public Orders getOrder(int id){

        try {
            return session.get(Orders.class, id);
        }catch (Exception e){

            session.close();
            e.printStackTrace();
            throw e;
        }
    }
    public int saveOrder(Orders orders){
        Transaction transaction = session.beginTransaction();

        try {
            int id =  (int) session.save(orders);
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return -1;
        }finally {
            session.close();
        }
    }
    public boolean updateOrder(Orders orders){
        Transaction transaction = session.beginTransaction();

        try {
            session.update(orders);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }
    public boolean deleteOrder(Orders orders){
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(orders);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }


    }
}
