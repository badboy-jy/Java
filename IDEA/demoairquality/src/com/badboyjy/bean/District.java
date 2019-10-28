package com.badboyjy.bean;

public class District {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
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

    public District() {
    }

    public District(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
