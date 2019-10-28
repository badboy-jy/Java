package com.badboyjy.dao;

import com.badboyjy.bean.AirQualityIndex;

import java.util.List;

public interface AirQualityIndexDao {
  public   List<AirQualityIndex> findall(int districtId, int pageindex, int pagesize);

   public int totalcount(int districtId);

  public   int addAirQuality(AirQualityIndex airQualityIndex);

    public AirQualityIndex findbyid(int parseInt);

   public int update(AirQualityIndex airQualityIndex);

   public int delete(int parseInt);
}
