package henu.bean;

import java.util.Date;

public class Order {
    private int orderID;
    private String userID;
    private Date orderDate;

    public Order() {
    }

    public Order(int orderID, String userID, Date orderDate) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
    }

    public Order(int orderID, String userID) {
        this.orderID = orderID;
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID='" + userID + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
