package com.igeek.wtfitness.entity;


import java.util.Date;

/**
 * @Description: 教练信息实体类
 * @Author: wt
 * @Date: 2021/3/3
 */
public class Coach  {

  private long coachId;
  private String coachName;
  private String coachPhone;
  private long coachSex;
  private long coachAge;
  private Date coachData;
  private long teach;
  private double coachWages;
  private long coachStatic;
  private String coachAddress;

  public Coach() {
  }

  public Coach(long coachId, String coachName, String coachPhone, long coachSex, long coachAge, Date coachData, long teach, double coachWages, long coachStatic, String coachAddress) {
    this.coachId = coachId;
    this.coachName = coachName;
    this.coachPhone = coachPhone;
    this.coachSex = coachSex;
    this.coachAge = coachAge;
    this.coachData = coachData;
    this.teach = teach;
    this.coachWages = coachWages;
    this.coachStatic = coachStatic;
    this.coachAddress = coachAddress;
  }

  /**
   * 获取
   * @return coachId
   */
  public long getCoachId() {
    return coachId;
  }

  /**
   * 设置
   * @param coachId
   */
  public void setCoachId(long coachId) {
    this.coachId = coachId;
  }

  /**
   * 获取
   * @return coachName
   */
  public String getCoachName() {
    return coachName;
  }

  /**
   * 设置
   * @param coachName
   */
  public void setCoachName(String coachName) {
    this.coachName = coachName;
  }

  /**
   * 获取
   * @return coachPhone
   */
  public String getCoachPhone() {
    return coachPhone;
  }

  /**
   * 设置
   * @param coachPhone
   */
  public void setCoachPhone(String coachPhone) {
    this.coachPhone = coachPhone;
  }

  /**
   * 获取
   * @return coachSex
   */
  public long getCoachSex() {
    return coachSex;
  }

  /**
   * 设置
   * @param coachSex
   */
  public void setCoachSex(long coachSex) {
    this.coachSex = coachSex;
  }

  /**
   * 获取
   * @return coachAge
   */
  public long getCoachAge() {
    return coachAge;
  }

  /**
   * 设置
   * @param coachAge
   */
  public void setCoachAge(long coachAge) {
    this.coachAge = coachAge;
  }

  /**
   * 获取
   * @return coachData
   */
  public Date getCoachData() {
    return coachData;
  }

  /**
   * 设置
   * @param coachData
   */
  public void setCoachData(Date coachData) {
    this.coachData = coachData;
  }

  /**
   * 获取
   * @return teach
   */
  public long getTeach() {
    return teach;
  }

  /**
   * 设置
   * @param teach
   */
  public void setTeach(long teach) {
    this.teach = teach;
  }

  /**
   * 获取
   * @return coachWages
   */
  public double getCoachWages() {
    return coachWages;
  }

  /**
   * 设置
   * @param coachWages
   */
  public void setCoachWages(double coachWages) {
    this.coachWages = coachWages;
  }

  /**
   * 获取
   * @return coachStatic
   */
  public long getCoachStatic() {
    return coachStatic;
  }

  /**
   * 设置
   * @param coachStatic
   */
  public void setCoachStatic(long coachStatic) {
    this.coachStatic = coachStatic;
  }

  /**
   * 获取
   * @return coachAddress
   */
  public String getCoachAddress() {
    return coachAddress;
  }

  /**
   * 设置
   * @param coachAddress
   */
  public void setCoachAddress(String coachAddress) {
    this.coachAddress = coachAddress;
  }

  public String toString() {
    return "Coach{coachId = " + coachId + ", coachName = " + coachName + ", coachPhone = " + coachPhone + ", coachSex = " + coachSex + ", coachAge = " + coachAge + ", coachData = " + coachData + ", teach = " + teach + ", coachWages = " + coachWages + ", coachStatic = " + coachStatic + ", coachAddress = " + coachAddress + "}";
  }
}
