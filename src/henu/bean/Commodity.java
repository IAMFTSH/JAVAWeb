package henu.bean;

public class Commodity {
    private int commodityID;
    private int shopID;
    private String commodityName;
    private int commodityPrice;
    private int commodityNumber;
    private String commodityIntroduce;
    private String commodityImage;

    public Commodity() {
    }

    public String getCommodityImage() {
        return commodityImage;
    }

    public void setCommodityImage(String commodityImage) {
        this.commodityImage = commodityImage;
    }

    public String getCommodityIntroduce() {
        return commodityIntroduce;
    }

    public void setCommodityIntroduce(String commodityIntroduce) {
        this.commodityIntroduce = commodityIntroduce;
    }

    public Commodity(int commodityID, int shopID, String commodityName, int commodityPrice, int commodityNumber) {
        this.commodityID = commodityID;
        this.shopID = shopID;
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
        this.commodityNumber = commodityNumber;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(int commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public int getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(int commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "commodityID=" + commodityID +
                ", shopID=" + shopID +
                ", commodityName='" + commodityName + '\'' +
                ", commodityPrice=" + commodityPrice +
                ", commodityNumber=" + commodityNumber +
                ", commodityIntroduce=" + commodityIntroduce +
                '}';
    }
}
