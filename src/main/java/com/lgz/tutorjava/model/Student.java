package com.lgz.tutorjava.model;

import java.util.Date;

/**
 * @author lgz
 * @date 2020/4/7 17:00
 */
public class Student {
    private Integer studentId;
    private String studentName;
    private Integer studentAge;
    private String studentSex;
    private String studentGrade;
    private String studentSubject;
    private String phoneNumber;
    private String expectTutorAble;
    private String expectTutorLocation;
    private String studentHobby;
    private String regTime;
    private String userName;
    private int isDel;
    private String updateTime;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStudentSubject() {
        return studentSubject;
    }

    public void setStudentSubject(String studentSubject) {
        this.studentSubject = studentSubject;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getExpectTutorAble() {
        return expectTutorAble;
    }

    public void setExpectTutorAble(String expectTutorAble) {
        this.expectTutorAble = expectTutorAble;
    }

    public String getExpectTutorLocation() {
        return expectTutorLocation;
    }

    public void setExpectTutorLocation(String expectTutorLocation) {
        this.expectTutorLocation = expectTutorLocation;
    }

    public String getStudentHobby() {
        return studentHobby;
    }

    public void setStudentHobby(String studentHobby) {
        this.studentHobby = studentHobby;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAge=" + studentAge +
                ", studentSex='" + studentSex + '\'' +
                ", studentGrade='" + studentGrade + '\'' +
                ", studentSubject='" + studentSubject + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", expectTutorAble='" + expectTutorAble + '\'' +
                ", expectTutorLocation='" + expectTutorLocation + '\'' +
                ", studentHobby='" + studentHobby + '\'' +
                ", regTime=" + regTime +
                ", userName=" + userName +
                ", isDel=" + isDel +
                '}';
    }
}
