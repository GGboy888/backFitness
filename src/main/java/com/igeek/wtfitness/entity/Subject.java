package com.igeek.wtfitness.entity;

/**
 * @Description 课程信息实体类
 * @Author wt
 * @Date 2021/2/25 14:49
 */
public class Subject {
    private long subId;
    private String subName;
    private int subPrice;


    public Subject() {
    }

    public Subject(long subId, String subName, int subPrice) {
        this.subId = subId;
        this.subName = subName;
        this.subPrice = subPrice;
    }

    /**
     * 获取
     * @return subId
     */
    public long getSubId() {
        return subId;
    }

    /**
     * 设置
     * @param subId
     */
    public void setSubId(long subId) {
        this.subId = subId;
    }

    /**
     * 获取
     * @return subName
     */
    public String getSubName() {
        return subName;
    }

    /**
     * 设置
     * @param subName
     */
    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     * 获取
     * @return subPrice
     */
    public int getSubPrice() {
        return subPrice;
    }

    /**
     * 设置
     * @param subPrice
     */
    public void setSubPrice(int subPrice) {
        this.subPrice = subPrice;
    }

    public String toString() {
        return "Subject{subId = " + subId + ", subName = " + subName + ", subPrice = " + subPrice + "}";
    }
}
