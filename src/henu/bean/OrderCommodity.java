package henu.bean;

public class OrderCommodity {
    private int orderID;
    private int commodityID;
    private int number;

    public OrderCommodity() {
    }

    public OrderCommodity(int orderID, int commodityID, int number) {
        this.orderID = orderID;
        this.commodityID = commodityID;
        this.number = number;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OrderCommodity{" +
                "commodityID=" + commodityID +
                ", orderID=" + orderID +
                ", number=" + number +
                '}';
    }
}
