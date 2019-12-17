package henu.bean;

public class ShoppingCar {
    private String userID;
    private int commodityID;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ShoppingCar() {
    }

    public ShoppingCar(String userID, int commodityID) {
        this.userID = userID;
        this.commodityID = commodityID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "userID='" + userID + '\'' +
                ", commodityID='" + commodityID + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
