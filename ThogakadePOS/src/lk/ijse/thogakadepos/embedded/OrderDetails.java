package lk.ijse.thogakadepos.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetails {

   @Column(name = "qty")
   private int qty;

   @Column(name = "unit_price")
   private double unitPrice;

   @Column(name = "item_code")
   private int itemId;

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

   public int getItemId() {
      return itemId;
   }

   public void setItemId(int itemId) {
      this.itemId = itemId;
   }

   public OrderDetails(int qty, double unitPrice, int itemId) {
      this.qty = qty;
      this.unitPrice = unitPrice;
      this.itemId = itemId;
   }
   public OrderDetails(){}

   @Override
   public String toString() {
      return "OrderDetails{" +
              "qty=" + qty +
              ", unitPrice=" + unitPrice +
              ", itemId=" + itemId +
              '}';
   }
}
