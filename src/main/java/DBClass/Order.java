package DBClass;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private int productId;
    private LocalDate orderTime;
    private double orderPrice;

    public Order(int orderId, int productId, LocalDate orderTime, double orderPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDate getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDate orderTime) {
        this.orderTime = orderTime;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
