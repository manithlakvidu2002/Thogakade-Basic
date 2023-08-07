package lk.ijse.thogakadepos.entity;
import lk.ijse.thogakadepos.embedded.OrderDetails;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id" ,nullable = false, length = 20)
    private int orderId;

    @Column(name = "customer_id")
    private int cusId;

    @CreationTimestamp
    @Column(name = "order_time")
    private Timestamp createdDateTime;

    @ElementCollection
    @CollectionTable(name = "order_details",
            joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderDetails> orderDetail = new ArrayList<>();

    public Orders(){}

    public Orders(int orderId, int cusId, List<OrderDetails> orderDetail) {
        this.orderId = orderId;
        this.cusId = cusId;
        this.orderDetail = orderDetail;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public List<OrderDetails> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetails> orderDetail) {
        this.orderDetail = orderDetail;
    }

}
