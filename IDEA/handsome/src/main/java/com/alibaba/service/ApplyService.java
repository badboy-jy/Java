package com.alibaba.service;

import com.alibaba.bean.Apply;
import com.alibaba.bean.Houselist;

import java.util.List;

public interface ApplyService {

    public void insertapply(Apply apply);

    public List<Apply> findapplylist() throws Exception;

    Apply findbyhouse_id(String house_id);

    public void deletebyhouse_id(String house_id);

    public void refuseapply(Houselist houselist);
}
