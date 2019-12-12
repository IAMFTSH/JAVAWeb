package henu.bean;

public class UserInformation {
    private String userID;
    private String userName;
    private int userSex;
    private String userAddress;

    public UserInformation() {
    }

    public UserInformation(String userID, String userName, int userSex, String userAddress) {
        this.userID = userID;
        this.userName = userName;
        this.userSex = userSex;
        this.userAddress = userAddress;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
