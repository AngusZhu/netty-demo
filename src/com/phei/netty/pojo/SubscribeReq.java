package com.phei.netty.pojo;

import java.io.Serializable;

/**
 * Created by Angus_Zhu on 8/25/2015.
 */
public class SubscribeReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private int subReqID;

    private String username;

    private String productName;

    private String photoNumber;

    private String address;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(String photoNumber) {
        this.photoNumber = photoNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SubscribeReq{" +
                "subReqID=" + subReqID +
                ", username='" + username + '\'' +
                ", productName='" + productName + '\'' +
                ", photoNumber='" + photoNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
