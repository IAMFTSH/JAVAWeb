package henu.bean;

public class Shop {
    private int shopID;
    private String shopName;
    private String shopManager;

    public Shop() {
    }

    public Shop(int shopID, String shopName, String shopManager) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.shopManager = shopManager;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopManager() {
        return shopManager;
    }

    public void setShopManager(String shopManager) {
        this.shopManager = shopManager;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopID=" + shopID +
                ", shopName='" + shopName + '\'' +
                ", shopManager='" + shopManager + '\'' +
                '}';
    }
}
