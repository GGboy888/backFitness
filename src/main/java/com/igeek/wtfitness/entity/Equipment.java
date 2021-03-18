package com.igeek.wtfitness.entity;

/**
 * @Description 设备信息实体类
 * @Author wt
 * @Date 2021/2/25 13:51
 */
public class Equipment {
    private long eqId;
    private String eqName;
    private String eqText;


    public Equipment() {
    }

    public Equipment(long eqId, String eqName, String eqText) {
        this.eqId = eqId;
        this.eqName = eqName;
        this.eqText = eqText;
    }

    /**
     * 获取
     * @return eqId
     */
    public long getEqId() {
        return eqId;
    }

    /**
     * 设置
     * @param eqId
     */
    public void setEqId(long eqId) {
        this.eqId = eqId;
    }

    /**
     * 获取
     * @return eqName
     */
    public String getEqName() {
        return eqName;
    }

    /**
     * 设置
     * @param eqName
     */
    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    /**
     * 获取
     * @return eqText
     */
    public String getEqText() {
        return eqText;
    }

    /**
     * 设置
     * @param eqText
     */
    public void setEqText(String eqText) {
        this.eqText = eqText;
    }

    public String toString() {
        return "Equipment{eqId = " + eqId + ", eqName = " + eqName + ", eqText = " + eqText + "}";
    }
}
