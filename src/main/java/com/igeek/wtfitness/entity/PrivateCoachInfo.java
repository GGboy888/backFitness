package com.igeek.wtfitness.entity;



import java.sql.Date;

/**
 * @Description: 私教信息实体类
 * @Author: LiuJian
 * @Date: 2020/4/3
 */

public class PrivateCoachInfo  {

  private long pid;

  private Subject subject;
  private Coach coach;

  private Member member;
  private int count;
  private double countprice;
  private double realprice;
  private Date date;
  private int state;
  private String remark;
  private String admin;

  public PrivateCoachInfo() {
  }

  public PrivateCoachInfo(long pid, Subject subject, Coach coach, Member member, int count, double countprice, double realprice, Date date, int state, String remark, String admin) {
    this.pid = pid;
    this.subject = subject;
    this.coach = coach;
    this.member = member;
    this.count = count;
    this.countprice = countprice;
    this.realprice = realprice;
    this.date = date;
    this.state = state;
    this.remark = remark;
    this.admin = admin;
  }

  /**
   * 获取
   * @return pid
   */
  public long getPid() {
    return pid;
  }

  /**
   * 设置
   * @param pid
   */
  public void setPid(long pid) {
    this.pid = pid;
  }

  /**
   * 获取
   * @return subject
   */
  public Subject getSubject() {
    return subject;
  }

  /**
   * 设置
   * @param subject
   */
  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  /**
   * 获取
   * @return coach
   */
  public Coach getCoach() {
    return coach;
  }

  /**
   * 设置
   * @param coach
   */
  public void setCoach(Coach coach) {
    this.coach = coach;
  }

  /**
   * 获取
   * @return member
   */
  public Member getMember() {
    return member;
  }

  /**
   * 设置
   * @param member
   */
  public void setMember(Member member) {
    this.member = member;
  }

  /**
   * 获取
   * @return count
   */
  public int getCount() {
    return count;
  }

  /**
   * 设置
   * @param count
   */
  public void setCount(int count) {
    this.count = count;
  }

  /**
   * 获取
   * @return countprice
   */
  public double getCountprice() {
    return countprice;
  }

  /**
   * 设置
   * @param countprice
   */
  public void setCountprice(double countprice) {
    this.countprice = countprice;
  }

  /**
   * 获取
   * @return realprice
   */
  public double getRealprice() {
    return realprice;
  }

  /**
   * 设置
   * @param realprice
   */
  public void setRealprice(double realprice) {
    this.realprice = realprice;
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
   * @return state
   */
  public int getState() {
    return state;
  }

  /**
   * 设置
   * @param state
   */
  public void setState(int state) {
    this.state = state;
  }

  /**
   * 获取
   * @return remark
   */
  public String getRemark() {
    return remark;
  }

  /**
   * 设置
   * @param remark
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }

  /**
   * 获取
   * @return admin
   */
  public String getAdmin() {
    return admin;
  }

  /**
   * 设置
   * @param admin
   */
  public void setAdmin(String admin) {
    this.admin = admin;
  }

  public String toString() {
    return "PrivateCoachInfo{pid = " + pid + ", subject = " + subject + ", coach = " + coach + ", member = " + member + ", count = " + count + ", countprice = " + countprice + ", realprice = " + realprice + ", date = " + date + ", state = " + state + ", remark = " + remark + ", admin = " + admin + "}";
  }
}
