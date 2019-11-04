package com.badboyjy.bean;

public class User {
    private int userId;
    private String userName;
    private String userPass;
    private String userSex;
    private int userAge;
    private String userPhone;
    private String userAddress;
    private int userQuanXian;

    public User(int userId, String userName, String userPass, String userSex, int userAge, String userPhone, String userAddress, int userQuanXian) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userSex = userSex;
        this.userAge = userAge;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userQuanXian = userQuanXian;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userQuanXian=" + userQuanXian +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserQuanXian() {
        return userQuanXian;
    }

    public void setUserQuanXian(int userQuanXian) {
        this.userQuanXian = userQuanXian;
    }
}
