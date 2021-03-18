package com.igeek.wtfitness.entity;

import java.util.Date;

/**
 * @Description TODO
 * @Author wt
 * @Date 2021/3/9 22:14
 */
public class Chongzhi {
    private long id;
    private int memberId;

    private int membertype;
    private long money;
    private long ssmoney;
    private long zlmoney;
    private Date date;
    private long czjine;
    private String beizhu;
    private long czStatic;


    public Chongzhi() {
    }

    public Chongzhi(long id, int memberId, int membertype, long money, long ssmoney, long zlmoney, Date date, long czjine, String beizhu, long czStatic) {
        this.id = id;
        this.memberId = memberId;
        this.membertype = membertype;
        this.money = money;
        this.ssmoney = ssmoney;
        this.zlmoney = zlmoney;
        this.date = date;
        this.czjine = czjine;
        this.beizhu = beizhu;
        this.czStatic = czStatic;
    }

    /**
     * 获取
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return memberId
     */
    public int getMemberId() {
        return memberId;
    }

    /**
     * 设置
     * @param memberId
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取
     * @return membertype
     */
    public int getMembertype() {
        return membertype;
    }

    /**
     * 设置
     * @param membertype
     */
    public void setMembertype(int membertype) {
        this.membertype = membertype;
    }

    /**
     * 获取
     * @return money
     */
    public long getMoney() {
        return money;
    }

    /**
     * 设置
     * @param money
     */
    public void setMoney(long money) {
        this.money = money;
    }

    /**
     * 获取
     * @return ssmoney
     */
    public long getSsmoney() {
        return ssmoney;
    }

    /**
     * 设置
     * @param ssmoney
     */
    public void setSsmoney(long ssmoney) {
        this.ssmoney = ssmoney;
    }

    /**
     * 获取
     * @return zlmoney
     */
    public long getZlmoney() {
        return zlmoney;
    }

    /**
     * 设置
     * @param zlmoney
     */
    public void setZlmoney(long zlmoney) {
        this.zlmoney = zlmoney;
    }

    /**
     * 获取
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取
     * @return czjine
     */
    public long getCzjine() {
        return czjine;
    }

    /**
     * 设置
     * @param czjine
     */
    public void setCzjine(long czjine) {
        this.czjine = czjine;
    }

    /**
     * 获取
     * @return beizhu
     */
    public String getBeizhu() {
        return beizhu;
    }

    /**
     * 设置
     * @param beizhu
     */
    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    /**
     * 获取
     * @return czStatic
     */
    public long getCzStatic() {
        return czStatic;
    }

    /**
     * 设置
     * @param czStatic
     */
    public void setCzStatic(long czStatic) {
        this.czStatic = czStatic;
    }

    public String toString() {
        return "Chongzhi{id = " + id + ", memberId = " + memberId + ", membertype = " + membertype + ", money = " + money + ", ssmoney = " + ssmoney + ", zlmoney = " + zlmoney + ", date = " + date + ", czjine = " + czjine + ", beizhu = " + beizhu + ", czStatic = " + czStatic + "}";
    }
}
