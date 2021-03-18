package com.igeek.wtfitness.entity;

/**
 * @Description TODO
 * @Author wt
 * @Date 2021/2/26 14:28
 */
public class MemberType {
    private long typeId;
    private String typeName;
    private long typeDay;

    private float typemoney;


    public MemberType() {
    }

    public MemberType(long typeId, String typeName, long typeDay, float typemoney) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDay = typeDay;
        this.typemoney = typemoney;
    }

    /**
     * 获取
     * @return typeId
     */
    public long getTypeId() {
        return typeId;
    }

    /**
     * 设置
     * @param typeId
     */
    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取
     * @return typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取
     * @return typeDay
     */
    public long getTypeDay() {
        return typeDay;
    }

    /**
     * 设置
     * @param typeDay
     */
    public void setTypeDay(long typeDay) {
        this.typeDay = typeDay;
    }

    /**
     * 获取
     * @return typemoney
     */
    public float getTypemoney() {
        return typemoney;
    }

    /**
     * 设置
     * @param typemoney
     */
    public void setTypemoney(float typemoney) {
        this.typemoney = typemoney;
    }

    public String toString() {
        return "MemberType{typeId = " + typeId + ", typeName = " + typeName + ", typeDay = " + typeDay + ", typemoney = " + typemoney + "}";
    }
}
