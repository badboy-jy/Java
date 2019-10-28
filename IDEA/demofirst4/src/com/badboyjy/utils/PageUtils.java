package com.badboyjy.utils;

import java.util.List;

public class PageUtils<T> {
    //分页工具类
    private int pageIndex;//页码
    private int pageSize=DataUtils.PAGESIZE;//每页显示条数
    private int totalCount;//总条数
    private int totalPages;//总页数
    private List<T> dataList;//查询的结果数据

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        if (totalCount==0){
            return 1;
        }else {
            return totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize;
        }
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public PageUtils() {
    }

    public PageUtils(int pageIndex, int pageSize, int totalCount, int totalPages, List<T> dataList) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPages=" + totalPages +
                ", dataList=" + dataList +
                '}';
    }
}
