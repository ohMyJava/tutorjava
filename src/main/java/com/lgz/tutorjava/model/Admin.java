package com.lgz.tutorjava.model;

import java.io.Serializable;

/**
 * @author lgz
 * @date 2020/3/30 21:47
 * 管理员类，继承了User类
 */
public class Admin implements Serializable {
    private Integer adminId;
    private String adminName;
    private String adminPassword;
    private Integer power;
    private String adminInformation;
    private String regTime;
    private Integer isDel;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getAdminInformation() {
        return adminInformation;
    }

    public void setAdminInformation(String adminInformation) {
        this.adminInformation = adminInformation;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", power=" + power +
                ", adminInformation='" + adminInformation + '\'' +
                ", regTime='" + regTime + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}
