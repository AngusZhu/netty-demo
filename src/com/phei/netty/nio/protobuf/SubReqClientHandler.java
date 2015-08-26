package com.phei.netty.nio.protobuf;

import com.phei.netty.pojo.SubscribeReq;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angus_Zhu on 8/25/2015.
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i <10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReqProto.SubscibeReq subReq(int i) {
        SubscribeReqProto.SubscibeReq.Builder builder =SubscribeReqProto.SubscibeReq.newBuilder();
        builder.setSubReqID(i);
        builder.setUserName("Angus");
        builder.setProductName("你想想");
        List<String> address = new ArrayList<String>();
        address.add("广东");
        address.add("罗湖");
        address.add("tianxin block 5");
        builder.addAllAddress(address);
        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response:"+msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
