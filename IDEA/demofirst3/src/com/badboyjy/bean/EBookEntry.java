package com.badboyjy.bean;

//实例化EBookEntry对象类
import java.util.Date;

public class EBookEntry {
    private Integer id;
    private Integer categoryId;
    private String title;
    private String summary;
    private String uploadUser;
    private Date creatDate;
    private EBookCategory type;

    public EBookCategory getType() {
        return type;
    }

    public void setType(EBookCategory type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public EBookEntry(Integer id, Integer categoryId, String title, String summary, String uploadUser, Date creatDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.summary = summary;
        this.uploadUser = uploadUser;
        this.creatDate = creatDate;
    }

    public EBookEntry() {
    }

    @Override
    public String toString() {
        return "EBookEntry{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", uploadUser='" + uploadUser + '\'' +
                ", creatDate='" + creatDate + '\'' +
                '}';
    }
}
