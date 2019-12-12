package henu.bean;

public class ShoppingCar {
    private String userID;
    private String commodityID;

    public ShoppingCar() {
    }

    public ShoppingCar(String userID, String commodityID) {
        this.userID = userID;
        this.commodityID = commodityID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "userID='" + userID + '\'' +
                ", commodityID='" + commodityID + '\'' +
                '}';
    }
}
