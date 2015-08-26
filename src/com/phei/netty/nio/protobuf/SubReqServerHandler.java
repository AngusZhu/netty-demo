package com.phei.netty.nio.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Angus_Zhu on 8/26/2015.
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscibeReq req=(SubscribeReqProto.SubscibeReq)msg;
        System.out.println(" server accept client subscriber req ="+ req.toString());
        if ("Angus".equals(req.getUserName())) {
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }


    private SubscribeRespProto.SubscibeResp resp(int subReqID) {
        SubscribeRespProto.SubscibeResp.Builder resp = SubscribeRespProto.SubscibeResp.newBuilder();
        resp.setSubReqID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("我收到了：Angus your request is succeed,and you are so great!");
        return resp.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
