package com.phei.netty.nio.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;


/**
 * Created by Angus_Zhu on 8/28/2015.
 */
public class ChineseProverbClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

        String response = msg.content().toString(CharsetUtil.UTF_8);
        if (response.startsWith("the query result:")) {
            System.out.print(response);
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
        ctx.close();
    }
}
