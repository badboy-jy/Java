package com.alibaba.util;
//枚举值
public enum PageEnum {

    PAGESIZE(5);//调用有参构造进行赋值 调用枚举值，语法: 枚举名.枚举值
    private int size;

    public int getSize() {
        return size;
    }

    PageEnum(int size){
        this.size=size;
    }

}
