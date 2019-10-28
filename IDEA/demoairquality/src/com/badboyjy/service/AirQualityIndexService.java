package com.badboyjy.service;

import com.badboyjy.bean.AirQualityIndex;
import com.badboyjy.bean.District;

import java.util.List;

public interface AirQualityIndexService {
   public List<AirQualityIndex> findall(int districtId, int pageindex, int pagesize);

    public int totalcount(int districtId);


  public   int addAirQuality(AirQualityIndex airQualityIndex);

   public AirQualityIndex findbyid(int parseInt);

    public int update(AirQualityIndex airQualityIndex);

    public int delete(int parseInt);
}
