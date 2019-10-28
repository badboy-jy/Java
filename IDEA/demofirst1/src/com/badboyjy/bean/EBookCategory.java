package com.badboyjy.bean;
//实例化EBookCategory对象类
//两表关系：一对多，多对一，一对一。多对多
public class EBookCategory {
    private Integer id;
    private String name;

    public EBookCategory() {
    }

    public EBookCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EBookCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
