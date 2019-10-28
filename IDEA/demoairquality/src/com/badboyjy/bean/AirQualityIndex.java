package com.badboyjy.bean;

import java.util.Date;

public class AirQualityIndex {
    private Integer id;
    private Integer districtId;
    private Date monitorTime;
    private Integer pm10;
    private Integer pm2_5;
    private String monitoringStation;
    private Date lastMondifyTime;
    private District district;

    @Override
    public String toString() {
        return "AirQualityIndex{" +
                "id=" + id +
                ", districtId=" + districtId +
                ", monitorTime=" + monitorTime +
                ", pm10=" + pm10 +
                ", pm2_5=" + pm2_5 +
                ", monitoringStation='" + monitoringStation + '\'' +
                ", lastMondifyTime=" + lastMondifyTime +
                ", district=" + district +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public Integer getPm10() {
        return pm10;
    }

    public void setPm10(Integer pm10) {
        this.pm10 = pm10;
    }

    public Integer getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(Integer pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public String getMonitoringStation() {
        return monitoringStation;
    }

    public void setMonitoringStation(String monitoringStation) {
        this.monitoringStation = monitoringStation;
    }

    public Date getLastMondifyTime() {
        return lastMondifyTime;
    }

    public void setLastMondifyTime(Date lastMondifyTime) {
        this.lastMondifyTime = lastMondifyTime;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public AirQualityIndex() {
    }

    public AirQualityIndex(Integer id, Integer districtId, Date monitorTime, Integer pm10, Integer pm2_5, String monitoringStation, Date lastMondifyTime) {
        this.id = id;
        this.districtId = districtId;
        this.monitorTime = monitorTime;
        this.pm10 = pm10;
        this.pm2_5 = pm2_5;
        this.monitoringStation = monitoringStation;
        this.lastMondifyTime = lastMondifyTime;
    }


}