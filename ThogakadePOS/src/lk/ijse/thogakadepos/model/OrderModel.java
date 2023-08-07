package lk.ijse.thogakadepos.model;

import javafx.scene.control.Alert;
import lk.ijse.thogakadepos.entity.Orders;
import lk.ijse.thogakadepos.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.engine.spi.RowSelection;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.Parameter;
import java.sql.SQLException;

public class OrderModel {
    public static Integer generateNextOrderId() throws SQLException, ClassNotFoundException {
        try {
            Session session = SessionFactoryConfiguration.getInstance().getSession();

            Query query = session.createNativeQuery("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1;");
            query.setResultTransformer(Transformers.aliasToBean(Orders.class));

            Orders singleResult = (Orders) query.getSingleResult();
            int lastNumber = singleResult.getOrderId();

            if (lastNumber > 0) {
                return lastNumber+1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
            return 1;
        }
    }
}
