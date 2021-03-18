package com.igeek.wtfitness.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description 会员信息实体类
 * @Author wt
 * @Date 2021/2/25 13:55
 */
public class Member {
    private int memberId;
    private String memberName;
    private String memberPassword;
    private String memberPhone;
    private int memberSex;
    private int memberAge;
    private Date birthday;
    private Date nenberDate;
    private int memberTypes;

    private int memberStatic;

    private float memberBalance;

    private Date memberXufei;


    public Member() {
    }

    public Member(int memberId, String memberName, String memberPassword, String memberPhone, int memberSex, int memberAge, Date birthday, Date nenberDate, int memberTypes, int memberStatic, float memberBalance, Date memberXufei) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberSex = memberSex;
        this.memberAge = memberAge;
        this.birthday = birthday;
        this.nenberDate = nenberDate;
        this.memberTypes = memberTypes;
        this.memberStatic = memberStatic;
        this.memberBalance = memberBalance;
        this.memberXufei = memberXufei;
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
     * @return memberName
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 设置
     * @param memberName
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取
     * @return memberPassword
     */
    public String getMemberPassword() {
        return memberPassword;
    }

    /**
     * 设置
     * @param memberPassword
     */
    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    /**
     * 获取
     * @return memberPhone
     */
    public String getMemberPhone() {
        return memberPhone;
    }

    /**
     * 设置
     * @param memberPhone
     */
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    /**
     * 获取
     * @return memberSex
     */
    public int getMemberSex() {
        return memberSex;
    }

    /**
     * 设置
     * @param memberSex
     */
    public void setMemberSex(int memberSex) {
        this.memberSex = memberSex;
    }

    /**
     * 获取
     * @return memberAge
     */
    public int getMemberAge() {
        return memberAge;
    }

    /**
     * 设置
     * @param memberAge
     */
    public void setMemberAge(int memberAge) {
        this.memberAge = memberAge;
    }

    /**
     * 获取
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取
     * @return nenberDate
     */
    public Date getNenberDate() {
        return nenberDate;
    }

    /**
     * 设置
     * @param nenberDate
     */
    public void setNenberDate(Date nenberDate) {
        this.nenberDate = nenberDate;
    }

    /**
     * 获取
     * @return memberTypes
     */
    public int getMemberTypes() {
        return memberTypes;
    }

    /**
     * 设置
     * @param memberTypes
     */
    public void setMemberTypes(int memberTypes) {
        this.memberTypes = memberTypes;
    }

    /**
     * 获取
     * @return memberStatic
     */
    public int getMemberStatic() {
        return memberStatic;
    }

    /**
     * 设置
     * @param memberStatic
     */
    public void setMemberStatic(int memberStatic) {
        this.memberStatic = memberStatic;
    }

    /**
     * 获取
     * @return memberBalance
     */
    public float getMemberBalance() {
        return memberBalance;
    }

    /**
     * 设置
     * @param memberBalance
     */
    public void setMemberBalance(float memberBalance) {
        this.memberBalance = memberBalance;
    }

    /**
     * 获取
     * @return memberXufei
     */
    public Date getMemberXufei() {
        return memberXufei;
    }

    /**
     * 设置
     * @param memberXufei
     */
    public void setMemberXufei(Date memberXufei) {
        this.memberXufei = memberXufei;
    }

    public String toString() {
        return "Member{memberId = " + memberId + ", memberName = " + memberName + ", memberPassword = " + memberPassword + ", memberPhone = " + memberPhone + ", memberSex = " + memberSex + ", memberAge = " + memberAge + ", birthday = " + birthday + ", nenberDate = " + nenberDate + ", memberTypes = " + memberTypes + ", memberStatic = " + memberStatic + ", memberBalance = " + memberBalance + ", memberXufei = " + memberXufei + "}";
    }
}
