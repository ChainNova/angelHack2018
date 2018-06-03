package com.angehack.sharedparking.module.entity;

/**
 * Created by xingle on 2018/6/2.
 */

public class ParkBean extends BaseBean {

    private Integer id;
    private String name;
    private String address;
    private String startTime;
    private String endTime;
    private Float price;
    private boolean payment;
    private Integer status; // 0:free 1:pending  2:lock 3:ending


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ParkBean() {
    }

    public ParkBean(Integer id, String name, String address, String startTime, String endTime, Float price, Integer status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.status = status;
    }

    public ParkBean(Integer id, Integer status,boolean payment) {
        this.id = id;
        this.status = status;
        this.payment = payment;
    }
}
