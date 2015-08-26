package com.phei.netty.nio.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.phei.netty.pojo.SubscribeReq;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angus_Zhu on 8/26/2015.
 */
public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscibeReq req) {
        return req.toByteArray();
    }

    public static SubscribeReqProto.SubscibeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscibeReq.parseFrom(body);
    }

    public static SubscribeReqProto.SubscibeReq createSubscribeReq(){
        SubscribeReqProto.SubscibeReq.Builder builder =SubscribeReqProto.SubscibeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("Angus");
        builder.setProductName("你想想");
        List<String> address = new ArrayList<String>();
        address.add("广东");
        address.add("罗湖");
        address.add("tianxin block 5");
        builder.addAllAddress(address);
        return builder.build();


    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
          SubscribeReqProto.SubscibeReq req=createSubscribeReq();
        System.out.println("before encode :"+ req.toString());
        SubscribeReqProto.SubscibeReq req2 = decode(encode(req));
        System.out.println("After encode :"+ req.toString());
        System.out.println("Assert equal -->"+req2.toString());
    }
}
