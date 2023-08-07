package lk.ijse.thogakadepos.entity;

public class OrderTM {
    private int orderId;
    private int cusId;
    private int itemId;
    private int qty;
    private double unitPrice;

    public OrderTM(){}
    public OrderTM(int orderId, int cusId, int itemId, int qty, double unitPrice) {
        this.orderId = orderId;
        this.cusId = cusId;
        this.itemId = itemId;
        this.qty = qty;
        this.unitPrice = unitPrice;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "orderId=" + orderId +
                ", cusId=" + cusId +
                ", itemId=" + itemId +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
