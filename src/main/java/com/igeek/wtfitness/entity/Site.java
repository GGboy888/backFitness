package com.igeek.wtfitness.entity;

/**
 * @Description 场地
 * @Author wt
 * @Date 2021/3/18 9:22
 */
public class Site {
    private long adId;
    private String adName;
    private String address;
    private String adText;

    public Site() {
    }

    public Site(long adId, String adName, String address, String adText) {
        this.adId = adId;
        this.adName = adName;
        this.address = address;
        this.adText = adText;
    }

    /**
     * 获取
     * @return adId
     */
    public long getAdId() {
        return adId;
    }

    /**
     * 设置
     * @param adId
     */
    public void setAdId(long adId) {
        this.adId = adId;
    }

    /**
     * 获取
     * @return adName
     */
    public String getAdName() {
        return adName;
    }

    /**
     * 设置
     * @param adName
     */
    public void setAdName(String adName) {
        this.adName = adName;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取
     * @return adText
     */
    public String getAdText() {
        return adText;
    }

    /**
     * 设置
     * @param adText
     */
    public void setAdText(String adText) {
        this.adText = adText;
    }

    public String toString() {
        return "Site{adId = " + adId + ", adName = " + adName + ", address = " + address + ", adText = " + adText + "}";
    }
}
