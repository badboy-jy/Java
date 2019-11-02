package com.zhangjingyi.bean;

import java.util.Date;

public class User {
    private String cardId;
    private  String name;
    private  int sex;
    private Date lastModifyTime;
    private  String password;
    private  int status;

    @Override
    public String toString() {
        return "User{" +
                "cardId='" + cardId + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", lastModifyTime=" + lastModifyTime +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User() {
    }

    public User(String cardId, String name, int sex, Date lastModifyTime, String password, int status) {
        this.cardId = cardId;
        this.name = name;
        this.sex = sex;
        this.lastModifyTime = lastModifyTime;
        this.password = password;
        this.status = status;
    }
}
