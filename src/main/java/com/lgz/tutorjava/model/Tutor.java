package com.lgz.tutorjava.model;

import java.util.Date;

/**
 * @author lgz
 * @date 2020/4/17 9:50
 * 家教类
 */
public class Tutor {
    private Integer tutorId;
    private String tutorName;
    private Integer tutorAge;
    private String tutorSex;
    private String tutorGoodSubjects;
    private String tutorSchool;
    private String tutorEducation;
    private String tutorLocation;
    private String phoneNumber;
    private String tutorHobby;
    private String userName;
    private String regTime;
    private String updateTime;
    private Integer isDel;

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public Integer getTutorAge() {
        return tutorAge;
    }

    public void setTutorAge(Integer tutorAge) {
        this.tutorAge = tutorAge;
    }

    public String getTutorSex() {
        return tutorSex;
    }

    public void setTutorSex(String tutorSex) {
        this.tutorSex = tutorSex;
    }

    public String getTutorGoodSubjects() {
        return tutorGoodSubjects;
    }

    public void setTutorGoodSubjects(String tutorGoodSubjects) {
        this.tutorGoodSubjects = tutorGoodSubjects;
    }

    public String getTutorSchool() {
        return tutorSchool;
    }

    public void setTutorSchool(String tutorSchool) {
        this.tutorSchool = tutorSchool;
    }

    public String getTutorEducation() {
        return tutorEducation;
    }

    public void setTutorEducation(String tutorEducation) {
        this.tutorEducation = tutorEducation;
    }

    public String getTutorLocation() {
        return tutorLocation;
    }

    public void setTutorLocation(String tutorLocation) {
        this.tutorLocation = tutorLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTutorHobby() {
        return tutorHobby;
    }

    public void setTutorHobby(String tutorHobby) {
        this.tutorHobby = tutorHobby;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "tutorId=" + tutorId +
                ", tutorName='" + tutorName + '\'' +
                ", tutorAge='" + tutorAge + '\'' +
                ", tutorSex='" + tutorSex + '\'' +
                ", tutorGoodSubjects='" + tutorGoodSubjects + '\'' +
                ", tutorSchool='" + tutorSchool + '\'' +
                ", tutorEducation='" + tutorEducation + '\'' +
                ", tutorLocation='" + tutorLocation + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tutorHobby='" + tutorHobby + '\'' +
                ", userName='" + userName + '\'' +
                ", regTime='" + regTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}
