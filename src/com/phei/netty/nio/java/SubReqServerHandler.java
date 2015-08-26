package com.phei.netty.nio.java;

import com.phei.netty.pojo.SubscribeReq;
import com.phei.netty.pojo.SubscribeResp;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Angus_Zhu on 8/25/2015.
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req=(SubscribeReq)msg;
        System.out.println(" server accept client subscriber req ="+ req.toString());
        if ("Angus".equals(req.getUsername())) {
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }

    private SubscribeResp resp(int subReqID) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("Angus your request is succeed,and you are so great!");
        return resp;
    }
}
