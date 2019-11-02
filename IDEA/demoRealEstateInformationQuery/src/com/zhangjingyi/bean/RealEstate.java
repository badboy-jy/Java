package com.zhangjingyi.bean;

import java.util.Date;

public class RealEstate {
    private int id;
    private String cardId;
    private String projectName;
    private String houseType;
    private  int area;
    private Date buildTime;
    private String address;
    private User user;

    @Override
    public String toString() {
        return "RealEstate{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", houseType='" + houseType + '\'' +
                ", area=" + area +
                ", buildTime=" + buildTime +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RealEstate(int id, String cardId, String projectName, String houseType, int area, Date buildTime, String address, User user) {
        this.id = id;
        this.cardId = cardId;
        this.projectName = projectName;
        this.houseType = houseType;
        this.area = area;
        this.buildTime = buildTime;
        this.address = address;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RealEstate() {
    }

    public RealEstate(int id, String cardId, String projectName, String houseType, int area, Date buildTime, String address) {
        this.id = id;
        this.cardId = cardId;
        this.projectName = projectName;
        this.houseType = houseType;
        this.area = area;
        this.buildTime = buildTime;
        this.address = address;
    }
}
