package com.phei.netty.pojo;

import java.io.Serializable;
import java.rmi.server.ServerRef;

/**
 * Created by Angus_Zhu on 8/25/2015.
 */
public class SubscribeResp implements Serializable{

    private static final long serialVersionUID = 1L;

    private String desc;

    private int respCode;

    private int subReqID;

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getRespCode() {
        return respCode;
    }

    public int getSubReqID() {
        return subReqID;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "desc='" + desc + '\'' +
                ", respCode=" + respCode +
                ", subReqID=" + subReqID +
                '}';
    }
}
