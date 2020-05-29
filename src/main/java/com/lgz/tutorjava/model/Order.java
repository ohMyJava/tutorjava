package com.lgz.tutorjava.model;

/**
 * @author lgz
 * @date 2020/5/29 14:10
 * 订单信息表
 */
public class Order {
    private Integer orderId;
    /**
     * 订单编号，格式为：A（ABCDE小学、初中、高中、大学、其他）20200529（日期）141546（时间）
     */
    private String serial;
    private String time;
    private Integer isDel;
    private Integer studentUserId;
    private Integer studentId;
    private Integer tutorUserId;
    private Integer tutorId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(Integer studentUserId) {
        this.studentUserId = studentUserId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTutorUserId() {
        return tutorUserId;
    }

    public void setTutorUserId(Integer tutorUserId) {
        this.tutorUserId = tutorUserId;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", serial='" + serial + '\'' +
                ", time='" + time + '\'' +
                ", isDel=" + isDel +
                ", studentUserId=" + studentUserId +
                ", studentId=" + studentId +
                ", tutorUserId=" + tutorUserId +
                ", tutorId=" + tutorId +
                '}';
    }
}
