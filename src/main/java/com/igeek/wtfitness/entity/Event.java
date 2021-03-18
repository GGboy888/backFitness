package com.igeek.wtfitness.entity;

import java.util.Date;

/**
 * @Description 赛事实体类
 * @Author wt
 * @Date 2021/2/25 15:01
 */
public class Event {
    private long eventId;
    private String eventName;
    private String eventAddress;
    private Date date;
    private int money;
    private String eventText;


    public Event() {
    }

    public Event(long eventId, String eventName, String eventAddress, Date date, int money, String eventText) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.date = date;
        this.money = money;
        this.eventText = eventText;
    }

    /**
     * 获取
     * @return eventId
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * 设置
     * @param eventId
     */
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    /**
     * 获取
     * @return eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * 设置
     * @param eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * 获取
     * @return eventAddress
     */
    public String getEventAddress() {
        return eventAddress;
    }

    /**
     * 设置
     * @param eventAddress
     */
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
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
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * 设置
     * @param money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * 获取
     * @return eventText
     */
    public String getEventText() {
        return eventText;
    }

    /**
     * 设置
     * @param eventText
     */
    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public String toString() {
        return "Event{eventId = " + eventId + ", eventName = " + eventName + ", eventAddress = " + eventAddress + ", date = " + date + ", money = " + money + ", eventText = " + eventText + "}";
    }
}
